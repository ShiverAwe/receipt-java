package com.github.shiverawe.controller;

import com.github.shiverawe.entity.Receipt;
import com.github.shiverawe.repository.ReceiptRepository;
import com.github.shiverawe.util.DateUtil;
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

  private final ReceiptRepository myReceiptRepository;

  @Autowired
  public MainController(ReceiptRepository receiptRepository) {
    this.myReceiptRepository = receiptRepository;
  }

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public Iterable<Receipt> get(String fn, String i, String fp, String t, String s) {
    return myReceiptRepository.findByCredentials(
      fn, fn != null,
      i, i != null,
      fp, fp != null,
      t == null ? null : DateUtil.parseReceiptDate(t), t != null,
      s == null ? null : Double.parseDouble(s), s != null);
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public void create(@RequestBody String data) {
    throw HttpServerErrorException.create(HttpStatus.NOT_IMPLEMENTED, "Not implemented", HttpHeaders.EMPTY, null, null);
  }

  @RequestMapping(value = "/report", method = RequestMethod.PUT)
  public void report(@RequestBody String query) {
    throw HttpServerErrorException.create(HttpStatus.NOT_IMPLEMENTED, "Not implemented", HttpHeaders.EMPTY, null, null);
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
