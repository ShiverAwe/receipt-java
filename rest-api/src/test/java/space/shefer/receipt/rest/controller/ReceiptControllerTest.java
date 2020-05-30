package space.shefer.receipt.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.entity.UserProfile;
import space.shefer.receipt.platform.core.service.UserProfileService;
import space.shefer.receipt.platform.core.util.DateUtil;
import space.shefer.receipt.rest.dto.ReceiptCreateDto;
import space.shefer.receipt.rest.service.ReceiptService;
import space.shefer.receipt.tests.util.ResourceUtil;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReceiptControllerTest {

  private MockMvc mockMvc;
  private ReceiptController controller;
  private ReceiptService receiptService;
  private UserProfileService userProfileService;

  @Before
  public void setUp() {
    receiptService = mock(ReceiptService.class);
    userProfileService = mock(UserProfileService.class);
    controller = spy(new ReceiptController(receiptService, userProfileService));
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void create_withoutToken() throws Exception {
    String body = ResourceUtil.getResourceAsString("/controller/ReceiptCreateControllerTest_create.json", getClass());
    doAnswer(n -> new Receipt()).when(receiptService).create(any(), isNull());
    mockMvc.perform(post("/create").content(body)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
    ArgumentCaptor<ReceiptCreateDto> createDtoCaptor = ArgumentCaptor.forClass(ReceiptCreateDto.class);
    verify(receiptService, times(1)).create(createDtoCaptor.capture(), isNull());
    ReceiptCreateDto receiptCreateDto = createDtoCaptor.getValue();
    assertEquals(DateUtil.parseReceiptDate("20190801T1032"), receiptCreateDto.getDate());
    assertEquals(123629, receiptCreateDto.getSum(), 1e-5);
    assertEquals("936933", receiptCreateDto.getFn());
    assertEquals("832555", receiptCreateDto.getFd());
    assertEquals("535594", receiptCreateDto.getFp());
  }

  @Test
  public void create_withToken() throws Exception {
    String testToken = "56hty46eyfh";
    UserProfile userProfile = new UserProfile();
    String body = ResourceUtil.getResourceAsString("/controller/ReceiptCreateControllerTest_create.json", getClass());
    doAnswer(n -> new Receipt()).when(receiptService).create(any(), argThat(it -> it.equals(userProfile)));
    doAnswer(__ -> userProfile).when(userProfileService).getUserByToken(testToken);
    mockMvc.perform(post("/create").content(body)
      .header("Authorization", "Bearer " + testToken)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
    ArgumentCaptor<ReceiptCreateDto> createDtoCaptor = ArgumentCaptor.forClass(ReceiptCreateDto.class);
    verify(receiptService, times(1)).create(createDtoCaptor.capture(), eq(userProfile));
    ReceiptCreateDto receiptCreateDto = createDtoCaptor.getValue();
    assertEquals(DateUtil.parseReceiptDate("20190801T1032"), receiptCreateDto.getDate());
    assertEquals(123629, receiptCreateDto.getSum(), 1e-5);
    assertEquals("936933", receiptCreateDto.getFn());
    assertEquals("832555", receiptCreateDto.getFd());
    assertEquals("535594", receiptCreateDto.getFp());
  }

}
