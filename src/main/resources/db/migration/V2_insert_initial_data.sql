-- Insert roles
INSERT INTO roles (id, name) VALUES
                                 (1, 'ROLE_ADMIN'),
                                 (2, 'ROLE_STAFF'),
                                 (3, 'ROLE_CUSTOMER');

-- Insert admin user
INSERT INTO users (id, first_name, last_name, email, created_at, updated_at)
VALUES (1, 'System', 'Admin', 'budreckytegermante@gmail.com', NOW(), NOW());

-- Assign admin role
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1);
