-- Modifying Data

-- 1.
INSERT INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance) VALUES (9, 'Spa', 20, 30, 100000, 800);

-- 2.
INSERT INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance) SELECT (SELECT MAX(facid) FROM cd.facilities) + 1, 'Spa', 20, 30, 100000, 800;

-- 3.
UPDATE cd.facilities SET initialoutlay = 10000 WHERE facid = 1;

-- 4.
UPDATE cd.facilities facs
SET
membercost = (SELECT membercost * 1.1 FROM cd.facilities WHERE facid = 0),
guestcost = (SELECT guestcost * 1.1 FROM cd.facilities WHERE facid = 0)
WHERE facs.facid = 1;

-- 5.
DELETE FROM cd.bookings;

-- 6.
DELETE FROM cd.members WHERE memid = 37;


-- Basics

-- 1.
SELECT facid, name, membercost, monthlymaintenance
FROM cd.facilities
WHERE membercost > 0
AND (membercost < monthlymaintenance/50.0);

-- 2.
SELECT * FROM cd.facilities WHERE name like '%Tennis%';

-- 3.
SELECT * FROM cd.facilities WHERE facid in (1,5);

-- 4.
SELECT memid, surname, firstname, joindate FROM cd.members WHERE joindate >= '2012-09-01';

-- 5.
SELECT surname FROM cd.members UNION SELECT name FROM cd.facilities;

-- Join
-- 1.
SELECT bks.starttime
FROM cd.bookings bks
INNER JOIN cd.members mems
ON mems.memid = bks.memid
WHERE mems.firstname='David'
AND mems.surname='Farrell';

-- 2.

-- 3.
-- 4.
-- 5.

-- Aggregation
-- 1.
-- 2.
-- 3.
-- 4.
-- 5.
-- 6.
-- 7.
-- 8.
-- 9.

-- String
-- 1.
-- 2.
-- 3.
