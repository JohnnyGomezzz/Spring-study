BEGIN;

DROP TABLE IF EXISTS categories CASCADE;
CREATE TABLE categories (id bigserial PRIMARY KEY, title VARCHAR(255));
INSERT INTO categories (title) VALUES
('Фрукты-овощи'),
('Молочные продукты'),
('Хлебобулочные изделия');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), category_id bigint REFERENCES categories (id), price int);
INSERT INTO products (title, category_id, price) VALUES
('апельсин', 1, 50),
('помидор', 1, 80),
('батон', 3, 50),
('свердловская булочка', 3, 45),
('йогурт', 2, 50),
('кефир', 2, 40);

COMMIT;