create table IF NOT EXISTS merchant
(
    id               bigserial    not null
        constraint merchant_pkey
            primary key,
    address          varchar(255) not null,
    inn              varchar(255) not null,
    last_update_time timestamp    not null,
    name             varchar(255) not null
);

alter table merchant
    owner to receipt_dev;

