CREATE TABLE comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comment TEXT NOT NULL,
    id_origin BIGINT,
    id_new BIGINT,

    CONSTRAINT fk_id_origin
    FOREIGN KEY (id_origin)
    REFERENCES comments(id)
    ON DELETE SET NULL,

    CONSTRAINT fk_id_new
    FOREIGN KEY (id_new)
    REFERENCES news(id)
    ON DELETE SET NULL
);