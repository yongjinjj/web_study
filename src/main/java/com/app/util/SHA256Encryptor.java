
package com.app.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Encryptor {
	
	//암호화		복호화
	//Encrypt	Decrypt
	//Encode	Decode

	private static final String ENC_SALT = "sha256encsalt"; // 옵션

	public static String encrypt(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		text = text + ENC_SALT; // salt 는 옵션
		md.update(text.getBytes());

		return bytesToHex(md.digest());
	}

	private static String bytesToHex(byte[] cs) {
		StringBuilder sb = new StringBuilder();
		for (byte b : cs) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
}
