package space.shefer.receipt.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "API health checks")
@RestController
public class HealthController {

  @Operation(
    description = "Health check method. Returns 'pong' string.",
    responses = @ApiResponse(
      responseCode = "200",
      description = "Rest API is up",
      content = @Content(schema = @Schema(
        description = "The `pong` string"
      ))
    )
  )
  @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
  public String ping() {
    return "pong";
  }

}
