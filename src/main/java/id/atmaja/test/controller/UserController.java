package id.atmaja.test.controller;

import id.atmaja.test.base.BaseResponse;
import id.atmaja.test.builder.ResponseBuilder;
import id.atmaja.test.dao.user.UserModel;
import id.atmaja.test.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/:userId")
    public BaseResponse<UserModel> getUser(@PathParam("userId") final long userId) {
        return ResponseBuilder.success(userService.getUserById(userId));
    }
}
