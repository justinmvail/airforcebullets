package utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	
	public static String getHash(String password){
		if(password == null || password.isEmpty()){
			return password;
		}
		String hash = null;
		try {
	        //Create MessageDigest object for MD5
	        MessageDigest digest = MessageDigest.getInstance("MD5");
	        //Update input string in message digest
	        digest.update(password.getBytes(), 0, password.length());
	        //Converts message digest value in base 16 (hex)
	        hash = new BigInteger(1, digest.digest()).toString(16);
	    }catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return hash;
	}
	
	public static String getHash(int id){
		String strId = Integer.toString(id);
		String hash = null;
		try {
	        //Create MessageDigest object for MD5
	        MessageDigest digest = MessageDigest.getInstance("MD5");
	        //Update input string in message digest
	        digest.update(strId.getBytes(), 0, strId.length());
	        //Converts message digest value in base 16 (hex)
	        hash = new BigInteger(1, digest.digest()).toString(16);
	    }catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return hash;
	}

}
