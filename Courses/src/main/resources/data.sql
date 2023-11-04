INSERT INTO course(`id`, `name`, `description`) values (101, 'frontend development', 'Learn UI developement from scratch');
INSERT INTO course(`id`, `name`, `description`) values (102, 'backend development', 'Learn developing RestFul Backend');
INSERT INTO course(`id`, `name`, `description`) values (103, 'Mobile App development', 'Learn developing Mobile apps');


INSERT INTO price(`id`, `base_amount`, `course_id`) values (444, 1899.50, 101);
INSERT INTO price(`id`, `base_amount`, `course_id`) values (555, 1899.50, 101);
INSERT INTO price(`id`, `base_amount`, `course_id`) values (666, 2899.50, 102);
INSERT INTO price(`id`, `base_amount`, `course_id`) values (777, 0, 103);

INSERT INTO pricing_strategy(`id`, `duration_in_months`, `type`, `price_id`) values (111, 6, 'ONE_TIME', 444);
INSERT INTO pricing_strategy(`id`, `duration_in_months`, `type`, `price_id`) values (222, 12, 'SUBSCRIPTION', 555);
INSERT INTO pricing_strategy(`id`, `duration_in_months`, `type`, `price_id`) values (333, 6, 'ONE_TIME', 666);
INSERT INTO pricing_strategy(`id`, `duration_in_months`, `type`, `price_id`) values (444, 6, 'FREE',777);


INSERT INTO tax_component(`id`, `gst_amount`, `price_id`) values (123, 100, 444);
INSERT INTO tax_component(`id`, `gst_amount`, `price_id`) values (234, 100, 555);
INSERT INTO tax_component(`id`, `gst_amount`, `price_id`) values (345, 100, 666);
INSERT INTO tax_component(`id`, `gst_amount`, `price_id`) values (456, 0, 777);