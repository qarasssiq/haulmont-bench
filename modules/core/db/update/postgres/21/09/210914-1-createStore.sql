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
);