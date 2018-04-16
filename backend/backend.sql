CREATE TABLE members (
    id Serial Unique Primary Key ,
    memberName text,
    gender text,
    age text,
    phoneNumber text,
    githubLink text,
    password Varchar,
    sessionKey text
);

CREATE TABLE preferences (id Serial Unique Primary Key, 
memberId Integer references members (id) ON DELETE CASCADE,
answer1 text,
answer2 text,
answer3 text,
answer4 text,
answer5 text,
answer6 text,
answer7 text,
answer8 text);

CREATE TABLE matchBox (id Serial Unique Primary Key,
senderID Integer references members (id) ON DELETE CASCADE,
receiverID Integer references members (id) ON DELETE CASCADE,
 message Text,
 postedAt timestamp)