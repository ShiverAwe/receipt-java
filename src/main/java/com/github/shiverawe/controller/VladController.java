package com.github.shiverawe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("vlad")
@RestController
public class VladController {

  @RequestMapping
  public String hello(@RequestParam String name) {
    if (name == null) {
      return "Hello user";
    } else {
      return "Hello " + name;
    }
  }
}
