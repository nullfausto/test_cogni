-- Creating the database
CREATE DATABASE SocialMediaPlatform;

-- Designing the tables for the database

CREATE TABLE Users(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password CHAR(60) NOT NULL,
    date_of_birth DATE,
    profile_picture VARCHAR(255)
);

CREATE TABLE Posts(
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    post_text TEXT,
    post_date DATETIME NOT NULL,
    media_url VARCHAR(512),
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

-- ADD USER ID -- DONE
CREATE TABLE Comments(
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    comment_text TEXT,
    comment_date DATETIME NOT NULL,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id) ON DELETE CASCADE
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

CREATE TABLE Likes(
    like_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    like_date DATETIME,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);


-- MODIFY THE FOLLOWER ID TO FOREIGN KEY TO USER ID -- DONE
CREATE TABLE Follows(
    follow_id INT AUTO_INCREMENT PRIMARY KEY,
    follower_id INT NOT NULL,  -- The user who is following
    following_id INT NOT NULL,  -- The user being followed
    follow_date DATETIME,
    FOREIGN KEY (follower_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (following_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    UNIQUE KEY (follower_id, following_id) 
);

CREATE TABLE Messages(
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    message_text TEXT,
    message_date DATETIME,
    is_read BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (sender_id) REFERENCES Users(user_id),
    FOREIGN KEY (receiver_id) REFERENCES Users(user_id)
);

CREATE TABLE Notification(
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    notification_text TEXT,
    notification_date DATETIME,
    is_read BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Populating the tables with diverse and extensive sample data
INSERT INTO Users (username, email, password, date_of_birth, profile_picture) VALUES
('selenelop', 'sellop@io.com', '$2a$10$x', '2000-12-13', '/images/profiles/selenel.jpg'),
('denzel1x', 'denzelvp@test.com', '$2a$10$x', '2001-12-24', '/images/avatars/snoopy.jpg'),
('fausto_vr', 'favr@io.com', '$2a$10$x', '1995-02-14', NULL),
('sararob1', 'sararb@test.com', '$2a$10$x', '1988-11-30', 'sara.jpg'),
('ricardo_em', 'rickyjr@io.com', '$2a$10$x', '2020-04-16', NULL),
('oscar_it', 'itsoscar@test.com', '$2a$10$x', '1998-06-05', 'oscar.png'),
('david_l', 'david@example.com', '$2a$10$x', '1983-09-12', NULL),
('maxiriv', 'maxr@io.com', '$2a$10$x', '2001-01-25', 'max.jpg'),
('tom_h', 'tom@example.com', '$2a$10$x', '1987-07-08', NULL),
('fridavd', 'fridsv@io.com', '$2a$10$x', '2001-08-25', '/images/profiles/fvd.jpg');

-- Creating posts for each user
INSERT INTO Posts (user_id, post_text, post_date, media_url) VALUES
(1, 'Just enjoying the sunny weather today! #summer', '2023-06-15 09:23:45', 'sunny_day.jpg'),
(1, 'Check out my new blog post about database design', '2023-06-10 14:12:33', NULL),
(1, 'What''s everyone reading this weekend? Looking for book recommendations.', '2023-06-03 18:45:12', NULL),
(2, 'Just completed my 5K run! New personal best time.', '2023-06-14 07:30:00', 'finish_line.jpg'),
(2, 'Homemade pizza night was a success!', '2023-06-08 19:15:22', 'pizza.jpg'),
(2, 'Working on a new project. Can''t wait to share more details soon!', '2023-06-01 10:05:17', NULL),
(3, 'The new JavaScript framework looks promising. Anyone tried it yet?', '2023-06-13 11:42:08', NULL),
(3, 'Weekend coding marathon begins now. Coffee is ready!', '2023-06-09 22:10:55', 'coffee_setup.jpg'),
(3, 'Debugging tip: Always check your console logs first!', '2023-06-02 15:33:41', NULL),
(4, 'Beautiful sunset at the beach today', '2023-06-12 20:05:37', 'sunset.jpg'),
(4, 'Just adopted this little cutie! Meet our new family member.', '2023-06-07 16:22:19', 'new_pet.jpg'),
(4, 'Recommendations for good Italian restaurants in town?', '2023-05-30 12:18:54', NULL),
(5, 'My new gaming setup is complete!', '2023-06-11 13:47:29', 'gaming_rig.jpg'),
(5, 'Just released version 2.0 of my app. Download link in bio!', '2023-06-06 09:55:10', NULL),
(5, 'The key to productivity: Pomodoro technique + noise-canceling headphones', '2023-05-29 08:12:43', NULL),
(6, 'Morning hike with amazing views', '2023-06-10 10:33:56', 'mountain_view.jpg'),
(6, 'Baking sourdough bread for the first time. Wish me luck!', '2023-06-05 17:40:21', 'dough.jpg'),
(6, 'Just finished this great novel. Highly recommend!', '2023-05-28 21:15:08', NULL),
(7, 'Working remotely from the cabin this week', '2023-06-09 08:20:14', 'cabin_office.jpg'),
(7, 'The secret to good barbecue is patience and quality wood', '2023-06-04 12:55:37', 'bbq.jpg'),
(7, 'Does anyone actually enjoy writing documentation?', '2023-05-27 16:30:49', NULL),
(8, 'Yoga session by the lake this morning', '2023-06-08 07:15:42', 'yoga_lake.jpg'),
(8, 'My indoor plant collection is getting out of hand...', '2023-06-03 19:22:18', 'plants.jpg'),
(8, 'Looking for podcast recommendations - what are you listening to?', '2023-05-26 14:08:33', NULL),
(9, 'New camera, who dis? Testing out the lens quality', '2023-06-07 15:43:27', 'test_photo.jpg'),
(9, 'The most underrated feature in VS Code? The command palette.', '2023-06-02 10:37:55', NULL),
(9, 'Anyone going to the developer conference next month?', '2023-05-25 11:52:16', NULL),
(10, 'Weekend market finds! Fresh produce and handmade crafts', '2023-06-06 12:28:39', 'market.jpg'),
(10, 'My sourdough starter is officially one year old today!', '2023-06-01 09:14:27', 'sourdough.jpg'),
(10, 'Why is it so hard to find comfortable office chairs?', '2023-05-24 13:45:02', NULL);

-- Creating comments for some posts
INSERT INTO Comments (post_id, user_id, comment_text, comment_date) VALUES
(1, 2, 'Looks amazing! Wish I was there too.', '2023-06-15 10:05:22'),
(1, 4, 'Perfect day for a picnic!', '2023-06-15 11:30:45'),
(2, 3, 'Great read! Especially liked the normalization section.', '2023-06-10 16:45:12'),
(4, 1, 'Congrats on the PB! That''s awesome!', '2023-06-14 08:15:33'),
(4, 5, 'What was your time? I ran it last month too!', '2023-06-14 09:22:17'),
(5, 6, 'Recipe please! That crust looks perfect.', '2023-06-08 20:30:55'),
(7, 2, 'Tried it last week - the learning curve is steep but powerful', '2023-06-13 13:20:41'),
(7, 8, 'Waiting for more documentation before diving in', '2023-06-13 15:12:08'),
(10, 7, 'Stunning capture! What camera do you use?', '2023-06-12 21:15:29'),
(11, 3, 'SO CUTE! What''s their name?', '2023-06-07 17:45:10'),
(11, 9, 'Adoption is the best way! Congrats', '2023-06-07 18:30:22'),
(13, 4,'That monitor is huge! What specs?', '2023-06-11 15:20:18'),
(16, 10, 'Which trail is this? Looks familiar', '2023-06-10 12:05:47'),
(19, 2, 'Cabin looks cozy! How''s the wifi?', '2023-06-09 09:45:33'),
(22, 5, 'Such a peaceful spot for yoga!', '2023-06-08 08:30:12'),
(25, 6, 'Great bokeh! What lens is that?', '2023-06-07 16:50:41'),
(28, 1, 'Those strawberries look delicious!', '2023-06-06 13:45:19'),
(28, 3, 'Which market was this?', '2023-06-06 14:20:05'),
(29, 7, 'Happy birthday to your starter! Mine just turned 6 months', '2023-06-01 10:30:15');

-- Adding likes to some posts
INSERT INTO Likes (post_id, user_id, like_date) VALUES
(1, 2, '2023-06-15 09:45:12'),
(1, 4, '2023-06-15 10:20:33'),
(1, 6, '2023-06-15 11:15:47'),
(1, 8, '2023-06-15 12:30:18'),
(4, 1, '2023-06-14 08:05:22'),
(4, 3, '2023-06-14 09:12:45'),
(4, 5, '2023-06-14 10:30:11'),
(5, 7, '2023-06-08 19:45:09'),
(5, 9, '2023-06-08 20:15:37'),
(7, 2, '2023-06-13 12:15:28'),
(7, 4, '2023-06-13 14:22:53'),
(7, 6, '2023-06-13 16:40:12'),
(10, 1, '2023-06-12 20:35:44'),
(10, 3, '2023-06-12 21:10:25'),
(10, 5, '2023-06-12 22:05:17'),
(10, 7, '2023-06-13 08:12:39'),
(10, 9, '2023-06-13 09:45:50'),
(11, 2, '2023-06-07 17:15:33'),
(11, 4, '2023-06-07 18:20:48'),
(11, 6, '2023-06-07 19:05:12'),
(11, 8, '2023-06-08 10:30:22'),
(13, 3, '2023-06-11 14:30:55'),
(13, 7, '2023-06-11 16:45:18'),
(16, 1, '2023-06-10 11:20:37'),
(16, 5, '2023-06-10 12:45:09'),
(16, 9, '2023-06-10 14:15:26'),
(19, 2, '2023-06-09 10:30:44'),
(22, 4, '2023-06-08 08:05:17'),
(22, 10, '2023-06-08 09:20:33'),
(25, 5, '2023-06-07 16:15:42'),
(25, 7, '2023-06-07 17:30:18'),
(25, 9, '2023-06-08 08:45:55'),
(28, 2, '2023-06-06 13:20:15'),
(28, 4, '2023-06-06 14:05:37'),
(28, 6, '2023-06-06 15:30:48'),
(28, 8, '2023-06-07 09:15:22'),
(29, 1, '2023-06-01 10:05:33'),
(29, 3, '2023-06-01 11:45:19');

-- Adding followers to the database
INSERT INTO Follows (follower_id, following_id, follow_date) VALUES
(1, 2, '2023-01-15 09:23:12'),  -- selene follows denz
(1, 3, '2023-02-10 14:45:33'),  -- sel follows faus
(1, 5, '2023-03-05 11:12:47'),  -- sel follows ricem
(2, 1, '2023-01-20 08:15:22'),  -- denz follows sel
(2, 4, '2023-02-18 16:30:55'),  -- denz follows sara
(2, 7, '2023-04-12 10:45:19'),  -- denz follows david
(3, 1, '2023-01-25 12:33:41'),  -- faus follows sel
(3, 6, '2023-03-15 14:22:08'),  -- faus follows oscar
(3, 9, '2023-05-01 18:15:37'),  -- faus follows tom
(4, 2, '2023-02-05 10:20:44'),  -- sara follows denz
(4, 5, '2023-03-22 19:05:12'),  -- sara follows alex
(4, 8, '2023-04-30 09:45:29'),  -- sara follows ricem
(4, 10, '2023-05-15 15:30:18'), -- sara follows frids
(5, 3, '2023-02-15 11:45:33'),  -- ric follows faus
(5, 7, '2023-04-05 13:20:47'),  -- ric follows david
(6, 1, '2023-01-30 17:15:22'),  -- oscar follows sel
(6, 4, '2023-03-10 10:30:45'),  -- oscar follows sara
(6, 8, '2023-05-05 14:45:12'),  -- oscar follows max
(7, 2, '2023-02-22 08:45:19'),  -- david follows denz
(7, 5, '2023-04-18 16:30:33'),  -- david follows ric
(7, 9, '2023-05-20 11:15:47'),  -- david follows tom
(8, 3, '2023-03-01 12:30:55'),  -- max follows faus
(8, 6, '2023-04-22 15:45:12'),  -- max follows oscar
(8, 10, '2023-05-25 19:30:18'), -- max follows frids
(9, 4, '2023-03-12 10:15:33'),  -- tom follows sara
(9, 7, '2023-05-08 14:30:47'),  -- tom follows david
(10, 1, '2023-02-08 09:45:22'), -- frids follows sel
(10, 5, '2023-04-15 18:30:44'), -- frids follows ric
(10, 8, '2023-05-18 12:15:37'); -- frids follows max

-- Messages between users
INSERT INTO Messages (sender_id, receiver_id, message_text, message_date, is_read) VALUES
(1, 2, 'Hey Denzel, are we still meeting for coffee tomorrow?', '2023-06-10 14:23:45', TRUE),
(2, 1, 'Absolutely! 2pm at our usual spot work for you?', '2023-06-10 14:35:12', TRUE),
(1, 2, 'Perfect, see you then!', '2023-06-10 14:36:30', TRUE),
(3, 4, 'Sara, do you have that database schema we discussed last week?', '2023-06-12 09:15:33', TRUE),
(4, 3, 'Yes, I just emailed it to you. Let me know if you need anything else.', '2023-06-12 09:20:47', TRUE),
(3, 4, 'Got it, thanks!', '2023-06-12 09:21:15', FALSE),  
(4, 6, 'Oscar, your hike photos looked amazing! Which trail was that?', '2023-06-11 18:45:22', TRUE),
(6, 4, 'Thanks! It was the Blue Ridge Trail. We should go together sometime!', '2023-06-11 19:12:37', TRUE),
(4, 6, 'I would love that! How about next weekend?', '2023-06-11 19:30:15', FALSE), 
(7, 8, 'Max, do you remember where we put the project documentation?', '2023-06-13 11:05:42', TRUE),
(8, 7, 'It should be in the shared drive under "Project Files"', '2023-06-13 11:30:18', FALSE), 
(9, 10, 'Frida, your sourdough starter looks great! Any tips for a beginner?', '2023-06-14 15:22:55', TRUE),
(10, 9, 'Thanks! The key is regular feeding and keeping it at consistent temperature.', '2023-06-14 16:45:33', FALSE),  
(10, 9, 'I can share my feeding schedule if you would like?', '2023-06-14 16:46:12', FALSE),
(2, 3, 'Fausto, can you review my PR when you get a chance?', '2023-06-15 10:15:47', FALSE),
(5, 1, 'Hey, how are you doing?', '2023-06-15 13:30:22', TRUE),
(6, 8, 'Max, are you going to the yoga workshop this weekend?', '2023-06-09 17:45:33', TRUE),
(8, 6, 'Yes! I signed up yesterday. Want to carpool?', '2023-06-09 18:12:47', TRUE),
(6, 8, 'That would be great! I can drive', '2023-06-09 18:30:15', TRUE),
(8, 6, 'Perfect, let us meet at my place at 8am?', '2023-06-09 18:45:22', TRUE),
(6, 8, 'Sounds like a plan!', '2023-06-09 18:46:30', FALSE),  
(10, 4, 'Sara, I found that Italian restaurant you were asking about! It is called Bella Roma', '2023-06-14 12:15:18', TRUE);

-- Populating Notifications
INSERT INTO Notification (user_id, notification_text, notification_date, is_read) VALUES
(1, 'denzel1x liked your post "Just enjoying the sunny weather today!"', '2023-06-15 09:45:12', TRUE),
(1, 'oscar_it commented on your post "What''s everyone reading this weekend?"', '2023-06-03 19:05:12', TRUE),
(1, 'fridavd started following you', '2023-02-08 09:45:22', FALSE),
(2, 'selenelop liked your post "Just completed my 5K run!"', '2023-06-14 08:05:22', TRUE),
(2, 'maxiriv liked your post "Homemade pizza night was a success!"', '2023-06-08 19:45:09', FALSE),
(2, 'You have 3 new matches on your dating preferences', '2023-06-13 11:30:45', TRUE),
(3, 'ricardo_em replied to your comment on "The new JavaScript framework"', '2023-06-13 15:45:33', TRUE),
(3, 'tom_h started following you', '2023-05-01 18:15:37', FALSE),
(3, 'Your scheduled maintenance is complete', '2023-06-12 08:30:22', TRUE),
(4, 'oscar_it liked your post "Beautiful sunset at the beach today"', '2023-06-12 20:35:44', TRUE),
(4, 'maxiriv replied to your restaurant question', '2023-05-30 13:18:54', FALSE),
(4, 'Your subscription will renew in 3 days', '2023-06-12 09:15:33', TRUE),
(5, 'fausto_vr liked your post "My new gaming setup is complete!"', '2023-06-11 14:30:55', TRUE),
(5, 'sararob1 started following you', '2023-03-22 19:05:12', FALSE),
(5, 'New version available for your installed apps', '2023-06-14 10:45:18', TRUE),
(6, 'sararob1 replied to your hike photo', '2023-06-10 12:05:47', TRUE),
(6, 'selenelop liked your post "Morning hike with amazing views"', '2023-06-10 11:20:37', FALSE),
(6, 'Your friend maxiriv is going to an event near you', '2023-06-11 17:30:45', TRUE),
(7, 'maxiriv liked your post "Working remotely from the cabin this week"', '2023-06-09 10:30:44', TRUE),
(7, 'ricardo_em started following you', '2023-04-05 13:20:47', FALSE),
(7, 'Security alert: New login from Chicago, IL', '2023-06-14 08:15:33', TRUE),
(8, 'oscar_it accepted your carpool invitation', '2023-06-09 18:30:15', TRUE),
(8, 'fridavd liked your post "Yoga session by the lake this morning"', '2023-06-08 09:20:33', FALSE),
(8, 'Your post was shared 5 times', '2023-06-13 14:45:22', TRUE),
(9, 'fridavd replied to your sourdough question', '2023-06-14 16:45:33', FALSE),
(9, 'fausto_vr started following you', '2023-05-01 18:15:37', TRUE),
(9, 'Your photo was featured in the community gallery', '2023-06-13 12:30:18', TRUE),
(10, 'sararob1 liked your post "Weekend market finds!"', '2023-06-06 13:20:15', TRUE),
(10, 'maxiriv started following you', '2023-05-25 19:30:18', FALSE),
(10, 'You have 5 new recipe suggestions based on your interests', '2023-06-15 09:15:42', TRUE);

-- Queries
-- Retrieve the posts and activities of an user's timeline
SELECT u.username,
    p.post_text,
    c.comment_text
FROM Users u
INNER JOIN Follows f ON u.user_id = f.following_id
INNER JOIN Posts p ON p.user_id = f.following_id
INNER JOIN Comments c ON c.user_id = f.following_id
WHERE f.follow_id = 1;

-- Retrieve the comments and likes for a specific post
SELECT u.username,
    p.post_text,
    c.comment_text
FROM Users u
INNER JOIN Follows f ON u.user_id = f.following_id
INNER JOIN Posts p ON p.user_id = f.following_id
INNER JOIN Comments c ON c.user_id = f.following_id
WHERE f.follower_id = 1;

-- Retrieve the list of followers for an user 
SELECT u.user_id,
    u.username,
    f.follow_date
FROM Users u
JOIN Follows f ON u.user_id = f.follower_id
WHERE f.following_id = 1;


-- Retrive the unread messages for an user
SELECT sender.username AS sender_username,
    m.message_text,
    m.message_date
FROM Messages m
JOIN Users sender ON m.sender_id = sender.user_id
WHERE m.receiver_id = 6 AND m.is_read = FALSE
ORDER BY m.message_date DESC;


-- Retrieve the most liked posts
SELECT 
    p.post_id,
    u.username AS author_username,
    p.post_text,
    COUNT(l.like_id) AS like_count,
    p.post_date
FROM Posts p
JOIN Users u ON p.user_id = u.user_id
LEFT JOIN Likes l ON p.post_id = l.post_id
GROUP BY p.post_id, u.username, p.post_text, p.post_date
ORDER BY like_count DESC, p.post_date DESC LIMIT 3; 

-- Retrieve the latest notifications for an user
SELECT 
    n.notification_id,
    n.notification_text,
    n.notification_date,
    n.is_read,
    CASE 
        WHEN n.is_read = FALSE THEN 'UNREAD'
        ELSE 'READ'
    END AS read_status
FROM Notification n
WHERE n.user_id = 1 
ORDER BY n.notification_date DESC LIMIT 3;  

-- Data Modification
-- Add a new post to the platform
INSERT INTO Posts (user_id, post_text, post_date, media_url) VALUES
(1, 'Summer nights with rain plus a good book equals #happiness', '2023-06-16 21:15:17', 'rain_and_reading_day.jpg');

-- Comment on a post
INSERT INTO Comments (post_id, user_id, comment_text, comment_date) VALUES
(30, 3, 'I swear they always turn into life changing moments', '2023-06-16 22:07:28');

-- Update user profile information
UPDATE Users
SET 
    username = "sel_lv",
    email = "bran_new_sel@io.com",
WHERE
    user_id = 1;

-- Remove a like from a post
-- Basic version (remove a specific like)
DELETE FROM Likes
WHERE post_id = [POST_ID] 
AND user_id = [USER_ID];

-- Complex queries
-- Identify users with the most followers
SELECT u.user_id,
    u.username,
    COUNT(f.follower_id) AS follower_count
FROM Users u
LEFT JOIN Follows f ON u.user_id = f.following_id
GROUP BY u.user_id, u.username
ORDER BY follower_count DESC LIMIT 3;

-- Find the most active users based on post count and interaction
SELECT u.user_id,
    u.username,
    COUNT(DISTINCT p.post_id) AS posts,
    COUNT(DISTINCT c.comment_id) AS comments,
    COUNT(DISTINCT l.like_id) AS likes,
    COUNT(DISTINCT p.post_id) + 
    (COUNT(DISTINCT c.comment_id) * 0.7) + 
    (COUNT(DISTINCT l.like_id) * 0.3) AS engagement_score
FROM Users u
LEFT JOIN Posts p ON u.user_id = p.user_id
LEFT JOIN Comments c ON u.user_id = c.user_id
LEFT JOIN Likes l ON u.user_id = l.user_id
GROUP BY u.user_id, u.username
ORDER BY engagement_score DESC LIMIT 5;


-- Calculate the average number of comments per post
SELECT 
    COUNT(comment_id) * 1.0 / COUNT(DISTINCT post_id) AS avg_comments_per_post
FROM Comments;


