package space.shefer.receipt.rest.service;

import org.junit.Before;
import org.junit.Test;
import space.shefer.receipt.rest.entity.Place;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.repository.ReceiptRepository;
import space.shefer.receipt.rest.service.report.ReportMetaFilter;
import space.shefer.receipt.rest.util.DateUtil;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class ReceiptServiceTest {

  private ReceiptService service;
  private ReceiptRepository receiptRepository;
  private Place place = new Place(123L, "", null);

  @Before
  public void setUp() {
    receiptRepository = mock(ReceiptRepository.class);
    service = spy(new ReceiptService(receiptRepository));
  }

  @Test
  public void report() {
    ReportMetaFilter metaFilter = new ReportMetaFilter();
    metaFilter.setDateFrom(DateUtil.parseReceiptDate("20190725T2313"));
    metaFilter.setDateTo(DateUtil.parseReceiptDate("20190807T0815"));
    metaFilter.setFn("1111");
    metaFilter.setFd("2222");
    metaFilter.setFp("3333");
    metaFilter.setSumMin(100.0);
    metaFilter.setSumMax(800.0);
    when(receiptRepository.getReceipts(any()))
      .thenReturn(Arrays.asList(
        Receipt.builder().id(1L).place(place).build(),
        Receipt.builder().id(2L).place(place).build()
      ));
    service.getReceipts(metaFilter);
    verify(receiptRepository, times(1)).getReceipts(metaFilter);
  }
}