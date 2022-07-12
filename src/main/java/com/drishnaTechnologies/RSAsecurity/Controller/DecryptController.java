package com.drishnaTechnologies.RSAsecurity.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/decrypt")
public class DecryptController {

	@GetMapping
	public String decryptData(@RequestParam("encryptedText") String encryptedText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {

		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		
		byte[] encodedText = java.util.Base64.getDecoder().decode(encryptedText);
		
        String privateK ="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";

        System.out.println(privateK);
        PrivateKey prvKey = null;
        byte[] privateBytes = java.util.Base64.getDecoder().decode(privateK);
        PKCS8EncodedKeySpec keySpec1 = new PKCS8EncodedKeySpec(privateBytes);
        KeyFactory keyFactory1 = KeyFactory.getInstance("RSA");
        prvKey = keyFactory1.generatePrivate(keySpec1);

        cipher.init(Cipher.DECRYPT_MODE,prvKey);

        //Decrypting the text
        byte[] decipheredText = cipher.doFinal(encodedText);
        System.out.println("------------" + new String(decipheredText));
		return null;
	}

	private static String getKey(String filename) throws IOException {
		// Read key from file
		String strKeyPEM = "";
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = br.readLine()) != null) {
			strKeyPEM += line + "\n";
		}
		br.close();
		return strKeyPEM;
	}
}
