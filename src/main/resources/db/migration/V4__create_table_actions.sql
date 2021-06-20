 ALTER TABLE reply ADD COLUMN modified_by BIGINT;
 ALTER TABLE reply ADD constraint FKoc3papw02agfcia1hmjontq89 foreign key (modified_by) references users(id);

  ALTER TABLE topics ADD COLUMN modified_by BIGINT;
  ALTER TABLE topics ADD constraint FKofcia1hmjontq89c3papw02ag foreign key (modified_by) references users(id);

CREATE TABLE IF NOT EXISTS "actions" (
	"id" BIGSERIAL,
	"user_id" BIGINT NOT NULL,
	"topic_id" BIGINT NOT NULL,
	"seen" BOOLEAN NOT NULL DEFAULT 'false',
	PRIMARY KEY ("id")
);

