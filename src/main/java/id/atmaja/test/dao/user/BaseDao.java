package id.atmaja.test.dao.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T> {
    private final EntityManager entityManager;
    private final Class<T> daoClass;

    abstract String getTableName();

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    BaseDao(final EntityManager entityManager,
            final Class<T> daoClass) {
        this.entityManager = entityManager;
        this.daoClass = daoClass;
    }

    public Optional<T> get(long id) {
        return Optional.ofNullable(entityManager.find(daoClass, id));
    }

    public List<T> getAll() {
        final Query query = entityManager.createNativeQuery("SELECT * FROM " + getTableName());

        return query.getResultList();
    }

    public void save(T t) {
        entityManager.persist(t);
    }
}
