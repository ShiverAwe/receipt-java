package space.shefer.receipt.rest.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import space.shefer.receipt.rest.dto.ReceiptStatus;
import space.shefer.receipt.rest.dto.ReportItemFilter;
import space.shefer.receipt.rest.entity.Item;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.tests.util.SpringJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringJpaTest
@Transactional
public class ItemRepositorySlowTest {
  @Autowired
  ItemRepository repository;
  @Autowired
  ReceiptRepository receiptRepository;

  @Test
  public void getItems_defaultFilter() {
    List<Item> itemsInitial = asList(
      new Item(null, "text1", 1001.0, 101.0, null),
      new Item(null, "text2", 1002.0, 102.0, null),
      new Item(null, "text3", 1003.0, 103.0, null),
      new Item(null, "text4", 1004.0, 104.0, null)
    );
    repository.saveAll(itemsInitial);
    List<Item> itemsAll = repository.findAll();
    assertEquals(4, itemsAll.size());

    ReportItemFilter filter = new ReportItemFilter();
    List<Item> itemsFound = repository.getItems(filter);

    assertEquals(itemsInitial.size(), itemsAll.size());
    assertEquals(itemsInitial.size(), itemsFound.size());
    for (int i = 0; i < itemsInitial.size(); i++) {
      assertSimilar(itemsInitial.get(i), itemsAll.get(i));
      assertSimilar(itemsInitial.get(i), itemsFound.get(i));
    }
  }

  @Test
  public void getItems_fullFilter() {
    Receipt receiptOk = createDummyReceipt();
    double priceOk = 100.0;
    double priceMax = priceOk + 5;
    double priceMin = priceOk - 5;
    // OK
    repository.save(new Item(null, "text1", priceOk, 101.0, receiptOk));
    // OK
    repository.save(new Item(null, "text1", priceMin, 101.0, receiptOk));
    // OK
    repository.save(new Item(null, "text1", priceMax, 101.0, receiptOk));
    // Wrong text
    repository.save(new Item(null, "text2", priceOk, 101.0, receiptOk));
    // Wrong receipt
    repository.save(new Item(null, "text1", priceOk, 101.0, null));
    // Price less than minimal
    repository.save(new Item(null, "text1", priceMin - 1, 101.0, receiptOk));
    // Price greater than maximal
    repository.save(new Item(null, "text1", priceMax + 1, 101.0, receiptOk));
    {
      ReportItemFilter filter = new ReportItemFilter();
      filter.setReceiptIds(asList(receiptOk.getId()));
      filter.setMinPrice(priceMin);
      filter.setMaxPrice(priceMax);
      filter.setTextEquals("text1");
      List<Item> actual = repository.getItems(filter);
      assertEquals(3, actual.size());
      assertSimilar(new Item(null, "text1", priceOk, 101.0, receiptOk), actual.get(0));
      assertSimilar(new Item(null, "text1", priceMin, 101.0, receiptOk), actual.get(1));
      assertSimilar(new Item(null, "text1", priceMax, 101.0, receiptOk), actual.get(2));
    }
  }

  private Receipt createDummyReceipt() {
    return receiptRepository.save(Receipt
      .builder()
      .status(ReceiptStatus.IDLE)
      .sum(0.0)
      .fn("dummy")
      .fd("dummy")
      .fp("dummy")
      .date(LocalDateTime.now())
      .build());
  }

  private void assertSimilar(Item i1, Item i2) {
    assertEquals(i1.getText(), i2.getText());
    assertEquals(i1.getPrice(), i2.getPrice());
    assertEquals(i1.getAmount(), i2.getAmount());
    assertEquals(i1.getReceipt(), i2.getReceipt());
  }
}
