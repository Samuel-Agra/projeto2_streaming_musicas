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
ALTER TABLE singer
    ADD CONSTRAINT UNIQ_FIRST_NAME_AND_LAST_NAME
        UNIQUE (first_name, last_name);

SELECT * FROM singer;

ALTER TABLE tbl_role
    ADD CONSTRAINT UNIQ_ROLE
        UNIQUE (role);

ALTER TABLE tbl_user
    ADD CONSTRAINT USER_ROLE_TO_ROLE_ID
        FOREIGN KEY (user_role) REFERENCES tbl_role (role_id);
