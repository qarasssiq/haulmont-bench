alter table MYPROJECT_ORDER add constraint FK_MYPROJECT_ORDER_ON_STORE foreign key (STORE_ID) references MYPROJECT_STORE(ID);
create index IDX_MYPROJECT_ORDER_ON_STORE on MYPROJECT_ORDER (STORE_ID);
