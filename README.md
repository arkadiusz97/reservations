# reservations - recruitment task

# Configure
Before run application, you need to setup database and import to it data from file reservations.sql.

# Build under Windows 
./mvnw clean package

# Test with Curl
Get reservations for object "apartment":
curl http://localhost:8081/getreservationsforobject?objectname=apartment

Get reservations for tenant Jan Kowalski:
curl 'http://localhost:8081/getreservationsfortenant?tenantname=Jan&tenansurname=Kowalski'

Update reservation with id 1: 
curl -X PUT -d 'id=1&tenantid=1&objectforrentid=2&firstday="2022-09-23 00:00:00"&lastday="2022-09-25 00:00:00"' http://localhost:8081/updatereservation

Create a new reservation:
curl -X POST -d 'tenantid=1&objectforrentid=2&firstday="2022-10-23 00:00"&lastday="2022-10-25 00:00"' http://localhost:8081/createreservation