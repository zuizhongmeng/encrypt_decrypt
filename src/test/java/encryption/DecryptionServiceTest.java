package encryption;

import static org.junit.jupiter.api.Assertions.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

public class DecryptionServiceTest {
    @Test
    public void testDecryption() throws Exception {
        // 暗号化と復号化のサービスインスタンスを作成
        EncryptionService encryptionService = new EncryptionService();
        DecryptionService decryptionService = new DecryptionService();
        
        // 暗号化に使用する秘密鍵を生成
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

        // テスト用のデータ（暗号化対象の文字列）
        String originalData = "companyId=12345&userId=67890&authToken=abcd1234";

        // 暗号化
        String encryptedData = encryptionService.encrypt(originalData, secretKey);

        // 復号化
        String decryptedData = decryptionService.decrypt(encryptedData, secretKey);

        // 元データと復号化データが一致することを確認
        assertEquals(originalData, decryptedData);

        // コンソールに出力（デバッグ用）
        System.out.println("暗号化前のデータ: " + originalData);
        System.out.println("暗号化後のデータ: " + encryptedData);
        System.out.println("復号化後のデータ: " + decryptedData);
    }
}
