package com.github.shiverawe.controller;
import json;
import urllib;
  from typing import Dict, List;
  from urllib.error import URLError

  from src.dao.models import Merchant



public class Merchant_Inform {


  void get_merchant_from_prima_inform_or_none(String inn) {
    try:
    Object response_json = urllib.request.urlopen("https://www.prima-inform.ru/utils/search?query=" + inn).read();
    except URLError as e:
    print("WARNING: Counld not connect to www.prima-inform.ru:", e)
    return None
    response:
    Dict[str, List[dict]] =json.loads(response_json)
    suggestions = response.get("suggestions")
    rows_with_matching_inn:
    list = [{
      "inn":n.get("data").get("inn"),
        "name":n.get("value"),
        "address":n.get("data").get("address")
    } for n in suggestions if inn == n.get("data").get("inn")]
    if len(rows_with_matching_inn) == 0:
    return None
    else:
    merchant_row = rows_with_matching_inn[0]
    return Merchant( **merchant_row)
  }
}
