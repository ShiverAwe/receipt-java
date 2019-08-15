package space.shefer.receipt.rest.service;

import org.junit.Before;
import org.junit.Test;
import space.shefer.receipt.rest.repository.ReceiptRepository;
import space.shefer.receipt.rest.service.report.ReportItemFilter;
import space.shefer.receipt.rest.service.report.ReportMetaFilter;
import space.shefer.receipt.rest.service.report.ReportReceiptFilter;
import space.shefer.receipt.rest.util.DateUtil;

import static org.mockito.Mockito.*;


public class ReceiptServiceTest {

  private ReceiptService service;
  private ReceiptRepository repository;

  @Before
  public void setUp() {
    repository = mock(ReceiptRepository.class);
    service = spy(new ReceiptService(repository));
  }

  @Test
  public void report() {
    ReportReceiptFilter parameters = new ReportReceiptFilter();
    ReportMetaFilter metaFilter = new ReportMetaFilter();
    ReportItemFilter itemFilter = new ReportItemFilter();
    metaFilter.setDateEquals("20190801T1234");
    metaFilter.setFn("1111");
    metaFilter.setFd("2222");
    metaFilter.setFp("3333");
    metaFilter.setSumEquals(444.4);
    parameters.setMeta(metaFilter);
    parameters.setItem(itemFilter);
    service.report(parameters);
    verify(repository, times(1)).findByCredentials(
      "1111", true,
      "2222", true,
      "3333", true,
      DateUtil.parseReceiptDate("20190801T1234"), true,
      444.4, true);
  }
}