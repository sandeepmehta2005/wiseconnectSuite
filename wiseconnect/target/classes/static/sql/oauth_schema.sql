-- used in tests that use HSQL
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table oauth_client_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

create table oauth_code (
  code VARCHAR(256), authentication BLOB
);

create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- customized oauth_client_details table
create table oauth1_client_details (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);


INSERT INTO oauth_client_details(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
 VALUES ('spring-security-oauth2-read-client', 'resource-server-rest-api',
 /*spring-security-oauth2-read-client-password1234*/'$2a$04$WGq2P9egiOYoOFemBRfsiO9qTcyJtNRnPKNBl5tokP7IP.eZn93km',
 'read', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);
INSERT INTO oauth_client_details(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
 VALUES ('spring-security-oauth2-read-write-client', 'resource-server-rest-api',
 /*spring-security-oauth2-read-write-client-password1234*/'$2a$04$soeOR.QFmClXeFIrhJVLWOQxfHjsJLSpWrU1iGxcMGdu.a5hvfY4W',
 'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);


 -- Create new Table tbl_user_roles

 CREATE TABLE `cluster_wiseconnect`.`tbl_user_role` (
   `user_role_id` BIGINT NOT NULL,
   `user_id` INT(11) NOT NULL,
   `role_id` BIGINT NOT NULL,
   PRIMARY KEY (`user_role_id`));

 ALTER TABLE `cluster_wiseconnect`.`tbl_user_role`
 ADD INDEX `USER_ROLE_FK1_idx` (`user_id` ASC),
 ADD INDEX `USER_ROLE_ROLEFK_idx` (`role_id` ASC);
 ALTER TABLE `cluster_wiseconnect`.`tbl_user_role`
 ADD CONSTRAINT `USER_ROLE_USERFK`
   FOREIGN KEY (`user_id`)
   REFERENCES `cluster_wiseconnect`.`tbl_user` (`user_id`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION,
 ADD CONSTRAINT `USER_ROLE_ROLEFK`
   FOREIGN KEY (`role_id`)
   REFERENCES `cluster_wiseconnect`.`tbl_roles` (`role_id`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

   insert into tbl_user_role (user_role_id, USER_ID, ROLE_ID)
   values (1, 4, 1);

   insert into tbl_user_role (user_role_id, USER_ID, ROLE_ID)
   values (2, 4, 2);

   insert into tbl_user_role (user_role_id, USER_ID, ROLE_ID)
   values (3, 2, 2);

ALTER TABLE `cluster_wiseconnect`.`oauth_access_token`
ADD COLUMN `phone_id` VARCHAR(45) NULL AFTER `phone_id`;