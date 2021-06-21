--drop table if exists flyway_schema_history CASCADE;
--CREATE SCHEMA IF NOT EXISTS ;
drop table if exists reply CASCADE;
drop table if exists topics CASCADE;
drop table if exists users CASCADE;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;

create table reply (id bigint not null, created_at timestamp, modified_at timestamp, text varchar(255), topic_id bigint, user_id bigint, primary key (id));
create table topics (id bigint not null, created_at timestamp, modified_at timestamp, title varchar(255), user_id bigint, primary key (id));

CREATE TABLE "users" (
	"id" BIGINT NOT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"modified_at" TIMESTAMP NULL DEFAULT NULL,
	"first_name" VARCHAR(255) NOT NULL,
	"last_name" VARCHAR(255) NOT NULL,
	"password" VARCHAR(255) NOT NULL,
	"role" VARCHAR(255) NOT NULL,
	"username" VARCHAR(255) NOT NULL UNIQUE,
	PRIMARY KEY ("id")
)
;
alter table topics add constraint UK_71rjsqaorlydivvwh8xgousre unique (title);
alter table reply add constraint FKsn9spnfgg9g3i4m52afs5cypx foreign key (topic_id) references topics;
alter table reply add constraint FKoc3papw02ag1hmjontq89fcia foreign key (user_id) references users(id);
alter table topics add constraint FKoc3papwmjontq89fcia02ag1h foreign key (user_id) references users;

