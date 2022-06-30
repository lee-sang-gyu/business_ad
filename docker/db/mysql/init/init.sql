create table if not exists advertiser
(
    id           VARCHAR(10) PRIMARY KEY,
    name         VARCHAR(100),
    index idx_name (name)
);
