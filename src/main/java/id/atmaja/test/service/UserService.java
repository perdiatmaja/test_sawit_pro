package id.atmaja.test.service;

import id.atmaja.test.base.BaseResponse;
import id.atmaja.test.builder.ResponseBuilder;
import id.atmaja.test.controller.request.LoginRequest;
import id.atmaja.test.dao.user.UserDao;
import id.atmaja.test.dao.user.UserModel;
import id.atmaja.test.utils.JwtTokenUtil;
import id.atmaja.test.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public UserModel getUserById(final long id) {
        return userDao.get(id).orElse(null);
    }

    public List<UserModel> getUserList() {
        return userDao.getAll();
    }

    public BaseResponse<String> login(final LoginRequest loginRequest) throws NoSuchAlgorithmException {
        final Optional<UserModel> optionalUserModel = userDao.getByPhonenumber(loginRequest.getPhoneNumber());

        if (optionalUserModel.isEmpty()) {
            return ResponseBuilder.error("2000", "User not Found");
        }
        final UserModel userModel = optionalUserModel.get();

        if (!userModel.getPassword().equalsIgnoreCase(loginRequest.getPassword())) {
            return ResponseBuilder.error("3000", "Password incorrect");
        }

        return ResponseBuilder.success(JwtTokenUtil.generateToken(String.valueOf(userModel.getId())));
    }

    public BaseResponse update(final UserModel userModel) {
        final Optional<UserModel> optionalUserModel = userDao.get(userModel.getId());

        if (optionalUserModel.isEmpty()) {
            return ResponseBuilder.error("2000", "User not Found");
        }

        final UserModel dbUserModel = optionalUserModel.get();

        dbUserModel.merge(userModel);

        userDao.save(dbUserModel);

        return ResponseBuilder.success(userModel);
    }

    @Transactional
    public BaseResponse<UserModel> add(final UserModel userModel) {
        if (!userModel.getPhonenumber().startsWith("08")) {
            return ResponseBuilder.error("3000", "Phone number must start with 08");
        }

        if (!SecurityUtil.isPasswordValid(userModel.getPassword())) {
            return ResponseBuilder.error("3000", "Password mandatory min 6, max 16, containing at least 1 capital letter\n" +
                    "and 1 number.");
        }

        userModel.setPassword(SecurityUtil.hashPassword(userModel.getPassword()));

        userDao.save(userModel);

        return ResponseBuilder.success(userModel);
    }
}
