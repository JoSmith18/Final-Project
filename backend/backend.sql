CREATE TABLE members (
    id Serial Unique Primary Key, 
    memberName text, 
    age text,
    matchingPoints Integer, 
    email text, 
    password Varchar
);

CREATE TABLE preferences (id Serial Unique Primary Key, 
memberId Integer references members (id),
answer1 text,
answer2 text,
answer3 text,
answer4 text,
answer5 text,
answer6 text,
answer7 text,
answer8 text);

CREATE VIEW memberPreferences 
AS SELECT memberId,
memberName, 
answer1 as Answer1, 
answer2 as Answer2,
answer3 as Answer3,
answer4 as Answer4,
answer5 as Answer5,
answer6 as Answer6,
answer7 as Answer7,
answer8 as Answer8
FROM members, preferences 
GROUP BY memberId,memberName,answer1,answer2,answer3,answer4,answer5,answer6,answer7,answer8;

--INSERT INTO members (memberName,email,password) VALUES (
--    'Shedlia',
--    'sfreeman@basecampcodingacademy.org',
--    'password123');
--
--INSERT INTO preferences (memberId,answer1,answer2,answer3,answer4,answer5,answer6,answer7,answer8) VALUES (1, 75, 'Female','A','C','C','A','A','A');
--
--SELECT * FROM memberPreferences;