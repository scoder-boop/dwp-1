CREATE database noisy;
GRANT ALL PRIVILEGES ON noisy.* TO 'noisy'@'localhost' IDENTIFIED BY 'sssssss';


SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS profile;
CREATE TABLE profile
( id int unsigned not null auto_increment
, username varchar(20) not null
, first_name varchar(50) not null
, last_name varchar(50) not null
, email varchar(50)
, address varchar(100)
, phone_number integer
, active boolean DEFAULT true
, PRIMARY KEY (id)
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

DROP TABLE IF EXISTS site;
CREATE TABLE site
( id int unsigned not null auto_increment
, name varchar(50) not null
, site_type ENUM('APP','URL') not null DEFAULT 'URL'
, connection_string varchar(50) not null
, description varchar(50)
, site_auth_details varchar(300)
, accept_xpath varchar(300)
, useable boolean DEFAULT true
, PRIMARY KEY (id)
);

DROP TABLE IF EXISTS profile_site;
CREATE TABLE profile_site
( profile_id int unsigned not null
, site_id int unsigned not null
, frequency_type ENUM('MONTHLY','WEEKLY','DAILY','HOURLY') not null
, frequency int not null default 0
, login_string varchar(20) not null
, active boolean DEFAULT true
, password_required boolean DEFAULT true
, password varchar(50)
, auth_details varchar(300)
, PRIMARY KEY (profile_id, site_id)
, FOREIGN KEY (profile_id)
          REFERENCES profile(id)
          ON DELETE CASCADE
, FOREIGN KEY (site_id)
          REFERENCES site(id)
);

DROP TABLE IF EXISTS profile_history;
CREATE TABLE profile_history
( id int unsigned not null auto_increment
, profile_id int unsigned not null
, event_time timestamp DEFAULT CURRENT_TIMESTAMP
, event_time_epoch long not null
, event varchar(150)
, PRIMARY KEY (id)
, FOREIGN KEY (profile_id)
          REFERENCES profile(id)
          ON DELETE CASCADE
);

DROP TABLE IF EXISTS site_history;
CREATE TABLE site_history
( id int unsigned not null auto_increment
, site_id int unsigned not null
, event_time timestamp DEFAULT CURRENT_TIMESTAMP
, event_time_epoch long not null
, event varchar(150)
, PRIMARY KEY (id)
, FOREIGN KEY (site_id)
          REFERENCES site(id)
          ON DELETE CASCADE
);

