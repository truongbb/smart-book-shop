-- person
create table gender(
    id int primary key,
    gender_value nvarchar2(50) not null
);

create table person(
    id int primary key,
    first_name nvarchar2(50) not null,
    mid_name nvarchar2(50),
    last_name varchar2(50) not null,
    birthday date not null,
    address nvarchar2(500),
    email nvarchar2(100) not null,
    phone_number nvarchar2(20) not null,
    gender_id number not null constraint gender_fk references gender (id) on delete cascade
);

-- customer
create table customer_type(
    id int primary key,
    customer_type nvarchar2(200) not null
);

create table ship_address(
    id int primary key,
    address nvarchar2(500) not null,
    is_main_addr number not null
);
comment on column ship_address.is_main_addr is '1:YES, 0:NO';

create table customer(
    id int primary key,
    customer_type_id number constraint customer_type_fk references customer_type(id) on delete cascade,
    person_id number constraint person_fk unique references person(id) on delete cascade,
    ship_addr_id number constraint ship_adr_fk references ship_address(id) on delete cascade
);

-- account
create table role(
    id int primary key,
    role_name nvarchar2(50) not null
);

create table account(
    id int primary key,
    username nvarchar2(30) not null,
    password nvarchar2(50) not null,
    is_active number not null,
    avatar_url nvarchar2(500),
    role_id int not null constraint role_fk references role(id) on delete cascade,
    person_id int not null constraint account_person_fk references person(id) on delete cascade
);
comment on column account.is_active is '1:ACTIVE, 0:DEACTIVE';

-- employee
create table department(
    id int primary key,
    name nvarchar2(200) not null,
    phone_number nvarchar2(20) not null
);

create table ethnic(
    id int primary key,
    value nvarchar2(200) not null
);

create table salary(
    salary_level int primary key,
    basic_salary float not null,
    salary_rate float not null,
    allowance_rate float not null
);

create table position(
    id int primary key,
    name nvarchar2(200) not null,
    salary_level int not null constraint salary_level_fk references salary(salary_level) on delete cascade
);


create table literacy(
    id int primary key,
    value nvarchar2(100) not null
);

create table employee(
    id int primary key,
    start_working_date date not null,
    profile_img_url nvarchar2(500),
    literacy_id int not null constraint literacy_fk references literacy(id) on delete cascade,
    position_id int not null constraint position_fk references position(id) on delete cascade,
    department_id int not null constraint department_fk references department(id) on delete cascade,
    ethnic_id int not null constraint ethnic_fk references ethnic(id) on delete cascade,
    person_id int not null constraint emp_pserson_fk unique references person(id) on delete cascade
);

-- product
create table product_category(
    id int primary key,
    category nvarchar2(200) not null
);

create table discount(
    id int primary key,
    discount_rate float not null,
    begin_date date not null,
    end_date date not null
);

create table warehouse(
    id int primary key,
    code nvarchar2(20) not null,
    name nvarchar2(100) not null,
    address nvarchar2(300) not null,
    phone_number nvarchar2(20) not null
);


create table product(
    id int primary key,
    unit_price float not null,
    description nvarchar2(500),
    quantity number not null,
    product_image_url nvarchar2(500),
    is_active number not null,
    warehouse_id number not null constraint warehouse_fk references warehouse(id) on delete cascade,
    discount_id number not null constraint discount_fk references discount(id) on delete cascade,
    category_id number not null constraint category_fk references product_category(id) on delete cascade
);
comment on column product.is_active is '1:ACTIVE, 0:DEACTIVE';

--book
create table publisher(
    id int primary key,
    name nvarchar2(300) not null,
    phone_number nvarchar2(20),
    email nvarchar2(100) not null,
    address nvarchar2(500) not null
);

create table author(
    id int primary key,
    name nvarchar2(200) not null,
    description nvarchar2(1000)
);

create table book_category(
    id int primary key,
    category nvarchar2(200) not null
);

create table book(
    id int primary key,
    name nvarchar2(500) not null,
    published_year int not null,
    total_page int not null,
    category_id int not null constraint book_category_fk references book_category(id) on delete cascade,
    author_id int not null constraint author_fk references author(id) on delete cascade,
    publisher_id int not null constraint publisher_fk references publisher(id) on delete cascade,
    product_id int not null constraint product_fk unique references product(id) on delete cascade
);

-- order
create table purchase_method(
    id int primary key,
    method nvarchar2(100) not null
);

create table orders(
    id int primary key,
    product_cash float not null,
    shipping_cash float not null,
    order_date date not null,
    expired_date date not null,
    delivery_date date,
    received_date date,
    cashier int not null constraint employee_fk references employee(id) on delete cascade,
    purchase_method_id int not null constraint purchase_method_fk references purchase_method(id) on delete cascade,
    customer_id int not null constraint customer_fk references customer(id) on delete cascade
);

create table order_product(
    id int primary key,
    order_id int not null constraint order_fk references orders(id) on delete cascade,
    product_id int not null constraint order_product_dtl_fk references product(id) on delete cascade
);


