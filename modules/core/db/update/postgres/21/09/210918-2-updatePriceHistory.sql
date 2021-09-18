alter table MYPROJECT_PRICE_HISTORY rename column store_id to store_id__u74346 ;
alter table MYPROJECT_PRICE_HISTORY alter column store_id__u74346 drop not null ;
alter table MYPROJECT_PRICE_HISTORY drop constraint FK_MYPROJECT_PRICE_HISTORY_ON_STORE ;
drop index IDX_MYPROJECT_PRICE_HISTORY_ON_STORE ;
alter table MYPROJECT_PRICE_HISTORY rename column product_id to product_id__u63263 ;
alter table MYPROJECT_PRICE_HISTORY alter column product_id__u63263 drop not null ;
alter table MYPROJECT_PRICE_HISTORY drop constraint FK_MYPROJECT_PRICE_HISTORY_ON_PRODUCT ;
drop index IDX_MYPROJECT_PRICE_HISTORY_ON_PRODUCT ;
-- alter table MYPROJECT_PRICE_HISTORY add column STORE_PRODUCT_ID uuid ^
-- update MYPROJECT_PRICE_HISTORY set STORE_PRODUCT_ID = <default_value> ;
-- alter table MYPROJECT_PRICE_HISTORY alter column STORE_PRODUCT_ID set not null ;
alter table MYPROJECT_PRICE_HISTORY add column STORE_PRODUCT_ID uuid not null ;
