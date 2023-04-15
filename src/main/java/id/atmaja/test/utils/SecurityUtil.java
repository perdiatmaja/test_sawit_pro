package id.atmaja.test.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class SecurityUtil {
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?=&]{6,16}$";


    public static boolean isPasswordValid(final String password) {
        final Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        return pattern.matcher(password).find();
    }

    public static String hashPassword(final String password) {
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }
}
