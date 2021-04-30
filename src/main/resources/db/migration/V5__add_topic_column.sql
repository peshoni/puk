ALTER TABLE reply
ADD COLUMN user_id BIGINT;
alter table reply add constraint FKoc3papw02ag1hmjontq89fcia foreign key (user_id) references users;