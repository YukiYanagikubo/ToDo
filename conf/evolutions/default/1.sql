# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table todo (
  id                            bigint auto_increment not null,
  text                          varchar(255),
  deadline                      timestamp,
  done                          boolean default false not null,
  postdate                      timestamp not null,
  constraint pk_todo primary key (id)
);


# --- !Downs

drop table if exists todo;

