drop table if exists comment;
drop table if exists review;

create table review (
	id integer not null auto_increment,
	content varchar(255),
	username varchar(255),
	rating bigint,
	course_id bigint,
	created_at datetime DEFAULT CURRENT_TIMESTAMP,
	updated_at datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key (id)
);

create table comment (
	id integer not null auto_increment,
	content varchar(255),
	username varchar(255),
	review_id integer not null,
	created_at datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key (id),
  FOREIGN KEY (review_id) REFERENCES review(id)
);