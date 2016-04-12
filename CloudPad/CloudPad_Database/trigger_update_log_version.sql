drop trigger if exists `generate_version`;

DELIMITER $$
CREATE TRIGGER `generate_version` BEFORE INSERT ON `groceries_update_log`
FOR EACH ROW
BEGIN
DECLARE message text;
    IF new.version = 0 THEN
			SET new.version = (select version from update_log where id = (select max(id) from update_log)) + 0.001;
    END IF;
END$$   
DELIMITER ;  

select * from update_log;