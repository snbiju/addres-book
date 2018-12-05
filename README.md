**Basic requirement**

`Java 8 or higher`

`Spring boot 2.1.0.RELEASE Apache Maven 4.0.0 Postman or any other REST API Testing tool.git 
`

**To run**

`clone https://github.com/snbiju/address-book.git / download

go to address-book-master

mvn spring-boot:run`

**How many males are in the address book?**

GET Request: 
http://localhost:8080/address/gender/{gender}

**Who is the oldest person in the address book?**
GET Request :
http://localhost:8080/address/older

**How many days older is Bill than Paul?**

GET Request: 
http://localhost:8080/address/name?firstName={person1}&secondName={person2}


**List all Persons in the Address Book**

GET Request : 
http://localhost:8080/address
