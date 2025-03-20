package encryption;

import static org.junit.jupiter.api.Assertions.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import utils.SessionUtils;

public class SessionTest {
    @Test
    public void testEncryptionDecryptionWithUrlOutput() throws Exception {
        // モック作成
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpSession mockSession = Mockito.mock(HttpSession.class);
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);

        // 暗号化サービスと復号化サービスのインスタンス
        EncryptionService encryptionService = new EncryptionService();
        DecryptionService decryptionService = new DecryptionService();
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

        // ベースURLとパラメータ部分を設定
        String baseUrl = "https://example.com";
        String companyId = "12345";
        String userId = "67890";
        String authToken = "abcd1234";
        String queryParams = "companyId=" + companyId + "&userId=" + userId + "&authToken=" + authToken;

        // 修正前のURLと暗号化対象の文字列を出力
        System.out.println("修正前のURL: " + baseUrl + "?" + queryParams);
        System.out.println("暗号化対象の文字列: " + queryParams);

        // パラメータ部分を暗号化
        String encryptedParams = encryptionService.encrypt(queryParams, secretKey);
        System.out.println("暗号化後のURL: " + baseUrl + "?" + encryptedParams);

        // 暗号化データをセッションに保存
        SessionUtils.saveEncryptedDataToSession(mockRequest, "encryptedUrl", baseUrl + "?" + encryptedParams);
        Mockito.verify(mockSession).setAttribute("encryptedUrl", baseUrl + "?" + encryptedParams);

        // セッションから復号化
        Mockito.when(mockSession.getAttribute("encryptedUrl")).thenReturn(baseUrl + "?" + encryptedParams);
        String retrievedEncryptedUrl = SessionUtils.getEncryptedDataFromSession(mockRequest, "encryptedUrl");

        // ベースURLと暗号化部分を分離
        String retrievedEncryptedParams = retrievedEncryptedUrl.substring(baseUrl.length() + 1);
        String decryptedParams = decryptionService.decrypt(retrievedEncryptedParams, secretKey);

        // 復号化後のURLを出力
        System.out.println("復号化後のURL: " + baseUrl + "?" + decryptedParams);

        // 元のパラメータと一致することを確認
        assertEquals(queryParams, decryptedParams);
    }
}
