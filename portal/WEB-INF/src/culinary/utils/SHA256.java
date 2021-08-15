package culinary.utils;

import java.security.MessageDigest;

public class SHA256 {

	public static String generate(String password) throws Exception{

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
