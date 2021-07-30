
Download and unzip the source repository, or clone it using Git:
git clone https://github.com/easunhu/shop.git
cd into shop.authorization and run "mvnw spring-boot:run", wait until the server is running, then cd into shop and run "mvnw spring-boot:run"

Now both authorization and resource server are running, call the service with curl(or postman) as below:

test 1.1) get read token, please update it to below token place>
curl -X POST --user shop-client:secret http://localhost:9000/oauth2/token -d "grant_type=client_credentials&scope=shop:read"

test 1.2) get all items>
curl -X GET http://localhost:8080/api/items/ -H "authorization: Bearer eyJraWQiOiI5OGU1YzQ2Yi1hMGFmLTQ0ZmItYjdlMi05Y2EzODllYTY3NTQiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzaG9wLWNsaWVudCIsImF1ZCI6InNob3AtY2xpZW50IiwibmJmIjoxNjI3NjQwMzE2LCJzY29wZSI6WyJzaG9wOnJlYWQiXSwiaXNzIjoiaHR0cDpcL1wvbG9jYWxob3N0OjkwMDAiLCJleHAiOjE2Mjc2NDA2MTYsImlhdCI6MTYyNzY0MDMxNiwianRpIjoiZTU2NjY3NWYtNDBmNi00M2U2LTg1Y2MtNTY3MDcwNWUxZTI5In0.WP_xA3gWVrknDHODpyQ1YKakv3ZceXs5gRm8Ek0hR4N2rG9KpEg6g1jI_bgKV0MQbt-yvrCdbRcoqHBRjZH4w7Iay5kSYSF91ySQlOxZUE-cF5QzIOi6IGmlexVKlWCiCxywi2npC4wJbSzOLU486AeUjuxoxiVAlR4chX1QjWONCu9XjU-v0vb5WYArNZqOWu6pXVMGfYoq85hoHakRHGQCOcXVUIZdi_w8fWNZAnkqfdaV6q42Dgmpr2aQGXkzI3_wHBUflxpinzuPyx5PF-_vGw97sUxMJI2oNKGDbJ6XOPysdWx0tx6t4dLJEHWmkwjvOKs2-Vf2Jcp9dfpaKQ"

test 1.3) get item by id, do more than 10 times for same id will see the price change from 100 to 110>
curl -X GET http://localhost:8080/api/items/1 -H "authorization: Bearer eyJraWQiOiI5OGU1YzQ2Yi1hMGFmLTQ0ZmItYjdlMi05Y2EzODllYTY3NTQiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzaG9wLWNsaWVudCIsImF1ZCI6InNob3AtY2xpZW50IiwibmJmIjoxNjI3NjQwMzE2LCJzY29wZSI6WyJzaG9wOnJlYWQiXSwiaXNzIjoiaHR0cDpcL1wvbG9jYWxob3N0OjkwMDAiLCJleHAiOjE2Mjc2NDA2MTYsImlhdCI6MTYyNzY0MDMxNiwianRpIjoiZTU2NjY3NWYtNDBmNi00M2U2LTg1Y2MtNTY3MDcwNWUxZTI5In0.WP_xA3gWVrknDHODpyQ1YKakv3ZceXs5gRm8Ek0hR4N2rG9KpEg6g1jI_bgKV0MQbt-yvrCdbRcoqHBRjZH4w7Iay5kSYSF91ySQlOxZUE-cF5QzIOi6IGmlexVKlWCiCxywi2npC4wJbSzOLU486AeUjuxoxiVAlR4chX1QjWONCu9XjU-v0vb5WYArNZqOWu6pXVMGfYoq85hoHakRHGQCOcXVUIZdi_w8fWNZAnkqfdaV6q42Dgmpr2aQGXkzI3_wHBUflxpinzuPyx5PF-_vGw97sUxMJI2oNKGDbJ6XOPysdWx0tx6t4dLJEHWmkwjvOKs2-Vf2Jcp9dfpaKQ"

test 2.1) get all visits>
curl -X GET http://localhost:8080/api/visits/ -H "authorization: Bearer eyJraWQiOiI5OGU1YzQ2Yi1hMGFmLTQ0ZmItYjdlMi05Y2EzODllYTY3NTQiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzaG9wLWNsaWVudCIsImF1ZCI6InNob3AtY2xpZW50IiwibmJmIjoxNjI3NjQwMzE2LCJzY29wZSI6WyJzaG9wOnJlYWQiXSwiaXNzIjoiaHR0cDpcL1wvbG9jYWxob3N0OjkwMDAiLCJleHAiOjE2Mjc2NDA2MTYsImlhdCI6MTYyNzY0MDMxNiwianRpIjoiZTU2NjY3NWYtNDBmNi00M2U2LTg1Y2MtNTY3MDcwNWUxZTI5In0.WP_xA3gWVrknDHODpyQ1YKakv3ZceXs5gRm8Ek0hR4N2rG9KpEg6g1jI_bgKV0MQbt-yvrCdbRcoqHBRjZH4w7Iay5kSYSF91ySQlOxZUE-cF5QzIOi6IGmlexVKlWCiCxywi2npC4wJbSzOLU486AeUjuxoxiVAlR4chX1QjWONCu9XjU-v0vb5WYArNZqOWu6pXVMGfYoq85hoHakRHGQCOcXVUIZdi_w8fWNZAnkqfdaV6q42Dgmpr2aQGXkzI3_wHBUflxpinzuPyx5PF-_vGw97sUxMJI2oNKGDbJ6XOPysdWx0tx6t4dLJEHWmkwjvOKs2-Vf2Jcp9dfpaKQ"

test 2.2) get visit by id>
curl -X GET http://localhost:8080/api/visits/1 -H "authorization: Bearer eyJraWQiOiI5OGU1YzQ2Yi1hMGFmLTQ0ZmItYjdlMi05Y2EzODllYTY3NTQiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzaG9wLWNsaWVudCIsImF1ZCI6InNob3AtY2xpZW50IiwibmJmIjoxNjI3NjQwMzE2LCJzY29wZSI6WyJzaG9wOnJlYWQiXSwiaXNzIjoiaHR0cDpcL1wvbG9jYWxob3N0OjkwMDAiLCJleHAiOjE2Mjc2NDA2MTYsImlhdCI6MTYyNzY0MDMxNiwianRpIjoiZTU2NjY3NWYtNDBmNi00M2U2LTg1Y2MtNTY3MDcwNWUxZTI5In0.WP_xA3gWVrknDHODpyQ1YKakv3ZceXs5gRm8Ek0hR4N2rG9KpEg6g1jI_bgKV0MQbt-yvrCdbRcoqHBRjZH4w7Iay5kSYSF91ySQlOxZUE-cF5QzIOi6IGmlexVKlWCiCxywi2npC4wJbSzOLU486AeUjuxoxiVAlR4chX1QjWONCu9XjU-v0vb5WYArNZqOWu6pXVMGfYoq85hoHakRHGQCOcXVUIZdi_w8fWNZAnkqfdaV6q42Dgmpr2aQGXkzI3_wHBUflxpinzuPyx5PF-_vGw97sUxMJI2oNKGDbJ6XOPysdWx0tx6t4dLJEHWmkwjvOKs2-Vf2Jcp9dfpaKQ"

test 2.3) get visits by itemId>
curl -X GET http://localhost:8080/api/visits/itemId/1 -H "authorization: Bearer eyJraWQiOiI5OGU1YzQ2Yi1hMGFmLTQ0ZmItYjdlMi05Y2EzODllYTY3NTQiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzaG9wLWNsaWVudCIsImF1ZCI6InNob3AtY2xpZW50IiwibmJmIjoxNjI3NjQwMzE2LCJzY29wZSI6WyJzaG9wOnJlYWQiXSwiaXNzIjoiaHR0cDpcL1wvbG9jYWxob3N0OjkwMDAiLCJleHAiOjE2Mjc2NDA2MTYsImlhdCI6MTYyNzY0MDMxNiwianRpIjoiZTU2NjY3NWYtNDBmNi00M2U2LTg1Y2MtNTY3MDcwNWUxZTI5In0.WP_xA3gWVrknDHODpyQ1YKakv3ZceXs5gRm8Ek0hR4N2rG9KpEg6g1jI_bgKV0MQbt-yvrCdbRcoqHBRjZH4w7Iay5kSYSF91ySQlOxZUE-cF5QzIOi6IGmlexVKlWCiCxywi2npC4wJbSzOLU486AeUjuxoxiVAlR4chX1QjWONCu9XjU-v0vb5WYArNZqOWu6pXVMGfYoq85hoHakRHGQCOcXVUIZdi_w8fWNZAnkqfdaV6q42Dgmpr2aQGXkzI3_wHBUflxpinzuPyx5PF-_vGw97sUxMJI2oNKGDbJ6XOPysdWx0tx6t4dLJEHWmkwjvOKs2-Vf2Jcp9dfpaKQ"

test 3.1) get write token, please update it to below token place>
curl -X POST --user shop-client:secret http://localhost:9000/oauth2/token -d "grant_type=client_credentials&scope=shop:write"

test 3.2) create purchase use purchase-itemid-1.json>
curl -X POST --header "Content-Type: application/json" http://localhost:8080/api/purchases/createPurchase -H "authorization: Bearer eyJraWQiOiI5OGU1YzQ2Yi1hMGFmLTQ0ZmItYjdlMi05Y2EzODllYTY3NTQiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzaG9wLWNsaWVudCIsImF1ZCI6InNob3AtY2xpZW50IiwibmJmIjoxNjI3NjQwNDQ0LCJzY29wZSI6WyJzaG9wOndyaXRlIl0sImlzcyI6Imh0dHA6XC9cL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNjI3NjQwNzQ0LCJpYXQiOjE2Mjc2NDA0NDQsImp0aSI6IjQxMmVmYmM0LWI0MzAtNGNmZS05YTljLTBhMTZlMmFkNWQ4NSJ9.VjEKEuEdx8jsENLDWLWPNeuv0lu_2gDwXiL_WWK6AcodaGjDcFYbgVdAxzPjmFCokONRDOVr7pjNUcVPDvceX-UOOf1ISas1k4nf4l5tkIxiKm-bJJ4yeVv-OUHPTU7zfVidiMXgaT0vMDwDKP0Xg_KAumWgYwf37PX2ybsrfVLVlLqGoqGDkQBRuuU0n46oLuMjwtzMcgyYztviVFP6ivUJImPJQFxk21Vk2-gWuRog1LAhrAwLe01EsCacaRhfFcUuc62y1xMl_jJ556NWBToOufJPTU3b5nU1GjpiA618TvOfW3-lG8yRt6ntNdK4TELIZiWobguzExieoNf8IA" -d @purchase-itemid-1.json

test 3.3) create purchase use purchase-all.json, repeat about 10 times will see item out of order message>
curl -X POST --header "Content-Type: application/json" http://localhost:8080/api/purchases/createPurchase -H "authorization: Bearer eyJraWQiOiI5OGU1YzQ2Yi1hMGFmLTQ0ZmItYjdlMi05Y2EzODllYTY3NTQiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzaG9wLWNsaWVudCIsImF1ZCI6InNob3AtY2xpZW50IiwibmJmIjoxNjI3NjQwNDQ0LCJzY29wZSI6WyJzaG9wOndyaXRlIl0sImlzcyI6Imh0dHA6XC9cL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNjI3NjQwNzQ0LCJpYXQiOjE2Mjc2NDA0NDQsImp0aSI6IjQxMmVmYmM0LWI0MzAtNGNmZS05YTljLTBhMTZlMmFkNWQ4NSJ9.VjEKEuEdx8jsENLDWLWPNeuv0lu_2gDwXiL_WWK6AcodaGjDcFYbgVdAxzPjmFCokONRDOVr7pjNUcVPDvceX-UOOf1ISas1k4nf4l5tkIxiKm-bJJ4yeVv-OUHPTU7zfVidiMXgaT0vMDwDKP0Xg_KAumWgYwf37PX2ybsrfVLVlLqGoqGDkQBRuuU0n46oLuMjwtzMcgyYztviVFP6ivUJImPJQFxk21Vk2-gWuRog1LAhrAwLe01EsCacaRhfFcUuc62y1xMl_jJ556NWBToOufJPTU3b5nU1GjpiA618TvOfW3-lG8yRt6ntNdK4TELIZiWobguzExieoNf8IA" -d @purchase-all.json

test 4) use http://localhost:8080/h2-ui to check the database>

Architect and Design:

This shop use Spring Data JPA + Hibernate + H2 In-Memory Database as the data persistence,
by adding the visit table to record user view history, for log and price surge purpose,
by adding the purchase and purchaseItem tables to implement the purchase order and check inventory functions.

The shop's apis are secured and authorized by the Resource Server and the Authorization Server,
the Resource Server are configured at http://localhost:8080, while the Authorization Server are
configured at http://localhost:9000, jwt token are used for each api call.

The application is built with latest version of Spring Boot 2.6.0 (SNAPSHOT), running with Java 11(works for Java 8 to Java 15 as well).

Here are the checklists to the requirements:

API requirements 
-	Retrieve the current inventory (i.e. list of items) - **implement findAllItems, findItemById etc.**
-	Buy an item (user must be authenticated) - **implement create purchase.**

A couple questions to consider: 
-	How do we know if a user is authenticated? - **user need to call authorization server to get a token, every call to resource server apis need verify the token(not expired, with right permit)**
-	Is it always possible to buy an item? - **add a remainning column to item table to track the inventory, block the purchase and display out-of-stock message when item quantity not enough to fulfill the order.**

Deliverables 
1.	A runnable system with instructions on how to build/run your application - **README.me**
a.	The application should be built using Java and the Spring framework - **Jave 11 with Spring boot 2.6.0 Snapshot**
b.	The application should be able to be run from the command line without any dependencies or database’s required to run your application.  (Other than maven, gradle and Java) - **mvnw spring-boot:run**
2.	A system that can process the two API requests via HTTP - **can be cal or test with curl or postman**
3.	Appropriate tests (unit, integration etc) - **yes**
4.	A quick explanation of: 
a.	Your application, how you set it up, how it was built, how you designed the surge pricing and the type of architecture chosen. - **use https://start.spring.io/ to generate a skeleton, create the dto, service and controller layers and corresponding class, implement the surge pricing by adding a visit table to record the item visit history, with easy implementation and extention.**
b.	Choice of data format. Include one example of a request and response. - **json**
c.	What authentication mechanism was chosen, and why? - **spring security, spring authorization server and resource server, as the latest and most secured api service.**
