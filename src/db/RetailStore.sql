DROP DATABASE IF EXISTS RetailStore;
CREATE DATABASE IF NOT EXISTS RetailStore;
USE RetailStore;

create table customer(
custID varchar(6) PRIMARY KEY,
custTital varchar(5),
custName varchar (30),
custPhoneNo varchar (13),
custAddress varchar(30),
custEmail varchar(30),
city varchar(20),
state varchar(30),
RegDate DATE
);

create table cashier(
castID varchar(6) PRIMARY KEY,
castName varchar (30),
castBirthDay DATE,
castAddress varchar(30),
castPhoto varchar(100),
caslogin varchar(20),
caspassword varchar(20)

);

create table orders(
orderID varchar(6) PRIMARY KEY,
orderDate DATE,
orderTime TIME,
custName varchar (30) NOT NULL,
custPhoneNo varchar (13),
custAddress varchar(30),
custEmail varchar(30),
castID varchar(6),
FOREIGN KEY (castID) REFERENCES cashier(castID)
);

create table supplier (
supplierID varchar(6) PRIMARY KEY,
supplierName varchar(50),
supplierAddress varchar(20),
supplierPhone varchar(13),
supplierEmail varchar(20)
);



create table item (
itemCode varchar(6) PRIMARY KEY,
supplierID varchar(6),
Description varchar(50),
packSize varchar(20),
unitPrice DECIMAL(6,2),
QtyOnHand INT(5),
BatchID varchar(6)

);


create table payment(
payID varchar(6) PRIMARY KEY,
custName varchar(30),
amount DECIMAL  (10,2),
Discount DECIMAL(10,2),
orderID varchar(6),
FOREIGN KEY (orderID) REFERENCES orders(orderID)
);


create table orderDetail(
orderID varchar(6),
itemCode varchar(6) ,
orderQTY int(11),
unitPrice DOUBLE(10,2),
FOREIGN KEY (orderID) REFERENCES orders(orderID),
FOREIGN KEY (itemCode) REFERENCES item(itemCode),
CONSTRAINT PRIMARY KEY (orderID,itemCode)

);