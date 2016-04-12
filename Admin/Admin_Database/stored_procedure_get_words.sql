drop procedure if exists procedure_get_words;

DELIMITER //
CREATE PROCEDURE procedure_get_words
-- p_word is the word to insert, may be up to 100 characters.
(p_first_word VARCHAR(100))

BEGIN

select content from admin_word w, admin_combination c where w.id = c.second_word_id and first_word_id = (select id from admin_word w2 where w2.content = p_first_word) order by count desc limit 3;

END //
DELIMITER ;

call procedure_get_words('select');