CASE WHEN EXISTS(USERS) = TRUE
	THEN INSERT INTO USERS VALUES (1, 'mkyong', 'mkyong@gmail.com');
INSERT INTO USERS VALUES (2, 'alex', 'alex@yahoo.com');
INSERT INTO USERS VALUES (3, 'joel', 'joel@gmail.com');

