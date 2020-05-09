package space.shefer.receipt.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import space.shefer.receipt.rest.dto.ReceiptCreateDto;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.service.ReceiptService;
import space.shefer.receipt.rest.util.DateUtil;
import space.shefer.receipt.tests.util.ResourceUtil;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReceiptCreateControllerTest {

  private MockMvc mockMvc;
  private ReceiptCreateController controller;
  private ReceiptService service;

  @Before
  public void setUp() {
    service = mock(ReceiptService.class);
    controller = spy(new ReceiptCreateController(service));
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void create() throws Exception {
    String body = ResourceUtil.getResourceAsString("/controller/ReceiptCreateControllerTest_create.json", getClass());
    doAnswer(n-> new Receipt()).when(service).create(any());
    mockMvc.perform(post("/create").content(body)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
    ArgumentCaptor<ReceiptCreateDto> createDtoCaptor= ArgumentCaptor.forClass(ReceiptCreateDto.class);
    verify(service, times(1)).create(createDtoCaptor.capture());
    ReceiptCreateDto receiptCreateDto =  createDtoCaptor.getValue();
    assertEquals(DateUtil.parseReceiptDate("20190801T1032"), receiptCreateDto.getDate());
    assertEquals(123629, receiptCreateDto.getSum(), 1e-5);
    assertEquals("936933", receiptCreateDto.getFn());
    assertEquals("832555", receiptCreateDto.getFd());
    assertEquals("535594", receiptCreateDto.getFp());
  }

}
