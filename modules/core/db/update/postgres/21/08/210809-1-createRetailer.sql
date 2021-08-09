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
);