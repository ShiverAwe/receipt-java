CREATE TABLE quiz_entity
(
    ID                      INT     NOT NULL,
    date               timestamp    NOT NULL,
    fd                      varchar NOT NULL,
    fn                      varchar NOT NULL,
    fp                      varchar NOT NULL,
    provider                         varchar,
    status                  varchar NOT NULL,
    sum                       float NOT NULL,
    place_id                             INT,
    merchant_inn                     varchar,
    merchant_name                    varchar,
    merchant_place_address           varchar,
    user_profile_id                  varchar,
    load_attempts                        INT,
    PRIMARY KEY (ID)
);
