package space.shefer.receipt.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import space.shefer.receipt.platform.core.entity.UserProfile;
import space.shefer.receipt.platform.core.repository.UserProfileRepository;
import space.shefer.receipt.platform.core.service.UserProfileService;
import space.shefer.receipt.platform.core.util.DateUtil;
import space.shefer.receipt.rest.dto.ReceiptCreateDto;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.rest.service.ReceiptService;
import space.shefer.receipt.tests.util.ResourceUtil;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReceiptCreateControllerTest {

  private MockMvc mockMvc;
  private ReceiptCreateController controller;
  private ReceiptService service;
  private UserProfileService userProfileService;
  private UserProfileRepository userProfileRepository;

  @Before
  public void setUp() {
    service = mock(ReceiptService.class);
    controller = spy(new ReceiptCreateController(service, userProfileService));
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void create() throws Exception {
    UserProfile userProfile = new UserProfile();
    userProfile.setId("bcce81c9-cbf3-4216-819d-70b9e37da6e3");
    userProfile.setPassword("aaaaa");
    userProfile.setPhone("+7999999999");
    userProfile.setAccessToken("6b6c0abf-82cc-40fb-8379-30e9d0e72bc7");
    String body = ResourceUtil.getResourceAsString("/controller/ReceiptCreateControllerTest_create.json", getClass());
    doAnswer(n -> new Receipt()).when(service).create(any(), userProfile);
    mockMvc.perform(post("/create").content(body)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
    ArgumentCaptor<ReceiptCreateDto> createDtoCaptor = ArgumentCaptor.forClass(ReceiptCreateDto.class);
    verify(service, times(1)).create(createDtoCaptor.capture(), userProfile);
    ReceiptCreateDto receiptCreateDto = createDtoCaptor.getValue();
    assertEquals(DateUtil.parseReceiptDate("20190801T1032"), receiptCreateDto.getDate());
    assertEquals(123629, receiptCreateDto.getSum(), 1e-5);
    assertEquals("936933", receiptCreateDto.getFn());
    assertEquals("832555", receiptCreateDto.getFd());
    assertEquals("535594", receiptCreateDto.getFp());
  }

}
