package org.ipl;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This immutable enum class represents the constant attributes for players. Players are declared as enum constant.
 * Player is identified a unique id e.g. INxxx. Player probability to score the run per ball is set here.
 * Any new player must be added here before adding to any team.
 * This class ensure type safety
 *
 * @author Vishal
 * @version 1.0
 * @since 19-08-2018
 */

enum PlayerDataEnum {
    IN001(new int[]{5, 30, 25, 10, 15, 1, 9, 5}, "Kirat Boli"),
    IN002(new int[]{10, 40, 20, 5, 10, 1, 4, 10}, "N.S Nodhi"),
    IN003(new int[]{20, 30, 15, 5, 5, 1, 4, 20}, "R Rumrah"),
    IN004(new int[]{30, 25, 5, 0, 5, 1, 4, 30}, "Shashi Henra");
    //IN005(new int[]{0, 0, 0, 50, 0, 0, 50, 0}, "Test");

    // outcome array represents the possible outcomes per ball
    private static final int[] outcomeArray = new int[]{0, 1, 2, 3, 4, 5, 6, -1};

    private final int[] playerProbabilityArray;   //player probabilities to score the run per ball.
    private final String playerName;

    // Constructor to associate data with enum constants
    PlayerDataEnum(int[] playerProbabilityArray, String playerName) {
        this.playerProbabilityArray = playerProbabilityArray;
        this.playerName = playerName;
    }

    /**
     * This method generates a random outcome from outcomeArray on the basis of player probability to score run per ball
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
        return generateOutcome(this.playerProbabilityArray);
    }

    /**
     * Get player name associated with enum constant
     *
     * @return String The player name
     */
    String getPlayerName() {
        return this.playerName;
    }

    /**
     * This method generates a random outcome on the basis of player probability to determine the runs scored per ball
     *
     * @param playerProbabilityArray The player probabilities to score the run per ball.
     * @return int  a weighted selection from @outcomeArray
     * @throws KPLException
     */
    private int generateOutcome(int[] playerProbabilityArray) throws KPLException {
        if (playerProbabilityArray.length != outcomeArray.length) {
            throw new RuntimeException("frequency distribution does not correlate with outcome array");
        }
        int[] pds = new int[playerProbabilityArray.length];
        pds[0] = playerProbabilityArray[0];

        for (int i = 1; i < playerProbabilityArray.length; i++) {
            pds[i] = pds[i - 1] + playerProbabilityArray[i];
        }

        //Returns a random, uniformly distributed value between the given least value (inclusive) and bound (exclusive).
        // Added 1 to make top value inclusive
        int randomNum = ThreadLocalRandom.current().nextInt(1, pds[pds.length - 1] + 1);

        //finding index of ceiling of randomNum using binary search
        int ceilIndex = findCeil(randomNum, pds);
        if (ceilIndex == -1) {
            throw new KPLException(CustomExceptionMessages.Error_Finding_Random.toString());
        } else {
            return outcomeArray[ceilIndex];
        }
    }

    /**
     * Find ceiling for @randNum
     *
     * @param randNum Number
     * @param pds     Array to look ceiling for
     * @return int    randNum ceiling from pds array
     */
    private int findCeil(int randNum, int[] pds) {
        int startIndex = 0;
        int endIndex = pds.length - 1;

        while (startIndex < endIndex) {
            int mid = (startIndex + endIndex) / 2;
            if (randNum < pds[mid]) {
                endIndex = mid;
            } else {
                startIndex = mid + 1;
            }
        }
        if (pds[startIndex] >= randNum) {
            return startIndex;
        }
        return -1;
    }
}
