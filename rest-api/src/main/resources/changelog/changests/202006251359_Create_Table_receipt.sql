create table receipt
(
    id                     bigserial        not null
        constraint receipt_pkey
            primary key,
    date                   timestamp        not null,
    fd                     varchar(255)     not null,
    fn                     varchar(255)     not null,
    fp                     varchar(255)     not null,
    provider               varchar(255),
    status                 varchar(255)     not null,
    sum                    double precision not null,
    place_id               bigint
        constraint fk2x2wkgo5re36vwpxctma1o4s6
            references place,
    merchant_inn           varchar(255),
    merchant_name          varchar(255),
    merchant_place_address varchar(255),
    user_profile_id        varchar(255)
        constraint fk7n4ubagih0n1tdwvx4h7qdn5d
            references user_profile,
    load_attempts          bigint default 0
);

alter table receipt
    owner to receipt_dev;
