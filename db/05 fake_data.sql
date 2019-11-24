-- person
insert into person values (person_seq.nextval, 'manager', null, 'manager', to_date('19000101', 'YYYYMMDD'), null, 'manager@admin.com', '0000000000', 1);
insert into person values (person_seq.nextval, 'marketing', null, 'marketing', to_date('19000101', 'YYYYMMDD'), null, 'marketing@admin.com', '0000000000', 1);
insert into person values (person_seq.nextval, 'sale', null, 'sale', to_date('19000101', 'YYYYMMDD'), null, 'sale@admin.com', '0000000000', 1);
insert into person values (person_seq.nextval, 'customer', null, 'customer', to_date('19000101', 'YYYYMMDD'), null, 'customer@customer.com', '0000000000', 1);


--user
insert into users values (user_seq.nextval, 'manager', 'manager', 1, null, 1);
insert into users values (user_seq.nextval, 'marketing', 'marketing', 1, null, 2);
insert into users values (user_seq.nextval, 'sale', 'sale', 1, null, 3);
insert into users values (user_seq.nextval, 'customer', 'customer', 1, null, 4);


--user_role
insert into user_role values (1, 5);
insert into user_role values (2, 4);
insert into user_role values (3, 3);
insert into user_role values (4, 2);



--customer
insert into customer values (customer_seq.nextval, 1, 4, null);


/*---------------------------------------------*/

--salary
insert into salary values (salary_seq.nextval, 5500000, 1.0, 1.0);
insert into salary values (salary_seq.nextval, 7500000, 1.5, 1.0);
insert into salary values (salary_seq.nextval, 8500000, 1.8, 1.0);
insert into salary values (salary_seq.nextval, 10500000, 2.0, 1.5);

--position
insert into position values (position_seq.nextval, 'Nhân viên', 1);
insert into position values (position_seq.nextval, 'Phó phòng', 2);
insert into position values (position_seq.nextval, 'Trưởng phòng', 2);
insert into position values (position_seq.nextval, 'Quản lý đon vị', 3);
insert into position values (position_seq.nextval, 'Giám đốc', 4);


--department
insert into department values (department_seq.nextval, 'Sale', '0123456789');
insert into department values (department_seq.nextval, 'Marketing', '0123456789');
insert into department values (department_seq.nextval, 'Manager', '0123456789');
insert into department values (department_seq.nextval, 'Warehouse', '0123456789');
insert into department values (department_seq.nextval, 'BOD', '0123456789');

