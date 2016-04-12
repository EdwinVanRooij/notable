drop trigger if exists `test_before_insert`;

DELIMITER $$
CREATE TRIGGER `test_before_insert` BEFORE INSERT ON `note`
FOR EACH ROW
BEGIN
    IF new.active < 0 || new.active > 1 THEN
        SIGNAL SQLSTATE '12345'
            SET MESSAGE_TEXT = 'check constraint on note failed';
    END IF;
    
    set new.last_edited = current_timestamp();
    
END$$   
DELIMITER ;  