package space.shefer.receipt.rest.controller;

import space.shefer.receipt.rest.dto.ReceiptDto;
import space.shefer.receipt.rest.service.ReceiptService;
import space.shefer.receipt.rest.service.report.ReportMetaFilter;
import space.shefer.receipt.rest.service.report.ReportReceiptFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Arrays;
import java.util.List;

@RestController
public class MainController {

  private final ReceiptService receiptService;

  @Autowired
  public MainController(ReceiptService receiptService) {
    this.receiptService = receiptService;
  }

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ReceiptDto get(String fn, String i, String fp, String t, Double s) {
    ReportMetaFilter metaFilter = new ReportMetaFilter();
    metaFilter.setSumEquals(s);
    metaFilter.setDateEquals(t);
    metaFilter.setFn(fn);
    metaFilter.setFd(i);
    metaFilter.setFp(fp);
    return receiptService.report(metaFilter).get(0);
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public void create(@RequestBody String data) {
    throw HttpServerErrorException.create(HttpStatus.NOT_IMPLEMENTED, "Not implemented", HttpHeaders.EMPTY, null, null);
  }

  @RequestMapping(value = "/report", method = RequestMethod.PUT)
  public List<ReceiptDto> report(ReportReceiptFilter query) {
    return receiptService.report(query.getMeta());
  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public String signup() {
    throw HttpServerErrorException.create(HttpStatus.NOT_IMPLEMENTED, "Not implemented", HttpHeaders.EMPTY, null, null);
  }

  @RequestMapping(value = "/token", method = RequestMethod.GET)
  public String getAuthToken() {
    throw HttpServerErrorException.create(HttpStatus.NOT_IMPLEMENTED, "Not implemented", HttpHeaders.EMPTY, null, null);
  }

  /**
   * This method allows to search in cheques data by words.
   * `q` is the request param.
   * Multiple words should be joined via `_` (underline).
   * Searching engine can omit too short words.
   * Example: /q=milk_sugar
   */
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public String search(String q) {
    List<String> query = Arrays.asList(q.split("_"));
    throw HttpServerErrorException.create(HttpStatus.NOT_IMPLEMENTED, "Not implemented", HttpHeaders.EMPTY, null, null);
  }

  @RequestMapping(value = "/merchant", method = RequestMethod.GET)
  public String merchantInfo(int inn) {
    throw HttpServerErrorException.create(HttpStatus.NOT_IMPLEMENTED, "Not implemented", HttpHeaders.EMPTY, null, null);
  }

  @RequestMapping("/ping")
  public String ping() {
    return "pong";
  }
}
