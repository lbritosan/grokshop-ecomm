-- Criar categorias
INSERT INTO categories (id, name, description) VALUES (1, 'Electronics', 'Electronic products');

-- Criar produtos
INSERT INTO products (id, name, description, price, stock) VALUES (1, 'Smartphone', 'High-end smartphone', 999.99, 50);

-- Associar produtos a categorias
INSERT INTO product_categories (product_id, category_id) VALUES (1, 1);