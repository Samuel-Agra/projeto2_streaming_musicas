-- ------------------------------------------------
-- Create table Singer
-- ------------------------------------------------
CREATE TABLE SINGER (
        SINGER_ID   BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
        FIRST_NAME  VARCHAR(250) NOT NULL,
        LAST_NAME   VARCHAR(250) NOT NULL,
        BIRTH_DATE  DATE NOT NULL
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


    -- ------------------------------------------------
-- Inserting ficticious Singer data
-- ------------------------------------------------
    INSERT INTO SINGER (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES
        ('Ava',      'Monroe',    '1992-06-15'),
        ('Liam',     'Stone',     '1987-03-22'),
        ('Emma',     'Sky',       '1990-11-09'),
        ('Noah',     'Hendrix',   '1985-07-30'),
        ('Olivia',   'Sage',      '1995-01-14'),
        ('Mason',    'Bennett',   '1991-12-25'),
        ('Sophia',   'Lennon',    '1988-05-05'),
        ('Ethan',    'Rhodes',    '1993-09-17'),
        ('Isabella', 'Taylor',    '1989-04-03'),
        ('Logan',    'Knight',    '1994-08-19');

-- ------------------------------------------------
-- Inserting ficticious role
-- ------------------------------------------------
insert tbl_role (role) values ('admin');
insert tbl_role (role) values ('user');

-- ------------------------------------------------
-- Inserting ficticious user
-- ------------------------------------------------
insert tbl_user (user_name, user_password, user_role)
       values ('admin', '$2a$12$vZ2s/PeHdcTmVzYHr75i9.Nfy4DW9l0MDUVmqEz8GUc1SZ1w3Ddx6', '1');

insert tbl_user (user_name, user_password, user_role)
       values ('Samuel', '$2a$12$IGXrzEz4eptOZLSVLuc/JOKf37l52AffEfg7NAvyDKH3HkGcHe.1u', '1');

-- ------------------------------------------------
-- Constraints FK & Unique
-- ------------------------------------------------
ALTER TABLE SINGER
    ADD CONSTRAINT UNIQ_FIRST_NAME_AND_LAST_NAME
        UNIQUE (FIRST_NAME, LAST_NAME);

SELECT * FROM SINGER;

ALTER TABLE tbl_role
    ADD CONSTRAINT UNIQ_ROLE
        UNIQUE (role);

ALTER TABLE tbl_user
    ADD CONSTRAINT USER_ROLE_TO_ROLE_ID
        FOREIGN KEY (user_role) REFERENCES tbl_role (role_id);
