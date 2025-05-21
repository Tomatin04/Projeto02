create table users(

    id bigint not null auto_increment,
    nome varchar(128) not null,
    email varchar(128) not null unique,
    senha varchar(80) not null,

    primary key(id)

);

