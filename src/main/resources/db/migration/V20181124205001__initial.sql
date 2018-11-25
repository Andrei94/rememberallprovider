create table `rememberall`.`remark`
(
  `idremark`    int         not null default 0,
  `description` mediumtext  not null,
  `quality`     varchar(45) not null,
  primary key (`idremark`)
)
  default character set = utf8;
