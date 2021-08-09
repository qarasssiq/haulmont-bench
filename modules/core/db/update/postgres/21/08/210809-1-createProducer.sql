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
);