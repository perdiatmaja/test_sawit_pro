package id.atmaja.test.dao.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class BaseDao<T> {
    private final EntityManager entityManager;
    private final Class<T> daoClass;

    BaseDao(final EntityManager entityManager,
            final Class<T> daoClass) {
        this.entityManager = entityManager;
        this.daoClass = daoClass;
    }

    public Optional<T> get(long id) {
        return Optional.ofNullable(entityManager.find(daoClass, id));
    }

    public List<T> getAll() {
        final Query query = entityManager.createQuery("SELECT * FROM " + daoClass.getSimpleName());

        return query.getResultList();
    }

    public void save(T t) {
        entityManager.persist(t);
    }

    void update(T t) {
        executeInsideTransaction(entityManager -> entityManager.persist(t));
    }

    void delete(T t) {
        executeInsideTransaction(entityManager -> entityManager.remove(t));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        final EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
