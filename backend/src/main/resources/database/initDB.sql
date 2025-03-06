create table if not exists coffee (
    id uuid unique primary key,
    name varchar(50) not null
);

create table if not exists clients (
    id uuid unique primary key,
    name varchar(50) not null, --сделать проверку на банворды
    email varchar(100) unique not null,
    date_of_birth date,
    points numeric(10, 2) not null default 0.00,
    creation_date date not null,
    update_date date not null,
    is_deleted boolean not null default false
);

create table if not exists goods (
    id uuid unique primary key,
    category varchar(50) not null,
    name varchar(100) not null,
    size integer not null,
    composition varchar(1000) not null ,
    price numeric(10, 2) not null,
    image bytea not null,
    creation_date date not null,
    update_date date not null,
    is_deleted boolean not null default false
);

create table if not exists discounts (
    id uuid unique primary key,
    goods_id uuid not null references goods (id),
    percent integer not null check (percent between 0 and 100),
    -- TODO
    -- это можно делать с помощью например hangfire(альтернативу в java не знаю)
    -- оно выполняет таски на фоне, например через определенное время, или запускает таймеры на таски
    creation_date date not null, -- дата создания, сделать правило чтобы со временем само удаляло скидку
    update_date date not null,
    is_deleted boolean not null default false
);

create table if not exists coupons (
    id uuid unique primary key,
    clients_id uuid not null references clients (id),
    goods_id uuid not null references goods (id),
    percent integer not null check (percent between 0 and 100),
    creation_date date not null,
    update_date date not null,
    is_deleted boolean not null default false
);

create table if not exists orders (
    id uuid unique primary key,
    clients_id uuid not null references clients (id),
    total_amount numeric(10, 2) not null,
    creation_date date not null,
    update_date date not null,
    is_deleted boolean not null default false
);

create table if not exists order_items (
    id serial unique primary key,
    orders_id uuid not null references orders (id),
    goods_id uuid not null references goods (id),
    quantity integer not null,
    price numeric(10, 2) not null,
    creation_date date not null,
    update_date date not null,
    is_deleted boolean not null default false
);
