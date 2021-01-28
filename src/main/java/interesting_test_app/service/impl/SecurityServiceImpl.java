package interesting_test_app.service.impl;

import interesting_test_app.service.SecurityService;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.ResourceBundle;

@Service
@Log4j2
public class SecurityServiceImpl implements SecurityService {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Override
    public String encrypt(String stringToEncrypt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(resourceBundle.getString("secret.key").toCharArray(),
                    resourceBundle.getString("salt").getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            String encryptedString =
                    Base64.encodeBase64String(cipher.doFinal(stringToEncrypt.getBytes(StandardCharsets.UTF_8)));

            log.info("=== encryption: {}", encryptedString);
            return encryptedString;

        } catch (Exception e) {
            log.error("Error while encrypting: " + e.toString());
        }
        return null;
    }

    @Override
    public String decrypt(String stringToDecrypt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(resourceBundle.getString("secret.key").toCharArray(),
                    resourceBundle.getString("salt").getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(stringToDecrypt)));

            log.info("=== decryption: {}", decryptedString);
            return decryptedString;

        } catch (Exception e) {
            log.error("Error while encrypting: " + e.toString());
        }
        return null;
    }
}
