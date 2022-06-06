create table accident (
id serial primary key,
name text,
text text,
address text);
create table typeAccident (
id serial primary key,
name text);


create table rules (
id serial primary key,
name text);


create table accident_rules (
id serial primary key,
rules_id int references rules(id),
accident_id int references accident(id));

insert into typeaccident (name)
values ('Управление транспортным средством, не зарегистрированным в установленном порядке'),
       ('Передача управления транспортным средством лицу, не имеющему при себе документов на право управления им'),
       ('Превышение установленной скорости движения транспортного средства на величину более 20, но не более 40 километров в час'),
       ('Нарушение правил расположения транспортного средства на проезжей части дороги, встречного разъезда, а равно движение по обочинам или пересечение организованной транспортной или пешей колонны либо занятие места в ней'),
       ('Поворот налево или разворот в нарушение требований, предписанных дорожными знаками или разметкой проезжей части дороги');

insert into rules (name) values ('12.1 ч.1 КоАП РФ'),
                                ('12.3 ч.3 КоАП РФ'),
                                ('12.9 ч.2 КоАП РФ'),
                                ('12.16 ч.3 КоАП РФ'),('12.16 ч.2 КоАП РФ');