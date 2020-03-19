# Dragons of Mugloar

Auto playing client for the game API.\
Client can reliably get past 1000 points and is able to play indefinetly without ever losing with some frequency.
 

## Installation

Make sure to have JDK 1.8 installed.\
Also, make sure to have maven CLI working. You can check [this guide](https://www.baeldung.com/install-maven-on-windows-linux-mac) for a quick setup.\
On project root directory, run:
```
$ mvn clean install
```
This will build the project.

### Running the game
On project root directory, run:
```
$ mvn spring-boot:run
```
Check console for turn to turn notification until it finishes the game.

### Game Breakdown

- The game is a turn based rpg, where higher the turn, higher the difficulty.
- One way to get on par with the difficulty is leveling up your dragon by buying items on the shop.
- Getting messages (missions or ads) doesn't end turn. Solving mission, investigating reputation or buying items does.
- Couldn't find any useful purpose to investigate reputation, since it skips a turn and is better to not increase game difficulty for no reason.
- Sometimes messages are encrypted (1 for base64 encoding and 2 for Rot13 encoding).
- Noticed that missions that involved stealing would mostly fail even with the best probability of success, so they will be removed when getting the best message to solve.
- Probabilities were weighted by playing the game repeatedly and, when finding the best mission to solve, the easiest one with higher reward would be chosen.
- Shopping should prioritize buying 300 gold items since they level up your dragon twice for the cost of a turn.
- Ramping up the game is the difficult part.