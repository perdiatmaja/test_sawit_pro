package id.atmaja.test.builder;

import id.atmaja.test.base.BaseResponse;

public class ResponseBuilder {

    private static final String CODE_SUCCESS = "1000";

    public static <T> BaseResponse success(T data) {
        return new BaseResponse.Builder().message("Success.").code(CODE_SUCCESS).data(data).build();
    }

    public static BaseResponse error(final String errorCode, final String message) {
        return new BaseResponse.Builder().code(errorCode).message(message).build();
    }
}
