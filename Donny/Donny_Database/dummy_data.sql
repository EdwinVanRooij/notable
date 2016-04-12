-- Reusability
delete from donny_word_phrase;
delete from donny_position;
delete from donny_syntax;

delete from donny_verb;
delete from donny_noun;
delete from donny_personal_pronoun;
delete from donny_article;

delete from donny_word;
delete from donny_phrase;
ALTER TABLE donny_syntax AUTO_INCREMENT = 1;
ALTER TABLE donny_position AUTO_INCREMENT = 1;
ALTER TABLE donny_word AUTO_INCREMENT = 1;
ALTER TABLE donny_phrase AUTO_INCREMENT = 1;

-- Insert phrase types
insert into donny_phrase (name) values ('persoonsvorm');
insert into donny_phrase (name) values ('onderwerp');
insert into donny_phrase (name) values ('lijdend_voorwerp');
insert into donny_phrase (name) values ('ignored_phrase');

insert into donny_phrase (name) values ('test_purposes_1');
insert into donny_phrase (name) values ('test_purposes_2');
insert into donny_phrase (name) values ('test_purposes_3');
insert into donny_phrase (name) values ('test_purposes_4');
insert into donny_phrase (name) values ('test_purposes_5');
insert into donny_phrase (name) values ('test_purposes_6');
insert into donny_phrase (name) values ('test_purposes_7');

-- Insert verbs
insert into donny_word (language) values ('dutch');
insert into donny_verb (id, infinitive, first_person_singular, second_person_singular, third_person_singular, first_person_plural, second_person_plural, third_person_plural) values ((select max(id) from donny_word), 'lopen', 'loop','loopt','loopt', 'lopen', 'lopen', 'lopen');
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'persoonsvorm'), (select max(id) from donny_verb));
-- alle vervoegingen van lopen, zinsdeel: persoonsvorm

insert into donny_word (language) values ('dutch');
insert into donny_verb (id, infinitive, first_person_singular, second_person_singular, third_person_singular, first_person_plural, second_person_plural, third_person_plural) values ((select max(id) from donny_word), 'praten', 'praat','praat','praat','praten', 'praten', 'praten');
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'persoonsvorm'), (select max(id) from donny_verb));
-- alle vervoegingen van praten, zinsdeel: persoonsvorm

insert into donny_word (language) values ('dutch');
insert into donny_verb (id, infinitive, first_person_singular, second_person_singular, third_person_singular, first_person_plural, second_person_plural, third_person_plural) values ((select max(id) from donny_word), 'neuken', 'neuk','neukt','neukt','neuken', 'neuken', 'neuk');
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'persoonsvorm'), (select max(id) from donny_verb));
-- alle vervoegingen van praten, zinsdeel: persoonsvorm

insert into donny_word (language) values ('dutch');
insert into donny_verb (id, infinitive, first_person_singular, second_person_singular, third_person_singular, first_person_plural, second_person_plural, third_person_plural) values ((select max(id) from donny_word), 'roepen', 'roep', 'roept','roept','roepen', 'roepen', 'roepen');
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'persoonsvorm'), (select max(id) from donny_verb));
-- alle vervoegingen van lopen, zinsdeel: persoonsvorm

-- Insert nouns
insert into donny_word (language) values ('dutch');
insert into donny_noun (id, word, sex) values ((select max(id) from donny_word), 'boom', 1);
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'onderwerp'), (select max(id) from donny_noun));
-- boom, zinsdeel: onderwerp

insert into donny_word (language) values ('dutch');
insert into donny_noun (id, word, sex) values ((select max(id) from donny_word), 'aap', 1);
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'onderwerp'), (select max(id) from donny_noun));
-- aap, zinsdeel: onderwerp

insert into donny_word (language) values ('dutch');
insert into donny_noun (id, word, sex) values ((select max(id) from donny_word), 'schaap', 0);
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'onderwerp'), (select max(id) from donny_noun));
-- schaap, zinsdeel: onderwerp

-- Insert personal_pronoun
insert into donny_word (language) values ('dutch');
insert into donny_personal_pronoun (id, sex, formal, first_person_singular, second_person_singular, third_person_singular, first_person_plural, second_person_plural, third_person_plural) 
values ((select max(id) from donny_word), 1, 0, 'ik', 'jij', 'hij', 'wij', 'jullie', 'zij');
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'lijdend_voorwerp'), (select max(id) from donny_personal_pronoun));
-- psv, mannelijk persoonlijk voornaamwoord

insert into donny_word (language) values ('dutch');
insert into donny_article (id, singular, plural, sex, is_definite) 
values ((select max(id) from donny_word), 'een', 'de', 0, 0);
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'ignored_phrase'), (select max(id) from donny_article));
-- lidwoord, ignored_phrase, onbepaald lw onzijdig

	insert into donny_word (language) values ('dutch');
insert into donny_article (id, singular, plural, sex, is_definite) 
values ((select max(id) from donny_word), 'een', 'de', 1, 0);
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'ignored_phrase'), (select max(id) from donny_article));
-- lidwoord, ignored_phrase, onbepaald lw mannelijk

	insert into donny_word (language) values ('dutch');
insert into donny_article (id, singular, plural, sex, is_definite) 
values ((select max(id) from donny_word), 'een', 'de', 2, 0);
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'ignored_phrase'), (select max(id) from donny_article));
-- lidwoord, ignored_phrase, onbepaald lw vrouwelijk

insert into donny_word (language) values ('dutch');
insert into donny_article (id, singular, plural, sex, is_definite) 
values ((select max(id) from donny_word), 'het', 'het', 0, 1);
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'ignored_phrase'), (select max(id) from donny_article));
-- lidwoord, ignored_phrase, bepaald lw onzijdig

	insert into donny_word (language) values ('dutch');
insert into donny_article (id, singular, plural, sex, is_definite) 
values ((select max(id) from donny_word), 'de', 'de', 1, 1);
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'ignored_phrase'), (select max(id) from donny_article));
-- lidwoord, ignored_phrase, bepaald lw mannelijk

	insert into donny_word (language) values ('dutch');
insert into donny_article (id, singular, plural, sex, is_definite) 
values ((select max(id) from donny_word), 'de', 'de', 2, 1);
insert into donny_word_phrase (phrase_id, word_id) values ((select id from donny_phrase where name = 'ignored_phrase'), (select max(id) from donny_article));
-- lidwoord, ignored_phrase, bepaald lw vrouwelijk

-- Insert syntaxes
insert into donny_syntax (sentence_type) values ('normale_zin');
insert into donny_position (syntax_id, phrase_id, position) values ((select max(id) from donny_syntax), (select id from donny_phrase where name = 'ignored_phrase'), 1);
insert into donny_position (syntax_id, phrase_id, position) values ((select max(id) from donny_syntax), (select id from donny_phrase where name = 'onderwerp'), 2);
insert into donny_position (syntax_id, phrase_id, position) values ((select max(id) from donny_syntax), (select id from donny_phrase where name = 'persoonsvorm'), 3);

insert into donny_syntax (sentence_type) values ('vraagzin');
insert into donny_position (syntax_id, phrase_id, position) values ((select max(id) from donny_syntax), (select id from donny_phrase where name = 'persoonsvorm'), 1);
insert into donny_position (syntax_id, phrase_id, position) values ((select max(id) from donny_syntax), (select id from donny_phrase where name = 'ignored_phrase'), 2);
insert into donny_position (syntax_id, phrase_id, position) values ((select max(id) from donny_syntax), (select id from donny_phrase where name = 'onderwerp'), 3);