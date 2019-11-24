package vn.ntqsolution.smart_shop.utils;

import lombok.extern.slf4j.Slf4j;

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
    return srcString != null && !"".equals(srcString.trim());
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
  public static String generateRandomString() {
    return UUID.randomUUID().toString();
  }

}
