package id.atmaja.test.controller;

import id.atmaja.test.controller.request.LoginRequest;
import id.atmaja.test.dao.user.UserModel;
import id.atmaja.test.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;

class UserControllerTest {

    private final UserService userService = Mockito.mock(UserService.class);

    private final UserController userController = new UserController(userService);

    @Test
    public void testLogin() throws NoSuchAlgorithmException {
        final LoginRequest loginRequest = new LoginRequest();
        userController.login(loginRequest);
        Mockito.verify(userService).login(loginRequest);
    }

    @Test
    public void testGetUser() {
        final long userId = 0L;
        userController.getUser(userId);
        Mockito.verify(userService).getUserById(userId);
    }

    @Test
    public void testUpdate() {
        final UserModel userModel = new UserModel();
        userController.update(userModel);
        Mockito.verify(userService).update(userModel);
    }

    @Test
    public void testAddUser() {
        final UserModel userModel = new UserModel();
        userController.add(userModel);
        Mockito.verify(userService).add(userModel);
    }

    @Test
    public void testGetUserList() {
        userController.getUserList();
        Mockito.verify(userService).getUserList();
    }
}