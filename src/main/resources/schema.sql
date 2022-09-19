create table users
(
   id         bigint       not null  auto_increment,
   login_name varchar(255) not null,
   first_name varchar(255) not null,
   last_name  varchar(255) not null,
   primary key(id)
);

create table tasks
(
   id          bigint         not null auto_increment,
   user_note   varchar(2000),
   task_data   varchar(4000),
   acquired_by bigint,
   created_by  bigint         not null,
   primary key(id),
   foreign key (acquired_by) references users(id),
   foreign key (created_by)  references users(id)
);
