package org.ipl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Represents the Team
 * Not thread safe
 * final :- class can not inherited, methods can not overridden by subclasses.
 *
 * @author Vishal
 * @version 1.0
 * @since 19-08-2018
 */
public final class Team {
    private final Queue<String> playerQueue;                 // The queue to store batting order
    private String teamName;
    private List<Player> playerStatsList;                    // player state is stored in here after the player has retired


    private Team(String teamName) {
        this.teamName = teamName;
        playerQueue = new LinkedList<>();
        playerStatsList = new LinkedList<>();
    }

    /**
     * Get the team Instance
     *
     * @param teamName The team name
     * @return Team the instance of @teamName
     */
    public static Team getInstance(String teamName) {
        return new Team(teamName);
    }

    /**
     * Return the state of the retired players
     *
     * @return List<Player> a list of retired players
     */
    List<Player> getPlayerStats() {
        return playerStatsList;
    }

    /**
     * Add player stats to list after player has retired
     *
     * @param player The player
     */
    void addPlayerStats(Player player) {
        this.playerStatsList.add(player);
    }

    /**
     * Add player to the team. This method should be used to add players before the match start.
     * Insertion order defines the batting order
     * <b>This is not a thread safe method.</b> It's client responsibility to make sure that this method is not invoked
     * on object after match has started.
     *
     * @param playerId The unique player id to be added to batting order
     */
    public void addPlayer(String playerId) {
        if (this.playerQueue.contains(playerId)) {
            return;
        }

        this.playerQueue.add(playerId);
    }

    /**
     * Get the next man in from the queue
     *
     * @return Player
     * @throws KPLException
     */
    Player getPlayer() throws KPLException {
        if (isEmpty()) {
            throw (new KPLException("Entire team has been bowled out"));
        }
        return Player.getInstance(playerQueue.remove());
    }

    /**
     * Get the number of not out players at given moment of time.
     *
     * @return int The no of players who has not retired
     */
    int getNotBowledOutPlayerCount() {
        return playerQueue.size() + 2;
    }

    /**
     * Get team name
     *
     * @return String Team Name
     */

    String getTeamName() {
        return this.teamName;
    }

    /**
     * Check if the entire team has been bowled out.
     *
     * @return boolean  True, if all players has been bowled out
     */
    boolean isEmpty() {
        return playerQueue.isEmpty();
    }

}
