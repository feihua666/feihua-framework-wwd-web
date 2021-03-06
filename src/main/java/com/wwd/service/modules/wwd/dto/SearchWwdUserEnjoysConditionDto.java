package com.wwd.service.modules.wwd.dto;



import feihua.jdbc.api.pojo.BaseConditionDto;

import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table wwd_user_enjoy
 *
 * @mbg.generated 2018-05-19 15:53:51
*/
public class SearchWwdUserEnjoysConditionDto extends BaseConditionDto {
    private String id;

    private String wwdUserId;

    private String enjoyedWwdUserId;

    private String message;

    private String dataUserId;

    private String dataOfficeId;

    private String dataType;

    private String dataAreaId;

    private String delFlag;

    private Date createAt;

    private String createBy;

    private Date updateAt;

    private String updateBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWwdUserId() {
        return wwdUserId;
    }

    public void setWwdUserId(String wwdUserId) {
        this.wwdUserId = wwdUserId;
    }

    public String getEnjoyedWwdUserId() {
        return enjoyedWwdUserId;
    }

    public void setEnjoyedWwdUserId(String enjoyedWwdUserId) {
        this.enjoyedWwdUserId = enjoyedWwdUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDataUserId() {
        return dataUserId;
    }

    public void setDataUserId(String dataUserId) {
        this.dataUserId = dataUserId;
    }

    public String getDataOfficeId() {
        return dataOfficeId;
    }

    public void setDataOfficeId(String dataOfficeId) {
        this.dataOfficeId = dataOfficeId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataAreaId() {
        return dataAreaId;
    }

    public void setDataAreaId(String dataAreaId) {
        this.dataAreaId = dataAreaId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}