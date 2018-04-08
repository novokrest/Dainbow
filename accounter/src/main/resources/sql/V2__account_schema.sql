CREATE TABLE account (
  username VARCHAR(1024) NOT NULL PRIMARY KEY,
  password VARCHAR(45) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1
);

CREATE TABLE account_role (
  username varchar(1024) NOT NULL,
  role varchar(1024) NOT NULL,
  UNIQUE KEY ux_username_role (username, role),
  KEY ix_username (username),
  CONSTRAINT fk_account_username FOREIGN KEY (username) REFERENCES account (username)
);