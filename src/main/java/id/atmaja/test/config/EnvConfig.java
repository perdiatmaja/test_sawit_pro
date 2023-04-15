package id.atmaja.test.config;

public class EnvConfig {

    public static final int SERVER_PORT = Integer.parseInt(System.getenv("serverPort"));

    public static final String DB_HOST = System.getenv("dbHost");

    public static final String DB_NAME = System.getenv("dbName");

    public static final String DB_USERNAME = System.getenv("dbUsername");

    public static final String DB_PASSWORD = System.getenv("dbPassword");

    public static final String DDL_AUTO = System.getenv("ddlAuto");
}
