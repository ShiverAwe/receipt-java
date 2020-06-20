package space.shefer.receipt.platform.core.dto.util;

public class DateUtil {

  public static final String RECEIPT_DATETIME_PATTERN = "yyyyMMdd'T'HHmm";

  public static final String RECEIPT_DATETIME_DESCRIPTION = "Date and time in format 'yyyymmddThhmm'";

  public static final String RECEIPT_DATETIME_REGEX = ""
    + "20[0-2][0-9]" // year
    + "[1-2][0-9]" // month
    + "[0-3][0-9]" //day
    + "T" // delimiter
    + "[0-5][0-9]" // minutes
    + "[0-5][0-9]"; //hours

}
