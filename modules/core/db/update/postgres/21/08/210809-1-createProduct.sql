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
);