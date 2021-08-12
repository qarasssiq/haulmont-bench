alter table MYPROJECT_STORE rename column num to num__u93879 ;
alter table MYPROJECT_STORE alter column num__u93879 drop not null ;
-- alter table MYPROJECT_STORE add column NUMBER varchar(255) ^
-- update MYPROJECT_STORE set NUMBER = <default_value> ;
-- alter table MYPROJECT_STORE alter column NUMBER set not null ;
alter table MYPROJECT_STORE add column NUMBER varchar(255) ;
