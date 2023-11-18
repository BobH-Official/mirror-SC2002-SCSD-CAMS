package CAMS.Data.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

public class HashingHelper {
  public static String sha256(String input) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] encodedHash =
        digest.digest(input.getBytes(StandardCharsets.UTF_8));
      return bytesToHex(encodedHash);

    } catch (Exception e) {
      Random random = new Random();
      return "" + random.nextInt(10000000, 99999999) +
        random.nextInt(10000000, 99999999) +
        random.nextInt(10000000, 99999999) +
        random.nextInt(10000000, 99999999) +
        random.nextInt(10000000, 99999999) +
        random.nextInt(10000000, 99999999) +
        random.nextInt(10000000, 99999999) + /*\n*/
        random.nextInt(10000000, 99999999);
    }
  }

  private static String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (byte b : hash) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
}
