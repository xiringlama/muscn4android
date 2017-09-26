
package com.awecode.muscn.model.http.resultdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import lombok.Getter;

@Getter
public class ResultDetailsResponse extends RealmObject {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("is_home_game")
    @Expose
    private Boolean isHomeGame;
    @SerializedName("opponent_name")
    @Expose
    private String opponentName;
    @SerializedName("mufc_score")
    @Expose
    private Integer mufcScore;
    @SerializedName("venue")
    @Expose
    private String venue;
    @SerializedName("opponent_score")
    @Expose
    private Integer opponentScore;
    @SerializedName("opponent_crest")
    @Expose
    private String opponentCrest;
    @SerializedName("opponent_short_name")
    @Expose
    private String opponentShortName;
    @SerializedName("competition_name")
    @Expose
    private String competitionName;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("broadcast_on")
    @Expose
    private String broadcastOn;
    @SerializedName("goals")
    @Expose
    private RealmList<Goal> goals;
}
