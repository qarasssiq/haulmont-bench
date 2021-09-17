update MYPROJECT_STORE set CITY = '' where CITY is null ;
alter table MYPROJECT_STORE alter column CITY set not null ;
update MYPROJECT_STORE set STREET = '' where STREET is null ;
alter table MYPROJECT_STORE alter column STREET set not null ;
update MYPROJECT_STORE set BUILDING = '' where BUILDING is null ;
alter table MYPROJECT_STORE alter column BUILDING set not null ;
update MYPROJECT_STORE set TYPE = 'Convenience store' where TYPE is null ;
alter table MYPROJECT_STORE alter column TYPE set not null ;
