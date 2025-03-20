package encryption;

import static org.junit.jupiter.api.Assertions.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

public class EncryptionServiceTest {
    @Test
    public void testEncryption() throws Exception {
        // 暗号化サービスのインスタンスを作成
        EncryptionService encryptionService = new EncryptionService();

        // 暗号化に使用する秘密鍵を生成
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

        // テスト用のデータ（暗号化対象の文字列）
        String dataToEncrypt = "companyId=12345&userId=67890&authToken=abcd1234";

        // 暗号化を実行
        String encryptedData = encryptionService.encrypt(dataToEncrypt, secretKey);

        // 暗号化されたデータがnullでないことを確認
        assertNotNull(encryptedData);

        // 暗号化されたデータが元のデータと異なることを確認
        assertNotEquals(dataToEncrypt, encryptedData);

        // コンソールに出力（デバッグ用）
        System.out.println("暗号化前のデータ: " + dataToEncrypt);
        System.out.println("暗号化後のデータ: " + encryptedData);
    }
}
