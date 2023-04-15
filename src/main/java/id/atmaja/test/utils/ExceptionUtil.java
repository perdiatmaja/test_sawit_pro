package id.atmaja.test.utils;

import id.atmaja.test.base.BaseResponse;
import id.atmaja.test.constant.ErrorResponse;

public class ExceptionUtil {

    public static BaseResponse createGeneralErrorResponse() {
        return createErrorResponse(ErrorResponse.Message.GENERAL_ERROR, ErrorResponse.Code.GENERAL_ERROR);
    }

    public static BaseResponse createAccessDeniedResponse() {
        return createErrorResponse(ErrorResponse.Message.ACCESS_DENIED, ErrorResponse.Code.ACCESS_DENIED);
    }

    public static BaseResponse createUnauthorizedResponse() {
        return createErrorResponse(ErrorResponse.Message.UNAUTHORIZED, ErrorResponse.Code.UNAUTHORIZED);
    }

    public static BaseResponse createErrorResponse(String message, String code) {
        return new BaseResponse
                .Builder()
                .code(code)
                .message(message)
                .build();
    }
}
