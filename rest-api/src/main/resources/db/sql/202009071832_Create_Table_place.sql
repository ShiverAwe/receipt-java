create table place
(
    id   bigserial    not null
        constraint place_pkey
            primary key,
    text varchar(255) not null
);

alter table place
    owner to receipt_dev;

