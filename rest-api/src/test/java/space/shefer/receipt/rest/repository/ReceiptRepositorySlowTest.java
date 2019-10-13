package space.shefer.receipt.rest.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.service.report.ReportMetaFilter;
import space.shefer.receipt.rest.util.DateUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ReceiptRepositorySlowTest {
  @Autowired
  ReceiptRepository repository;

  @Test
  public void testFindByCredentials_givenSomeReceipts_whenNoFilter_thenAllDataReturned() {
    Date date = DateUtil.parseReceiptDate("20190813T1355");
    List<Receipt> receiptsInitial = Arrays.asList(
      new Receipt(null, date, "83479", "96253", "76193", 123.45, "TAXCOM", "LOADED", null, emptyList()),
      new Receipt(null, date, "34780", "89255", "82661", 121.44, "TAXCOM", "LOADED", null, emptyList()),
      new Receipt(null, date, "03845", "11111", "11547", 723.75, "TAXCOM", "LOADED", null, emptyList()),
      new Receipt(null, date, "82640", "34579", "99999", 103.55, "TAXCOM", "LOADED", null, emptyList())
    );
    repository.saveAll(receiptsInitial);
    List<Receipt> receiptsAll = repository.findAll();
    assertEquals(4, receiptsAll.size());

    List<Receipt> receiptsFound = repository.getReceipts(new ReportMetaFilter());

    assertEquals(receiptsInitial.size(), receiptsAll.size());
    assertEquals(receiptsInitial.size(), receiptsFound.size());
    for (int i = 0; i < receiptsInitial.size(); i++) {
      assertSimilar(receiptsInitial.get(i), receiptsAll.get(i));
      assertSimilar(receiptsInitial.get(i), receiptsFound.get(i));
    }
  }

  @Test
  public void testGetReceipts() {
    Date dateOk = DateUtil.parseReceiptDate("20190813T105527");
    Date dateWrongYear = DateUtil.parseReceiptDate("20180813T105527");
    Date dateWrongMonth = DateUtil.parseReceiptDate("20190713T105527");
    Date dateWrongDate = DateUtil.parseReceiptDate("20190811T105527");
    Date dateWrongHour = DateUtil.parseReceiptDate("20190813T115527");
    Date dateWrongMinute = DateUtil.parseReceiptDate("20190813T105627");
    Date dateWrongSecond = DateUtil.parseReceiptDate("20190813T105529");
    double sumOk = 44.4;
    // OK
    repository.save(new Receipt(null, dateOk, "11111", "22222", "33333", sumOk, "TAXCOM", "LOADED", null, emptyList()));
    // OK
    repository.save(new Receipt(null, dateOk, "11111", "22222", "33333", sumOk, "TAXCOM", "LOADED", null, emptyList()));
    // WRONG DATE: WRONG YEAR
    repository.save(new Receipt(null, dateWrongYear, "11111", "22222", "33333", sumOk, "TAXCOM", "LOADED", null, emptyList()));
    // WRONG DATE: WRONG MONTH
    repository.save(new Receipt(null, dateWrongMonth, "11111", "22222", "33333", sumOk, "TAXCOM", "LOADED", null, emptyList()));
    // WRONG DATE: WRONG DATE
    repository.save(new Receipt(null, dateWrongDate, "11111", "22222", "33333", sumOk, "TAXCOM", "LOADED", null, emptyList()));
    // WRONG DATE: WRONG HOUR
    repository.save(new Receipt(null, dateWrongHour, "11111", "22222", "33333", sumOk, "TAXCOM", "LOADED", null, emptyList()));
    // WRONG DATE: WRONG MINUTE
    repository.save(new Receipt(null, dateWrongMinute, "11111", "22222", "33333", sumOk, "TAXCOM", "LOADED", null, emptyList()));
    // WRONG DATE: WRONG SECOND
    repository.save(new Receipt(null, dateWrongSecond, "11111", "22222", "33333", sumOk, "TAXCOM", "LOADED", null, emptyList()));
    // WRONG FN
    repository.save(new Receipt(null, dateOk, "83759", "22222", "33333", sumOk, "TAXCOM", "LOADED", null, emptyList()));
    // WRONG FD
    repository.save(new Receipt(null, dateOk, "11111", "02349", "33333", sumOk, "TAXCOM", "LOADED", null, emptyList()));
    // WRONG FP
    repository.save(new Receipt(null, dateOk, "11111", "22222", "73458", sumOk, "TAXCOM", "LOADED", null, emptyList()));
    // WRONG SUM
    repository.save(new Receipt(null, dateOk, "11111", "22222", "33333", 65.3, "TAXCOM", "LOADED", null, emptyList()));
    {
      List<Receipt> actual = repository.getReceipts(
        ReportMetaFilter.builder()
          .fn("11111")
          .fd("22222")
          .fp("33333")
          .dateFrom(dateOk)
          .dateTo(dateOk)
          .sumMin(sumOk)
          .sumMax(sumOk)
          .build()
      );
      assertEquals(2, actual.size());
      for (Receipt receipt : actual) {
        assertSimilar(new Receipt(null, dateOk, "11111", "22222", "33333", sumOk, "TAXCOM", "LOADED", null, emptyList()), receipt);
      }
    }
  }

  public void assertSimilar(Receipt r1, Receipt r2) {
    assertEquals(r1.getDate(), r2.getDate());
    assertEquals(r1.getFn(), r2.getFn());
    assertEquals(r1.getFd(), r2.getFd());
    assertEquals(r1.getFp(), r2.getFp());
    assertEquals(r1.getSum(), r2.getSum());
    assertEquals(r1.getProvider(), r2.getProvider());
    assertEquals(r1.getStatus(), r2.getStatus());
    assertEquals(r1.getPlace(), r2.getPlace());
    assertEquals(r1.getItems(), r2.getItems());
  }
}