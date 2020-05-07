package space.shefer.receipt.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import space.shefer.receipt.platform.core.dto.ReportMetaFilter;
import space.shefer.receipt.platform.core.util.DateUtil;
import space.shefer.receipt.rest.service.ItemService;
import space.shefer.receipt.rest.service.ReceiptService;
import space.shefer.receipt.tests.util.ResourceUtil;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReportControllerTest {

  private MockMvc mockMvc;
  private ReportController controller;
  private ReceiptService service;

  @Before
  public void setUp() {
    service = mock(ReceiptService.class);
    controller = spy(new ReportController(service, mock(ItemService.class)));
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  public void testReceipts() throws Exception {
    String body = ResourceUtil.getResourceAsString("/controller/ReportControllerTest_testReceipts.json", getClass());
    mockMvc.perform(put("/receipts").content(body)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
    ArgumentCaptor<ReportMetaFilter> filterCaptor= ArgumentCaptor.forClass(ReportMetaFilter.class);
    verify(service, times(1)).getReceipts(filterCaptor.capture());
    ReportMetaFilter metaFilter =  filterCaptor.getValue();
    assertEquals(DateUtil.parseReceiptDate("20190801T1032"), metaFilter.getDateFrom());
    assertEquals(DateUtil.parseReceiptDate("20190802T1345"), metaFilter.getDateTo());
    assertEquals(362954, metaFilter.getSumMin(), 1e-5);
    assertEquals(123629, metaFilter.getSumMax(), 1e-5);
    assertEquals("936933", metaFilter.getFn());
    assertEquals("832555", metaFilter.getFd());
    assertEquals("535594", metaFilter.getFp());
    assertEquals("someplace", metaFilter.getPlace());
  }

}
