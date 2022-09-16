package com.drishnaTechnologies.RSAsecurity.Controller;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drishnaTechnologies.RSAsecurity.Model.EncryptedText;

/**
 * @author Priyanshu Daksha
 * 
 *
 */
@RestController
@RequestMapping("/api/v1/decrypt")
public class DecryptController {

	private String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEArV9RxoLFrOWvZudjjoxvevqoZKuK/NpaUwi02BaVguSe8PgT01/29Xh4QLyEtzIrHs3GyufwVVkooge+xf3oQLkoJD5eNWVGlTfiTyIl9QU0rD4U0RLmc6EF5VPLARqdPxv+vaH5Jst4P6pX/d+VrUrMy2QMVO3RpBObznLtjmh+2TxcA+TCD6QuqnDsQNHK5DKtECb6aaSaFbzUa3M+WGBNYIv4nptXfQx1sXRpw6VLKEpz6uP94ramg3GQj+pBRXSpKy0kET6r9Y37Ag5X/t9uOodOEY5AnavmHos1+IlORdOLzBjMUu+jo6fijvWHToPrQaTrg1RFJzBgXuI0EHWCTTKKFwVr3QUAijY4m53DYoETPKvXjy/2R7magrjvkFJfiJBQChBpFbWsogwFMMqeOsx2Q3PSltQVsGm4GA42Lh6Ra65qT7QmUFMY3NdRk+UxSpypQdAe6mqwnhAgiLz9/sJ6VInJ1wuaj5FH7K6ydqztYDPmGjkenVnFFbP4wYxASq+0vQCZerPgRuuigXUFGwZWCxWUgmQonRHpGCStigfopYEzUCqMPzNbXuq3JXsMHhxNzrZ058QJXJ5YT9lA6ZlvdF0CJJCD40wGI0yXapeAugtu6N67He2mMDhHpnl6mSXvounoLtK9jwLgM9AXZ6Ba8wGBmaPYhFTd5tMCAwEAAQ==";

	/**
	 * @param Model EncryptedText to JSON :- { "encryptedText":"EncryptedText" }
	 * @return String decrypted text of your input
	 * @apiNote This is an open Api.
	 */
	@PostMapping
	public String decryptData(@RequestBody EncryptedText encryptedText)
			throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

		byte[] encode = Base64.decodeBase64(publicKey);

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encode);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);

		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] data = java.util.Base64.getDecoder().decode(encryptedText.getText());

		byte[] decipheredText = cipher.doFinal(data);
		return new String(decipheredText);
	}

}
