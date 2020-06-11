package space.shefer.receipt.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.entity.UserProfile;
import space.shefer.receipt.platform.core.service.UserProfileService;
import space.shefer.receipt.rest.converters.ReceiptMetaConverter;
import space.shefer.receipt.rest.dto.ReceiptCreateDto;
import space.shefer.receipt.rest.dto.ReceiptMetaDto;
import space.shefer.receipt.rest.service.ReceiptService;

import javax.validation.Valid;


@Schema(description = "Managing receipts")
@RestController
@RequiredArgsConstructor
public class ReceiptController {

  private final ReceiptService receiptService;
  private final UserProfileService userProfileService;

  @Operation(
    description = "Submit receipt for loading. If receipt is submitted " +
      "multiple times, then deduplication could be performed.",
    responses = @ApiResponse(responseCode = "200", description = "Receipt has been successfully created")
  )
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ReceiptMetaDto create(@Valid @RequestBody ReceiptCreateDto query,
                               @Nullable @RequestHeader("Authorization") String authHeader) throws ResponseStatusException {
    UserProfile userProfile = null;
    if (authHeader != null) {
      userProfile = userProfileService.getUserByToken(authHeader.substring(authHeader.indexOf(" ") + 1));
    }
    Receipt receipt = receiptService.create(query, userProfile);
    return ReceiptMetaConverter.toDto(receipt);
  }

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
