
package com.awecode.muscn.model.http.eplmatchweek;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20161001 implements Comparable<_20161001> {

    @SerializedName("home_team")
    @Expose
    private String homeTeam;
    @SerializedName("away_team")
    @Expose
    private String awayTeam;
    @SerializedName("kickoff")
    @Expose
    private String kickoff;
    @SerializedName("live")
    @Expose
    private String live;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("eid")
    @Expose
    private String eid;
    @SerializedName("minute")
    @Expose
    private String minute;

    public String getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(String fixtureId) {
        this.fixtureId = fixtureId;
    }

    @SerializedName("fixture_id")
    @Expose
    private String fixtureId;

    /**
     * 
     * @return
     *     The homeTeam
     */
    public String getHomeTeam() {
        return homeTeam;
    }

    /**
     * 
     * @param homeTeam
     *     The home_team
     */
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    /**
     * 
     * @return
     *     The awayTeam
     */
    public String getAwayTeam() {
        return awayTeam;
    }

    /**
     * 
     * @param awayTeam
     *     The away_team
     */
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    /**
     * 
     * @return
     *     The kickoff
     */
    public String getKickoff() {
        return kickoff;
    }

    /**
     * 
     * @param kickoff
     *     The kickoff
     */
    public void setKickoff(String kickoff) {
        this.kickoff = kickoff;
    }

    /**
     * 
     * @return
     *     The live
     */
    public String getLive() {
        return live;
    }

    /**
     * 
     * @param live
     *     The live
     */
    public void setLive(String live) {
        this.live = live;
    }

    /**
     * 
     * @return
     *     The score
     */
    public String getScore() {
        return score;
    }

    /**
     * 
     * @param score
     *     The score
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * 
     * @return
     *     The eid
     */
    public String getEid() {
        return eid;
    }

    /**
     * 
     * @param eid
     *     The eid
     */
    public void setEid(String eid) {
        this.eid = eid;
    }

    /**
     * 
     * @return
     *     The minute
     */
    public String getMinute() {
        return minute;
    }

    /**
     * 
     * @param minute
     *     The minute
     */
    public void setMinute(String minute) {
        this.minute = minute;
    }

    @Override
    public int compareTo(_20161001 p) {
        return getKickoff().compareTo(p.getKickoff());
    }
}