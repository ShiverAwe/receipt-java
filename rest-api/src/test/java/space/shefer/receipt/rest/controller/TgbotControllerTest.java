package space.shefer.receipt.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import space.shefer.receipt.fnssdk.dto.FnsReceiptDto;
import space.shefer.receipt.platform.core.dto.ReceiptProvider;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.service.FnsReceiptService;
import space.shefer.receipt.rest.dto.TgbotCreateBody;
import space.shefer.receipt.tests.util.ResourceUtil;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TgbotControllerTest {

  private MockMvc mockMvc;
  private TgbotController controller;
  private FnsReceiptService service;

  @Before
  public void setUp() {
    service = mock(FnsReceiptService.class);
    controller = spy(new TgbotController(service));
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testReceipts() throws Exception {
    String receiptJson = ResourceUtil.getResourceAsString("/controller/TgbotControllerTest_testReceipts.json", getClass());
    TgbotCreateBody body = new TgbotCreateBody();
    body.setUserId("123");
    body.setReceiptJson(receiptJson);
    String bodyString = new ObjectMapper().writeValueAsString(body);

    doAnswer(__ -> {
      Receipt receipt = new Receipt();
      receipt.setId("807");
      return receipt;
    }).when(service).update(any(), any(), anyString());

    mockMvc
      .perform(post("/tgbot/create")
        .content(bodyString)
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(content().string("807"))
      .andExpect(status().isOk());

    ArgumentCaptor<FnsReceiptDto> filterCaptor = ArgumentCaptor.forClass(FnsReceiptDto.class);
    verify(service).update(filterCaptor.capture(), any(), eq(ReceiptProvider.TGBOT_NALOG.name()));

    FnsReceiptDto receipt = filterCaptor.getValue();
    assertEquals(LocalDateTime.of(2020, 2, 29, 16, 51), receipt.getDateTime());
    assertEquals(47900, receipt.getTotalSum());
    assertEquals("9289000100408074", receipt.getFiscalDriveNumber());
    // TODO check other fields

  }

}
