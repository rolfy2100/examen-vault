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
(1   , '2014 Jabberwocky Rd'                    , '26192'     , 'Southlake'          , 'Texas'       , 'US'      ),
(2   , '2011 Interiors Blvd'                    , '99236'     , 'South San Francisco', 'Califorina'  , 'US'      ),
(3   , '2004 Charade Rd'                        , '99236'     , 'Seattle'            , 'Washington'  , 'US'      ),
(4   , '460 Bloor St. W.'                       , 'on M5S 1XB', 'Toronto'            , 'Ontario'     , 'CA'      ),
(5   , 'Magdalen Center,The Oxford Science Park', 'OX9 9ZB'   , 'Oxford'             , 'Oxford'      , 'UK'      );

-- -----------------------------------------------------------------------------
-- DEPARTMENTS
-- -----------------------------------------------------------------------------
INSERT INTO DEPARTMENTS
(ID  , NAME           , MANAGER_ID, LOCATION_ID) VALUES
(1   , 'Adminstration', NULL      , 3          ),
(2   , 'Marketing'    , NULL      , 4          ),
(3   , 'Shipping'     , NULL      , 2          ),
(4   , 'IT'           , NULL      , 1          ),
(5   , 'Sales'        , NULL      , 5          ),
(6   , 'Executive'    , NULL      , 3          ),
(7   , 'Accouting'    , NULL      , 3          ),
(190 , 'Contracting'  , NULL      , 3          );

-- -----------------------------------------------------------------------------
-- EMPLOYEES
-- -----------------------------------------------------------------------------
INSERT INTO EMPLOYEES
(ID  , FIRST_NAME , LAST_NAME , EMAIL      , PHONE_NUMBER      , HIRE_DATE   , JOB_ID      , SALARY   , DEPARTMENT_ID, MANAGER_ID, COMMISSION_PCT) VALUES
(1   , 'Steven'   , 'King'    , 'SKING'    , '515.123.4567'    , '1987-06-17', 'AD_PRES'   , 24000    , 6            , NULL      , NULL          ),
(2   , 'Neena'    , 'Kochhar' , 'NKochhar' , '515.123.4568'    , '1989-09-21', 'AD_VP'     , 17000.100, 6            , 1         , NULL          ),
(3   , 'Lex'      , 'De Haaan', 'LDEHAAAN' , '515.123.4569'    , '1993-01-13', 'AD_VP'     , 17000.100, 6            , 1         , NULL          ),
(4   , 'Alexander', 'Hulond'  , 'AHULOND'  , '515.123.4561'    , '1990-01-03', 'IT_PROG'   , 9000.102 , 4            , 3         , NULL          ),
(5   , 'Bruce'    , 'Ernst'   , 'BERNST'   , '515.123.4562'    , '1991-05-21', 'IT_PROG'   , 6000.103 , 4            , 4         , NULL          ),
(6   , 'Diana'    , 'Lorentz' , 'DLORENTZ' , '515.123.4563'    , '1999-02-07', 'IT_PROG'   , 4200.103 , 4            , 4         , NULL          ),
(7   , 'Kevin'    , 'Mourgos' , 'KMOURGOS' , '515.123.4564'    , '1999-11-16', 'ST_MAN'    , 5800.100 , 3            , 1         , NULL          ),
(8   , 'Trenna'   , 'Rajs'    , 'TRAJS'    , '515.123.4565'    , '1995-10-17', 'ST_CLERK'  , 3500.124 , 3            , 7         , NULL          ),
(9   , 'Curtis'   , 'Davies'  , 'CDAVIES'  , '515.123.4566'    , '1997-01-29', 'ST_CLERK'  , 3100.124 , 3            , 7         , NULL          ),
(10  , 'Randall'  , 'Matos'   , 'RMATOS'   , '515.123.4567'    , '1998-03-15', 'ST_CLERK'  , 2600.124 , 3            , 7         , NULL          ),
(11  , 'Peter'    , 'Vargar'  , 'PVARGAR'  , '515.123.4570'    , '1998-06-09', 'ST_CLERK'  , 2500.124 , 3            , 7         , NULL          ),
(12  , 'Eleni'    , 'ZlotkeY' , 'EZLOTKET' , '011.515.123.4571', '2000-01-29', 'SA_MAN'    , 10500    , 5            , 1         , 0.2           ),
(13  , 'Ellen'    , 'Abel'    , 'EABEL'    , '011.515.123.4572', '1996-05-11', 'SA_REP'    , 11000.149, 5            , 12        , 0.3           ),
(14  , 'Jonathon' , 'Taylor'  , 'JTAYLOR'  , '011.515.123.4573', '1998-03-24', 'SA_REP'    , 860.149  , 5            , 12        , 0.2           ),
(15  , 'Kimberely', 'Grant'   , 'KGRANT'   , '011.515.123.4574', '1999-05-24', 'SA_REP'    , 7000     , NULL         , 12        , 0.15          ),
(16  , 'Jennifer' , 'Whalen'  , 'JWHALEN'  , '515.123.4580'    , '1987-09-17', 'AD_ASST'   , 4400     , 1            , 2         , NULL          ),
(17  , 'Michael'  , 'Harstein', 'MHARSTEIN', '515.123.4581'    , '1996-02-17', 'MK_MAN'    , 13000.100, 2            , 1         , NULL          ),
(18  , 'Pat'      , 'Fay'     , 'PFAY'     , '515.123.4582'    , '1997-08-17', 'MK_REP'    , 6000.201 , 2            , 17        , NULL          ),
(19  , 'Shelly'   , 'Higgins' , 'SHIGGINS' , '515.123.4583'    , '1994-06-07', 'AC_MGR'    , 12000    , 7            , 2         , NULL          ),
(20  , 'William'  , 'Gietz'   , 'WGIETZ'   , '515.123.4584'    , '1994-06-07', 'AC_ACCOUNT', 8300     , 7            , 19        , NULL          );

-- -----------------------------------------------------------------------------
-- JOB_HISTORY
-- -----------------------------------------------------------------------------
INSERT INTO JOB_HISTORY
(EMPLOYE_ID, START_DATE  , END_DATE    , JOB_ID      , DEPARTMENT_ID) VALUES
(3         , '1993-01-13', '1998-07-24', 'IT_PROG'   , 4            ),
(2         , '1989-09-21', '1993-10-27', 'AC_ACCOUNT', 7            ),
(2         , '1993-10-28', '1997-03-15', 'AC_MGR'    , 7            ),
(17        , '1996-02-17', '1999-12-19', 'MK_REP'    , 2            ),
(8         , '1998-03-24', '1999-12-31', 'ST_CLERK'  , 3            ),
(7         , '1999-01-01', '1999-12-31', 'ST_CLERK'  , 3            ),
(16        , '1987-09-17', '1993-06-17', 'AD_ASST'   , 6            ),
(14        , '1998-03-24', '1998-12-31', 'SA_REP'    , 5            ),
(14        , '1999-01-01', '1998-12-31', 'SA_MAN'    , 5            ),
(16        , '1994-07-01', '1998-12-31', 'AC_ACCOUNT', 6            );

UPDATE DEPARTMENTS SET MANAGER_ID = 16 WHERE ID = 1;
UPDATE DEPARTMENTS SET MANAGER_ID = 17 WHERE ID = 2;
UPDATE DEPARTMENTS SET MANAGER_ID = 7 WHERE ID = 3;
UPDATE DEPARTMENTS SET MANAGER_ID = 4 WHERE ID = 4;
UPDATE DEPARTMENTS SET MANAGER_ID = 12 WHERE ID = 5;
UPDATE DEPARTMENTS SET MANAGER_ID = 1 WHERE ID = 6;
UPDATE DEPARTMENTS SET MANAGER_ID = 19 WHERE ID = 7;


