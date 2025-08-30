-- Inserir categorias
INSERT INTO categories (id, name, description) VALUES (1, 'Electronics', 'Electronic products');
INSERT INTO categories (id, name, description) VALUES (2, 'Computers', 'Computer hardware and software');

-- Inserir produtos
INSERT INTO products (id, name, description, price, stock) VALUES (1, 'Smartphone', 'High-end smartphone', 999.99, 50);
INSERT INTO products (id, name, description, price, stock) VALUES (2, 'Laptop', 'Basic laptop', 799.99, 30);

-- Associar produtos a categorias
INSERT INTO product_category (product_id, category_id) VALUES (1, 1);
INSERT INTO product_category (product_id, category_id) VALUES (2, 1);
INSERT INTO product_category (product_id, category_id) VALUES (2, 2);