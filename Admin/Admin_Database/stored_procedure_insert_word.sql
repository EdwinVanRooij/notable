drop procedure if exists procedure_insert_word;

DELIMITER //
CREATE PROCEDURE procedure_insert_word
-- p_word is the word to insert, may be up to 100 characters.
(IN p_first_word VARCHAR(100),
IN p_second_word VARCHAR(100))

BEGIN
-- does_word_exist is the boolean to check wether the word already exists or not, 0 means it doesn't, 1 means it does
	DECLARE does_first_word_exist INT DEFAULT 0;
    DECLARE does_second_word_exist INT DEFAULT 0;
    DECLARE does_combination_exist INT DEFAULT 0;
    DECLARE v_first_word_id INT;
    DECLARE v_second_word_id INT;

-- initialize variables
	select count(*) into does_first_word_exist from admin_word where content = p_first_word;
    select count(*) into does_second_word_exist from admin_word where content = p_second_word;

-- if the word does not already exist, insert the word
	if does_first_word_exist = 0 THEN
		insert into admin_word (content) values (p_first_word);
	end if;
    select id into v_first_word_id from admin_word where content = p_first_word;
    
    if does_second_word_exist = 0 THEN
		insert into admin_word (content) values (p_second_word);
	end if;
    select id into v_second_word_id from admin_word where content = p_second_word;
    
    select count(*) 
    into does_combination_exist 
    from admin_combination
    where first_word_id = v_first_word_id
    and second_word_id = v_second_word_id;
    
    if does_combination_exist = 0 THEN
		insert into admin_combination (first_word_id, second_word_id) values (v_first_word_id, v_second_word_id);
	else
		update admin_combination
        set count = count + 1
        where first_word_id = v_first_word_id 
        and second_word_id = v_second_word_id;
	end if;

END //
DELIMITER ;