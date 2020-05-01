package util;

public class Group {
public String team1,team2;
public int team1Score,team2Score;
public Group(String team1In, String team2In){
    team1=team1In;
    team2=team2In;
}

public Group(int team1ScoreIn, int team2ScoreIn){
    team1Score=team1ScoreIn;
    team2Score=team2ScoreIn;
}
public Group(String team1In,int team1ScoreIn, String team2In,int team2ScoreIn){
    team1Score=team1ScoreIn;
    team1=team1In;
    team2Score=team2ScoreIn;
    team2=team2In;
}
public String getTeam1(){
    return team1;
}
public int getTeam1Score(){
    return team1Score;
}
public String getTeam2(){
    return team2;
}
public int getTeam2Score(){
    return team1Score;
}
}
