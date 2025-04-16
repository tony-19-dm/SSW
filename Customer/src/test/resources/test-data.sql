-- Добавляем клиентов
INSERT INTO customer (name, street, zipcode)
VALUES
  ('John Doe', 'Main St 123', '10001'),
  ('Jane Smith', 'Park Ave 456', '20002');

-- Добавляем товары
INSERT INTO item (shipping_weight_value, measurement_name, description)
VALUES
  (2.5, 'kg', 'Электронная книга'),
  (15.0, 'kg', 'Офисный стол');

-- Добавляем заказы (предполагаем, что customer_id 1 и 2 существуют)
INSERT INTO orders (date, status, customer_id)
VALUES
  (CURRENT_TIMESTAMP, 'NEW', 1),
  ('2024-03-15 14:30:00', 'PROCESSING', 2);

-- Добавляем детали заказов
INSERT INTO order_detail (quantity_value, measurement_name, tax_status, order_id, item_id)
VALUES
  (3, 'pcs', 'TAXABLE', 1, 1),
  (1, 'pcs', 'EXEMPT', 2, 2);

-- Добавляем платежи
INSERT INTO payment (amount, order_id, type)
VALUES
  (199.99, 1, 'CASH'),
  (599.50, 2, 'CREDIT');

-- Добавляем данные для платежа наличными
INSERT INTO cash (id, cash_tendered)
VALUES
  (1, 200.00);  -- id соответствует payment.id

-- Добавляем данные для кредитного платежа
INSERT INTO credit (id, number, type, exp_date)
VALUES
  (2, '4111111111111111', 'VISA', '2026-12-01');  -- id соответствует payment.id