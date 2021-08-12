-- begin MYPROJECT_PRODUCER
create table MYPROJECT_PRODUCER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CITY varchar(255),
    STREET varchar(255),
    BUILDING varchar(255),
    --
    NAME varchar(255) not null,
    FULL_NAME varchar(255),
    USER_ID uuid,
    --
    primary key (ID)
)^
-- end MYPROJECT_PRODUCER
-- begin MYPROJECT_PRODUCT
create table MYPROJECT_PRODUCT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    PRODUCER_ID uuid not null,
    PRICE decimal(19, 2) not null,
    --
    primary key (ID)
)^
-- end MYPROJECT_PRODUCT
-- begin MYPROJECT_STORE
create table MYPROJECT_STORE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CITY varchar(255),
    STREET varchar(255),
    BUILDING varchar(255),
    --
    NUMBER varchar(255) not null,
    NAME varchar(255) not null,
    RETAILER_ID uuid not null,
    TYPE varchar(50),
    --
    primary key (ID)
)^
-- end MYPROJECT_STORE
-- begin MYPROJECT_RETAILER
create table MYPROJECT_RETAILER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    FULL_NAME varchar(255),
    --
    primary key (ID)
)^
-- end MYPROJECT_RETAILER

-- begin MYPROJECT_STORE_PRODUCT
create table MYPROJECT_STORE_PRODUCT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PRODUCT_ID uuid not null,
    PRICE decimal(19, 2) not null,
    AMOUNT integer not null,
    STORE_ID uuid not null,
    --
    primary key (ID)
)^
-- end MYPROJECT_STORE_PRODUCT
-- begin MYPROJECT_PRICE_HISTORY
create table MYPROJECT_PRICE_HISTORY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PRODUCT_ID uuid not null,
    STORE_ID uuid not null,
    PRICE decimal(19, 2) not null,
    DATE date not null,
    --
    primary key (ID)
)^
-- end MYPROJECT_PRICE_HISTORY
-- begin MYPROJECT_PURCHASE
create table MYPROJECT_PURCHASE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    STORE_ID uuid not null,
    --
    primary key (ID)
)^
-- end MYPROJECT_PURCHASE
-- begin MYPROJECT_PURCHASE_PRODUCT
create table MYPROJECT_PURCHASE_PRODUCT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PRODUCT_ID uuid not null,
    AMOUNT integer not null,
    PURCHASE_ID uuid,
    --
    primary key (ID)
)^
-- end MYPROJECT_PURCHASE_PRODUCT
