package com.wwd.web.modules.wwd.dto;

import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table wwd_user_card
 *
 * @mbg.generated 2018-11-01 10:44:36
*/
public class AddWwdUserCardFormDto {
    private String wwdUserId;

    private String picOriginUrl;

    private String picThumbUrl;

    private Integer sequence;

    private String type;

    private String describtion;

    public String getWwdUserId() {
        return wwdUserId;
    }

    public void setWwdUserId(String wwdUserId) {
        this.wwdUserId = wwdUserId;
    }

    public String getPicOriginUrl() {
        return picOriginUrl;
    }

    public void setPicOriginUrl(String picOriginUrl) {
        this.picOriginUrl = picOriginUrl;
    }

    public String getPicThumbUrl() {
        return picThumbUrl;
    }

    public void setPicThumbUrl(String picThumbUrl) {
        this.picThumbUrl = picThumbUrl;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }
}