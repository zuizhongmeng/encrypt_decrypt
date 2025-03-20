package encryption;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class EncryptionService {
    private static final String ALGORITHM = "AES";

    public String encrypt(String data, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getUrlEncoder().encodeToString(encryptedBytes); // URLセーフなBase64
    }
}
