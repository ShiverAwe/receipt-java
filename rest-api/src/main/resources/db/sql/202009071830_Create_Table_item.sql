create table item
(
    id         bigserial        not null
        constraint item_pkey
            primary key,
    amount     double precision not null,
    price      double precision not null,
    text       varchar(255)     not null,
    receipt_id bigint
        constraint fk8s305nsnv4wi5iuh7kkr52gqt
            references receipt
);

alter table item
    owner to receipt_dev;

