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


UI check list


| No  |                             Check name                             | Status | 
|-----|:------------------------------------------------------------------:|:------:|
| 1   |       Login with correct credentials(correct login/password)       |  Pass  |
| 2   |     Incorrect credentials Pop-up (login with incorrect login)      |  Pass  |
| 3   |    Incorrect credentials Pop-up (login with incorrect password)    |  Pass  |
| 4   | Incorrect credentials Pop-up (login with incorrect login/password) |  Pass  |
| 5   |            "Incorrect credentials" Pop-up close button             |  Pass  |
| 6   |                    Page translation to Russian                     |  Pass  |
| 7   |                    Page translation to English                     |  Pass  |
| 8   |         Check minimum characters for login(2), password(8)         |  Pass  |
| 9   |          Check max characters for login(30), password(30)          |  Pass  |



Table for Xpath. Web page http://51.250.6.164:3000/signin


| No  |                              Component name                              |                             Xpath                              | 
|-----|:------------------------------------------------------------------------:|:--------------------------------------------------------------:|
| 1   |                             Field for login                              |             //input[@data-name ='username-input']              |
| 2   |                            Field for password                            |             //input[@data-name ='password-input']              |
| 3   |                              Sign in button                              |             //button[@data-name ='signIn-button']              |
| 4   |                    Button to switch the language - EN                    |           //button[@class="language__button false"]            |
| 5   |                    Button to switch the language - RU                    |  //button[@class="language__button language__button_active"]   |
| 6   | An error for cases of insufficient number of characters for the password |         //span[@class="form-error form-error_active"]          |
| 7   |  An error for cases of insufficient number of characters for the login   |             //span[@class="form-error undefined"]              |
| 8   |                       Incorrect credentials POP-UP                       |         //div[@data-name = "authorizationError-popup"]         |
| 9   |                           POP-UP close button                            | //button[@data-name = "authorizationError-popup-close-button"] |
| 10  |                               POP-UP text                                |              //span[@class="error-popup__title"]               |