CREATE TABLE IF NOT EXISTS accounts
(
    id      INTEGER NOT NULL CONSTRAINT id PRIMARY KEY,
    amount  BIGINT DEFAULT 0
);

CREATE SEQUENCE IF NOT EXISTS account_uuid_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 100000
    CACHE 1;

CREATE UNIQUE INDEX IF NOT EXISTS id ON accounts (id);

ALTER TABLE accounts OWNER TO postgres;

