drop view if exists all_phrases;

create view all_phrases as 
select * from donny_phrase;

select * from all_phrases;