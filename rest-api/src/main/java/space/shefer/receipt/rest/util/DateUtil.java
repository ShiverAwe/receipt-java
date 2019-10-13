package space.shefer.receipt.rest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

public final class DateUtil {

  public static final String RECEIPT_DATETIME_PATTERN = "yyyyMMdd'T'HHmm";

  public static LocalDateTime parseGMTDate(String date) {
    return simpleDateFormat(date, "dd-MM-yyyy");
  }

  public static LocalDateTime simpleDateFormat(String date, String format) {
    try {
      return LocalDateTime.ofInstant(new SimpleDateFormat(format).parse(date).toInstant(), ZoneId.systemDefault());
    }
    catch (ParseException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public static LocalDateTime parseReceiptDate(String date) {
    if (date == null) {
      return null;
    }
    int lenghtWithoutSeconds = 13;
    int lenghtWithSeconds = 15;
    if (date.length() == lenghtWithoutSeconds) {
      return simpleDateFormat(date, "yyyyMMdd'T'HHmm");
    }
    else if (date.length() == lenghtWithSeconds) {
      return simpleDateFormat(date, "yyyyMMdd'T'HHmmss");
    }
    else {
      throw new IllegalArgumentException("Date should be either 13 or 15 symbols length");
    }
  }

}
