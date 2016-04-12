drop trigger if exists `update_confirm_date`;

DELIMITER $$
CREATE TRIGGER `update_confirm_date` BEFORE UPDATE ON `friends_with`
FOR EACH ROW
BEGIN
    IF new.request_confirmed = 1 THEN
			SET new.confirm_date = current_timestamp();
    END IF;
END$$   
DELIMITER ;  

select * from friends_with;