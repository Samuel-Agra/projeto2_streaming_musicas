-- ------------------------------------------------
-- Create table Singer
-- ------------------------------------------------
CREATE TABLE singer (
                        singer_id   BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                        first_name  VARCHAR(250) NOT NULL,
                        last_name   VARCHAR(250) NOT NULL,
                        birth_date  DATE NOT NULL
);

-- ------------------------------------------------
-- Create table user
-- ------------------------------------------------
CREATE TABLE tbl_user (
                          user_id         BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          user_name       VARCHAR(250)    NOT NULL,
                          user_password   VARCHAR(250)    NOT NULL,
                          user_role       BIGINT          NOT NULL
);

-- ------------------------------------------------
-- Create table role
-- ------------------------------------------------
CREATE TABLE tbl_role (
                          role_id         BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          role            VARCHAR(250)    NOT NULL
);

ALTER TABLE tbl_role
    ADD CONSTRAINT UNIQ_ROLE
        UNIQUE (role);

ALTER TABLE tbl_user
    ADD CONSTRAINT USER_ROLE_TO_ROLE_ID
        FOREIGN KEY (user_role) REFERENCES tbl_role (role_id);