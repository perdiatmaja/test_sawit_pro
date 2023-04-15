package id.atmaja.test.builder;

import id.atmaja.test.base.BaseResponse;

public class ResponseBuilder {

    private static final String CODE_SUCCESS = "1000";

    public static <T> BaseResponse success(T data) {
        return new BaseResponse.Builder().code(CODE_SUCCESS).data(data).build();
    }

}
