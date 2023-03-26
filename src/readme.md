a) Checking positive and negative response code for GET and POST.
Testing is performed with a swager
1.  Checking for receipt of order data. 
If:
* the order exists (response code 200)
* the order does not exist (response code 400)

2. Checking positive order creation 
3. Checking incorrect request type posting 
4. Checking for status OPEN in the order 

Test case for Api

| No  |                                  Check name                                   | Status | 
|-----|:-----------------------------------------------------------------------------:|:------:|
| 1   |              Positive test for the order. To get Status code 200              |  Pass  |
| 2   |              Negative test for the order. To get Status code 400              |  Pass  |
| 3   | Positive test for the orders with multiple parameters. To get Status code 200 |  Pass  |
| 4   | Negative test for the orders with multiple parameters. To get Status code 400 |  Pass  |
| 5   |      Positive tests with multiple parameters for id that is more then 0       |  Pass  |
| 6   |                 Positive test for order creating. Method POST                 |  Pass  |
| 7   |         Negative test for order creating without header. Method POST          |  Pass  |
| 8   |                 Positive test for getting order status "Open"                 |  Pass  |


Test case for PetStoreApi


| No  |                         Check name                         | Status | 
|-----|:----------------------------------------------------------:|:------:|
| 1   |            Get Response code 200 about an order            |  Pass  |
| 2   | Get  response code 404 about an order that is do not found |  Pass  |
| 3   |     Check that complete status for the order is false.     |  Pass  |
| 4   |           Check that the pet id is more than 0.            |  Pass  |
