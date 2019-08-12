package com.github.shiverawe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

  public static Date parseGMTDate(String date) {
    try {
      return new SimpleDateFormat("dd-MM-yyyy").parse(date);
    }
    catch (ParseException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
