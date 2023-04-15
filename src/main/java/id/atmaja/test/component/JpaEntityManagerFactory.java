package id.atmaja.test.component;

import id.atmaja.test.config.EnvConfig;
import id.atmaja.test.dao.user.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import org.hibernate.jpa.boot.spi.PersistenceUnitDescriptor;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;

public class JpaEntityManagerFactory {

    private static final String DB = "jdbc:mysql://" + EnvConfig.DB_HOST + "/" + EnvConfig.DB_NAME;
    private static final String DB_USER_NAME = EnvConfig.DB_USERNAME;
    private static final String DB_PASSWORD = EnvConfig.DB_PASSWORD;
    private Class[] entityClasses;

    public JpaEntityManagerFactory() {
        setEntityClasses();
    }

    private void setEntityClasses() {
        this.entityClasses = new Class[]{UserModel.class};
    }

    public EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    protected EntityManagerFactory getEntityManagerFactory() {
        final PersistenceUnitInfo persistenceUnitInfo = getPersistenceUnitInfo(
                getClass().getSimpleName());

        final Map<String, Object> configuration = new HashMap<>();
        final PersistenceUnitDescriptor descriptor = new PersistenceUnitInfoDescriptor(persistenceUnitInfo);

        return new EntityManagerFactoryBuilderImpl(descriptor, configuration)
                .build();
    }

    protected PersistenceUnitInfo getPersistenceUnitInfo(String name) {
        return new HibernatePersistenceUnitInfo(name, getEntityClassNames(), getProperties());
    }

    protected List<String> getEntityClassNames() {
        return Arrays.asList(getEntities())
                .stream()
                .map(Class::getName)
                .collect(Collectors.toList());
    }

    protected Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.id.new_generator_mappings", false);
        properties.put("hibernate.connection.datasource", getMysqlDataSource());
        return properties;
    }

    protected Class[] getEntities() {
        return entityClasses;
    }

    protected DataSource getMysqlDataSource() {
        PGSimpleDataSource mysqlDataSource = new PGSimpleDataSource();
        mysqlDataSource.setURL(DB);
        mysqlDataSource.setUser(DB_USER_NAME);
        mysqlDataSource.setPassword(DB_PASSWORD);
        return mysqlDataSource;
    }
}
