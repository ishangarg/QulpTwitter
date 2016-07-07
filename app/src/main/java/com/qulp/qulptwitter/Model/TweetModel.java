
package com.qulp.qulptwitter.Model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class TweetModel {

    @SerializedName("statuses")
    @Expose
    private ArrayList<Status> statuses = new ArrayList<Status>();


    /**
     * 
     * @return
     *     The statuses
     */
    public ArrayList<Status> getStatuses() {
        return statuses;
    }

    /**
     * 
     * @param statuses
     *     The statuses
     */
    public void setStatuses(ArrayList<Status> statuses) {
        this.statuses = statuses;
    }

}
