insert into usuario (instante, login, senha) values ('2021-04-08T13:55:47.724619500', 'caio@email.com', '$2a$10$QhciAyk/pdM3IYE.t9Y.ROaBLkkjdQy7mqZxZF8CPgHDxQkYyiEIG')
insert into usuario (instante, login, senha) values ('2021-04-08T13:55:47.724619500', 'maria@email.com', '$2a$10$QhciAyk/pdM3IYE.t9Y.ROaBLkkjdQy7mqZxZF8CPgHDxQkYyiEIG')

insert into categoria (nome, categoria_mae_id) values ('Tecnologia', null), ('Smartphone', 1), ('Andorid', 2), ('IOS', 2), ('Notebook', 1)

insert into produto (descricao, instante, nome, quantidade, valor, categoria_id, usuario_id) values ('Um belo redmi note 10', '2021-04-09 13:01:39.527087', 'Redmi note 10', 2, 1100.00, 2, 1 )
