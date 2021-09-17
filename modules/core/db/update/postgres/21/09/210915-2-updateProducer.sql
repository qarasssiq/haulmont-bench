update MYPROJECT_PRODUCER set CITY = '' where CITY is null ;
alter table MYPROJECT_PRODUCER alter column CITY set not null ;
update MYPROJECT_PRODUCER set STREET = '' where STREET is null ;
alter table MYPROJECT_PRODUCER alter column STREET set not null ;
update MYPROJECT_PRODUCER set BUILDING = '' where BUILDING is null ;
alter table MYPROJECT_PRODUCER alter column BUILDING set not null ;
