INSERT INTO groups (name, topic, description, type, disabled) VALUES ('groupName1', 'groupTopic1', 'groupDescription1', 'groupType1', false);

INSERT INTO Users (username, email, password, enabled, role) VALUES ('user1', 'user1@gmai.com', 'pass', true, 'ROLE_USER');

INSERT INTO Post (title, body, user_id, group_id) VALUES ('postTitle1', 'postBody1', (SELECT user_id FROM Users WHERE username='user1') ,(SELECT group_id FROM Groups WHERE name='groupName1'));

INSERT INTO Member (role, disabled, user_id, group_id) VALUES ('ADMIN_ROLE', false ,(SELECT user_id FROM Users WHERE username='user1'), (SELECT group_id FROM Groups WHERE name='groupName1'));

INSERT INTO Likes (liked, user_id, post_id) VALUES (true, (SELECT user_id FROM Users WHERE username='user1'), (SELECT user_id FROM Post WHERE title='postTitle1'));

INSERT INTO Comment (body, user_id, post_id) VALUES ('comentBody1', (SELECT user_id FROM Users WHERE username='user1'), (SELECT user_id FROM Post WHERE title='postTitle1'));
