DROP table IF EXISTS REGIONS;

DROP table IF EXISTS COUNTRIES;

DROP table IF EXISTS LOCATIONS;

DROP table IF EXISTS DEPARTMENTS;

DROP table IF EXISTS JOBS;

DROP table IF EXISTS EMPLOYEES;

DROP table IF EXISTS JOB_HISTORY;

CREATE TABLE REGIONS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR (25)
);

CREATE TABLE COUNTRIES (
    ID VARCHAR (2) PRIMARY KEY,
    NAME VARCHAR (40),
    REGION_ID BIGINT
);

CREATE TABLE LOCATIONS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    STREET_ADRESS VARCHAR (40),
    POSTAL_CODE VARCHAR (12),
    CITY VARCHAR (30) NOT NULL,
    STATE_PROVINCE VARCHAR (25),
    COUNTRY_ID VARCHAR (2)
);

CREATE TABLE DEPARTMENTS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR (30) NOT NULL,
    MANAGER_ID BIGINT,
    LOCATION_ID BIGINT
);

CREATE TABLE JOBS (
    ID VARCHAR (10) PRIMARY KEY,
    TITLE VARCHAR (35) NOT NULL,
    MIN_SALARY INT,
    MAX_SALARY INT
);

CREATE TABLE EMPLOYEES (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    FIRST_NAME VARCHAR (20),
    LAST_NAME VARCHAR (25) NOT NULL,
    EMAIL VARCHAR (25) NOT NULL,
    PHONE_NUMBER VARCHAR (20),
    HIRE_DATE DATE NOT NULL,
    JOB_ID VARCHAR (10) NOT NULL,
    SALARY DOUBLE (10),
    COMMISSION_PCT DOUBLE (4),
    MANAGER_ID BIGINT,
    DEPARTMENT_ID BIGINT
);

CREATE TABLE JOB_HISTORY (
    EMPLOYE_ID BIGINT,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    JOB_ID VARCHAR (10) NOT NULL,
    DEPARTMENT_ID BIGINT
);

ALTER TABLE COUNTRIES ADD FOREIGN KEY (REGION_ID) REFERENCES REGIONS (ID);

ALTER TABLE LOCATIONS ADD FOREIGN KEY (COUNTRY_ID) REFERENCES COUNTRIES (ID);

ALTER TABLE DEPARTMENTS ADD FOREIGN KEY (LOCATION_ID) REFERENCES LOCATIONS (ID);

ALTER TABLE DEPARTMENTS ADD FOREIGN KEY (MANAGER_ID) REFERENCES EMPLOYEES (ID);

ALTER TABLE EMPLOYEES ADD FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENTS (ID);

ALTER TABLE EMPLOYEES ADD FOREIGN KEY (MANAGER_ID) REFERENCES EMPLOYEES (ID);

ALTER TABLE EMPLOYEES ADD FOREIGN KEY (JOB_ID) REFERENCES JOBS (ID);

ALTER TABLE JOB_HISTORY ADD FOREIGN KEY (JOB_ID) REFERENCES JOBS (ID);

ALTER TABLE JOB_HISTORY ADD FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENTS (ID);

-- -----------------------------------------------------------------------------
-- regions
-- -----------------------------------------------------------------------------
INSERT INTO regions
(ID  , NAME                    ) VALUES
(1   , 'Europe'                ),
(2   , 'America'               ),
(3   , 'Asia'                  ),
(4   , 'Middle East amd Africa');

-- -----------------------------------------------------------------------------
-- COUNTRIES
-- -----------------------------------------------------------------------------
INSERT INTO COUNTRIES
(ID  , NAME                      , REGION_ID) VALUES
('CA', 'Canada'                  , 2        ),
('DE', 'Germany'                 , 1        ),
('UK', 'United Kingdom'          , 1        ),
('US', 'United States of America', 2        );

-- -----------------------------------------------------------------------------
-- LOCATIONS
-- -----------------------------------------------------------------------------
INSERT INTO LOCATIONS
(ID  , STREET_ADRESS                            , POSTAL_CODE , CITY                 , STATE_PROVINCE, COUNTRY_ID) VALUES
(1400, '2014 Jabberwocky Rd'                    , '26192'     , 'Southlake'          , 'Texas'       , 'US'      ),
(1500, '2011 Interiors Blvd'                    , '99236'     , 'South San Francisco', 'Califorina'  , 'US'      ),
(1700, '2004 Charade Rd'                        , '99236'     , 'Seattle'            , 'Washington'  , 'US'      ),
(1800, '460 Bloor St. W.'                       , 'on M5S 1XB', 'Toronto'            , 'Ontario'     , 'CA'      ),
(2500, 'Magdalen Center,The Oxford Science Park', 'OX9 9ZB'   , 'Oxford'             , 'Oxford'      , 'UK'      );

-- -----------------------------------------------------------------------------
-- DEPARTMENTS
-- -----------------------------------------------------------------------------
INSERT INTO DEPARTMENTS
(ID  , NAME           , MANAGER_ID, LOCATION_ID) VALUES
(10  , 'Adminstration', NULL      , 1700       ),
(20  , 'Marketing'    , NULL      , 1800       ),
(50  , 'Shipping'     , NULL      , 1500       ),
(60  , 'IT'           , NULL      , 1400       ),
(80  , 'Sales'        , NULL      , 2500       ),
(90  , 'Executive'    , NULL      , 1700       ),
(110 , 'Accouting'    , NULL      , 1700       ),
(190 , 'Contracting'  , NULL      , 1700       );

-- -----------------------------------------------------------------------------
-- jobs
-- -----------------------------------------------------------------------------
INSERT INTO jobs
(ID          , TITLE                         , MIN_SALARY, MAX_SALARY) VALUES
('AD_PRES'   , 'President'                   , 2000      , 40000     ),
('AD_VP'     , 'Adminstration Vice President', 15000     , 30000     ),
('AD_ASST'   , 'Adminstration Assistant'     , 3000      , 6000      ),
('AC_MGR'    , 'Accounting Manager'          , 8200      , 16000     ),
('AC_ACCOUNT', 'Public Accountant'           , 4200      , 9000      ),
('SA_MAN'    , 'Sales Manager'               , 10000     , 20000     ),
('SA_REP'    , 'Sales Representative'        , 6000      , 12000     ),
('ST_MAN'    , 'Stock Manager'               , 5500      , 8500      ),
('ST_CLERK'  , 'Stock Clerk'                 , 2000      , 5000      ),
('IT_PROG'   , 'Programmer'                  , 400       , 10000     ),
('MK_MAN'    , 'Marketing Manager'           , 9000      , 15000     ),
('MK_REP'    , 'Marketing Representative'    , 4000      , 9000      );

-- -----------------------------------------------------------------------------
-- EMPLOYEES
-- -----------------------------------------------------------------------------
INSERT INTO EMPLOYEES
(ID  , FIRST_NAME , LAST_NAME , EMAIL      , PHONE_NUMBER      , HIRE_DATE   , JOB_ID      , SALARY   , DEPARTMENT_ID, MANAGER_ID, COMMISSION_PCT) VALUES
(100 , 'Steven'   , 'King'    , 'SKING'    , '515.123.4567'    , '1987-06-17', 'AD_PRES'   , 24000    , 90           , NULL      , NULL          ),
(101 , 'Neena'    , 'Kochhar' , 'NKochhar' , '515.123.4568'    , '1989-09-21', 'AD_VP'     , 17000.100, 90           , 100       , NULL          ),
(102 , 'Lex'      , 'De Haaan', 'LDEHAAAN' , '515.123.4569'    , '1993-01-13', 'AD_VP'     , 17000.100, 90           , 100       , NULL          ),
(103 , 'Alexander', 'Hulond'  , 'AHULOND'  , '515.123.4561'    , '1990-01-03', 'IT_PROG'   , 9000.102 , 60           , 102       , NULL          ),
(104 , 'Bruce'    , 'Ernst'   , 'BERNST'   , '515.123.4562'    , '1991-05-21', 'IT_PROG'   , 6000.103 , 60           , 103       , NULL          ),
(107 , 'Diana'    , 'Lorentz' , 'DLORENTZ' , '515.123.4563'    , '1999-02-07', 'IT_PROG'   , 4200.103 , 60           , 103       , NULL          ),
(124 , 'Kevin'    , 'Mourgos' , 'KMOURGOS' , '515.123.4564'    , '1999-11-16', 'ST_MAN'    , 5800.100 , 50           , 100       , NULL          ),
(141 , 'Trenna'   , 'Rajs'    , 'TRAJS'    , '515.123.4565'    , '1995-10-17', 'ST_CLERK'  , 3500.124 , 50           , 124       , NULL          ),
(142 , 'Curtis'   , 'Davies'  , 'CDAVIES'  , '515.123.4566'    , '1997-01-29', 'ST_CLERK'  , 3100.124 , 50           , 124       , NULL          ),
(143 , 'Randall'  , 'Matos'   , 'RMATOS'   , '515.123.4567'    , '1998-03-15', 'ST_CLERK'  , 2600.124 , 50           , 124       , NULL          ),
(144 , 'Peter'    , 'Vargar'  , 'PVARGAR'  , '515.123.4570'    , '1998-06-09', 'ST_CLERK'  , 2500.124 , 50           , 124       , NULL          ),
(149 , 'Eleni'    , 'ZlotkeY' , 'EZLOTKET' , '011.515.123.4571', '2000-01-29', 'SA_MAN'    , 10500    , 80           , 100       , 0.2           ),
(174 , 'Ellen'    , 'Abel'    , 'EABEL'    , '011.515.123.4572', '1996-05-11', 'SA_REP'    , 11000.149, 80           , 149       , 0.3           ),
(176 , 'Jonathon' , 'Taylor'  , 'JTAYLOR'  , '011.515.123.4573', '1998-03-24', 'SA_REP'    , 860.149  , 80           , 149       , 0.2           ),
(178 , 'Kimberely', 'Grant'   , 'KGRANT'   , '011.515.123.4574', '1999-05-24', 'SA_REP'    , 7000     , NULL         , 149       , 0.15          ),
(200 , 'Jennifer' , 'Whalen'  , 'JWHALEN'  , '515.123.4580'    , '1987-09-17', 'AD_ASST'   , 4400     , 10           , 101       , NULL          ),
(201 , 'Michael'  , 'Harstein', 'MHARSTEIN', '515.123.4581'    , '1996-02-17', 'MK_MAN'    , 13000.100, 20           , 100       , NULL          ),
(202 , 'Pat'      , 'Fay'     , 'PFAY'     , '515.123.4582'    , '1997-08-17', 'MK_REP'    , 6000.201 , 20           , 201       , NULL          ),
(205 , 'Shelly'   , 'Higgins' , 'SHIGGINS' , '515.123.4583'    , '1994-06-07', 'AC_MGR'    , 12000    , 110          , 101       , NULL          ),
(206 , 'William'  , 'Gietz'   , 'WGIETZ'   , '515.123.4584'    , '1994-06-07', 'AC_ACCOUNT', 8300     , 110          , 205       , NULL          );

-- -----------------------------------------------------------------------------
-- JOB_HISTORY
-- -----------------------------------------------------------------------------
INSERT INTO JOB_HISTORY
(EMPLOYE_ID, START_DATE  , END_DATE    , JOB_ID      , DEPARTMENT_ID) VALUES
(102       , '1993-01-13', '1998-07-24', 'IT_PROG'   , 60           ),
(101       , '1989-09-21', '1993-10-27', 'AC_ACCOUNT', 110          ),
(101       , '1993-10-28', '1997-03-15', 'AC_MGR'    , 110          ),
(201       , '1996-02-17', '1999-12-19', 'MK_REP'    , 20           ),
(141       , '1998-03-24', '1999-12-31', 'ST_CLERK'  , 50           ),
(124       , '1999-01-01', '1999-12-31', 'ST_CLERK'  , 50           ),
(200       , '1987-09-17', '1993-06-17', 'AD_ASST'   , 90           ),
(176       , '1998-03-24', '1998-12-31', 'SA_REP'    , 80           ),
(176       , '1999-01-01', '1998-12-31', 'SA_MAN'    , 80           ),
(200       , '1994-07-01', '1998-12-31', 'AC_ACCOUNT', 90           );

UPDATE DEPARTMENTS SET MANAGER_ID = 200 WHERE ID = 10;
UPDATE DEPARTMENTS SET MANAGER_ID = 201 WHERE ID = 20;
UPDATE DEPARTMENTS SET MANAGER_ID = 124 WHERE ID = 50;
UPDATE DEPARTMENTS SET MANAGER_ID = 103 WHERE ID = 60;
UPDATE DEPARTMENTS SET MANAGER_ID = 149 WHERE ID = 80;
UPDATE DEPARTMENTS SET MANAGER_ID = 100 WHERE ID = 90;
UPDATE DEPARTMENTS SET MANAGER_ID = 205 WHERE ID = 110;