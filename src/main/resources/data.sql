INSERT INTO groups (name, topic, description, type, disabled) VALUES ('groupName1', 'groupTopic1', 'groupDescription1', 'groupType1', false);

INSERT INTO Users (username, email, password, enabled, roles, ACCOUNT_NON_EXPIRED, ACCOUNT_NOT_LOCKED, CREDENTIALS_NON_EXPIRED, ONLINE) VALUES ('user1', 'user1@gmai.com', 'pass', true, 'ROLE_USER', true, true, true, false);

INSERT INTO Users (username, email, password, enabled, roles, ACCOUNT_NON_EXPIRED, ACCOUNT_NOT_LOCKED, CREDENTIALS_NON_EXPIRED, ONLINE) VALUES ('user2', 'user2@gmai.com', 'pass', true, 'ROLE_USER', true, true, true, false);

INSERT INTO Post (title, body, user_id, group_id) VALUES ('postTitle1', 'postBody1', (SELECT user_id FROM Users WHERE username='user1') ,(SELECT group_id FROM Groups WHERE name='groupName1'));

INSERT INTO Member (role, disabled, user_id, group_id) VALUES ('ADMIN_ROLE', false ,(SELECT user_id FROM Users WHERE username='user1'), (SELECT group_id FROM Groups WHERE name='groupName1'));

INSERT INTO Likes (liked, user_id, post_id) VALUES (true, (SELECT user_id FROM Users WHERE username='user1'), (SELECT post_id FROM Post WHERE title='postTitle1'));

INSERT INTO Comment (body, user_id, post_id) VALUES ('comentBody1', (SELECT user_id FROM Users WHERE username='user1'), (SELECT post_id FROM Post WHERE title='postTitle1'));

INSERT INTO Friendship (user_id, friend_of_id) VALUES ((SELECT user_id FROM Users WHERE username='user2'), (SELECT user_id FROM Users WHERE username='user1'))