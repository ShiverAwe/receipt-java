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
Returns header of created receipt
```
{
   "id": "1", //[required] Long
   "date": "2020-05-01 22:35", // [required] LocalDateTime
   "fn": "9282440300557138", // [required] string
   "fd": "7100", // [required] string
   "fp": "159748002", // [required] string
   "sum": "403", // [required] Double
   "provider": "provider", // [required] string
   "status": "LOADED", // [required] ReceiptStatus
   "place": "1" // [required] string
}
```

### Get the receipts
```
PUT /receipts
{
  "ids": [68, 99], // [optional] List of ids of receipts to be fetched
  "dateFrom": "20190131T0059", // [optional] date in format YYYYMMDD'T'HHmm
  "dateTo": "20190501T2359", // [optional] date in format YYYYMMDD'T'HHmm
  "sumMin": "10.0", // [optional] double
  "sumMax": "200.0", // [optional] double
  "fn": "23948523549", // [optional] string
  "fd": "230952", // [optional] string
  "fp": "2345045", // [optional] string
  "place": "Пятерочка", // [optional] string
  "limit": 123, // [optional] integer
  "offset": 12, // [optional] integer
  "sort": "DATE", // [optional] "DATE" (date) or null (id)
  "asc": true, // [optional] boolean
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

### Get items for receipts
```
PUT /items
{
  "receiptIds": [68, 99], // [optional] List of ids of receipts which items only required
  "minPrice": "10.0", // [optional] double
  "maxPrice": "200.0", // [optional] double
  "textEquals": "Молоко" // [optional] string
}
```
```json
[
  {
    "receiptId": 68,
    "text": "Сыр копченый Косичка  Кубански",
    "price": 150.0,
    "amount": 0.159
  },
  {
    "receiptId": 68,
    "text": "Веселый Молочник",
    "price": 120.0,
    "amount": 0.159
  },
  {
    "receiptId": 99,
    "text": "Сыр копченый Косичка  Кубански",
    "price": 150.0,
    "amount": 0.159
  }
]
```
