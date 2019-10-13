# receipt-java

[![Build Status](https://travis-ci.com/receipt-project/receipt-java.svg?branch=master)](https://travis-ci.com/receipt-project/receipt-java)

### Submit the receipt
```
POST /create
{
    "date": "20190813T2345", // [required] date in format YYYYMMDD'T'HHmm
    "fn": "1234567890", // [required] string 
    "fd": "12345", // [required] string 
    "fp": "12345678", // [required] string 
    "sum": "123.45", // [required] float 
}
```
Returns Id of created receipt
```json
123
```

### Get the receipts
```
PUT /receipts
{
  "ids": [68, 99], // [optional] List of ids of receipts to be fetched
  "dateFrom": "20190131T0059", //  date in format YYYYMMDD'T'HHmm
  "dateTo": "20190501T2359", //  date in format YYYYMMDD'T'HHmm
  "sumMin": "10.0", //  souble
  "sumMax": "200.0", //  souble
  "fn": "23948523549", //  string
  "fd": "230952", //  string
  "fp": "2345045", //  string
  "place": "Пятерочка" //  string
}
```
```json
[
  {
    "id": 6,
    "date": "2017-06-05T18:01:08",
    "fn": "871000234283939",
    "fd": "8532489",
    "fp": "280852498",
    "sum": 1740.0,
    "provider": "TAXCOM",
    "status": "LOADED",
    "place": "ООО \"О'КЕЙ\""
  }
]
```

