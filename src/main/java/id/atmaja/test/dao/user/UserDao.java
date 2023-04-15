package id.atmaja.test.dao.user;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDao extends BaseDao<UserModel>{

    @Autowired
    UserDao(final EntityManager entityManager) {
        super(entityManager, UserModel.class);
    }
}
