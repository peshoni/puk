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
	INSERT INTO "topics" ("id", "created_at", "modified_at", "title", "user_id", "modified_by") VALUES
	(1, '2021-06-20 18:21:16.285', '2021-06-20 18:21:16.285', 'Fishing', 1, NULL),
	(2, '2021-06-21 18:34:57', '2021-06-21 18:34:59', 'Beers', 2, NULL),
	(3, '2021-06-21 18:35:55', '2021-06-21 18:35:56', 'Cars', 4, NULL);

	INSERT INTO "reply" ("id", "created_at", "modified_at", "text", "topic_id", "user_id", "modified_by") VALUES
	(1, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 1, NULL),
	(2, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 2, NULL),
	(3, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 3, NULL),
	(4, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 1, NULL),
	(5, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 2, 2, NULL),
	(6, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 2, 3, NULL),
	(7, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 3, 1, NULL),
	(8, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 2, NULL),
	(9, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 3, NULL),
	(10, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 1, NULL),
	(11, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 2, NULL),
	(12, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 3, NULL),
	(13, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 3, NULL),
	(14, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 3, NULL),
	(15, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 3, NULL),
	(16, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 3, NULL),
	(17, '2021-06-20 18:21:21.928', '2021-06-20 18:21:21.928', 'reply...', 1, 1, NULL);

	INSERT INTO "actions" ("id", "user_id", "topic_id", "seen") VALUES
	(1, 1, 1, 'true'),
	(2, 2, 1, 'true'),
	(3, 3, 1, 'true');
