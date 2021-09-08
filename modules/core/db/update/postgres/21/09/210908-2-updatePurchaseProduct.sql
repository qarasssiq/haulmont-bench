alter table MYPROJECT_PURCHASE_PRODUCT add column IS_DISCOUNT boolean ^
update MYPROJECT_PURCHASE_PRODUCT set IS_DISCOUNT = false where IS_DISCOUNT is null ;
alter table MYPROJECT_PURCHASE_PRODUCT alter column IS_DISCOUNT set not null ;
