package com.wwd.web.modules.wwd.dto;

import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table wwd_activity_user_mutual_election
 *
 * @mbg.generated 2019-06-21 12:59:13
*/
public class AddWwdActivityUserMutualElectionFormDto {

    private String activityId;

    private String wwdUserId;

    private String selectedWwdUserId;

    private String level;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getWwdUserId() {
        return wwdUserId;
    }

    public void setWwdUserId(String wwdUserId) {
        this.wwdUserId = wwdUserId;
    }

    public String getSelectedWwdUserId() {
        return selectedWwdUserId;
    }

    public void setSelectedWwdUserId(String selectedWwdUserId) {
        this.selectedWwdUserId = selectedWwdUserId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}