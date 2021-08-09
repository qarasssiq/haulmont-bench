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
    PRODUCER_NAME varchar(255) not null,
    PRODUCER_FULL_NAME varchar(255),
    PRODUCER_USER_ID uuid,
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
    PRODUCT_NAME varchar(255) not null,
    PRODUCT_PRODUCER_ID uuid not null,
    PRODUCT_PRICE decimal(19, 2) not null,
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
    STORE_NUM varchar(255) not null,
    STORE_NAME varchar(255) not null,
    STORE_RETAILER_ID uuid not null,
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
    RETAILER_NAME varchar(255) not null,
    RETAILER_FULL_NAME varchar(255),
    --
    primary key (ID)
)^
-- end MYPROJECT_RETAILER
