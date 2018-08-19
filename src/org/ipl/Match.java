package org.ipl;

/**
 * Represents a Match in the tournament. Two teams participate in match.  This class simulates a single inning.
 * It is presumed that a team has already and set the target @targetScore.
 * Not ThreadSafe
 * final :- can not be inherited, methods can not be overridden.
 *
 * @author Vishal
 * @version 1.0
 * @since 19-08-2018
 */
public final class Match {

    private Team a;                                     // Team 1
    private Team b;                                     // Team 2
    private int targetScore;
    private int currentTeamScore;                       // current scoreboard of the team
    private int overs;
    private Player firstBatsman;
    private Player secondBatsman;
    private Player onStrikeBatsman;


    private Match(Team one, Team two, int targetScore, int overs) {
        this.a = one;
        this.b = two;
        this.targetScore = targetScore;
        this.overs = overs;
    }

    /**
     * Returns the instance of Match class.
     *
     * @param teamOne     This represent the batting team. Team must not be empty
     * @param teamTwo     This represent the bowling team.
     * @param targetScore This is the target score set by the opponent team
     * @param overs       The numbers of overs. Must be greater than 0.
     * @return Match      An instance of Match class
     * @throws IllegalArgumentException
     */

    public static Match getInstance(Team teamOne, Team teamTwo, int targetScore, int overs) throws IllegalArgumentException {
        if (teamOne.isEmpty()) {
            throw new IllegalArgumentException(teamOne.getTeamName() + CustomExceptionMessages.Has_No_Player.toString());
        } else if (targetScore < 0) {
            throw new IllegalArgumentException(CustomExceptionMessages.Target_Score_Negative.toString());
        } else if (overs <= 0) {
            throw new IllegalArgumentException(CustomExceptionMessages.No_Match_With_Zero_Overs.toString());
        } /*else if (teamTwo.isEmpty()) {
            // throw new KPLException(teamTwo.getTeamName() + " has no player.");
        }*/

        return new Match(teamOne, teamTwo, targetScore, overs);
    }

    /**
     * Send the pair of openers.
     *
     * @throws KPLException if no players available in team to play
     */
    private void sendOpeners() throws KPLException {
        if (this.firstBatsman == null) {
            this.firstBatsman = a.getPlayer();
        }
        if (this.secondBatsman == null) {
            this.secondBatsman = a.getPlayer();
        }
    }

    /**
     * Replace the batsman after previous batsman retire from the field.
     *
     * @throws KPLException if no players available in team to play
     */
    private void replaceBatsman() throws KPLException {
        if (!this.firstBatsman.isPlayerNotOut()) {
            this.firstBatsman = a.getPlayer();
            this.onStrikeBatsman = this.firstBatsman;
        } else {
            this.secondBatsman = a.getPlayer();
            this.onStrikeBatsman = this.secondBatsman;
        }
    }

    /**
     * Simulates the strike change after batsman has scored 1,3,5 runs or after the end of over.
     */
    private void rotateStrike() {
        if (this.onStrikeBatsman == this.firstBatsman) {
            this.onStrikeBatsman = this.secondBatsman;
        } else {
            this.onStrikeBatsman = this.firstBatsman;
        }

    }

    /**
     * Prints the match summary after match has ended.
     */
    private void printMatchResults() {

        for (Player player : a.getPlayerStats()) {
            StringBuilder sb = new StringBuilder();
            sb.append(player.getPlayerName()).append(" - ").append(player.getPlayerScore()).append(" (").append(player.getBallsPlayedCount()).append(" balls)");
            System.out.println(sb.toString());
        }
        if (this.firstBatsman.isPlayerNotOut()) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.firstBatsman.getPlayerName()).append(" - ").append(this.firstBatsman.getPlayerScore()).append("* (").append(this.firstBatsman.getBallsPlayedCount()).append(" balls)");
            System.out.println(sb.toString());
        }
        if (this.secondBatsman.isPlayerNotOut()) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.secondBatsman.getPlayerName()).append(" - ").append(this.secondBatsman.getPlayerScore()).append("* (").append(this.secondBatsman.getBallsPlayedCount()).append(" balls)");
            System.out.println(sb.toString());
        }

    }

    /**
     * Starts the match.
     *
     * @throws KPLException if the batting team has no players.
     */
    public void startMatch() throws KPLException {
        if (a.isEmpty()) {
            throw (new KPLException(a.getTeamName() + " " + CustomExceptionMessages.Has_No_Player));
        }

        sendOpeners();                                                  // Send openers on field
        this.onStrikeBatsman = this.firstBatsman;                       // First batsman on strike

        int currentOver = 0;

        // Start playing overs
        while (currentOver < this.overs) {

            // Print commentary
            StringBuilder sb = new StringBuilder();
            sb.append("\n").append((this.overs - currentOver)).append(" overs left. ").append((this.targetScore - this.currentTeamScore)).append(" runs to win.");
            System.out.println(sb.toString());

            int currentBallCount = 1;

            // Start playing balls
            while (currentBallCount <= 6) {
                // Get the weighted random output for the ball played by onStrikeBatsman.
                // @ballOutcome==(-1) represents player has been bowled out
                int ballOutcome = this.onStrikeBatsman.playBall();

                // Increment the balls played by onStrikeBatsman by +1
                onStrikeBatsman.incrementBallsPlayedCount();
                if (ballOutcome != -1) {

                    // Increment the on strike batsman score and team score
                    onStrikeBatsman.incrementScore(ballOutcome);
                    this.currentTeamScore += ballOutcome;
                }

                switch (ballOutcome) {

                    case 0:
                    case 2:
                    case 4:
                    case 6:
                        String runPlurality = ballOutcome == 0 ? " run" : " runs";

                        // Print commentary
                        sb = new StringBuilder();
                        sb.append(currentOver).append(".").append(currentBallCount).append(" ").append(onStrikeBatsman.getPlayerName()).append(" scores ").append(ballOutcome).append(runPlurality);
                        System.out.println(sb.toString());

                        break;
                    case 1:
                    case 3:
                    case 5:
                        runPlurality = ballOutcome == 1 ? " run" : " runs";

                        // Print commentary
                        sb = new StringBuilder();
                        sb.append(currentOver).append(".").append(currentBallCount).append(" ").append(onStrikeBatsman.getPlayerName()).append(" scores ").append(ballOutcome).append(runPlurality);
                        System.out.println(sb.toString());

                        // Switch strike after on strike bats man has scored odd run/runs.
                        rotateStrike();
                        break;

                    case -1:
                        // Player has has been bowled out.
                        onStrikeBatsman.retireBatsman();

                        // Print commentary
                        sb = new StringBuilder();
                        sb.append(currentOver).append(".").append(currentBallCount).append(" ").append(onStrikeBatsman.getPlayerName()).append(" bowled out");
                        System.out.println(sb.toString());

                        //add player statistics for match summary
                        a.addPlayerStats(onStrikeBatsman);

                        // Check if any more players left in the team
                        if (a.isEmpty()) {

                            // No players left, match has ended
                            sb = new StringBuilder();
                            sb.append("\n").append(a.getTeamName()).append(" lost the match by ").append(this.targetScore - this.currentTeamScore).append(" runs");
                            System.out.println(sb.toString());

                            // Print match summary.
                            printMatchResults();
                            return;
                        }
                        replaceBatsman();
                        break;
                    default:
                        throw new RuntimeException(CustomExceptionMessages.Invalid_Ball_Outcome.toString());

                }

                currentBallCount = currentBallCount + 1;
                // Check if target score achieved
                if (this.targetScore <= this.currentTeamScore) {

                    //Team won the match, print commentary
                    sb = new StringBuilder();
                    sb.append("\n").append(a.getTeamName()).append(" won by ").append(a.getNotBowledOutPlayerCount()).append(" wicket and ").append((this.overs - currentOver - 1) * 6 + (6 - currentBallCount + 1)).append(" balls remaining");
                    System.out.println(sb.toString());

                    printMatchResults();
                    return;
                }
            }
            // Rotate strike after over end
            rotateStrike();
            currentOver++;

        }
        // Check for a tie.
        if (this.targetScore - 1 == this.currentTeamScore) {
            System.out.println("Match was a tie");
        } else {

            // Print commentary
            StringBuilder sb = new StringBuilder();
            sb.append(a.getTeamName()).append(" lost  by ").append(this.targetScore - this.currentTeamScore).append(" runs");
            System.out.println(sb.toString());
        }
    }
}
