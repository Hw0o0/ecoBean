package com.coffeeSale.coffeeSaleEcoBean.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResDto {
    private HttpStatus httpStatus;
    private String message;

}
