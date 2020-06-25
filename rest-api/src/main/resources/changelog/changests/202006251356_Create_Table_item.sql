CREATE TABLE item
(
    ID             Int NOT NULL,
    amount       float NOT NULL,
    price        float NOT NULL,
    text       varchar NOT NULL,
    receipt_id              int,
    PRIMARY KEY (ID)
);
