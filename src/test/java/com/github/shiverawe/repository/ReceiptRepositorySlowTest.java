package com.github.shiverawe.repository;

import com.github.shiverawe.entity.Receipt;
import com.github.shiverawe.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ReceiptRepositorySlowTest {

  @Autowired
  ReceiptRepository repository;

  @Test
  public void testFindByCredentials() {
    Date date = DateUtil.parseGMTDate("31-12-1998");
    repository.save(new Receipt(null, date, "83479", "96253", "76193", 123.45, "TAXCOM", null, null, Collections.emptyList()));
    repository.save(new Receipt(null, date, "63485", "03485", "76193", 124.75, "TAXCOM", null, null, Collections.emptyList()));
    repository.save(new Receipt(null, date, "71522", "96253", "12345", 571.11, "TAXCOM", null, null, Collections.emptyList()));
    repository.save(new Receipt(null, date, "83479", "74747", "99229", 774.71, "TAXCOM", null, null, Collections.emptyList()));
    {
      List<Receipt> actual = repository.findByCredentials(
        null, false,
        null, false,
        null, false,
        null, false,
        null, false);
      assertEquals(4, actual.size());
    }
    {
      List<Receipt> actual = repository.findByCredentials(
        "83479", true,
        null, false,
        null, false,
        null, false,
        null, false);
      assertEquals(2, actual.size());
      assertEquals(123.45, actual.get(0).getSum().doubleValue(), 1e-5);
      assertEquals(774.71, actual.get(1).getSum().doubleValue(), 1e-5);
    }
  }

}