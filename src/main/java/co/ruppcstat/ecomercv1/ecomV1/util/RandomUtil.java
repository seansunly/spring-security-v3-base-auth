package co.ruppcstat.ecomercv1.ecomV1.util;

import java.security.SecureRandom;

public class RandomUtil {
    public static String random6Digits() {
        SecureRandom random = new SecureRandom();
        int number=100000 + random.nextInt(900000);
        return String.valueOf(number);
    }
}
