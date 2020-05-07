package space.shefer.receipt.platform.core.util;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {

  @Test
  public void parseReceiptDate_withoutSeconds() {
    LocalDateTime actual = DateUtil.parseReceiptDate("20190825T1344");
    // getYear returns yeast after 1900
    assertEquals(2019, actual.getYear());
    // months are zero-based
    assertEquals(8, actual.getMonthValue());
    assertEquals(25, actual.getDayOfMonth());
    assertEquals(13, actual.getHour());
    assertEquals(44, actual.getMinute());
    assertEquals(0, actual.getSecond());
  }

  @Test
  public void parseReceiptDate_withSeconds() {
    LocalDateTime actual = DateUtil.parseReceiptDate("20190825T134455");
    // getYear returns yeast after 1900
    assertEquals(2019, actual.getYear());
    // months are zero-based
    assertEquals(8, actual.getMonthValue());
    assertEquals(25, actual.getDayOfMonth());
    assertEquals(13, actual.getHour());
    assertEquals(44, actual.getMinute());
    assertEquals(55, actual.getSecond());
  }
}
