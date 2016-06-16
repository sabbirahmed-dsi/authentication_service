create table dsi_login (login_id varchar(40) not null, created_by varchar(40) not null, created_date datetime, email varchar(40) not null, first_name varchar(40), last_name varchar(40), modified_by varchar(40) not null, modified_date datetime, password varchar(200) not null, reset_password_token varchar(200), reset_token_expire_time datetime, salt varchar(50), user_id varchar(40) not null, version integer not null, primary key (login_id));

create table dsi_tenant (tenant_id varchar(40) not null, is_active bit, name varchar(50), short_name varchar(40), version integer not null, auth_handler_id varchar(40) not null, secret_key varchar(40) not null, primary key (tenant_id));

create table dsi_user_session (user_session_id varchar(40) not null, access_token text, created_by varchar(40) not null,
created_date datetime, modified_by varchar(40) not null, modified_date datetime, user_id varchar(40) not null, version integer not null, primary key (user_session_id));

create table ref_auth_handler (auth_handler_id varchar(40) not null, name varchar(50), type_impl varchar(100) not null, version integer not null, primary key (auth_handler_id));