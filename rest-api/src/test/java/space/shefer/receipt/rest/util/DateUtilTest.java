package space.shefer.receipt.rest.util;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {
  @Test
  public void parseReceiptDate_withoutSeconds() {
    Date actual = DateUtil.parseReceiptDate("20190825T1344");
    // getYear returns yeast after 1900
    assertEquals(2019, actual.getYear() + 1900);
    // months are zero-based
    assertEquals(8, actual.getMonth() + 1);
    assertEquals(25, actual.getDate());
    assertEquals(13, actual.getHours());
    assertEquals(44, actual.getMinutes());
    assertEquals(0, actual.getSeconds());
  }

  @Test
  public void parseReceiptDate_withSeconds() {
    Date actual = DateUtil.parseReceiptDate("20190825T134455");
    // getYear returns yeast after 1900
    assertEquals(2019, actual.getYear() + 1900);
    // months are zero-based
    assertEquals(8, actual.getMonth() + 1);
    assertEquals(25, actual.getDate());
    assertEquals(13, actual.getHours());
    assertEquals(44, actual.getMinutes());
    assertEquals(55, actual.getSeconds());
  }
}