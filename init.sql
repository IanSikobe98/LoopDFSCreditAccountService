-- V2__Create_Tables.sql
CREATE SCHEMA IF NOT EXISTS loopdfs;

CREATE TABLE loopdfs.client (
                                id serial4 NOT NULL,
                                "name" varchar(255) NULL,
                                phonenumber varchar(255) NULL,
                                nationalid varchar(255) NULL,
                                status int4 NULL,
                                CONSTRAINT client_pkey PRIMARY KEY (id)
);




CREATE TABLE loopdfs.account (
                                 id serial4 NOT NULL,
                                 iban varchar(255) NULL,
                                 bicswift varchar(255) NULL,
                                 clientid int4 NULL,
                                 status int4 NULL,
                                 CONSTRAINT account_pkey PRIMARY KEY (id)
);



ALTER TABLE loopdfs.account ADD CONSTRAINT account_clientid_fkey FOREIGN KEY (clientid) REFERENCES loopdfs.client(id) ON DELETE CASCADE ON UPDATE CASCADE;



CREATE TABLE loopdfs.card (
                              id serial4 NOT NULL,
                              card_name varchar(255) NULL,
                              account_id int4 NULL,
                              card_type varchar(255) NULL,
                              status int4 NULL,
                              CONSTRAINT card_pkey PRIMARY KEY (id)
);

ALTER TABLE loopdfs.card ADD CONSTRAINT card_account_id_fkey FOREIGN KEY (account_id) REFERENCES loopdfs.account(id) ON DELETE CASCADE ON UPDATE CASCADE;


INSERT INTO loopdfs.client ("name",phonenumber,nationalid,status) VALUES
                                                                      ('Ian Walter','0716561507','35032507',1),
                                                                      ('Mally','07192545550','34923178',0);


INSERT INTO loopdfs.account (iban,bicswift,clientid,status) VALUES
                                                                ('4567','9999999',2,1),
                                                                ('IB34','BIC23',1,1),
                                                                ('IB34','BIC23',1,1),
                                                                ('IB34','BIC23',1,1),
                                                                ('IB-222','BT-189',1,1),
                                                                ('IB34','BIC23',1,1);


INSERT INTO loopdfs.card (card_name,account_id,card_type,status) VALUES
                                                                     ('POSSYS',1,'VISA2',1),
                                                                     ('MOCKTEST',2,'MASTERCARD',1),
                                                                     ('VISA3',1,'VISA',1);



