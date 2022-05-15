Bank Account Service

Most of time went to understand REST better (enough to make assignment work).
From required technologies I used Java 11, REST, Gradle and SpringBoot.


Tested with Postman.

Create account:
localhost:8080/create/account
{
    "customerId": 2,
    "country": "EST",
    "currencies": [ "EUR","USD", "SEK", "GBP"]
}

Get Account:
localhost:8080/account/1

Create transaction:
localhost:8080/create/transaction
{
    "amount": 2050.55,
    "currency": "EUR",
    "direction": " IN",
    "description": "Test Description",
    "accountId": 1
}

Get transactions:
localhost:8080/transactions/1

