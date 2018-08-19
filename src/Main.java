import org.ipl.Match;
import org.ipl.Team;

/**
 * Main driver class for KPL match
 */
public class Main {

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

}
