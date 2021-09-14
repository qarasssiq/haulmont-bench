create table MYPROJECT_STORE_USER_LINK (
    STORE_ID uuid,
    USER_ID uuid,
    primary key (STORE_ID, USER_ID)
);
