package id.atmaja.test.service;

import id.atmaja.test.controller.request.LoginRequest;
import id.atmaja.test.dao.user.UserDao;
import id.atmaja.test.dao.user.UserModel;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.mockito.Mockito.*;

class UserServiceTest {

    final UserDao userDao = mock(UserDao.class);

    final UserService userService = new UserService(userDao);

    @Test
    public void login() throws NoSuchAlgorithmException {
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhoneNumber("087217377");
        loginRequest.setPassword("Kmzway87aa");

        final UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setPassword(loginRequest.getPassword());
        userModel.setPhonenumber(loginRequest.getPhoneNumber());

        when(userDao.getByPhonenumber(loginRequest.getPhoneNumber())).thenReturn(Optional.of(userModel));

        userService.login(loginRequest);
    }

    @Test
    public void testUpdate() {
        final UserModel request = new UserModel();
        request.setId(1L);
        request.setPassword("Kmzway87aa");
        request.setPhonenumber("087217377");

        final UserModel dbUser = new UserModel();
        dbUser.setId(1L);

        when(userDao.get(request.getId())).thenReturn(Optional.of(dbUser));

        userService.update(request);

        verify(userDao).save(dbUser);
    }

    @Test
    public void testAdd() {
        final UserModel request = new UserModel();
        request.setPassword("Kmzway87aa");
        request.setPhonenumber("087217377");

        userService.add(request);

        verify(userDao).save(request);
    }
}