drop trigger if exists `insert_query_for_self`;

DELIMITER $$
CREATE TRIGGER `insert_query_for_self` AFTER INSERT ON `note`
FOR EACH ROW
BEGIN
	insert into share (account_id, note_id) values (new.owner_id, new.id);
    
END$$   
DELIMITER ;  

insert into note (subject, text, owner_id) values ('subject test', 'text test', 4);

select * from note;