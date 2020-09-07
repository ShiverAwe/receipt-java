create table IF NOT EXISTS user_profile
(
    id                varchar(255)      not null
        constraint user_profile_pkey
            primary key,
    created_at        timestamp         not null,
    updated_at        timestamp,
    access_token      varchar(255),
    fns_request_count integer,
    password          varchar(255)      not null,
    phone             varchar(255)      not null
        constraint uk_1un6sdkbtaspkwmsiferm1dhm
            unique,
    load_count        integer default 0 not null,
    email             varchar(255),
    name              varchar(255)
);

alter table user_profile
    owner to receipt_dev;

