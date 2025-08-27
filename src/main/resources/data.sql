INSERT INTO categories (name, description) VALUES ('Electronics', 'Gadgets and devices');
INSERT INTO products (name, description, price, stock) VALUES ('Smartphone', 'Latest model', 999.99, 50);
INSERT INTO product_category (product_id, category_id) SELECT p.id, c.id FROM products p, categories c WHERE p.name = 'Smartphone' AND c.name = 'Electronics';
INSERT INTO users (name, email, password, role) VALUES ('Admin', 'admin@grokshop.com', 'temp', 'ADMIN');
INSERT INTO users (name, email, password, role) VALUES ('Cliente', 'cliente@grokshop.com', 'temp', 'CLIENTE');
INSERT INTO orders (user_id, total, status, created_at) SELECT u.id, 999.99, 'PENDENTE', '2025-08-27 18:00:00' FROM users u WHERE u.email = 'admin@grokshop.com';
INSERT INTO cart_items (order_id, product_id, quantity, subtotal) SELECT o.id, p.id, 1, 999.99 FROM orders o, products p WHERE o.status = 'PENDENTE' AND p.name = 'Smartphone';