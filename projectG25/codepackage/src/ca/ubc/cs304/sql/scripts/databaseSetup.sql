

CREATE TABLE Medical_Inventory (
	Medical_id		INTEGER primary key,
	Storage_num		INTEGER);

INSERT INTO Medical_Inventory (Medical_id, Storage_num) VALUES (200,50);
INSERT INTO Medical_Inventory (Medical_id, Storage_num) VALUES (201,50);
INSERT INTO Medical_Inventory (Medical_id, Storage_num) VALUES (202,5);
INSERT INTO Medical_Inventory (Medical_id, Storage_num) VALUES (203,5111);
INSERT INTO Medical_Inventory (Medical_id, Storage_num) VALUES (204,50);
(200, 50), (201, 50), (202, 30), (203, 500), (204, 5);
CREATE TABLE Drug (
	Medical_id		INTEGER primary key,
	Ingredient		VARCHAR(20),
	Storage_detail	VARCHAR(20),	
    FOREIGN KEY (Medical_id) REFERENCES Medical_Inventory (Medical_id)
				);


INSERT INTO drug VALUES (204,'black','morning');
INSERT INTO drug VALUES (200,'red','night');

CREATE TABLE distributor
(
    distributor_id integer NOT NULL,
    PRIMARY KEY (distributor_id)
);
INSERT INTO distributor VALUES (200);
INSERT INTO distributor VALUES (201);
INSERT INTO distributor VALUES  (202);
INSERT INTO distributor VALUES  (203);
INSERT INTO distributor VALUES  (204);

CREATE TABLE place_order_r3
(
    order_num integer NOT NULL,
    order_date integer,
    PRIMARY KEY (order_num)
);
INSERT INTO place_order_r3 VALUES (700, 20201010);
INSERT INTO place_order_r3 VALUES (701, 20201011);
INSERT INTO place_order_r3 VALUES (702, 20201012);
INSERT INTO place_order_r3 VALUES (703, 20201013);
INSERT INTO place_order_r3 VALUES (704, 20201014);


