INSERT INTO review(`id`, `content`, `username`, `rating`, `course_id`) values (1, 'Very Thorough study material', 'shubham', 4, 1);
INSERT INTO review(`id`, `content`, `username`, `rating`, `course_id`) values (2, 'Learning backend development is very easy with this course', 'Sharma', 5, 2);


INSERT INTO comment(`id`, `content`, `username`, `review_id`) values (1, 'Thanks for the comment', 'testingUser', 1);
INSERT INTO comment(`id`, `content`, `username`, `review_id`) values (2, 'Is live classes also part of course?', 'student1', 1);
INSERT INTO comment(`id`, `content`, `username`, `review_id`) values (3, 'Is the content updated?', 'student2', 2);
