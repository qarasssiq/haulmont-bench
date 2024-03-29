alter table MYPROJECT_STORE add constraint FK_MYPROJECT_STORE_ON_STORE_RETAILER foreign key (STORE_RETAILER_ID) references MYPROJECT_RETAILER(ID);
create unique index IDX_MYPROJECT_STORE_UK_STORE_NUM on MYPROJECT_STORE (STORE_NUM) where DELETE_TS is null ;
create index IDX_MYPROJECT_STORE_ON_STORE_RETAILER on MYPROJECT_STORE (STORE_RETAILER_ID);
