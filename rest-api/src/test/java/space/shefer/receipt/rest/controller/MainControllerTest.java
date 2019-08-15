package space.shefer.receipt.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import space.shefer.receipt.rest.service.ReceiptService;
import space.shefer.receipt.rest.service.report.ReportReceiptFilter;
import space.shefer.receipt.rest.util.DateUtil;
import space.shefer.receipt.tests.util.ResourceUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class MainControllerTest {

  private MockMvc mockMvc;
  private MainController controller;
  private ReceiptService service;
  private ArgumentCaptor<ReportReceiptFilter> filterCaptor;

  @Before
  public void setUp() {
    service = mock(ReceiptService.class);
    controller = spy(new MainController(service));
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    filterCaptor = ArgumentCaptor.forClass(ReportReceiptFilter.class);
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  public void testReport() throws Exception {
    String body = ResourceUtil.getResourceAsString("/controller/controllerRequest.json", getClass());
    mockMvc.perform(put("/report").content(body)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
    verify(service, times(1)).report(filterCaptor.capture());
    ReportReceiptFilter parameters = filterCaptor.getValue();
    System.out.println(parameters);
    assertNotNull(parameters.getMeta());
    assertEquals(DateUtil.parseReceiptDate("20190801T1255"), parameters.getMeta().getDateEquals());
    assertEquals(DateUtil.parseReceiptDate("20190801T1032"), parameters.getMeta().getDateFrom());
    assertEquals(DateUtil.parseReceiptDate("20190802T1345"), parameters.getMeta().getDateTo());
    assertEquals(236294, parameters.getMeta().getSumEquals(), 1e-5);
    assertEquals(362954, parameters.getMeta().getSumMin(), 1e-5);
    assertEquals(123629, parameters.getMeta().getSumMax(), 1e-5);
    assertEquals("936933", parameters.getMeta().getFn());
    assertEquals("832555", parameters.getMeta().getFd());
    assertEquals("535594", parameters.getMeta().getFp());
    assertEquals("someplace", parameters.getMeta().getPlace());
    assertNotNull(parameters.getItem());
  }
}