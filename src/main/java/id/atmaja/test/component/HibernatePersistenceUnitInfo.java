package id.atmaja.test.component;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

import javax.sql.DataSource;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HibernatePersistenceUnitInfo implements PersistenceUnitInfo {

    public static String JPA_VERSION = "2.1";

    private static String persistenceUnitName = "mainUnit";

    private static PersistenceUnitTransactionType transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;

    private List<String> managedClassNames;

    private List<String> mappingFileNames = new ArrayList<>();

    private Properties properties;

    private DataSource jtaDataSource;

    private DataSource nonJtaDataSource;

    private List<ClassTransformer> transformers = new ArrayList<>();

    public HibernatePersistenceUnitInfo(String persistenceUnitName,
                                        List<String> managedClassNames,
                                        Properties properties) {
        this.persistenceUnitName = persistenceUnitName;
        this.managedClassNames = managedClassNames;
        this.properties = properties;
    }

    @Override public String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    @Override public String getPersistenceProviderClassName() {
        return null;
    }

    @Override public PersistenceUnitTransactionType getTransactionType() {
        return transactionType;
    }

    @Override public DataSource getJtaDataSource() {
        return jtaDataSource;
    }

    @Override public DataSource getNonJtaDataSource() {
        return nonJtaDataSource;
    }

    @Override public List<String> getMappingFileNames() {
        return mappingFileNames;
    }

    @Override public List<URL> getJarFileUrls() {
        return null;
    }

    @Override public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override public List<String> getManagedClassNames() {
        return managedClassNames;
    }

    @Override public boolean excludeUnlistedClasses() {
        return true;
    }

    @Override public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override public ValidationMode getValidationMode() {
        return null;
    }

    @Override public Properties getProperties() {
        return properties;
    }

    @Override public String getPersistenceXMLSchemaVersion() {
        return null;
    }

    @Override public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer classTransformer) {

    }

    @Override public ClassLoader getNewTempClassLoader() {
        return null;
    }

}
