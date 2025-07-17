CREATE TABLE clients (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    bonus_points INT DEFAULT 0 CHECK (bonus_points >= 0),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE product_categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    display_order SMALLINT NOT NULL DEFAULT 0
);

CREATE TABLE products (
    id UUID PRIMARY KEY,
    category_id INT REFERENCES product_categories(id) ON DELETE SET NULL,
    name VARCHAR(100) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    description TEXT,
    volume VARCHAR(20),
    price NUMERIC(10,2) NOT NULL CHECK (price > 0),
    is_active BOOL DEFAULT true,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP
);

CREATE TABLE orders (
    id UUID PRIMARY KEY,
    client_id UUID REFERENCES clients(id) ON DELETE SET NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'new' CHECK (status IN ('new', 'preparing', 'ready', 'completed', 'cancelled')),
    total_sum NUMERIC(10,2) NOT NULL CHECK (total_sum >= 0),
    created_at TIMESTAMP DEFAULT NOW(),
    completed_at TIMESTAMP,
    bonus_points_used INT DEFAULT 0 CHECK (bonus_points_used >= 0),
    bonus_points_earned INT DEFAULT 0 CHECK (bonus_points_earned >= 0)
);

CREATE TABLE order_items (
    order_id UUID REFERENCES orders(id) ON DELETE CASCADE,
    product_id UUID REFERENCES products(id) ON DELETE RESTRICT,
    quantity INT NOT NULL CHECK (quantity > 0),
    price_at_order NUMERIC(10,2) NOT NULL,
    PRIMARY KEY (order_id, product_id)
);

CREATE TABLE promotions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    image_url VARCHAR(255),
    description TEXT,
    discount_type VARCHAR(20) CHECK (discount_type IN ('percentage', 'fixed', 'buy_x_get_y')),
    discount_value NUMERIC(10,2),
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    is_active BOOL DEFAULT true
);
