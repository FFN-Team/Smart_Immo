insert into home (marital_status)
values ('COHABITATION');

update prospect
set fk_home = 1
where id_prospect = 4;

insert into owner (fk_prospect)
values (4);

insert into property_owner(acquisition_date, main, fk_owner)
values ('2015-06-06', true, 1);