 ALTER TABLE reply ADD COLUMN modified_by BIGINT;
 ALTER TABLE reply ADD constraint FKoc3papw02agfcia1hmjontq89 foreign key (modified_by) references users(id);

  ALTER TABLE topics ADD COLUMN modified_by BIGINT;
  ALTER TABLE topics ADD constraint FKofcia1hmjontq89c3papw02ag foreign key (modified_by) references users(id);