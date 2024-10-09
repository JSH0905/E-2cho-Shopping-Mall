package org.e2cho.e2cho_shopping_mall.expection;

import lombok.Getter;
import org.e2cho.e2cho_shopping_mall.constant.ErrorType;

@Getter
public class CustomErrorException extends RuntimeException{

    private final ErrorType errorType;

    public CustomErrorException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
