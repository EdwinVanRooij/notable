drop view if exists all_personal_pronouns;

create view all_personal_pronouns as 
select w.language, p.name as phrase, n.sex, n.formal, n.first_person_singular, n.second_person_singular, n.third_person_singular, n.first_person_plural, n.second_person_plural , n.third_person_plural
from donny_personal_pronoun n, donny_word w, donny_word_phrase wp, donny_phrase p
where n.id = w.id
and w.id = wp.word_id
and wp.phrase_id = p.id;

select * from all_personal_pronouns;