Product Description
Let’s say that the product we’re building/selling is auto insurance. Our website collects a few
pieces of basic user information (name, zip code and date-of-birth). Once the user submits this
information, it is sent to the backend. The backend returns a quote that’s displayed to the user.
The backend also has additional API methods for an internal website. You are tasked with
building the backend and need to implement the following API methods.
1. An API method that takes in user information (name, zip code and date-of-birth) and
   returns
   a. A single number which is the auto-liability premium for the next 6 months along
   with a quote id associated with this quote
2. An API method to to lookup a previously issued quote
3. An API method that displays all quotes issued in the last 24 hours. The API method
   should also have an option to filter quotes by a specific zip code or by the quote amount
   being greater or less than a specified number.
4. An API method to compute premiums collected (for the month) from quotes issued so far
   in the current month. Assume that 100% of quotes issued convert to policies right when
   the quote is issued and that pro-rated premium for the month is paid at that time. NOTE:
   A policy from a quote on the 25th of March only pays premiums for 7 days in March.
   Additional considerations
5. Your data-store must be persistent. Please do not use an ORM framework.
6. Add relevant unit/integration tests for the backend service. At least 1 test is required (but
   please use your judgment to decide whether more are necessary).
7. Please feel free to make the following simplifying assumptions
   a. The API can simply be a set of methods that are called with the right information
   b. You’re welcome to use sqlite to simplify setup / testing
   To come up with the premium per-month, use a simple formula based on age
   f(x) = 600 + 0.3*(abs(x - 50))1.5, where x is the age in years.
   Please state any assumptions you’re making, and feel free to reach out if you have any
   questions.

## Run Spring Boot application
```
mvn spring-boot:run

```
Post Request to save the Quotes
http://localhost:8080/api/quotes

    {
        "name": "Partha",
        "zip": "08512",
        "dob":"02-12-1990"
    }

http://localhost:8080/api/quotes - get all the Quotes

http://localhost:8080/api/quotes/{id} get the quotes by quote id

http://localhost:8080/api/quotes/zip/08512?quoteValue=10000&condition=less -- Filter quote by zip which are issues in last 24 hours












