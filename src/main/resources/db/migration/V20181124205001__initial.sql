create table remark
(
  `idremark`    int         not null auto_increment,
  `description` mediumtext  not null,
  `quality`     varchar(45) not null,
  primary key (`idremark`)
);
