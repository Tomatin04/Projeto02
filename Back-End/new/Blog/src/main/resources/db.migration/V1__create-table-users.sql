CREATE TABLE users(
    id bigint not null auto_increment,
    username varchar(200) not null,
    password varchar(50) not null,
    firstname varchar(200) not null,
    enable BOOLEAN not null,

    primary key(id)
);
