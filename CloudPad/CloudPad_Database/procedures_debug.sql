DELIMITER $$

DROP PROCEDURE IF EXISTS `debug_bericht`$$
DROP PROCEDURE IF EXISTS `test_debug_bericht`$$

CREATE PROCEDURE debug_bericht(start INTEGER, bericht VARCHAR(255))
BEGIN
  IF start THEN BEGIN
    select concat("** ", bericht) AS '** DEBUG:';
  	END; 
  END IF;
END $$

CREATE PROCEDURE test_debug_bericht(arg1 INTEGER, arg2 TEXT)
BEGIN
  SET @enabled = TRUE;

  call debug_msg(@enabled, (select concat_ws('',"arg1:", arg1, " arg2:", arg2)));
  
END $$

DELIMITER ;