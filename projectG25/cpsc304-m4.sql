drop table shippedwith;
drop table shippingcompany;
drop table uses;
drop table supplies;
drop table receives;
drop table place_order_r4;
drop table place_order_r3;
drop table place_order_r2;
drop table place_order_r1;
drop table medical_equipment;
drop table manufacturer_r4;
drop table manufacturer_r3;
drop table manufacturer_r2;
drop table manufacturer_r1;
drop table contact;
drop table inventorymanager_updateinventory_r3;
drop table inventorymanager_updateinventory_r2;
drop table manager;
drop table equipment_supplier;
drop table drug_supplier;
drop table drug;
drop table employed_healthcareworkers_r6;
drop table employed_healthcareworkers_r5;
drop table employed_healthcareworkers_r4;
drop table employed_healthcareworkers_r3;
drop table employed_healthcareworkers_r2;
drop table employed_healthcareworkers_r1;
drop table medical_inventory;
drop table hospital_r2;
drop table hospital_r1;
drop table distributor;

CREATE TABLE distributor
(
    distributor_id integer NOT NULL,
    PRIMARY KEY (distributor_id)
);

grant select on distributor to public;

INSERT INTO distributor VALUES (200); 
INSERT INTO distributor VALUES (201);
INSERT INTO distributor VALUES  (202);
INSERT INTO distributor VALUES  (203);
INSERT INTO distributor VALUES  (204);

CREATE TABLE hospital_r1
(
    hospital_address VARCHAR(20) NOT NULL,
    hospital_name CHAR(40),
    PRIMARY KEY (hospital_address)
);

grant select on hospital_r1 to public;

INSERT INTO hospital_r1 VALUES ('123 A Street', 'Brown hospital');
INSERT INTO hospital_r1 VALUES  ('124 A Street', 'Yellow hospital');
INSERT INTO hospital_r1 VALUES  ('125 A Street', 'Blue hospital');
INSERT INTO hospital_r1 VALUES  ('123 B Street', 'Red hospital');
INSERT INTO hospital_r1 VALUES  ('124 B Street', 'Green hospital');


CREATE TABLE hospital_r2
(
    branch_num integer NOT NULL,
    hospital_address VARCHAR(20) NOT NULL,
    PRIMARY KEY (branch_num)
);

grant select on hospital_r2 to public;

INSERT INTO hospital_r2 VALUES (100, '123 A Street');
INSERT INTO hospital_r2 VALUES (101, '124 A Street');
INSERT INTO hospital_r2 VALUES (102, '125 A Street');
INSERT INTO hospital_r2 VALUES (103, '123 B Street'); 
INSERT INTO hospital_r2 VALUES (104, '124 B Street');

CREATE TABLE medical_inventory
(
    medical_id integer NOT NULL,
    storage_num integer,
    PRIMARY KEY (medical_id)
);

grant select on medical_inventory to public;

INSERT INTO medical_inventory VALUES (500, 50);
INSERT INTO medical_inventory VALUES (501, 50);
INSERT INTO medical_inventory VALUES  (502, 30);
INSERT INTO medical_inventory VALUES  (503, 500);
INSERT INTO medical_inventory VALUES  (504, 30);
INSERT INTO medical_inventory VALUES  (505, 55);
INSERT INTO medical_inventory VALUES  (506, 50);
INSERT INTO medical_inventory VALUES  (507, 500);
INSERT INTO medical_inventory VALUES  (508, 55);
INSERT INTO medical_inventory VALUES  (509, 20);

CREATE TABLE employed_healthcareworkers_r1
(
    work_id integer NOT NULL,
    department CHAR(20),
    PRIMARY KEY (work_id)
);

grant select on employed_healthcareworkers_r1 to public;

INSERT INTO  employed_healthcareworkers_r1 VALUES (400, 'Cardiology');
INSERT INTO  employed_healthcareworkers_r1 VALUES (401, 'Assist');
INSERT INTO  employed_healthcareworkers_r1 VALUES (402, 'Assist');
INSERT INTO  employed_healthcareworkers_r1 VALUES (403, 'Neurosurgery'); 
INSERT INTO  employed_healthcareworkers_r1 VALUES (404, 'Spine Center');


CREATE TABLE employed_healthcareworkers_r2
(
    work_id integer NOT NULL,
    worker_role CHAR(20) NOT NULL,
    supervisor CHAR(20),
    PRIMARY KEY (work_id, worker_role)
);

grant select on employed_healthcareworkers_r2 to public;

INSERT INTO  employed_healthcareworkers_r2 VALUES (400, 'Surgeon', 'Bob');
INSERT INTO  employed_healthcareworkers_r2 VALUES (401, 'Resident', 'Frank');
INSERT INTO  employed_healthcareworkers_r2 VALUES (402, 'Resident', 'Frank');
INSERT INTO  employed_healthcareworkers_r2 VALUES (403, 'Nurse', 'Jake');
INSERT INTO  employed_healthcareworkers_r2 VALUES (404, 'Surgeon', 'Selena');


CREATE TABLE employed_healthcareworkers_r3
(
    branch_num integer NOT NULL,
    phone_num CHAR(20) NOT NULL,
    worker_role CHAR(20),
    PRIMARY KEY (branch_num, phone_num),
    FOREIGN KEY (branch_num)
        REFERENCES hospital_r2 (branch_num)
        ON DELETE CASCADE
);

grant select on employed_healthcareworkers_r3 to public;

INSERT INTO employed_healthcareworkers_r3 VALUES (100, '1111111111', 'Surgeon');
INSERT INTO employed_healthcareworkers_r3 VALUES (100, '1111111112', 'Resident');
INSERT INTO employed_healthcareworkers_r3 VALUES (100, '1111111113', 'Resident');
INSERT INTO employed_healthcareworkers_r3 VALUES (101, '1111111114', 'Nurse');
INSERT INTO employed_healthcareworkers_r3 VALUES (101, '1111111115', 'Surgeon');


CREATE TABLE employed_healthcareworkers_r4
(
    branch_num integer NOT NULL,
    worker_address CHAR(30),
    phone_num CHAR(20) NOT NULL,
    PRIMARY KEY (branch_num, phone_num),
    FOREIGN KEY (branch_num)
        REFERENCES hospital_r2 (branch_num)
        ON DELETE CASCADE
);

grant select on employed_healthcareworkers_r4 to public;

INSERT INTO  employed_healthcareworkers_r4 VALUES (100, '100 A Street', '1111111111');
INSERT INTO  employed_healthcareworkers_r4 VALUES (100, '100 B Street', '1111111112');
INSERT INTO  employed_healthcareworkers_r4 VALUES (100, '100 C Street', '1111111113');
INSERT INTO  employed_healthcareworkers_r4 VALUES (101, '100 D Street', '1111111114');
INSERT INTO  employed_healthcareworkers_r4 VALUES (101, '100 E Street', '1111111115');


CREATE TABLE employed_healthcareworkers_r5
(
    work_id integer NOT NULL,
    branch_num integer NOT NULL,
    phone_num CHAR(20),
    PRIMARY KEY (work_id, phone_num),
    FOREIGN KEY (branch_num)
        REFERENCES hospital_r2 (branch_num)
        ON DELETE CASCADE
);

grant select on employed_healthcareworkers_r5 to public;

INSERT INTO  employed_healthcareworkers_r5 VALUES (400, 100, '1111111111');
INSERT INTO  employed_healthcareworkers_r5 VALUES (401, 100, '1111111112');
INSERT INTO  employed_healthcareworkers_r5 VALUES (402, 100, '1111111113'); 
INSERT INTO  employed_healthcareworkers_r5 VALUES (403, 102, '1111111114');
INSERT INTO  employed_healthcareworkers_r5 VALUES (404, 102, '1111111115');


CREATE TABLE employed_healthcareworkers_r6
(
    work_id integer NOT NULL,
    room_num integer,
    PRIMARY KEY (work_id)
);

grant select on employed_healthcareworkers_r6 to public;

INSERT INTO  employed_healthcareworkers_r6 VALUES (400, 1000);
INSERT INTO  employed_healthcareworkers_r6 VALUES (401,1001);
INSERT INTO  employed_healthcareworkers_r6 VALUES (402,1001);
INSERT INTO  employed_healthcareworkers_r6 VALUES (403, 1002);
INSERT INTO  employed_healthcareworkers_r6 VALUES (404, 1003);


CREATE TABLE drug
(
    medical_id integer NOT NULL,
    ingredient CHAR(40),
    storage_detail CHAR(40),
    PRIMARY KEY (medical_id),
    FOREIGN KEY (medical_id)
        REFERENCES medical_inventory (medical_id)
        ON DELETE CASCADE
);

grant select on drug to public;

INSERT INTO drug VALUES (500, 'MASKS', 'NA');
INSERT INTO drug VALUES (501, 'ICU', 'dark room');
INSERT INTO drug VALUES (502, 'RESPIRATOR', 'Pressurized');
INSERT INTO drug VALUES (503, 'PENICILLIN','cool');
INSERT INTO drug VALUES (504, 'MORPHINE', 'cool');


CREATE TABLE drug_supplier
(
    distributor_id integer NOT NULL,
    ingredients VARCHAR(40),
    PRIMARY KEY (distributor_id),
    FOREIGN KEY (distributor_id)
        REFERENCES distributor (distributor_id)
        ON DELETE CASCADE
);

grant select on drug_supplier to public;

INSERT INTO drug_supplier VALUES (200, 'MORPHINE');
INSERT INTO drug_supplier VALUES (201, 'HEXYLENE GLYCOL');
INSERT INTO drug_supplier VALUES (202, 'HIGH DENSITY POLYETHYLENE');
INSERT INTO drug_supplier VALUES (203, 'HYDROCHLORIC ACID');
INSERT INTO drug_supplier VALUES (204, 'PENTAERYTHRITOL ESTER');


CREATE TABLE equipment_supplier
(
    distributor_id integer NOT NULL,
    eqpmt_specialty VARCHAR(20),
    PRIMARY KEY (distributor_id),
    FOREIGN KEY (distributor_id)
        REFERENCES distributor (distributor_id)
        ON DELETE CASCADE
);

grant select on equipment_supplier to public;

INSERT INTO equipment_supplier VALUES (200, 'Masks');
INSERT INTO equipment_supplier VALUES (201, 'Scalpels');
INSERT INTO equipment_supplier VALUES (202, 'Respirators');
INSERT INTO equipment_supplier VALUES (203, 'IV Fluid');
INSERT INTO equipment_supplier VALUES (204, 'Oxymorphone');


CREATE TABLE manager
(
    manager_id integer NOT NULL,
    medical_id integer NOT NULL,
    phone_num CHAR(20),
    PRIMARY KEY (manager_id, medical_id),
    FOREIGN KEY (medical_id)
        REFERENCES medical_inventory (medical_id)
        ON DELETE CASCADE
);

grant select on manager to public;

INSERT INTO manager VALUES  (600, 500, '1000001');
INSERT INTO manager VALUES  (601, 501, '1000002');
INSERT INTO manager VALUES  (602, 502, '1000003');
INSERT INTO manager VALUES  (603, 503, '1000004');
INSERT INTO manager VALUES  (604, 504, '1000005');
INSERT INTO manager VALUES  (600, 501, '1000001');
INSERT INTO manager VALUES  (600, 502, '1000001');
INSERT INTO manager VALUES  (600, 503, '1000001');
INSERT INTO manager VALUES  (600, 504, '1000001');


CREATE TABLE inventorymanager_updateinventory_r2
(
    medical_id integer,
    update_date integer NOT NULL,
    PRIMARY KEY (update_date),
    FOREIGN KEY (medical_id)
        REFERENCES medical_inventory (medical_id)
        ON DELETE CASCADE
);

grant select on inventorymanager_updateinventory_r2 to public;

INSERT INTO inventorymanager_updateinventory_r2 VALUES (500, 20200525);
INSERT INTO inventorymanager_updateinventory_r2 VALUES (501, 20201101);
INSERT INTO inventorymanager_updateinventory_r2 VALUES (502, 20201212);
INSERT INTO inventorymanager_updateinventory_r2 VALUES (503, 20200303);
INSERT INTO inventorymanager_updateinventory_r2 VALUES (504, 20200915);


CREATE TABLE inventorymanager_updateinventory_r3
(
    manager_id integer NOT NULL,
    manager_name CHAR(20) NOT NULL,
    update_date integer NOT NULL,
    PRIMARY KEY (manager_id, manager_name)
);

grant select on inventorymanager_updateinventory_r3 to public;

INSERT INTO inventorymanager_updateinventory_r3 VALUES (603, 'jackson', 20200303);
INSERT INTO inventorymanager_updateinventory_r3 VALUES  (600, 'bob', 20200525);
INSERT INTO inventorymanager_updateinventory_r3 VALUES (604, 'helen', 20200915);
INSERT INTO inventorymanager_updateinventory_r3 VALUES (601, 'jack', 20201101);
INSERT INTO inventorymanager_updateinventory_r3 VALUES  (602, 'cindy', 20201212);

CREATE TABLE contact
(
    manager_id integer,
    branch_num integer,
    manager_name CHAR(20),
    phone_num CHAR(20),
    FOREIGN KEY (branch_num)
        REFERENCES hospital_r2 (branch_num)
        ON DELETE CASCADE,
    FOREIGN KEY (manager_id, manager_name)
        REFERENCES inventorymanager_updateinventory_r3 (manager_id, manager_name)
        ON DELETE CASCADE
);

grant select on contact to public;

INSERT INTO contact VALUES (600, 100, 'bob','1000001');
INSERT INTO contact VALUES (601,101, 'jack', '1000002');
INSERT INTO contact VALUES (602, 102, 'cindy', '1000003');
INSERT INTO contact VALUES (603, 103, 'jackson', '1000004');
INSERT INTO contact VALUES (604, 104, 'helen', '1000005');


CREATE TABLE manufacturer_r1
(
    manufacturer_id integer NOT NULL,
    manufacturer_name CHAR(20) NOT NULL,
    PRIMARY KEY (manufacturer_id)
);

grant select on manufacturer_r1 to public;

INSERT INTO manufacturer_r1 VALUES (300, 'Green man');
INSERT INTO manufacturer_r1 VALUES (301, 'Yellow man');
INSERT INTO manufacturer_r1 VALUES (302, 'juice man');
INSERT INTO manufacturer_r1 VALUES (303, 'chocolate factory');
INSERT INTO manufacturer_r1 VALUES (304, 'Father company');


CREATE TABLE manufacturer_r2
(
    manufacturer_id integer NOT NULL,
    city CHAR(30) NOT NULL,
    specialty VARCHAR(30),
    PRIMARY KEY (manufacturer_id, specialty)
);

grant select on manufacturer_r2 to public;

INSERT INTO manufacturer_r2 VALUES (300, 'TORONTO', 'MASKS');
INSERT INTO manufacturer_r2 VALUES (301, 'VANCOUVER', 'ICU');
INSERT INTO manufacturer_r2 VALUES (302, 'BEIJING', 'RESPIRATOR');
INSERT INTO manufacturer_r2 VALUES (303, 'SHANGHAI', 'PENICILLIN');
INSERT INTO manufacturer_r2 VALUES (304, 'HONGKONG', 'MORPHINE');


CREATE TABLE manufacturer_r3
(
    qty integer,
    specialty VARCHAR(20),
    PRIMARY KEY (specialty)
);

grant select on manufacturer_r3 to public;

INSERT INTO manufacturer_r3 VALUES (100, 'MASKS');
INSERT INTO manufacturer_r3 VALUES (200, 'ICU');
INSERT INTO manufacturer_r3 VALUES (150, 'RESPIRATOR');
INSERT INTO manufacturer_r3 VALUES (300, 'PENICILLIN');
INSERT INTO manufacturer_r3 VALUES (120, 'MORPHINE');


CREATE TABLE manufacturer_r4
(
    specialty VARCHAR(20) NOT NULL,
    ailment CHAR(40) NOT NULL,
    manufacturer_id integer NOT NULL,
    PRIMARY KEY (manufacturer_id, specialty, ailment)
);

grant select on manufacturer_r4 to public;

INSERT INTO manufacturer_r4 VALUES ('HEART', 'BLOOD', 300);
INSERT INTO manufacturer_r4 VALUES ('FOOT', 'SKIN CUT', 301);
INSERT INTO manufacturer_r4 VALUES ('ARM', 'BLEEDING', 302);
INSERT INTO manufacturer_r4 VALUES ('HEAD', 'HEADACHE', 303);
INSERT INTO manufacturer_r4 VALUES ('LEG', 'PROTECTION', 304);


CREATE TABLE medical_equipment
(
    medical_id integer NOT NULL,
    eqmt_description VARCHAR(20),
    PRIMARY KEY (medical_id),
    FOREIGN KEY (medical_id)
        REFERENCES medical_inventory (medical_id)
        ON DELETE CASCADE
);

grant select on medical_equipment to public;

INSERT INTO medical_equipment VALUES (500, 'MASKS');
INSERT INTO medical_equipment VALUES (501, 'ICU');
INSERT INTO medical_equipment VALUES (502, 'RESPIRATOR');
INSERT INTO medical_equipment VALUES (503, 'PENICILLIN');
INSERT INTO medical_equipment VALUES (504, 'MORPHINE');


CREATE TABLE place_order_r1
(
    branch_num integer NOT NULL,
    hospital_name CHAR(40),
    PRIMARY KEY (branch_num),
    FOREIGN KEY (branch_num)
        REFERENCES hospital_r2 (branch_num)
        ON DELETE CASCADE
);

grant select on place_order_r1 to public;

INSERT INTO place_order_r1 VALUES (100, 'Brown hospital');
INSERT INTO place_order_r1 VALUES (101, 'Yellow hospital');
INSERT INTO place_order_r1 VALUES (102, 'Blue hospital');
INSERT INTO place_order_r1 VALUES (103, 'Red hospital');
INSERT INTO place_order_r1 VALUES (104, 'Green hospital');


CREATE TABLE place_order_r2
(
    order_num integer NOT NULL,
    branch_num integer,
    PRIMARY KEY (order_num),
    FOREIGN KEY (branch_num)
        REFERENCES hospital_r2 (branch_num)
        ON DELETE CASCADE
);

grant select on place_order_r2 to public;

INSERT INTO place_order_r2 VALUES (700, 100);
INSERT INTO place_order_r2 VALUES (701, 101);
INSERT INTO place_order_r2 VALUES (702, 100);
INSERT INTO place_order_r2 VALUES (703, 100);
INSERT INTO place_order_r2 VALUES (704, 102);


CREATE TABLE place_order_r3
(
    order_num integer NOT NULL,
    order_date integer,
    PRIMARY KEY (order_num)
);

grant select on place_order_r3 to public;

INSERT INTO place_order_r3 VALUES (700, 20201010); 
INSERT INTO place_order_r3 VALUES (701, 20201011);
INSERT INTO place_order_r3 VALUES (702, 20201012);
INSERT INTO place_order_r3 VALUES (703, 20201013);
INSERT INTO place_order_r3 VALUES (704, 20201014);

CREATE TABLE place_order_r4
(
    order_num integer NOT NULL,
    order_description VARCHAR(30),
    PRIMARY KEY (order_num)
);

grant select on place_order_r4 to public;

INSERT INTO place_order_r4 VALUES (700, '100 masks');
INSERT INTO place_order_r4 VALUES (701, '100L alcohol');
INSERT INTO place_order_r4 VALUES (702, '200 masks');
INSERT INTO place_order_r4 VALUES (703, '100 Respirators');
INSERT INTO place_order_r4 VALUES (704, '10kg Penicillin');


CREATE TABLE receives
(
    order_num integer NOT NULL,
    distributor_id integer NOT NULL,
    PRIMARY KEY (order_num, distributor_id),
    FOREIGN KEY (distributor_id)
        REFERENCES distributor (distributor_id)
        ON DELETE CASCADE,
    FOREIGN KEY (order_num)
        REFERENCES place_order_r2 (order_num)
        ON DELETE CASCADE
);

grant select on receives to public;

INSERT INTO receives VALUES (700, 200);
INSERT INTO receives VALUES (701, 201);
INSERT INTO receives VALUES (702, 202);
INSERT INTO receives VALUES (703, 203);
INSERT INTO receives VALUES (704, 204);


CREATE TABLE supplies
(
    manufacturer_id integer NOT NULL,
    distributor_id integer NOT NULL,
    medical_id integer NOT NULL,
    specialty VARCHAR(20) NOT NULL,
    PRIMARY KEY (manufacturer_id, distributor_id, medical_id, specialty),
    FOREIGN KEY (distributor_id)
        REFERENCES distributor (distributor_id)
        ON DELETE CASCADE,
    FOREIGN KEY (manufacturer_id, specialty)
        REFERENCES manufacturer_r2 (manufacturer_id, specialty)
        ON DELETE CASCADE,
    FOREIGN KEY (medical_id)
        REFERENCES medical_inventory (medical_id)
        ON DELETE CASCADE
);

grant select on supplies to public;

INSERT INTO supplies VALUES	(300,200,500,'MASKS');
INSERT INTO supplies VALUES	(301,201,501,'ICU');
INSERT INTO supplies VALUES	(302,202,502,'RESPIRATOR');
INSERT INTO supplies VALUES	(303,203,503,'PENICILLIN');
INSERT INTO supplies VALUES	(304,204,504,'MORPHINE');


CREATE TABLE uses
(
    work_id integer NOT NULL,
    branch_num integer NOT NULL,
    medical_id integer NOT NULL,
    phone_num CHAR(20),
    PRIMARY KEY (medical_id, work_id, branch_num, phone_num),
    FOREIGN KEY (branch_num)
        REFERENCES hospital_r2 (branch_num)
        ON DELETE CASCADE,
    FOREIGN KEY (medical_id)
        REFERENCES medical_inventory (medical_id)
        ON DELETE CASCADE,
    FOREIGN KEY (work_id, phone_num)
        REFERENCES employed_healthcareworkers_r5 (work_id, phone_num)
        ON DELETE CASCADE
);

grant select on uses to public;

INSERT INTO uses VALUES (400, 100, 500, '1111111111');
INSERT INTO uses VALUES (401, 101, 501, '1111111112');
INSERT INTO uses VALUES (402, 102, 502,'1111111113'); 
INSERT INTO uses VALUES (403, 103, 503,'1111111114');
INSERT INTO uses VALUES (404, 104, 504,'1111111115');

CREATE TABLE shippingcompany
(
    company_id integer NOT NULL,
    company_name VARCHAR(20),
    shipdate integer,
    PRIMARY KEY (company_id)
);

grant select on shippingcompany to public;

INSERT INTO shippingcompany VALUES (900, 'Fedex', 20200101);
INSERT INTO shippingcompany VALUES (901, 'UPS', 20200105);
INSERT INTO shippingcompany VALUES (902, 'Canada Post', 20200202);
INSERT INTO shippingcompany VALUES (903, 'USPS', 20200303);
INSERT INTO shippingcompany VALUES (904, 'Purolator', 20200305);


CREATE TABLE shippedwith
(
    order_num integer NOT NULL,
    company_id integer NOT NULL,
    PRIMARY KEY (order_num, company_id),
    FOREIGN KEY (company_id)
        REFERENCES shippingcompany (company_id) 
        ON DELETE CASCADE,
    FOREIGN KEY (order_num)
        REFERENCES place_order_r2 (order_num)
        ON DELETE CASCADE
);

grant select on shippedwith to public;

INSERT INTO shippedwith VALUES (700,900);
INSERT INTO shippedwith VALUES (701,901);
INSERT INTO shippedwith VALUES (702,902);
INSERT INTO shippedwith VALUES (703,903);
INSERT INTO shippedwith VALUES (704,904);