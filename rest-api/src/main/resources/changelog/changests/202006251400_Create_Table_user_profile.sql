CREATE TABLE quiz_entity
(

    address               varchar NOT NULL,
    inn                   varchar NOT NULL,
    last_update_time    timestamp NOT NULL,
    name                  varchar NOT NULL,
    ID                    varchar NOT NULL,
    created_at          timestamp NOT NULL,
    updated_at                   timestamp,
    access_token                   varchar,
    fns_request_count                  int,
    password              varchar NOT NULL,
    phone                 varchar NOT NULL,
    load_count                int NOT NULL,
    email                          varchar,
    name                           varchar,
    PRIMARY KEY (ID)
);
