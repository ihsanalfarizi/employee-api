-- Buat table employees sesuai Entity Employee
CREATE TABLE employees (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    position VARCHAR(50) NOT NULL,
    salary DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert contoh data
INSERT INTO employees (name, email, position, salary) VALUES
('Budi Santoso', 'budi.santoso@company.com', 'Software Engineer', 12000000.00),
('Siti Nurhaliza', 'siti.nurhaliza@company.com', 'HR Manager', 15000000.00),
('Andi Wijaya', 'andi.wijaya@company.com', 'Financial Analyst', 11000000.00),
('Dewi Lestari', 'dewi.lestari@company.com', 'DevOps Engineer', 13000000.00),
('Rudi Hartono', 'rudi.hartono@company.com', 'Marketing Manager', 14000000.00);

-- Index untuk mempercepat search berdasarkan email
CREATE INDEX idx_employees_email ON employees(email);
