package encryption;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class DecryptionService {
    private static final String ALGORITHM = "AES";

    public String decrypt(String encryptedData, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encryptedData); // デコード
        return new String(cipher.doFinal(decodedBytes));
    }
}
