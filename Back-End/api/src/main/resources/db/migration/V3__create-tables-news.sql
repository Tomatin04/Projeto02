CREATE TABLE news (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo varchar(128) NOT NULL,
    conteudo TEXT NOT NULL,
    id_creator BIGINT NOT NULL,

    CONSTRAINT fk_id_creator
    FOREIGN KEY (id_creator)
    REFERENCES users(id)
);