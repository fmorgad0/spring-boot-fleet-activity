##### spring-boot-fleet-activity #####
## Sample project restAPI with spring-boot and mongoDB ##

Needed tools:
- mongoDB
- jdk 1.8
- maven 3.2+

mongoimport --type csv -d fleetDB -c fleetData --headerline --columnsHaveTypes --drop C:\projects\spring-boot-fleet-activity\database\siri.20130131.csv

http://localhost:8080/fleet/getRunningOperators?startTime=2013-01-30&endTime=2013-01-31
http://localhost:8080/fleet/getVehicleIds?startTime=2013-01-30&endTime=2013-01-31&operator=D1
http://localhost:8080/fleet/getVehicleIdsAtStop?startTime=2013-01-30&endTime=2013-01-31&vehicleIds=33098,33099,33100,33542
http://localhost:8080/fleet/getVehicleTracingData?startTime=2013-01-30&endTime=2013-01-31&vehicleId=33099