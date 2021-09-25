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
    CITY varchar(255) not null,
    STREET varchar(255) not null,
    BUILDING varchar(255) not null,
    --
    NUMBER varchar(255) not null,
    TOTAL_PRODUCTS_QUANTITY integer,
    LOCATION VARCHAR(100),
    NAME varchar(255) not null,
    RETAILER_ID uuid not null,
    TYPE varchar(50) not null,
    --
    primary key (ID)
);