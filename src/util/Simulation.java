package util;

import com.mongodb.*;

import java.util.*;

public class Simulation {
    String url = "localhost";
    MongoClient connect = new MongoClient(url, 27017);
    DB db = connect.getDB("Project");
    DBCollection players = db.getCollection("Players");
    DBCollection club = db.getCollection("groupStageClubs");
    Draft cls = new Draft();

    public void startGame() {

        List groupAList = cls.getGroupWiseList("A");

        List groupBList = cls.getGroupWiseList("B");

        List groupCList = cls.getGroupWiseList("C");

        List groupDList = cls.getGroupWiseList("D");
        gamePlay(groupAList);
        gamePlay(groupBList);
        gamePlay(groupCList);
        gamePlay(groupDList);

    }

    public List createTeam(String teamName) {

        List playerMap = new ArrayList();
        BasicDBObject findTeam = new BasicDBObject();
        findTeam.put("club", teamName);
        DBCursor playerCursor = players.find(findTeam);
        int GKFlag = 0;

        for (int i = 0; i < 11; i++) {
            Random rand = new Random();
            int index = rand.nextInt(playerCursor.toArray().size());
            String playerName = playerCursor.toArray().get(index).get("short_name").toString();
            String playerR = playerCursor.toArray().get(index).get("overall").toString();

            if (GKFlag == 0) {
                if (playerCursor.toArray().get(index).get("player_positions").toString().equals("GK")) {
                    playerMap.add(playerName);
                    GKFlag = 1;
                } else {
                    playerMap.add(playerName);
                }
            } else {
                playerMap.add(playerName);
            }
        }
        return playerMap;
    }

    public void gamePlay(List teamsList) {


        for (int i = 0; i < teamsList.size(); i++) {
            for (int j = 0; j < teamsList.size(); j++) {

                if (teamsList.get(i).toString() == teamsList.get(j).toString()) {

                } else {
                    int goalScoreForTeam1 = 0;
                    int goalScoreForTeam2 = 0;

                    String team1 = teamsList.get(i).toString();
                    String team2 = teamsList.get(j).toString();

                    BasicDBObject goalUpdate1 = new BasicDBObject();
                    BasicDBObject goalUpdate2 = new BasicDBObject();
                    BasicDBObject updateField1 = new BasicDBObject();

                    updateField1.append("team_score", 0);

                    BasicDBObject searchQuery1 = new BasicDBObject();
                    BasicDBObject searchQuery2 = new BasicDBObject();
                    searchQuery1.append("clubName", team1);

                    goalUpdate1.append("$set", updateField1);
                    club.update(searchQuery1, goalUpdate1);

                    searchQuery2.put("clubName", team2);
                    goalUpdate2.append("$set", updateField1);
                    club.update(searchQuery2, goalUpdate2);


                    List playerTeam1 = createTeam(team1);
                    List playerTeam2 = createTeam(team2);

                    BasicDBObject findTeam1 = new BasicDBObject();
                    BasicDBObject findTeam2 = new BasicDBObject();

                    DBCursor team1Cursor = players.find(findTeam1);
                    DBCursor team2Cursor = players.find(findTeam2);

                    Random rand = new Random();

                    for (int k = 0; k < playerTeam1.size() / 2; k++) {
                        int index = rand.nextInt(playerTeam1.size() / 2);

                        findTeam1.put("club", team1);
                        findTeam2.put("club", team2);

                        int team1Player = 0;
                        int team2Player = 0;

                        for (int p = 0; p < team1Cursor.size(); p++) {
                            if (team1Cursor.toArray().get(p).get("short_name").toString().equals(playerTeam1.get(index).toString())) {
                                team1Player = (int) team1Cursor.toArray().get(p).get("overall");
                                break;
                            }
                        }

                        for (int p = 0; p < team2Cursor.size(); p++) {
                            if (team2Cursor.toArray().get(p).get("short_name").toString().equals(playerTeam2.get(index).toString())) {
                                team2Player = (int) team2Cursor.toArray().get(p).get("overall");
                                break;
                            }
                        }

                        if (team1Player > team2Player) {

                            BasicDBObject goalUpdate3 = new BasicDBObject();
                            BasicDBObject goalUpdateT2 = new BasicDBObject();
                            BasicDBObject updateField2 = new BasicDBObject();
                            BasicDBObject updateFieldT2 = new BasicDBObject();
                            BasicDBObject searchQuery3 = new BasicDBObject();
                            BasicDBObject searchTeam2 = new BasicDBObject();

                            BasicDBObject uInc1 = new BasicDBObject();
                            BasicDBObject uInc2 = new BasicDBObject();

                            BasicDBObject playersQ1 = new BasicDBObject();
                            BasicDBObject playerUpdate1 = new BasicDBObject();

                            goalScoreForTeam1++;

                            playersQ1.put("short_name", playerTeam1.get(index).toString());
                            playerUpdate1.append("$inc", new BasicDBObject("goal_scored", 1));
                            playerUpdate1.append("$inc", new BasicDBObject("matchCount", 1));

                            updateField2.append("team_score", goalScoreForTeam1);
                            uInc1.append("$inc", new BasicDBObject("goalScore", 1));
                            uInc2.append("$inc", new BasicDBObject("goalConceded", 1));

                            searchQuery3.put("clubName", team1);
                            goalUpdate3.append("$set", updateField2);

                            searchTeam2.put("clubName", team2);
                            goalUpdateT2.append("$set", updateFieldT2);


                            players.update(playersQ1, playerUpdate1);
                            club.update(searchQuery3, uInc1);
                            club.update(searchTeam2, uInc2);
                            club.update(searchQuery3, goalUpdate3);
                            club.update(searchTeam2, goalUpdate3);


                        } else if (team1Player < team2Player) {

                            BasicDBObject playersQ2 = new BasicDBObject();
                            BasicDBObject searchTeam1 = new BasicDBObject();
                            BasicDBObject updateFieldT3 = new BasicDBObject();
                            BasicDBObject goalUpdateT3 = new BasicDBObject();

                            BasicDBObject uIncc1 = new BasicDBObject();
                            BasicDBObject uIncc2 = new BasicDBObject();


                            BasicDBObject playerUpdate2 = new BasicDBObject();

                            BasicDBObject goalUpdate4 = new BasicDBObject();
                            BasicDBObject updateField3 = new BasicDBObject();
                            BasicDBObject searchQuery4 = new BasicDBObject();
                            goalScoreForTeam2++;

                            uIncc1.append("$inc", new BasicDBObject("goalScore", 1));
                            uIncc2.append("$inc", new BasicDBObject("goalConceded", 1));

                            playersQ2.put("short_name", playerTeam2.get(index).toString());
                            playerUpdate2.append("$inc", new BasicDBObject("goal_scored", 1));
                            playerUpdate2.append("$inc", new BasicDBObject("matchCount", 1));
                            updateField3.append("team_score", goalScoreForTeam1);

                            searchQuery4.put("clubName", team2);
                            searchTeam1.put("clubName", team1);

                            goalUpdateT3.append("$set", updateFieldT3);
                            goalUpdate4.append("$set", updateField3);
                            club.update(searchQuery4, goalUpdate4);

                            players.update(playersQ2, playerUpdate2);
                            club.update(searchTeam1, goalUpdate4);
                            club.update(searchQuery4, uIncc2);
                            club.update(searchTeam1, uIncc1);

                        } else {
                            System.out.println(team1Player + " Equal " + team2Player);
                        }
                    }


                    if (goalScoreForTeam1 > goalScoreForTeam2) {
                        BasicDBObject searchQuery5 = new BasicDBObject();
                        BasicDBObject searchQuery6 = new BasicDBObject();

                        BasicDBObject goalUpdate4 = new BasicDBObject();
                        BasicDBObject goalUpdate5 = new BasicDBObject();

                        searchQuery5.put("clubName", teamsList.get(i).toString());
                        goalUpdate4.append("$inc", new BasicDBObject("Win", 1).append("Matches", 1).append("Points", 3));
                        club.update(searchQuery5, goalUpdate4);

                        searchQuery6.put("clubName", teamsList.get(j).toString());
                        goalUpdate5.append("$inc", new BasicDBObject("Loss", 1).append("Matches", 1));
                        club.update(searchQuery6, goalUpdate5);
                    } else if (goalScoreForTeam2 > goalScoreForTeam1) {

                        BasicDBObject searchQuery7 = new BasicDBObject();
                        BasicDBObject searchQuery8 = new BasicDBObject();

                        BasicDBObject goalUpdate6 = new BasicDBObject();
                        BasicDBObject goalUpdate7 = new BasicDBObject();

                        searchQuery7.put("clubName", teamsList.get(j).toString());
                        goalUpdate6.append("$inc", new BasicDBObject("Win", 1).append("Matches", 1).append("Points", 3));
                        club.update(searchQuery7, goalUpdate6);
                        searchQuery8.put("clubName", teamsList.get(i).toString());
                        goalUpdate7.append("$inc", new BasicDBObject("Loss", 1).append("Matches", 1));
                        club.update(searchQuery8, goalUpdate7);
                    } else if (goalScoreForTeam2 == goalScoreForTeam1) {

                        BasicDBObject goalUpdate9 = new BasicDBObject();
                        BasicDBObject goalUpdate10 = new BasicDBObject();

                        BasicDBObject searchQuery9 = new BasicDBObject();
                        BasicDBObject searchQuery10 = new BasicDBObject();
                        searchQuery9.put("clubName", teamsList.get(i).toString());
                        goalUpdate9.append("$inc", new BasicDBObject("Draw", 1).append("Matches", 1).append("Points", 1));
                        club.update(searchQuery9, goalUpdate9);
                        searchQuery10.put("clubName", teamsList.get(j).toString());
                        goalUpdate10.append("$inc", new BasicDBObject("Draw", 1).append("Matches", 1).append("Points", 1));
                        club.update(searchQuery10, goalUpdate10);
                    }
                }
            }
        }
    }
}
