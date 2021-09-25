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
    STORE_PRODUCT_ID uuid not null,
    PRICE decimal(19, 2) not null,
    DATE date not null,
    --
    primary key (ID)
);