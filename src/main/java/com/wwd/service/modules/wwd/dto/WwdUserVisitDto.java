package com.wwd.service.modules.wwd.dto;

import feihua.jdbc.api.pojo.BaseDbDto;
import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table wwd_user_visit
 *
 * @mbg.generated 2019-06-17 17:59:28
*/
public class WwdUserVisitDto extends BaseDbDto<String> {
    private String id;

    private String wwdUserId;

    private String wwdUserName;

    private String wwdUserGender;

    private String wwdUserPic;

    private String visitWwdUserId;

    private String visitWwdUserName;

    private String visitWwdUserGender;

    private String visitWwdUserPic;

    private String visitType;

    private String readFlag;

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

    public String getWwdUserName() {
        return wwdUserName;
    }

    public void setWwdUserName(String wwdUserName) {
        this.wwdUserName = wwdUserName;
    }

    public String getWwdUserGender() {
        return wwdUserGender;
    }

    public void setWwdUserGender(String wwdUserGender) {
        this.wwdUserGender = wwdUserGender;
    }

    public String getWwdUserPic() {
        return wwdUserPic;
    }

    public void setWwdUserPic(String wwdUserPic) {
        this.wwdUserPic = wwdUserPic;
    }

    public String getVisitWwdUserId() {
        return visitWwdUserId;
    }

    public void setVisitWwdUserId(String visitWwdUserId) {
        this.visitWwdUserId = visitWwdUserId;
    }

    public String getVisitWwdUserName() {
        return visitWwdUserName;
    }

    public void setVisitWwdUserName(String visitWwdUserName) {
        this.visitWwdUserName = visitWwdUserName;
    }

    public String getVisitWwdUserGender() {
        return visitWwdUserGender;
    }

    public void setVisitWwdUserGender(String visitWwdUserGender) {
        this.visitWwdUserGender = visitWwdUserGender;
    }

    public String getVisitWwdUserPic() {
        return visitWwdUserPic;
    }

    public void setVisitWwdUserPic(String visitWwdUserPic) {
        this.visitWwdUserPic = visitWwdUserPic;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
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