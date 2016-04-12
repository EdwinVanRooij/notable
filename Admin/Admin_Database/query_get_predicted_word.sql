call procedure_insert_word('from', 'swag');

select content
from admin_word w, admin_combination c
where w.id = c.second_word_id
and first_word_id = (select id from admin_word w2 where w2.content = 'from')
order by count desc
limit 3;