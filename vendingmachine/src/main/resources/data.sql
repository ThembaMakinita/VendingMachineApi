
CREATE TABLE Product (
    PRODUCT_ID BIGINT NOT NULL,
    Name VARCHAR(50),
    Price DOUBLE,
    Available INT
);

insert into Product values (1,'Coke',16.00,5),
                           (2,'Fanta',19.50,5),
                           (3,'Sprite',18.00,5),
                           (4,'Pepsi',16.50,5),
                           (5,'Stoney',19.00,5);
