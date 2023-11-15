insert into roles(role_name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into accounts(username, password, is_active)
values ('cosqun', '$2a$12$JGYyROlRdc1wOBswNWmHDOLt/lLZZvhOJGeL3jDiJSAPmwVMksSeu', true),
       ('maqsud', '$2a$12$JGYyROlRdc1wOBswNWmHDOLt/lLZZvhOJGeL3jDiJSAPmwVMksSeu', true);

insert into users(first_name, last_name, age, email, contact_number, account_id)
values ('Cosqun', 'Mammadov', '23', 'cosqun@gmail.com', '776006775', '1'),
       ('Maqsud', 'Ibrahimov', '34', 'maqsud@gmail.com', '503536721', '2');

insert into accounts_roles(account_id, role_id)
values ('1', '1'),
       ('2', '2');