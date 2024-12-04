# Getting Started

SELECT * FROM takeo_db.users;
SELECT * FROM takeo_db.roles;
SELECT * FROM takeo_db.users_roles;

#-------------
truncate table takeo_db.users_roles;
truncate table takeo_db.roles;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE takeo_db.users;
SET FOREIGN_KEY_CHECKS = 1;


