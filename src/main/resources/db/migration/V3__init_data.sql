-- All Permission for Admin
INSERT INTO role_permission (role_id, permission_id)
SELECT '6b57f6ba-0155-479e-a887-2217a8d98991', id FROM permission;

-- Permission for User
INSERT INTO role_permission (role_id, permission_id)
SELECT '9e90e372-1c3d-46a8-88e5-5148c5a27c91', id
FROM permission
WHERE name NOT IN ('CREATE_USER', 'READ_USER', 'UPDATE_USER', 'DELETE_USER');

-- Insert 2 users
INSERT INTO public.users (id, email, password, status, first_name, last_name, date_of_birth, present_address,
permanent_address, phone_number, city, country, state, profile_picture_url, cover_picture_url, is_delete,
last_login, created_at, last_modified, role_id)
VALUES
  ('f1b4cd98-9a3f-4045-b107-5cd2b0b089c7', 'user1@gmail.com', '$2a$12$dLGiLD8F.1fqkPk6j6MiJ.IFyMtLZlQlNuEHutTlroTDUGiCYZWeO', 'active', 'User', 'One', '1990-01-01', 'Address 1', 'Address 2', '123456789', 'City 1', 'Country 1', 'State 1', 'https://example.com/profile1.jpg', 'https://example.com/cover1.jpg', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '9e90e372-1c3d-46a8-88e5-5148c5a27c91'),
  ('d5b9c84c-65e7-46b7-bb35-619bd8c49a24', 'user2@gmail.com', '$2a$12$dLGiLD8F.1fqkPk6j6MiJ.IFyMtLZlQlNuEHutTlroTDUGiCYZWeO', 'active', 'User', 'Two', '1992-02-02', 'Address 3', 'Address 4', '987654321', 'City 2', 'Country 2', 'State 2', 'https://example.com/profile2.jpg', 'https://example.com/cover2.jpg', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '9e90e372-1c3d-46a8-88e5-5148c5a27c91');

-- Insert 2 admins
INSERT INTO public.users (id, email, password, status, first_name, last_name, date_of_birth, present_address,
permanent_address, phone_number, city, country, state, profile_picture_url, cover_picture_url, is_delete,
last_login, created_at, last_modified, role_id)
VALUES
  ('b3946278-6846-4cf4-bde5-c7ed653a7e57', 'admin1@gmail.com', '$2a$12$dLGiLD8F.1fqkPk6j6MiJ.IFyMtLZlQlNuEHutTlroTDUGiCYZWeO', 'active', 'Admin', 'One', '1985-03-03', 'Admin Address 1', 'Admin Address 2', '112233445', 'Admin City', 'Admin Country', 'Admin State', 'https://example.com/admin1_profile.jpg', 'https://example.com/admin1_cover.jpg', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '6b57f6ba-0155-479e-a887-2217a8d98991'),
  ('4a5c906f-9bc5-4b09-8b5d-39fbc238315d', 'admin2@gmail.com', '$2a$12$dLGiLD8F.1fqkPk6j6MiJ.IFyMtLZlQlNuEHutTlroTDUGiCYZWeO', 'active', 'Admin', 'Two', '1987-04-04', 'Admin Address 3', 'Admin Address 4', '556677889', 'Admin City 2', 'Admin Country 2', 'Admin State 2', 'https://example.com/admin2_profile.jpg', 'https://example.com/admin2_cover.jpg', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '6b57f6ba-0155-479e-a887-2217a8d98991');
