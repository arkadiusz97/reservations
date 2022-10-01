/*Number of reservations and total days of reservations for object.*/
SELECT objectForRentId, COUNT(objectForRentId) as numberOfReservations, SUM(DATEDIFF(DATE_ADD(lastDay,INTERVAL 1 DAY), firstDay)) as totalDays FROM reservations WHERE firstDay > "2022-08-01 00:00:00" AND lastDay < "2022-11-01 00:00:00" GROUP BY objectForRentId; 

/*Number of reservations for every lessor with cost of reservation for every project.*/
SELECT objectsforrent.lessorId, objectForRentId, COUNT(objectForRentId) as numberOfReservations, SUM(DATEDIFF(DATE_ADD(lastDay,INTERVAL 1 DAY), firstDay)) * objectsforrent.unitPrice * objectsforrent.surface as costOfReservations FROM reservations INNER JOIN objectsforrent ON objectForRentId = objectsforrent.id WHERE firstDay > "2022-08-01 00:00:00" AND lastDay < "2022-11-01 00:00:00" GROUP BY objectForRentId; 
