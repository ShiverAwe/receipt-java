package space.shefer.receipt.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import space.shefer.receipt.rest.service.ItemService;
import space.shefer.receipt.rest.service.ReceiptService;
import space.shefer.receipt.rest.service.report.ReportMetaFilter;
import space.shefer.receipt.rest.util.DateUtil;
import space.shefer.receipt.tests.util.ResourceUtil;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MainControllerTest {

  private MockMvc mockMvc;
  private MainController controller;
  private ReceiptService service;
  private ArgumentCaptor<ReportMetaFilter> filterCaptor;

  @Before
  public void setUp() {
    service = mock(ReceiptService.class);
    controller = spy(new MainController(service, mock(ItemService.class)));
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    filterCaptor = ArgumentCaptor.forClass(ReportMetaFilter.class);
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  public void testReceipts() throws Exception {
    String body = ResourceUtil.getResourceAsString("/controller/controllerRequest.json", getClass());
    mockMvc.perform(put("/receipts").content(body)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
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
