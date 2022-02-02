*create table accident (
id serial primary key,
name text,
text text,
address text);
create table typeAccident (
id serial primary key,
name text);

insert into typeAccident(name) values ('Машина и столб'),
                                      ('Две машины'), ('Машина и забор');


insert into accident (name, text, address) values
('Водитель, столб, ночь',
 'Начинающий водитель не смог разъехаться во дворе со столбом.
Отделался испугом и скрылся под покровом ночи. Пострадал багажник. Столб не пострадал ', 'Кесаева');

insert into accident (name, text, address) values
('Водитель и забор',
 'Начинающий водитель выехал из гаража прям в соседский забор.
Пострадал задний бампер(сколы и потертости). Водитель отделался скандалом', 'ТСН Дачи');

insert into accident (name, text, address) values
('Водитель и гараж',
 'Начинающий водитель въехал в стену гаража.
Пострадал передний бампер(сколы и потертости). Водитель отделался плохим настроением', 'ТСН Дачи');

insert into accident (name, text, address) values
('Водитель, пробка, окно',
'Водителя заворожил вид за окном во время простоя в пробке, поэтому он зажал педаль газа и въехал в спокойно стоющую в этой же пробке Toyota Camry.
Водитель отделался испорченным выходным и штрафом. Пострадал передний бамер - сломан пополам, также погнута рамка номера.', '5км');

/*alter table accident add column type_id int;*/
/*alter table accident add constraint type_constraint foreign key(type_id)
references typeAccident(id);*/