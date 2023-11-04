CREATE TABLE IF NOT EXISTS COURSE (
   id INT PRIMARY KEY auto_increment,
   created_at timestamp,
   description clob,
   name varchar(255) not null unique,
   updated_at timestamp
);


CREATE TABLE IF NOT EXISTS PRICE (
    id INT PRIMARY KEY auto_increment,
    base_amount double,
    created_at timestamp,
    updated_at timestamp,
    course_id bigint,
    foreign key (course_id) references COURSE(id)
);

CREATE TABLE IF NOT EXISTS TAX_COMPONENT (
    id INT PRIMARY KEY auto_increment,
    created_at timestamp,
    gst_amount double,
    updated_at timestamp,
    price_id bigint,
    foreign key (price_id) references PRICE(id)
);

CREATE TABLE IF NOT EXISTS PRICING_STRATEGY (
    id INT PRIMARY KEY auto_increment,
    created_at timestamp,
    duration_in_months integer not null,
    type varchar(255),
    updated_at timestamp,
    price_id bigint,
    foreign key (price_id) references PRICE(id)
);

CREATE INDEX COURSE_INDEX on COURSE(name);
CREATE INDEX PRICE_INDEX on PRICE(course_id);
CREATE INDEX PRICING_STRATEGY_INDEX on PRICING_STRATEGY(price_id);
CREATE INDEX TAX_COMPONENT_INDEX on TAX_COMPONENT(price_id);