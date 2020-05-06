# EDealer Interview Challenge

## Introduction
Welcome to the EDealer Interview Challenge. Please carefully read the instructions and complete the assigned tasks.
You can modify any file in the project unless otherwise stated. Commit your changes to a new branch in the git repository
but do not push to a remote repository.

When finished, compress the whole project into a ZIP file and send it to `vojtech@eblock.ca`.

Good luck!

## Quickstart

1. Execute `mvn spring-boot:run` in the root of the project.
2. Open <http://localhost:8080>.
3. Try executing this query: `{ hello }`. You should see `World!` in the response body.
4. Open <http://localhost:8080/h2-console>; set `JDBC URL` to `jdbc:h2:mem:edealer`.
5. Click `Connect` and explore the database schema.
    
## Challenge

Complete the following tasks:

1. Add a new field `year: Int!` to the `Car` type which stores the year when the car was manufactured. Start by editing
   `src/main/resources/schema.graphqls` and then update all layers as necessary.  
2. Handle these potential invalid inputs in the `carCreate` mutation:
   - invalid model ID
   - year out of range (1900-2020)
3. Create a new class `CarListQuery` and add a query handler that returns a list of cars: 
   ```graphql
   type Query {
     carList(
       limit: Int! 
       sortBy: [String!]!
       ascending: Boolean! = true
       color: Color
     ): [Car!]
   }
   ```
   - results are filtered by `color` unless the parameter is `null` or omitted
   - results are sorted by `year` and/or `color` as specified by the non-empty `sortBy` array in the direction specified by `ascending`
   - results can have up to `limit` of elements
4. Add a new type called `Customer` and a way to query and create it:
   ```graphql
   type Customer { 
     id: ID!
     firstName: String 
     lastName: String!
     # list of cars that the customer is interested in
     carsInterested: [Car!]!
   }
   
   type Query {
     customer(id: ID!): Customer
   }
   
   type Mutation {
     customerCreate(
       firstName: String
       lastName: String!
       carsInterestedIds: [ID!]!
     ): Customer
   }
   ``` 
   - use `V03` migration to add sample data
   - implement type resolver `GraphQLResolver<Customer>` to resolve the `carsInterested` field
   - add tests to file `CustomerIT` similar to those in `CarIT`

## Bonus challenge
Currently, an invalid car record can be created where a car has a model that does not belong to its make,
(for example the car with ID `bcc1da6d-393a-4232-870f-80a759ac4fe1`). Think about how could you prevent this from happening and
how would you fix the existing invalid records.

## Evaluation criteria
- completeness of assigned tasks 
- test implementation
- code style and consistency with the existing codebase
- use of git
