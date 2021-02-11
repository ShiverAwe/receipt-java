package space.shefer.receipt.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.shefer.receipt.rest.service.ReceiptService;

@Tag(name = "Receipts management")
@RestController
@RequiredArgsConstructor
public class ReceiptController {

  private final ReceiptService receiptService;

  @Operation(
    description = "Allows remove receipt if it is stuck in loading",
    responses = {
      @ApiResponse(responseCode = "200", description = "Receipt has been deleted or not found"),
      @ApiResponse(responseCode = "400", description = "Receipt already loaded")
    }
  )
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public void delete(
    @Parameter(description = "Receipt identifier", required = true)
    @RequestParam long id
  ) {
    receiptService.deleteReceipt(id);
  }

}
