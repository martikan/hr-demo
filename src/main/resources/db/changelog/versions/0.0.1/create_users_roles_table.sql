--liquibase formatted sql
--changeset rmartikan:create_users_roles_table splitStatements:true endDelimiter:;

create table USERS_ROLES (
     user_id bigint not null,
     role_id bigint not null,
     primary key(user_id, role_id),
     constraint FK_USERS_ROLES_USERS foreign key(user_id)
         references USERS(id)
         on delete cascade,
     constraint FK_USERS_ROLES_ROLES foreign key(role_id)
         references ROLES(id)
);

-- Insert Admin roles.
insert into USERS_ROLES (user_id, role_id) values (1, 1);
insert into USERS_ROLES (user_id, role_id) values (1, 2);
insert into USERS_ROLES (user_id, role_id) values (1, 3);