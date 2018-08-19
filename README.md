# Simpl-Challenge

This program simulates the single inning of a match played in T20 format.
The match simulation will require a weighted random number generation
based on probability of player to score the runs per ball.

Player specific probabilities are set in a enum class : <b>PlayerDataEnum.java</b>. Enums are more readable, type safe and more powerful.Any player needs to be added to player pool should be added here.


The entire ipl pacakage exposes only four public method:
1. Team.getInstance(String playerId)
2. Team.addPlayer(String playerId)
3. Match.getInstance(Team teamOne, Team teamTwo, int targetScore, int overs)
4. Match.start()

Player object is only brought into heap when required.

Here is how to start a match:

```Java
public static void main(String[] args) throws Exception {


        Team blrTeam = Team.getInstance("Bangalore");           //Bootstrap Bangalore team, will bat
        Team chennaiTeam = Team.getInstance("Chennai");         //Bootstrap opponent team Chennai, will field

        // Add players to team, each player has an unique ID. One player can be added only
        // once. The insertion order in the queue decides the batting order.

   
        blrTeam.addPlayer("IN001");     // adding Kirat Boli, refer PlayerDataEnum.java
        blrTeam.addPlayer("IN002");     // adding N.S Dhoni, refer PlayerDataEnum.java
        blrTeam.addPlayer("IN003");     // adding R Rumrah, , refer PlayerDataEnum.java
        blrTeam.addPlayer("IN004");     // adding Shashi Henra, , refer PlayerDataEnum.java
        

        // Adding no player to Chennai team, don't see any use as per the problem statement.

        // Bootstrap match with teams, score and no of overs
        Match match = Match.getInstance(blrTeam, chennaiTeam, 40, 4);

        // Let's play!!!!!!!!
        match.startMatch();
    }
```


===============================================================

## Sample output:

```
===========   Match: 1  ==========

4 overs left. 40 runs to win.
0.1 Kirat Boli scores 4 runs
0.2 Kirat Boli scores 4 runs
0.3 Kirat Boli scores 2 runs
0.4 Kirat Boli scores 1 run
0.5 N.S Nodhi scores 4 runs
0.6 N.S Nodhi scores 6 runs

3 overs left. 19 runs to win.
1.1 Kirat Boli scores 1 run
1.2 N.S Nodhi scores 1 run
1.3 Kirat Boli scores 2 runs
1.4 Kirat Boli scores 2 runs
1.5 Kirat Boli scores 6 runs
1.6 Kirat Boli scores 2 runs

2 overs left. 5 runs to win.
2.1 N.S Nodhi scores 2 runs
2.2 N.S Nodhi scores 6 runs

Bangalore won by 4 wicket and 10 balls remaining
Kirat Boli - 24* (9 balls)
N.S Nodhi - 19* (5 balls)

=================   Match: 2  =====================

4 overs left. 40 runs to win.
0.1 Kirat Boli scores 0 run
0.2 Kirat Boli scores 0 run
0.3 Kirat Boli scores 2 runs
0.4 Kirat Boli scores 2 runs
0.5 Kirat Boli scores 1 run
0.6 N.S Nodhi scores 2 runs

3 overs left. 33 runs to win.
1.1 Kirat Boli scores 1 run
1.2 N.S Nodhi scores 4 runs
1.3 N.S Nodhi scores 1 run
1.4 Kirat Boli scores 6 runs
1.5 Kirat Boli scores 4 runs
1.6 Kirat Boli scores 1 run

2 overs left. 16 runs to win.
2.1 Kirat Boli scores 4 runs
2.2 Kirat Boli scores 1 run
2.3 N.S Nodhi scores 2 runs
2.4 N.S Nodhi scores 1 run
2.5 Kirat Boli scores 1 run
2.6 N.S Nodhi scores 1 run

1 overs left. 6 runs to win.
3.1 N.S Nodhi scores 2 runs
3.2 N.S Nodhi scores 2 runs
3.3 N.S Nodhi scores 1 run
3.4 Kirat Boli scores 6 runs

Bangalore won by 4 wicket and 2 balls remaining
Kirat Boli - 29* (13 balls)
N.S Nodhi - 16* (9 balls)

===============   Match: 3  ==============

4 overs left. 40 runs to win.
0.1 Kirat Boli scores 3 runs
0.2 N.S Nodhi scores 2 runs
0.3 N.S Nodhi bowled out
0.4 R Rumrah scores 2 runs
0.5 R Rumrah scores 1 run
0.6 Kirat Boli scores 1 run

3 overs left. 31 runs to win.
1.1 Kirat Boli bowled out
1.2 Shashi Henra scores 4 runs
1.3 Shashi Henra scores 0 run
1.4 Shashi Henra scores 1 run
1.5 R Rumrah scores 0 run
1.6 R Rumrah scores 2 runs

2 overs left. 24 runs to win.
2.1 Shashi Henra bowled out

Bangalore lost the match by 24 runs
N.S Nodhi - 2 (2 balls)
Kirat Boli - 4 (3 balls)
Shashi Henra - 5 (4 balls)
R Rumrah - 5* (4 balls)

============  Match: 4  =================

4 overs left. 40 runs to win.
0.1 Kirat Boli scores 2 runs
0.2 Kirat Boli scores 4 runs
0.3 Kirat Boli scores 1 run
0.4 N.S Nodhi scores 1 run
0.5 Kirat Boli scores 1 run
0.6 N.S Nodhi scores 2 runs

3 overs left. 29 runs to win.
1.1 Kirat Boli scores 1 run
1.2 N.S Nodhi scores 1 run
1.3 Kirat Boli scores 1 run
1.4 N.S Nodhi scores 0 run
1.5 N.S Nodhi bowled out
1.6 R Rumrah scores 0 run

2 overs left. 26 runs to win.
2.1 Kirat Boli scores 6 runs
2.2 Kirat Boli scores 5 runs
2.3 R Rumrah bowled out
2.4 Shashi Henra bowled out

Bangalore lost the match by 15 runs
N.S Nodhi - 4 (5 balls)
R Rumrah - 0 (2 balls)
Shashi Henra - 0 (1 balls)
Kirat Boli - 21* (8 balls)

============   Match: 5  =================

4 overs left. 40 runs to win.
0.1 Kirat Boli scores 2 runs
0.2 Kirat Boli scores 2 runs
0.3 Kirat Boli scores 4 runs
0.4 Kirat Boli scores 3 runs
0.5 N.S Nodhi scores 5 runs
0.6 Kirat Boli scores 2 runs

3 overs left. 22 runs to win.
1.1 N.S Nodhi scores 0 run
1.2 N.S Nodhi scores 1 run
1.3 Kirat Boli scores 1 run
1.4 N.S Nodhi scores 0 run
1.5 N.S Nodhi scores 1 run
1.6 Kirat Boli scores 2 runs

2 overs left. 17 runs to win.
2.1 N.S Nodhi bowled out
2.2 R Rumrah bowled out
2.3 Shashi Henra scores 1 run
2.4 Kirat Boli scores 1 run
2.5 Shashi Henra bowled out

Bangalore lost the match by 15 runs
N.S Nodhi - 7 (6 balls)
R Rumrah - 0 (1 balls)
Shashi Henra - 1 (2 balls)
Kirat Boli - 17* (8 balls)

```
