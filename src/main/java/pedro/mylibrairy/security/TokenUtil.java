package pedro.mylibrairy.security;

import java.util.Base64;

public class TokenUtil {
    public static String generateToken(String username) {
        return Base64.getEncoder().encodeToString((username + ":secretKey").getBytes());
    }

    public static String extractUsername(String token) {
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String decodedString = new String(decodedBytes);
        return decodedString.split(":")[0];
    }
}
