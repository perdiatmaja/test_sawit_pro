package id.atmaja.test.base;

import id.atmaja.test.config.exception.InvalidTokenException;
import id.atmaja.test.config.exception.TokenException;
import id.atmaja.test.config.exception.TokenExpiredException;
import id.atmaja.test.utils.JwtTokenUtil;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    protected String authenticate(String token) throws TokenException {
        if (token == null || "".equals(token)) {
            throw new InvalidTokenException();
        }

        String tokenData = JwtTokenUtil.getTokenData(token);

        if (tokenData == null) {
            throw new TokenExpiredException();
        }

        return tokenData;
    }
}
