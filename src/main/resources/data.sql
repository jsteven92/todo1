INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, PARSEDATETIME('01-01-2016', 'dd-MM-yyyy'));
-- INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, PARSEDATETIME('01-01-2016','dd-MM-yyyy'));
-- INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, PARSEDATETIME('01-01-2016','dd-MM-yyyy'));


INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
-- INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
-- INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
-- INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);

INSERT INTO SUPER_HERO (ID,EDITORIAL,NAME) VALUES (1,'SUPER MAN','DC COMICS');
INSERT INTO SUPER_HERO (ID,EDITORIAL,NAME) VALUES (2,'SPIDER-MAN','MARVEL');
INSERT INTO SUPER_HERO (ID,EDITORIAL,NAME) VALUES (3,'IRON-MAN','MARVEL');

INSERT INTO PRODUCT (ID,NAME,PRICE,STOCK,SUPER_HERO_ID,STATUS) VALUES (1,'CAMISETA DE IRON-MAN',25000,50,3,'CREATED');
INSERT INTO PRODUCT (ID,NAME,PRICE,STOCK,SUPER_HERO_ID,STATUS) VALUES (2,'VASO DE SUPER MAN',50000,10,1,'CREATED');


INSERT INTO INVOICES (ID,NUMBER_INVOICE,DESCRIPTION,STATE) VALUES (1,'2021-01','VENTA','CREATED');

INSERT INTO INVOICE_ITEMS (ID,QUANTITY,PRICE,PRODUCT_ID,INVOICE_ID) VALUES (1,5,25000,1,1);
INSERT INTO INVOICE_ITEMS (ID,QUANTITY,PRICE,PRODUCT_ID,INVOICE_ID) VALUES (2,3,50000,2,1);

