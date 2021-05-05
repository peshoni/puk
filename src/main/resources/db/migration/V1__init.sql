--drop table if exists flyway_schema_history CASCADE;
--CREATE SCHEMA IF NOT EXISTS ;
drop table if exists reply CASCADE;
drop table if exists topics CASCADE;
drop table if exists users CASCADE;

drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;

create table reply (id bigint not null, created_at timestamp, modified_at timestamp, text varchar(255), topic_id bigint, user_id bigint, primary key (id));
create table topics (id bigint not null, created_at timestamp, modified_at timestamp, title varchar(255), user_id bigint, primary key (id));
create table users (id bigint not null, created_at timestamp, modified_at timestamp, first_name varchar(255), last_name varchar(255), password varchar(255), role varchar(255), username varchar(255), primary key (id));

alter table topics add constraint UK_71rjsqaorlydivvwh8xgousre unique (title);
alter table reply add constraint FKsn9spnfgg9g3i4m52afs5cypx foreign key (topic_id) references topics;
alter table reply add constraint FKoc3papw02ag1hmjontq89fcia foreign key (user_id) references users(id);
alter table topics add constraint FKoc3papwmjontq89fcia02ag1h foreign key (user_id) references users;

