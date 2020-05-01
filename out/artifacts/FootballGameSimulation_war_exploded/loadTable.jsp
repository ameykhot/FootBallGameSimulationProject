<%--
  Created by IntelliJ IDEA.
  User: Amey
  Date: 4/27/2020
  Time: 9:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.mongodb.*" %>
<!doctype html>

<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

    <%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

</head>
<style>
    body {
        background-image: url('./img/1348783.jpg');
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: cover;
        /* filter: blur(8px); */
        /* -webkit-filter: blur(8px); */
    }

    caption {
        caption-side: top;
        text-align: center;
        color: white;
    }

    table {
        background-color: #0c0c0c;
        border-collapse: collapse;
        border: 2px solid rgb(200, 200, 200);
        letter-spacing: 1px;
        font-family: sans-serif;
        font-size: .8rem;
    }

    table td {
        color: white;
    }

    table th {
        color: white;
    }
</style>
</head>

<body>
<%
    String url = "localhost";
    MongoClient connect = new MongoClient(url, 27017);
    DB db = connect.getDB("Project");
    DBCollection club = db.getCollection("groupStageClubs");
    DBCollection players = db.getCollection("Players");

    BasicDBObject sorting = new BasicDBObject();
    sorting.append("Points", -1);
    DBCursor li = club.find().sort(sorting);

    BasicDBObject sorting1 = new BasicDBObject();
    sorting1.append("goal_scored", -1);
    DBCursor play = players.find();
    DBCursor goalScorePlayer = play.sort(sorting1);
%>
<div class="container-fluid">
    <!-- Start of first row tables section -->
    <div class="row mt-4">
        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3">
            <p style="color: white">Group A</p>
            <table id="testID" class="table table-bordered table-responsive" style="background-color: #0c0c0c">

                <thead>
                <tr>
                    <th>Team Name</th>
                    <th>Matches</th>
                    <th>Win</th>
                    <th>Loss</th>
                    <th>Draw</th>
                    <th>Points</th>
                </tr>
                </thead>
                <tbody id="grpA">
                <%
                    for (int i = 0; i < li.length(); i++) {
                        if (li.toArray().get(i).get("GroupType").toString().equals("A")) {%>
                <tr>
                    <td><%=li.toArray().get(i).get("clubName")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Matches")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Win")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Loss")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Draw")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Points")%>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3">
            <p style="color: white">Group B</p>
            <table class="table table-bordered table-responsive" style="background-color: #0c0c0c">
                <thead>
                <tr>
                    <th>Team Name</th>
                    <th>Matches</th>
                    <th>Win</th>
                    <th>Loss</th>
                    <th>Draw</th>
                    <th>Points</th>
                </tr>
                </thead>
                <tbody id="grpB">
                <%
                    for (int i = 0; i < li.length(); i++) {
                        if (li.toArray().get(i).get("GroupType").toString().equals("B")) {%>
                <tr>
                    <td><%=li.toArray().get(i).get("clubName")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Matches")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Win")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Loss")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Draw")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Points")%>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3">
            <p style="color: white">Group C</p>
            <table class="table table-bordered table-responsive" style="background-color: #0c0c0c">
                <thead>
                <tr>
                    <th>Team Name</th>
                    <th>Matches</th>
                    <th>Win</th>
                    <th>Loss</th>
                    <th>Draw</th>
                    <th>Points</th>
                </tr>
                </thead>
                <tbody id="grpC">
                <%
                    for (int i = 0; i < li.length(); i++) {
                        if (li.toArray().get(i).get("GroupType").toString().equals("C")) {%>
                <tr>
                    <td><%=li.toArray().get(i).get("clubName")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Matches")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Win")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Loss")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Draw")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Points")%>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3">
            <p style="color: white">Group D</p>
            <table class="table table-bordered table-responsive" style="background-color: #0c0c0c">
                <thead>
                <tr>
                    <th>Team Name</th>
                    <th>Matches</th>
                    <th>Win</th>
                    <th>Loss</th>
                    <th>Draw</th>
                    <th>Points</th>
                </tr>
                </thead>
                <tbody id="grpD">
                <%
                    for (int i = 0; i < li.length(); i++) {
                        if (li.toArray().get(i).get("GroupType").toString().equals("D")) {%>
                <tr>
                    <td><%=li.toArray().get(i).get("clubName")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Matches")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Win")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Loss")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Draw")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Points")%>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
    <!-- End of first row tables section -->
    <div class="row mt-4">
        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4"></div>
        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4"></div>
        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4"></div>
        <!-- Start of last row tables section -->
    </div>

    <div class="row mt-4">
        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4">
            <p style="color: white">Top Goal Scorer</p>
            <table class="table table-bordered table-responsive" style="background-color: #0c0c0c">
                <thead>
                <tr>
                    <th>Player Name</th>
                    <th>Matches</th>
                    <th>Goal Score</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (int i = 0; i < 5; i++) {
                %>
                <tr>
                    <td><%=goalScorePlayer.toArray().get(i).get("short_name").toString()%>
                    </td>
                    <td><%=goalScorePlayer.toArray().get(i).get("matchCount").toString()%>
                    </td>
                    <td><%=goalScorePlayer.toArray().get(i).get("goal_scored").toString()%>
                    </td>

                </tr>
                <%
                    }

                %>
                </tbody>
            </table>
        </div>
        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4">
            <p style="color: white">Team Goal Score and Conceded</p>
            <table class="table table-bordered table-responsive tab-content" style="background-color: #0c0c0c">
                <thead>
                <tr>
                    <th>Team Name</th>
                    <th>Total Team Matches</th>
                    <th>Number Goal Score by team</th>
                    <th>Number of Goal Conceded by team</th>
                </tr>
                </thead>
                <tbody>

                <%
                    for (int i = 0; i < li.length(); i++) {
                %>
                <tr>
                    <td><%=li.toArray().get(i).get("clubName")%>
                    </td>
                    <td><%=li.toArray().get(i).get("Matches")%>
                    </td>
                    <td><%=li.toArray().get(i).get("goalScore")%>
                    </td>
                    <td><%=li.toArray().get(i).get("goalConceded")%>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4">
            <p>Top 10 Upcoming Talented Players</p>
            <table class="table table-bordered table-responsive tab-content" style="background-color: #0c0c0c">
                <thead>
                <tr>
                    <th>Player Name</th>
                    <th>Age</th>
                    <th>Current Rating</th>
                    <th>Potential Rating</th>
                </tr>
                </thead>
                <tbody>
                <%
                    BasicDBObject sorting12 = new BasicDBObject();
                    sorting12.put("potential", new BasicDBObject("$gte", 85));
                    sorting12.append("age", new BasicDBObject("$gte", 19).append("$lte", 25));
                    DBCursor da = players.find(sorting12);
                    for (int i = 0; i < 11; i++) {
                %>
                <tr>
                    <td><%=da.toArray().get(i).get("short_name")%>
                    </td>
                    <td><%=da.toArray().get(i).get("age")%>
                    </td>
                    <td><%=da.toArray().get(i).get("overall")%>
                    </td>
                    <td><%=da.toArray().get(i).get("potential")%>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <!-- End of last row tables section -->
    </div>
</div>

</body>

</html>