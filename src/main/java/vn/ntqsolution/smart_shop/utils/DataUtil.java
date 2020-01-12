package vn.ntqsolution.smart_shop.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.UUID;

/**
 * Utility class for data pre-handling
 *
 * @author truongbb
 * @version 1.0
 * @since 2019-11-16
 */

@Slf4j
public class DataUtil {

  public static boolean isNotNullAndEmptyString(String srcString) {
    return !(srcString == null || "".equals(srcString.trim()));
  }


  public static String removeWildcardCharacters(String srcString) {
    if (isNotNullAndEmptyString(srcString)) {
      return "%" + srcString
        .trim()
        .replaceAll("%", "\\%")
        .replaceAll("_", "\\_") + "%";
    }
    return null;
  }

  /**
   * @return UUID random string (always is 36 characters)
   */
  public static String generateUUIDRandomString() {
    return UUID.randomUUID().toString();
  }

  public static String generateRandomString(int lenght) {
    String saltCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < lenght) {
      int index = (int) (rnd.nextFloat() * saltCharacters.length());
      salt.append(saltCharacters.charAt(index));
    }
    String saltStr = salt.toString();
    return saltStr;
  }

}
