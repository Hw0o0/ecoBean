package com.coffeeSale.coffeeSaleEcoBean.orders.service;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.Menu;
import com.coffeeSale.coffeeSaleEcoBean.menu.repository.MenuRepository;
import com.coffeeSale.coffeeSaleEcoBean.orders.domain.OrderDetails;
import com.coffeeSale.coffeeSaleEcoBean.orders.domain.Orders;
import com.coffeeSale.coffeeSaleEcoBean.orders.dto.OrdersDto;
import com.coffeeSale.coffeeSaleEcoBean.orders.repository.OrderDetailsRepository;
import com.coffeeSale.coffeeSaleEcoBean.orders.repository.OrderRepository;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain.ReusableCup;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.repository.ReusableCupRepository;
import com.coffeeSale.coffeeSaleEcoBean.shop.domain.Shop;
import com.coffeeSale.coffeeSaleEcoBean.shop.repository.ShopRepository;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.*;
import com.coffeeSale.coffeeSaleEcoBean.user.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ReusableCupRepository reusableCupRepository;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final MenuRepository menuRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemsRepository shoppingCartItemsRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final PointDetailsRepository pointDetailsRepository;


    //일반 주문할 경우
    @Transactional
    public Orders orderMenus(Long userId, Long shopId, Long reusableCupId, Long couponId, OrdersDto ordersDto){
        User user = userRepository.findById(userId).orElseThrow();
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        ReusableCup reusableCup = reusableCupRepository.findById(reusableCupId).orElseThrow();
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        ShoppingCart shoppingCart = shoppingCartRepository.findById(1L).orElseThrow();
        Orders orders;
        int integratedPrice = shoppingCart.getTotalPrice() - ordersDto.getUsedPoint();
        inputShoppingCartItemInfo(shoppingCart);
        switch (coupon.getDiscountDetails()) {
            case "아이스 아메리카노 1잔 쿠폰" -> {
                coupon.setLimitAmount(2000); // 할인 가격 설정
                orders = createOrder(coupon,user,shop,reusableCup, shoppingCart,integratedPrice, ordersDto);
            }
            case "아이스 아메리카노 1+1  쿠폰" -> {
                coupon.setLimitAmount(4000); // 할인 가격 설정
                orders = createOrder(coupon,user,shop,reusableCup, shoppingCart,integratedPrice, ordersDto);
            }
            case "라떼 무료 쿠폰" -> {
                coupon.setLimitAmount(5000); // 할인 가격 설정
                orders = createOrder(coupon,user,shop,reusableCup, shoppingCart,integratedPrice, ordersDto);
            }
            case "주문 10% 할인권" -> {
                coupon.setLimitAmount(shoppingCart.getTotalAmount()  /10); // 할인 가격 설정
                orders = createOrder(coupon,user,shop,reusableCup, shoppingCart,integratedPrice, ordersDto);
            }
            default -> {
                orders = Orders.createOrder(user,shop,reusableCup,coupon,ordersDto);
                orders.setDeductedAmount(0);
                orders.setTotalPrice(shoppingCart.getTotalPrice());
                orders.setIntegratedPrice(integratedPrice);
                orderRepository.save(orders);
            }
        }
        addDetails(user,orders,shoppingCart); //주문으로 인한 기록들을 남기기위한 메서드
        return orders;
    }
    //주문으로 인한 기록들을 남기기위한 메서드
    public void addDetails(User user,Orders orders,ShoppingCart shoppingCart){
        user.setReusableUsed(user.getReusableUsed()+1);
        userRepository.save(user);
        addPointDetails(orders);   //PointDetails 사용 기록을 남기기위한 메서드
        minusPointDetails(orders);
        addOrderDetails(orders,shoppingCart);   //OrderDetails 기록을 남기기위한 메서드
    }
    //일반 주문 만들기 위한 메서드
    public Orders createOrder(Coupon coupon, User user, Shop shop, ReusableCup reusableCup, ShoppingCart shoppingCart,Integer integratedPrice, OrdersDto ordersDto){

        coupon.setState(false);
        coupon.setUsedDate(new Date()); // 쿠폰 사용 일자 설정
        couponRepository.save(coupon);

        Orders orders = Orders.createOrder(user,shop,reusableCup,coupon,ordersDto);
        orders.setDeductedAmount(coupon.getLimitAmount());
        orders.setTotalPrice(shoppingCart.getTotalPrice());
        orders.setIntegratedPrice(integratedPrice-coupon.getLimitAmount());
        return orderRepository.save(orders);
    }

    //리유저블 컵에 등록된 메뉴로 구매할 경우
    @Transactional
    public Orders reusableCupMenuOrder(Long userId, Long shopId, Long reusableCupId, Long couponId, OrdersDto ordersDto){
        User user = userRepository.findById(userId).orElseThrow();
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        ReusableCup reusableCup = reusableCupRepository.findById(reusableCupId).orElseThrow();
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        Menu menu = menuRepository.findById(reusableCup.getMenu().getId()).orElseThrow();
        return finishedOrder(coupon,user,shop,reusableCup,menu,ordersDto);
    }
    //리유저블 컵 메뉴 구매로 인한 주문을 마무리하는 메서드
    public Orders finishedOrder(Coupon coupon, User user, Shop shop, ReusableCup reusableCup, Menu menu, OrdersDto ordersDto){
        Orders orders = new Orders();
        switch (coupon.getDiscountDetails()) {
            case "아이스 아메리카노 1잔 쿠폰" -> {
                coupon.setLimitAmount(2000); // 할인 가격 설정
                orders = createReusableMenuOrder(coupon,user,shop,reusableCup,menu,ordersDto);
            }
            case "아이스 아메리카노 1+1  쿠폰" -> {
                coupon.setLimitAmount(4000);
                orders = createReusableMenuOrder(coupon,user,shop,reusableCup, menu, ordersDto);
            }
            case "라떼 무료 쿠폰" -> {
                coupon.setLimitAmount(5000);
                orders = createReusableMenuOrder(coupon,user,shop,reusableCup, menu, ordersDto);
            }
            case "주문 10% 할인권" -> {
                coupon.setLimitAmount(menu.getPrice() /10);
                orders = createReusableMenuOrder(coupon,user,shop,reusableCup, menu, ordersDto);
            }
            default -> {
                orders = Orders.createOrder(user,shop,reusableCup,coupon,ordersDto);
                orders.setDeductedAmount(0);
                orders.setTotalPrice(menu.getPrice());
                orders.setIntegratedPrice(menu.getPrice());
                orderRepository.save(orders);
            }
        }
        user.setReusableUsed(user.getReusableUsed()+1);
        userRepository.save(user);
        return orders;
    }
    //리유저블 등록 메뉴로 인한 메뉴 주문 만들기 메서드
    public Orders createReusableMenuOrder(Coupon coupon, User user, Shop shop, ReusableCup reusableCup, Menu menu, OrdersDto ordersDto){
        int integratedPrice = menu.getPrice() - ordersDto.getUsedPoint() - coupon.getLimitAmount();

        coupon.setState(false);
        coupon.setUsedDate(new Date()); // 쿠폰 사용 일자 설정
        couponRepository.save(coupon);

        Orders order = Orders.createOrder(user,shop,reusableCup,coupon,ordersDto);
        order.setDeductedAmount(coupon.getLimitAmount());
        order.setTotalPrice(menu.getPrice());
        order.setIntegratedPrice(integratedPrice);
        return orderRepository.save(order);
    }
    //ShoppingCart 총가격 및 총수량 입력
    public void inputShoppingCartItemInfo(ShoppingCart shoppingCart) {
        int totalPrice = 0, totalAmount = 0;
        List<ShoppingCartItems> shoppingCartItemsList = shoppingCartItemsRepository.findAll();
        for (ShoppingCartItems shoppingCartItems : shoppingCartItemsList) {
            shoppingCartItems.setShoppingCart(shoppingCart);
            totalPrice += shoppingCartItems.getMenu().getPrice()*shoppingCartItems.getAmount();
            totalAmount += 1;
        }
        shoppingCartItemsRepository.saveAll(shoppingCartItemsList);
        shoppingCart.setTotalAmount(totalAmount);
        shoppingCart.setTotalPrice(totalPrice);
        shoppingCartRepository.save(shoppingCart);
    }

    //OrderDetails에 내역 남기기 위한 메서드
    public void addOrderDetails(Orders orders,ShoppingCart shoppingCart){
        for(ShoppingCartItems shoppingCartItems : shoppingCartItemsRepository.findAll()){
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrders(orders);
                orderDetails.setMenu(shoppingCartItems.getMenu());
                orderDetails.setAmount(shoppingCart.getTotalAmount());
                orderDetails.setUsedReusable(shoppingCartItems.isUsedReusable());
                orderDetails.setTemperatureControl(shoppingCartItems.isTemperatureControl());
                orderDetailsRepository.save(orderDetails);
        }
    }
    //PointDetails에 내역 남기기 위한 메서드
    public void addPointDetails(Orders orders){
        PointDetails pointDetails = new PointDetails();
        pointDetails.setOrders(orders);
        pointDetails.setUser(orders.getUser());
        pointDetails.setContent("일반 주문으로 인한 포인트 적립");
        pointDetails.setPoint(orders.getIntegratedPrice()/100*5);
        pointDetailsRepository.save(pointDetails);
    }
    //PointDetails에 내역 남기기 위한 메서드
    public void minusPointDetails(Orders orders){
        PointDetails pointDetails = new PointDetails();
        pointDetails.setOrders(orders);
        pointDetails.setUser(orders.getUser());
        pointDetails.setContent("일반 주문에 사용된 사용 포인트");
        pointDetails.setPoint(-orders.getUsedPoint());
        pointDetailsRepository.save(pointDetails);
    }
}
