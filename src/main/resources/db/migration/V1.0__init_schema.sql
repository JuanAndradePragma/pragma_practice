create table if not exists persons
(
    uuid                        uuid not null primary key,
    name                        varchar(255) not null,
    email                       varchar(255) unique not null,
    document                    varchar(255) unique not null
);