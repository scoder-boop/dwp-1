CREATE database noisy;
GRANT ALL PRIVILEGES ON noisy.* TO 'noisy'@'localhost' IDENTIFIED BY 'noisyb****d';

DROP TABLE IF EXISTS profile;
CREATE TABLE profile 
( id smallint unsigned not null auto_increment
, username varchar(20) not null
, first_name varchar(50) not null
, last_name varchar(50) not null
, email varchar(50)
, address varchar(100)
, phone_number integer
, active boolean DEFAULT true
, constraint pk_example primary key (id) 
);

INSERT INTO profile
(username
,first_name
,last_name
,email
,address
,phone_number
,active
) VALUES
(
'abc'
,'abbey'
,'bow-cop'
,'abc@xyz.com'
,'123 zyx st, peterbow, pb99 9zz'
,0123456789
,true
);
INSERT INTO profile
(username
,first_name
,last_name
,email
,address
,phone_number
,active
) VALUES
(
'ppp'
,'pabbey'
,'bow-cop'
,'ppp@xyz.com'
,'123 zyx st, peterbow, pb99 9zz'
,0123456788
,true
);
