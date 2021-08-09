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
);