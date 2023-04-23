-- Modifying Data

-- 1.
INSERT INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
VALUES (9, 'Spa', 20, 30, 100000, 800);

-- 2.
INSERT INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
SELECT (SELECT MAX(facid) FROM cd.facilities) + 1, 'Spa', 20, 30, 100000, 800;

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
SELECT bks.starttime as start, facs.name
AS name
FROM cd.facilities facs
INNER JOIN cd.bookings bks
ON facs.facid = bks.facid
WHERE facs.name in ('Tennis Court 2','Tennis Court 1')
AND bks.starttime >= '2012-09-21'
AND bks.starttime < '2012-09-22'
ORDER BY bks.starttime;

-- 3.
SELECT mems.firstname
AS memfname, mems.surname
AS memsname, recs.firstname
AS recfname, recs.surname
AS recsname
FROM cd.members mems
LEFT OUTER JOIN cd.members recs
ON recs.memid = mems.recommendedby
ORDER BY memsname, memfname;

-- 4.
SELECT DISTINCT recs.firstname
AS firstname, recs.surname
AS surname
FROM cd.members mems
INNER JOIN cd.members recs
ON recs.memid = mems.recommendedby
ORDER BY surname, firstname;

-- 5.
SELECT DISTINCT mems.firstname || ' ' ||  mems.surname
AS member, (SELECT recs.firstname || ' ' || recs.surname
			AS recommender
			FROM cd.members recs
			WHERE recs.memid = mems.recommendedby)
FROM cd.members mems
ORDER BY member;


-- Aggregation

-- 1.
SELECT recommendedby,
COUNT(*)
FROM cd.members
WHERE recommendedby
IS NOT null
GROUP BY recommendedby
ORDER BY recommendedby;

-- 2.
SELECT facid, SUM(slots)
AS "Total Slots"
FROM cd.bookings
GROUP BY facid
ORDER BY facid;

-- 3.
SELECT facid, SUM(slots)
AS "Total Slots"
FROM cd.bookings
WHERE starttime >= '2012-09-01'
AND starttime < '2012-10-01'
GROUP BY facid
ORDER BY SUM(slots);

-- 4.
SELECT facid,
EXTRACT(month FROM starttime)
AS month, sum(slots)
AS "Total Slots"
FROM cd.bookings
WHERE EXTRACT(year FROM starttime) = 2012
GROUP BY facid, month
ORDER BY facid, month;

-- 5.
SELECT COUNT(DISTINCT memid) FROM cd.bookings;

-- 6.
SELECT mems.surname, mems.firstname, mems.memid, MIN(bks.starttime)
AS starttime
FROM cd.bookings bks
INNER JOIN cd.members mems
ON mems.memid = bks.memid
WHERE starttime >= '2012-09-01'
GROUP BY mems.surname, mems.firstname, mems.memid
ORDER BY mems.memid;

-- 7.
SELECT COUNT(*) OVER(), firstname, surname FROM cd.members ORDER BY joindate;

-- 8.
SELECT ROW_NUMBER()
OVER(ORDER BY joindate), firstname, surname
FROM cd.members
ORDER BY joindate;

-- 9.
SELECT facid, total
FROM (SELECT facid, SUM(slots) total, RANK()
OVER (ORDER BY SUM(slots) DESC) rank FROM cd.bookings GROUP BY facid)
AS ranked
WHERE rank = 1;


-- String

-- 1.
SELECT surname || ', ' || firstname AS name FROM cd.members;

-- 2.
SELECT memid, telephone FROM cd.members WHERE telephone ~ '[()]';

-- 3.
SELECT substr (mems.surname,1,1)
AS letter, COUNT(*)
AS count
FROM cd.members mems
GROUP BY letter
ORDER BY letter;
