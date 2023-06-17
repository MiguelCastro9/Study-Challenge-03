## Overview

The challenge is to create a deck (using the API available at the end of the file) and assemble four “hands” with 5 cards each, checking which “hand” has the highest sum. If there is a tie, return all players on the screen.

Rules:
<br>
ACE = 1
<br>
KING = 13
<br>
QUEEN = 12
<br>
JACK = 11

Example:
<br>
Player 1 = [A,2,3,4,5]
<br>
Player 2 = [K,Q,J,10,9]
<br>
Player 3 = [8,9,2,A,J]
<br>
Player 4 = [2,2,5,7,2]
<br>

Winner is Player 2 with 55 points.

API: https://deckofcardsapi.com/

<br> 

## Execution

Tools:
<li> Java 11 </li>
<li> SpringBoot 2.7.12 </li>
<br>
Back-end: 
Import the "cardsAPI" project into an IDE of your choice, the IDE used in the development was Netbeans IDE,
so just build the application to download the dependencies that are managed by Maven,
in relation to the database it is already configured and when running the application it will connect automatically,
the database used was H2 (database in memory), and the application was also documented with OpenAPI (Swagger),
and while the application is running, just access the link http://localhost:8080/swagger-ui/index.html to analyze the application's endpoints.


<br>

## Demo

![image](https://github.com/MiguelCastro9/Study-Challenge-03/assets/56695817/b8a4c807-93b1-4bf4-9025-a758bfa7e5a5)

![image](https://github.com/MiguelCastro9/Study-Challenge-03/assets/56695817/b5dfd26b-581a-45cb-977e-c5d14ee4a019)






