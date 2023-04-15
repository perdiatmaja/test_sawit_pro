package id.atmaja.test.constant;

public class ErrorResponse {

    public static class Message {

        public static final String GENERAL_ERROR = "System Busy";

        public static final String ACCESS_DENIED = "Access Denied";

        public static final String UNAUTHORIZED = "You are not authorized to access this service";
    }

    public static class Code {

        public static final String GENERAL_ERROR = "3000";

        public static final String ACCESS_DENIED = "3001";

        public static final String UNAUTHORIZED = "3002";
    }
}
