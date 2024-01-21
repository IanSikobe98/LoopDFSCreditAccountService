LOOP DFS ACCOUNT AND CARD MICROSERVICE SET UP INFORMATION
----------------------------------------------------------------

1. Set up your postgres Database and ensure your environment has java 8.
2. Create a database with the name postgres and a schema name o loopdfs
3. Run all the queries for table creation and data entry in the init.sql file.
3. Build the application by running ./mvnw clean package at the root folder of the project
4. Run application using java -jar target/LoopDFSCreditAccount-0.0.1-SNAPSHOT.jar at the root folder of the project whist ensuring it is 
   in the same folder as the log4j2-spring.xml file.
5. Alternatively,If you wish to run docker ensure server has openjdk 8 and postgresql
6. Run Database queries in init.sql file.
7. Run command to build docker image: docker build --tag loopdfsdemo
8. Run docker image  using command :docker compose -f docker-compose.yml up --build


