package space.shefer.receipt.rest.service;

import org.junit.Before;
import org.junit.Test;
import space.shefer.receipt.platform.core.dto.ReportMetaFilter;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.repository.ReceiptRepository;
import space.shefer.receipt.platform.core.util.DateUtil;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReceiptServiceTest {

  private ReceiptService service;
  private ReceiptRepository receiptRepository;

  @Before
  public void setUp() {
    receiptRepository = mock(ReceiptRepository.class);
    MerchantLogoService merchantLogoService = mock(MerchantLogoService.class);
    service = spy(new ReceiptService(receiptRepository, merchantLogoService));
  }

  @Test
  public void getReceipts() {
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
        createReceiptWithId("1"),
        createReceiptWithId("2")
      ));
    service.getReceipts(metaFilter);
    verify(receiptRepository).getReceipts(metaFilter);
  }

  @Test
  public void trimAddressLine() {
    assertEquals(ReceiptService.trimAddressLine("----asdasdasdasd312314--"), "asdasdasdasd312314");
    assertEquals(ReceiptService.trimAddressLine("фы---!вфыв__---__---__--____---"), "фы---!вфыв");
    assertNull(ReceiptService.trimAddressLine(null));
    assertEquals(ReceiptService.trimAddressLine("-----7----"), "7");
    assertEquals(ReceiptService.trimAddressLine("asdad312312rfa"), "asdad312312rfa");
    assertEquals(ReceiptService.trimAddressLine(""), "");
    assertEquals(ReceiptService.trimAddressLine("----!#@#$!____--"), "");
  }

  private Receipt createReceiptWithId(String id){
    Receipt receipt = new Receipt();
    receipt.setId(id);
    return receipt;
  }

}
