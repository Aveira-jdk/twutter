INSERT INTO tweets (tweet, user_id) VALUES
                   ('IktLab course .', 1),
                   ('Java Programming.', 2),
                   ('Java Group SIP.', 3),
                   ('Azerbaijan.', 4),
                   ('Java remote works.', 1),
                   ('Interview questions.', 5),
                   ('Protect nature and animals.', 2),
                   ('Object oriented programming.', 3),
                   ('Java advanced topics.', 6);


INSERT INTO reviews (review, user_id, tweet_id) VALUES
                     ('Great course!', 7, 1),
                     ('I love Java!', 8, 2),
                     ('SIP sounds interesting.', 9, 3),
                     ('Beautiful country!', 10, 4),
                     ('Remote work is the future.', 11, 5),
                     ('Any tips for interviews?', 12, 6),
                     ('Nature conservation is crucial.', 13, 7),
                     ('OOP is fascinating.', 14, 8),
                     ('What topics are covered?', 15, 9),
                     ('Enjoying the course content.', 16, 1),
                     ('Can''t wait for the next lesson!', 17, 1),
                     ('Learning a lot from IktLab.', 18, 1),
                     ('Java is my favorite language!', 19, 2),
                     ('Programming in Java is fun.', 20, 2),
                     ('Interested in joining the SIP.', 21, 3),
                     ('Group projects are exciting!', 22, 3),
                     ('Planning to visit Azerbaijan soon.', 23, 4),
                     ('The culture is rich and diverse.', 24, 4),
                     ('Remote work allows for flexibility.', 25, 5),
                     ('How to stay productive while remote?', 26, 5),
                     ('Preparing for interviews is crucial.', 27, 6),
                     ('Any resources for interview prep?', 28, 6),
                     ('Support wildlife conservation efforts!', 29, 7),
                     ('Animals need our protection.', 30, 7),
                     ('OOP principles make code robust.', 31, 8),
                     ('Understanding abstraction is key.', 32, 8),
                     ('Excited to explore advanced Java topics.', 33, 9),
                     ('What are the prerequisites for advanced topics?', 34, 9);


INSERT INTO tweet_likes (user_id, tweet_id) VALUES
(17, 6),(18, 6),(19, 3),(20, 7),(21, 4),(22, 8),
(23, 9),(24, 8),(25, 1),(26, 2),(17, 9),(18, 5),
(19, 7),(20, 8),(21, 2),(22, 8),(23, 3),(24, 6),
(25, 1),(26, 7),(17, 5),(18, 1),(19, 2),(20, 6),
(21, 8),(22, 4),(23, 4),(24, 9),(25, 1),(26, 5),
(17, 6),(18, 9),(19, 7),(20, 3),(21, 8),(22, 2),
(23, 9),(24, 5),(25, 4),(26, 1);

INSERT INTO review_likes (user_id, review_id) VALUES
(1, 1), (2, 21), (3, 1), (4, 1), (5, 1),
(6, 2), (7, 22), (8, 2), (9, 2), (10, 22),
(11, 3), (12,3), (13, 13), (14, 17), (15, 23),
(16, 4), (17, 14), (18, 4), (19, 4), (20, 14),
(21, 21), (22, 21), (23, 1), (24, 1), (25, 11),
(26, 22), (27, 2), (28, 2), (29, 22), (30, 2),
(31, 3), (32, 3), (33, 13), (34, 23), (35, 3),
(36, 4), (37, 14), (38, 4), (39, 14), (40, 4),
(17, 26),(18, 6),(19, 3),(20, 7),(21, 4),(22, 8),
(23, 9),(24, 8),(25, 1),(26, 2),(17, 19),(18, 5);