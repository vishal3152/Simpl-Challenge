package org.ipl;

/**
 * Represents the Player.
 * All members are private to package
 * final :- methods can not be overridden
 *
 * @author Vishal
 * @version 1.0
 * @since 19-08-2018
 */
final class Player {
    private String playerName;
    private String playerId;                                // Each player has a unique id, refer to PlayerDataEnum.java
    private int runScored;
    private int ballsPlayedCount;
    private boolean isNotOut;                               // if true, player has not retired yet

    private Player(String playerId) {
        this.playerId = playerId;
        this.runScored = 0;
        this.ballsPlayedCount = 0;
        this.isNotOut = true;
        this.playerName = PlayerDataEnum.valueOf(playerId).getPlayerName();

    }

    /**
     * Get the player instance
     *
     * @param playerId This is the unique playerId, refer to PlayerDataEnum.java
     * @return Player
     */
    static Player getInstance(String playerId) {
        return new Player(playerId);
    }

    /**
     * Retire the batsman
     */
    void retireBatsman() {
        this.isNotOut = false;
    }

    /**
     * check if player has retired or not
     *
     * @return boolean  True, if player has not retired, else false
     */
    boolean isPlayerNotOut() {
        return this.isNotOut;
    }

    /**
     * Increment player scoreboard
     *
     * @param run The run scored by player
     */
    void incrementScore(int run) {
        this.runScored += run;
    }

    /**
     * Return the player score
     *
     * @return int  The player score
     */
    int getPlayerScore() {
        return this.runScored;
    }

    /**
     * increment the ball played by a factor of +1
     */
    void incrementBallsPlayedCount() {
        this.ballsPlayedCount++;
    }

    /**
     * Get no of balls played by player
     *
     * @return int The number of balls played by the player
     */
    int getBallsPlayedCount() {
        return this.ballsPlayedCount;
    }

    /**
     * Get Player Name
     *
     * @return String the player name
     */
    String getPlayerName() {
        return this.playerName;
    }

    /**
     * This method generates a random outcome on the basis of player probability to determine the runs scored per ball
     *
     * @return int <li>
     * 0, player has scored 0 run
     * 1, player has scored 1 run
     * 2, player has scored 2 runs
     * 3, player has scored 3 runs
     * 4, player has scored 4 runs
     * 5, player has scored 5 runs
     * 6, player has scored 6 runs
     * -1, player has bowled out
     * </li>
     * @throws KPLException
     */
    int playBall() throws KPLException {

        return PlayerDataEnum.valueOf(this.playerId).playBall();
    }


}
