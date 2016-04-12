drop view if exists all_syntaxes;

create view all_syntaxes as 
select s.id as syntax_id, s.sentence_type as syntax_name, ph.name as phrase
from donny_phrase ph, donny_position po, donny_syntax s
where s.id = po.syntax_id
and ph.id = po.phrase_id
order by syntax_id, po.position;

select * from all_syntaxes;