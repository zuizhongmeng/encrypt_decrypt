package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static void saveEncryptedDataToSession(HttpServletRequest request, String key, String encryptedData) {
        HttpSession session = request.getSession();
        session.setAttribute(key, encryptedData); // 暗号化データをセッションに保存
    }

    public static String getEncryptedDataFromSession(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(key); // セッションからデータを取得
    }
}
