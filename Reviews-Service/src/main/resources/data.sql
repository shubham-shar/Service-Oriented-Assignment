INSERT INTO review(`id`, `content`, `username`, `rating`, `course_id`) values (111, 'Very Thorough study material', 'shubham', 4, 101);
INSERT INTO review(`id`, `content`, `username`, `rating`, `course_id`) values (222, 'Learning backend development is very easy with this course', 'Sharma', 5, 102);


INSERT INTO comment(`id`, `content`, `username`, `review_id`) values (167, 'Thanks for the comment', 'testingUser', 111);
INSERT INTO comment(`id`, `content`, `username`, `review_id`) values (278, 'Is live classes also part of course?', 'student1', 111);
INSERT INTO comment(`id`, `content`, `username`, `review_id`) values (389, 'Is the content updated?', 'student2', 222);
