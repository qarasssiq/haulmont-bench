alter table MYPROJECT_ORDER_PRODUCT add constraint FK_MYPROJECT_ORDER_PRODUCT_ON_PRODUCT foreign key (PRODUCT_ID) references MYPROJECT_PRODUCT(ID);
alter table MYPROJECT_ORDER_PRODUCT add constraint FK_MYPROJECT_ORDER_PRODUCT_ON_ORDER foreign key (ORDER_ID) references MYPROJECT_ORDER(ID);
create index IDX_MYPROJECT_ORDER_PRODUCT_ON_PRODUCT on MYPROJECT_ORDER_PRODUCT (PRODUCT_ID);
create index IDX_MYPROJECT_ORDER_PRODUCT_ON_ORDER on MYPROJECT_ORDER_PRODUCT (ORDER_ID);
