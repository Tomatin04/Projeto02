ALTER TABLE comments
DROP CONSTRAINT fk_id_origin;

ALTER TABLE comments
ADD CONSTRAINT fk_id_origin
FOREIGN KEY (id_origin)
REFERENCES comments(id) ON DELETE CASCADE;