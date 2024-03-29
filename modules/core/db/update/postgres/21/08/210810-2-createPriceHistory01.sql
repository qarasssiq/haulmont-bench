alter table MYPROJECT_PRICE_HISTORY add constraint FK_MYPROJECT_PRICE_HISTORY_ON_PRODUCT foreign key (PRODUCT_ID) references MYPROJECT_PRODUCT(ID);
alter table MYPROJECT_PRICE_HISTORY add constraint FK_MYPROJECT_PRICE_HISTORY_ON_STORE foreign key (STORE_ID) references MYPROJECT_STORE(ID);
create index IDX_MYPROJECT_PRICE_HISTORY_ON_PRODUCT on MYPROJECT_PRICE_HISTORY (PRODUCT_ID);
create index IDX_MYPROJECT_PRICE_HISTORY_ON_STORE on MYPROJECT_PRICE_HISTORY (STORE_ID);
