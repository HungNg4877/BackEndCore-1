-- CRUD User API permissions
INSERT INTO public.permission (id, name, method, modules, created_at, api_path,is_delete)
VALUES
('df4b0e5e-43bb-4ec3-9349-bd1c7f3e6f59', 'DELETE_USER', 'DELETE', 'USER', CURRENT_DATE, '/api/v1/user/{userId}',false),
('aad0e1d1-9a98-4b1e-a8fc-30f21e25c4b7', 'CREATE_USER', 'POST', 'USER', CURRENT_DATE, '/api/v1/user',false),
('be1e56a9-45a1-4bfb-8724-06a1c8ef56d5', 'READ_USER', 'GET', 'USER', CURRENT_DATE, '/api/v1/user/{userId}',false),
('c66f58c6-f348-4b89-bc3e-e52af5e47b7b', 'UPDATE_USER', 'PUT', 'USER', CURRENT_DATE, '/api/v1/user/{userId}',false);

-- CRUD Post API permissions
INSERT INTO public.permission (id, name, method, modules, created_at, api_path,is_delete)
VALUES
('ca1e01f1-2a9b-4e58-985e-cc9c5423e2d1', 'CREATE_POST', 'POST', 'POST', CURRENT_DATE, '/api/v1/post',false),
('db5b9f42-8c31-49c4-a1a5-e12ae28f47c1', 'GET_POST', 'GET', 'POST', CURRENT_DATE, '/api/v1/post/{postId}',false),
('ec2b9e87-6b3b-4882-9355-6f31c1d81f0d', 'UPDATE_POST', 'PUT', 'POST', CURRENT_DATE, '/api/v1/post',false),
('fa9b0d8d-b6f9-41bc-bdb4-6d5e8e28e5e2', 'DELETE_POST', 'DELETE', 'POST', CURRENT_DATE, '/api/v1/post/{postId}',false),
('fa9b0d8d-b6f9-41bc-bdb4-6d5e8e28e5e3', 'GET_NEWS', 'GET', 'POST', CURRENT_DATE, '/api/v1/post/news',false),
('fa9b0d8d-b6f9-41bc-bdb4-6d5e8e28e5e4', 'REPORT_POST', 'POST', 'POST', CURRENT_DATE, '/api/v1/post/news',false),
('fa9b0d8d-b6f9-41bc-bdb4-6d5e8e28e5e5', 'SHARE_POST', 'POST', 'POST', CURRENT_DATE, '/api/v1/post/news',false);

-- Insert roles first to ensure they exist before role_permission
INSERT INTO public.role (id, name, created_at,is_delete)
VALUES
  ('6b57f6ba-0155-479e-a887-2217a8d98991', 'admin', CURRENT_DATE,false),
  ('9e90e372-1c3d-46a8-88e5-5148c5a27c91', 'user', CURRENT_DATE,false);

INSERT INTO public.setting (id, key, value, created_at, last_modified) VALUES ('a66aa00d-db57-41ce-a2e6-73699318d0a9', 'MAX_REPORTS', '5', '2024-10-21 13:48:55.034981', '2024-10-21 13:48:55.034981');
INSERT INTO public.setting (id, key, value, created_at, last_modified) VALUES ('f362ab8c-af3e-47ff-9665-fd85eb046ddd', 'APPROVE_TIME', '100000', '2024-10-21 13:49:17.053450', '2024-10-21 13:49:17.053450');


