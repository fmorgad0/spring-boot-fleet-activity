##### spring-boot-fleet-activity #####
## Sample project restAPI with spring-boot and mongoDB ##

Needed tools:
- mongoDB 4+
- jdk 1.8
- maven 3.3+

### MongoDB setup ###
- Create a database named fleetDB
- Navigate to $mongoDirectory\Server\4.2\bin and run the command below (change the directory of the csv file if needed)
# mongoimport --type csv -d fleetDB -c fleetData --headerline --columnsHaveTypes --drop C:\projects\spring-boot-fleet-activity\database\siri.20130131.csv

### Running the application ###
- Installing the project: mvn clean install
- Running the project: mvn spring-boot:run

### API usage and examples ###
- Given a time frame [start-time, end-time], returns the list of running operators
http://localhost:8080/fleet/getRunningOperators?startTime=2013-01-30&endTime=2013-01-31

- Given a time frame [start-time, end-time] and an operator, returns the list of vehicle IDs
http://localhost:8080/fleet/getVehicleIds?startTime=2013-01-30&endTime=2013-01-31&operator=D1

- Given a time frame [start-time, end-time] and a fleet, returns the list of vehicle IDs at a stop
http://localhost:8080/fleet/getVehicleIdsAtStop?startTime=2013-01-30&endTime=2013-01-31&vehicleIds=33098,33099,33100,33542

- Given a time frame [start-time, end-time] and a vehicle, returns its tracing data, orderer by timestamp
http://localhost:8080/fleet/getVehicleTracingData?startTime=2013-01-30&endTime=2013-01-31&vehicleId=33099