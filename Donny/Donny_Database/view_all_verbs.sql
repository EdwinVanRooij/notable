drop view if exists all_verbs;

create view all_verbs as 
select w.language, p.name as phrase, v.infinitive, v.first_person_singular, v.second_person_singular, v.third_person_singular, v.first_person_plural, v.second_person_plural, v.third_person_plural
from donny_verb v, donny_word w, donny_word_phrase wp, donny_phrase p
where v.id = w.id
and w.id = wp.word_id
and wp.phrase_id = p.id;

select * from all_verbs;