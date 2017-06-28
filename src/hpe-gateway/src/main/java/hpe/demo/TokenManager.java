package hpe.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TokenManager {
	// Map<token, appkey>
	static Map<String, String> tokenMap = new HashMap<String, String>();

	public static synchronized boolean varifyToken(String appkey, String token) {
		if (appkey == null || token == null) {
			return false;
		}
		return appkey.equals(tokenMap.get(token));
	}

	public static synchronized String genToken(String appkey, String username, String password) {
		return randomLetter(32);
	}
	
    private static String randomLetter(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(base.charAt(random.nextInt(base.length())));
        }
        return sb.toString();
    }
}
