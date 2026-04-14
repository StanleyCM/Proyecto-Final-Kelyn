package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

// Encapsulamiento - utilidad de hashing privada al paquete Utils
public class HashUtil {

    // Convierte la contrasena en texto plano a SHA-256
    // La contrasena nunca se guarda ni compara en texto plano
    public static String sha256(String input) {
        if (input == null) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 no disponible", e);
        }
    }
}