drop schema if exists `cloudFriendly_db`;
create schema if not exists `cloudFriendly_db`;
use `cloudFriendly_db`;

drop table if exists `user`;

create table user (
  id       BIGINT NOT NULL AUTO_INCREMENT,
  name     VARCHAR(255),
  mail     VARCHAR(255),
  password VARCHAR(255),
  CONSTRAINT pk_user PRIMARY KEY (id),
  CONSTRAINT uq_user UNIQUE (name)
);
