package id.atmaja.test.service;

import id.atmaja.test.dao.user.UserDao;
import id.atmaja.test.dao.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public UserModel getUserById(final long id) {
        return userDao.get(id).orElse(null);
    }
}
