drop view if exists all_articles;

create view all_articles as 
select w.language, p.name as phrase, a.singular, a.plural, a.sex, a.is_definite
from donny_article a, donny_word w, donny_word_phrase wp, donny_phrase p
where a.id = w.id
and w.id = wp.word_id
and wp.phrase_id = p.id;

select * from all_articles;