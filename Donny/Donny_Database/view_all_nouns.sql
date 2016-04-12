drop view if exists all_nouns;

create view all_nouns as 
select w.language, p.name as phrase, n.word, n.sex
from donny_noun n, donny_word w, donny_word_phrase wp, donny_phrase p
where n.id = w.id
and w.id = wp.word_id
and wp.phrase_id = p.id;

select * from all_nouns;