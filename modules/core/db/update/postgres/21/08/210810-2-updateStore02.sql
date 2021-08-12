-- update MYPROJECT_STORE set NUMBER = <default_value> where NUMBER is null ;
alter table MYPROJECT_STORE alter column NUMBER set not null ;
