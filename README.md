# birdsingning
Birdsinging is an example of backend for a quiz application name birdsing manage by level. 

Définition

Quiz
- a question 
- 3 options (String that could answer the question)
- an answer 

Level 
- id : 1, 2, 3..N
- a statut : ACTIVE, INACTIVE
- quizes a list of quiz

Business Needs : 
- A level should always have at least one quiz to be activate
- The level activate should have an id that correspond to coherant sequence 1, 2, 3, N 
For example: 1, 3, 4 is not allowed

