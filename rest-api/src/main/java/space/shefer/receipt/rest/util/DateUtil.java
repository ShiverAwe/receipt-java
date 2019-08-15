package space.shefer.receipt.rest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

  public static Date parseGMTDate(String date) {
    return simpleDateFormat(date, "dd-MM-yyyy");
  }

  public static Date simpleDateFormat(String date, String format) {
    try {
      return new SimpleDateFormat(format).parse(date);
    }
    catch (ParseException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public static Date parseReceiptDate(String date) {
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
