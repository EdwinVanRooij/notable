CREATE OR REPLACE VIEW login_details_view AS
SELECT username, password
FROM account
WHERE blocked = 0;

select * from login_details_view;

drop view login_details_view;

CREATE VIEW login_details_view AS
SELECT username, password
FROM account
WHERE blocked = 0;

select * from login_details_view;