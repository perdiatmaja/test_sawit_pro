package id.atmaja.test.config;

import java.util.Properties;

public class AppProperties extends Properties {

    private static final String SERVER_PORT = "server.port";

    private static final String DATASOURCE_URL = "spring.datasource.url";

    private static final String DATASOURCE_USERNAME = "spring.datasource.username";

    private static final String DATASOURCE_PASSWORD = "spring.datasource.password";

    private static final String DDL_AUTO = "spring.jpa.hibernate.ddl-auto";

    private static final String NAMING_IMPLICIT_STRATEGY = "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl";

    private static final String NAMING_IMPLICIT_STRATEGY_KEY = "spring.jpa.hibernate.naming.implicit-strategy";

    private static final String NAMING_PHYSICAL_STRATEGY = "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl";

    private static final String NAMING_PHYSICAL_STRATEGY_KEY = "spring.jpa.hibernate.naming.physical-strategy";

    private static final String BANNER_MODE_KEY = "spring.main.banner-mode";

    private static final String BANNER_MODE = "off";

    private static final String DB = "jdbc:mysql://" + EnvConfig.DB_HOST + "/" + EnvConfig.DB_NAME;

    private static final String CORS_ALLOWED_KEY = "endpoints.cors.allowed-origins";

    private static final String LOGGING_FILE_KEY = "logging.file";

    public AppProperties() {
        put(SERVER_PORT, EnvConfig.SERVER_PORT);
        put(DDL_AUTO, EnvConfig.DDL_AUTO);
        put(BANNER_MODE_KEY, BANNER_MODE);
        setDBConfig();
        setNamingStrategy();
    }

    public static Properties createAppProperties() {
        return new AppProperties();
    }

    private void setDBConfig() {
        put(DATASOURCE_URL, DB);
        put(DATASOURCE_USERNAME, EnvConfig.DB_USERNAME);
        put(DATASOURCE_PASSWORD, EnvConfig.DB_PASSWORD);
    }

    private void setNamingStrategy() {
        put(NAMING_IMPLICIT_STRATEGY_KEY, NAMING_IMPLICIT_STRATEGY);
        put(NAMING_PHYSICAL_STRATEGY_KEY, NAMING_PHYSICAL_STRATEGY);
    }
}
