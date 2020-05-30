package space.shefer.receipt.platform.core.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import space.shefer.receipt.platform.core.dto.ReportMetaFilter;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.util.DateUtil;
import space.shefer.receipt.tests.util.SpringJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static space.shefer.receipt.platform.core.dto.ReceiptStatus.IDLE;
import static space.shefer.receipt.platform.core.dto.ReceiptStatus.LOADED;

@RunWith(SpringRunner.class)
@SpringJpaTest
@Transactional
public class ReceiptRepositorySlowTest {

  @Autowired
  ReceiptRepository repository;

  @Test
  public void getReceipts_noFilter() {
    LocalDateTime date = DateUtil.parseReceiptDate("20190813T1355");
    String merchantName = "ООО \"Лента\"";
    String merchantInn = "7814148471";
    String merchantPlaceAddress = "197374, СПб, ул. Савушкина, 112, лит. А";
    List<Receipt> receiptsInitial = Arrays.asList(
      new Receipt(null, date, "83479", "96253", "76193", 123.45, "TAXCOM", LOADED,
        emptyList(), merchantName, merchantInn, merchantPlaceAddress),
      new Receipt(null, date, "34780", "89255", "82661", 121.44, "TAXCOM", LOADED,
        emptyList(), merchantName, merchantInn, merchantPlaceAddress),
      new Receipt(null, date, "03845", "11111", "11547", 723.75, "TAXCOM", LOADED,
        emptyList(), merchantName, merchantInn, merchantPlaceAddress),
      new Receipt(null, date, "82640", "34579", "99999", 103.55, "TAXCOM", LOADED,
        emptyList(), merchantName, merchantInn, merchantPlaceAddress)
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
  public void getReceipt_fullFilter() {
    LocalDateTime dateOk = DateUtil.parseReceiptDate("20190813T105527");
    LocalDateTime dateWrongYear = DateUtil.parseReceiptDate("20180813T105527");
    LocalDateTime dateWrongMonth = DateUtil.parseReceiptDate("20190713T105527");
    LocalDateTime dateWrongDate = DateUtil.parseReceiptDate("20190811T105527");
    LocalDateTime dateWrongHour = DateUtil.parseReceiptDate("20190813T115527");
    LocalDateTime dateWrongMinute = DateUtil.parseReceiptDate("20190813T105627");
    LocalDateTime dateWrongSecond = DateUtil.parseReceiptDate("20190813T105529");
    double sumOk = 44.4;
    String merchantName = "ООО \"Лента\"";
    String merchantInn = "7814148471";
    String merchantPlaceAddress = "197374, СПб, ул. Савушкина, 112, лит. А";
    Long bannedId;
    List<Receipt> expectedReceipts = new ArrayList<>();

    {// WRONG ID
      Receipt receipt =
        repository.save(new Receipt(null,
          dateOk, "11111", "22222", "33333", sumOk, "TAXCOM", LOADED, emptyList(),
          merchantName, merchantInn, merchantPlaceAddress));
      bannedId = receipt.getId();
    }
    {// OK
      Receipt receipt =
        repository.save(new Receipt(null,
          dateOk, "11111", "22222", "33333", sumOk, "TAXCOM", LOADED, emptyList(),
          merchantName, merchantInn, merchantPlaceAddress));
      expectedReceipts.add(receipt);
    }
    {// OK
      Receipt receipt =
        repository.save(new Receipt(null,
          dateOk, "11111", "22222", "33333", sumOk, "TAXCOM", LOADED, emptyList(),
          merchantName, merchantInn, merchantPlaceAddress));
      expectedReceipts.add(receipt);
    }
    {// WRONG DATE: WRONG YEAR
      repository.save(new Receipt(null,
        dateWrongYear, "11111", "22222", "33333", sumOk, "TAXCOM", LOADED, emptyList(),
        merchantName, merchantInn, merchantPlaceAddress));
    }
    {// WRONG DATE: WRONG MONTH
      repository.save(new Receipt(null,
        dateWrongMonth, "11111", "22222", "33333", sumOk, "TAXCOM", LOADED, emptyList(),
        merchantName, merchantInn, merchantPlaceAddress));
    }
    {// WRONG DATE: WRONG DATE
      repository.save(new Receipt(null,
        dateWrongDate, "11111", "22222", "33333", sumOk, "TAXCOM", LOADED, emptyList(),
        merchantName, merchantInn, merchantPlaceAddress));
    }
    {// WRONG DATE: WRONG HOUR
      repository.save(new Receipt(null,
        dateWrongHour, "11111", "22222", "33333", sumOk, "TAXCOM", LOADED, emptyList(),
        merchantName, merchantInn, merchantPlaceAddress));
    }
    {// WRONG DATE: WRONG MINUTE
      repository.save(new Receipt(null,
        dateWrongMinute, "11111", "22222", "33333", sumOk, "TAXCOM", LOADED, emptyList(),
        merchantName, merchantInn, merchantPlaceAddress));
    }
    {// WRONG DATE: WRONG SECOND
      repository.save(new Receipt(null,
        dateWrongSecond, "11111", "22222", "33333", sumOk, "TAXCOM", LOADED, emptyList(),
        merchantName, merchantInn, merchantPlaceAddress));
    }
    {// WRONG FN
      repository.save(new Receipt(null,
        dateOk, "83759", "22222", "33333", sumOk, "TAXCOM", LOADED, emptyList(),
        merchantName, merchantInn, merchantPlaceAddress));
    }
    {// WRONG FD
      repository.save(new Receipt(null,
        dateOk, "11111", "02349", "33333", sumOk, "TAXCOM", LOADED, emptyList(),
        merchantName, merchantInn, merchantPlaceAddress));
    }
    {// WRONG FP
      repository.save(new Receipt(null,
        dateOk, "11111", "22222", "73458", sumOk, "TAXCOM", LOADED, emptyList(),
        merchantName, merchantInn, merchantPlaceAddress));
    }
    {// WRONG SUM
      repository.save(new Receipt(null,
        dateOk, "11111", "22222", "33333", 65.3, "TAXCOM", LOADED, emptyList(),
        merchantName, merchantInn, merchantPlaceAddress));
    }
    {// WRONG STATUS
      repository.save(new Receipt(null,
        dateOk, "11111", "22222", "33333", sumOk, "TAXCOM", IDLE, emptyList(),
        merchantName, merchantInn, merchantPlaceAddress));
    }

    repository.flush();
    Long finalBannedId = bannedId;
    List<Long> allowedIds = repository.findAll().stream().map(Receipt::getId)
      .filter(it -> !Objects.equals(it, finalBannedId)).collect(Collectors.toList());

    {
      List<Receipt> actual = repository.getReceipts(
        ReportMetaFilter.builder()
          .ids(allowedIds)
          .fn("11111")
          .fd("22222")
          .fp("33333")
          .dateFrom(dateOk)
          .dateTo(dateOk)
          .sumMin(sumOk)
          .sumMax(sumOk)
          .statuses(EnumSet.of(LOADED))
          .build()
      );

      assertEquals(expectedReceipts, actual);
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
    assertEquals(r1.getItems(), r2.getItems());
    assertEquals(r1.getMerchantName(), r2.getMerchantName());
    assertEquals(r1.getMerchantInn(), r2.getMerchantInn());
    assertEquals(r1.getMerchantPlaceAddress(), r2.getMerchantPlaceAddress());
  }
}
