package space.shefer.receipt.rest.controller;

import space.shefer.receipt.rest.service.ReceiptService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class MainControllerTest {

  private MockMvc mockMvc;
  private MainController controller;
  private ReceiptService service;

  @Before
  public void setUp() throws Exception {
    service = mock(ReceiptService.class);
    controller = spy(new MainController(service));
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void name() throws Exception {
    String body = "{\"\"}";
    mockMvc.perform(post("/report").content(body))
      .andExpect(status().isOk());
  }
}