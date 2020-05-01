package util;

import com.mongodb.*;

import java.util.*;

public class Draft {
    public static boolean gameFlag;

    String url = "localhost";
    MongoClient connect = new MongoClient(url, 27017);
    DB db = connect.getDB("Project");
    DBCollection club = db.getCollection("groupStageClubs");
    public static List groupList = new ArrayList();
    DBCursor cur = club.find();

    public DBCursor getData() {

        System.out.println("Start getData::::");
        String url = "localhost";
        MongoClient connect = new MongoClient(url, 27017);
        DB db = connect.getDB("Project");
        DBCollection Players = db.getCollection("Players");

        List clubList = Players.distinct("club");

        club.remove(new BasicDBObject());

        Random rand = new Random();

        for (int i = 0; i < 16; i++) {
            int index = rand.nextInt(clubList.size());
            groupList.add(clubList.get(index));
            collectionForClub(groupList);
        }
        System.out.println("Draft::::" + groupList.toString());
        return club.find();
    }


    public void collectionForClub(List clubList1) {

        BasicDBObject objectDetails = new BasicDBObject();
        for (int j = 0; j < clubList1.size(); j++) {
            objectDetails.put("clubName", clubList1.get(j));
            objectDetails.put("Matches", 0);
            objectDetails.put("Win", 0);
            objectDetails.put("Loss", 0);
            objectDetails.put("Draw", 0);
            objectDetails.put("Points", 0);
            objectDetails.put("team_score", 0);

            if (j < 4) {
                objectDetails.put("GroupType", "A");
            }

            if (j >= 4 && j < 8) {
                objectDetails.put("GroupType", "B");
            }

            if (j >= 8 && j < 12) {
                objectDetails.put("GroupType", "C");
            }

            if (j >= 12 && j < 16) {
                objectDetails.put("GroupType", "D");
            }

        }
        club.insert(objectDetails);
        groupList = clubList1;
    }

    public List getGroupWiseList(String groupType) {

        List listOfGroup = new ArrayList();

        for (int i = 0; i < groupList.size(); i++) {
            if (cur.toArray().get(i).get("GroupType").toString().equals(groupType)) {
                listOfGroup.add(cur.toArray().get(i).get("clubName").toString());
            }

        }
        return listOfGroup;
    }

    public List<Group> pairs(String name) {
        int team1Score = 0, team2Score = 0;

        BasicDBObject team1Query = new BasicDBObject();
        BasicDBObject team2Query = new BasicDBObject();

        List groupListA = getGroupWiseList(name);
        List<Group> result = new ArrayList<>();
        Group obj;

        for (int i = 0; i < groupListA.size(); i++) {
            for (int j = 0; j < groupListA.size(); j++) {
                if (i != j) {

                    team1Query.append("clubName", groupList.get(i).toString());
                    team2Query.append("clubName", groupList.get(j).toString());

                    DBCursor scoreCursor1 = club.find(team1Query);
                    DBCursor scoreCursor2 = club.find(team2Query);

                    for (int p = 0; p < scoreCursor1.size(); p++) {
                        if (scoreCursor1.toArray().get(p).get("clubName").toString().equals(groupListA.get(i).toString())) {
                            team1Score = (int) scoreCursor1.toArray().get(p).get("team_score");
                            break;
                        }
                    }
                    for (int p = 0; p < scoreCursor2.size(); p++) {
                        if (scoreCursor2.toArray().get(p).get("clubName").toString().equals(groupListA.get(j).toString())) {
                            team2Score = (int) scoreCursor2.toArray().get(p).get("team_score");
                            break;
                        }
                    }

                    obj = new Group(groupListA.get(i).toString(), team1Score, groupListA.get(j).toString(), team2Score);
                    result.add(obj);
                }
            }

        }
        return result;
    }

}


