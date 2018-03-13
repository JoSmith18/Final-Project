CREATE TYPE gender 
as ENUM 
('Male',
'Female');

CREATE TYPE answers 
as ENUM
('A',
'B',
'C',
'D');

CREATE TABLE members (
    id Serial Unique Primary Key, 
    memberName text, 
    age Integer,
    matchingPoints Integer, 
    email text, 
    password Varchar
);

CREATE TABLE preferences (id Serial Unique Primary Key, 
memberId Integer references members (id),
answer1 answers,
answer2 gender,
answer3 answers,
answer4 answers,
answer5 answers,
answer6 answers,
answer7 answers,
answer8 answers);

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

INSERT INTO members (memberName,email,password) VALUES (
    'Shedlia',
    'sfreeman@basecampcodingacademy.org',
    'password123');

INSERT INTO preferences (memberId,answer1,answer2,answer3,answer4,answer5,answer6,answer7,answer8) VALUES (1, 75, 'Female','A','C','C','A','A','A');

SELECT * FROM memberPreferences;