--liquibase formatted sql
--changeset rmartikan:create_roles_table splitStatements:true endDelimiter:;

create table ROLES (
   id bigserial primary key,
   name varchar(50) unique not null
);

-- Insert pre-defined roles.
insert into ROLES(name) values('ROLE_SUPER_ADMIN');
insert into ROLES(name) values('ROLE_ADMIN');
insert into ROLES(name) values('ROLE_USER');