package id.atmaja.test.dao.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDao extends BaseDao<UserModel>{

    @Autowired
    UserDao(final EntityManager entityManager) {
        super(entityManager, UserModel.class);
    }

    @Override
    String getTableName() {
        return "user_model";
    }

    public Optional<UserModel> getByPhonenumber(final String phonenumber)  {
        final Query query = getEntityManager().createNativeQuery("SELECT * FROM " + getTableName() + " u " + "WHERE u.phonenumber=:phonenumber", UserModel.class);
        query.setParameter("phonenumber", phonenumber);
        final List<UserModel> userModels = query.getResultList();
        if (userModels.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userModels.get(0));
    }
}
