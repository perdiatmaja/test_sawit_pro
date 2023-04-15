package id.atmaja.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.atmaja.test.base.BaseResponse;
import id.atmaja.test.builder.ResponseBuilder;
import id.atmaja.test.controller.request.LoginRequest;
import id.atmaja.test.dao.user.UserModel;
import id.atmaja.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody final LoginRequest loginRequest) throws NoSuchAlgorithmException {
        return userService.login(loginRequest);
    }

    @GetMapping("/{userId}")
    public BaseResponse<UserModel> getUser(@PathVariable final Long userId) {
        return ResponseBuilder.success(userService.getUserById(userId));
    }

    @PutMapping
    public BaseResponse<UserModel> update(@RequestBody UserModel userModel) {
        return userService.update(userModel);
    }

    @PostMapping()
    public BaseResponse<UserModel> getUser(@RequestBody final UserModel userModel) {
        return userService.add(userModel);
    }

    @GetMapping
    public BaseResponse<List<UserModel>> getUserList() {
        return ResponseBuilder.success(userService.getUserList());
    }
}
