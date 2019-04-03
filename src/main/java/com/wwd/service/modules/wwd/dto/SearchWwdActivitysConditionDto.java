package com.wwd.service.modules.wwd.dto;

import feihua.jdbc.api.pojo.BaseConditionDto;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table wwd_activity
 *
 * @mbg.generated 2019-03-11 09:21:27
*/
public class SearchWwdActivitysConditionDto extends BaseConditionDto {
    private String id;

    private String title;

    private String titleUrl;

    private String author;

    private Date startTime;

    private Date endTime;

    private String addr;

    private String contact;

    private Integer sequence;

    private String type;

    private String status;

    private String requireIdCard;

    private Integer headcount;

    private Integer headcountMale;

    private Integer headcountFemale;

    private String headcountDesc;

    private String introduced;

    private String headcountRule;

    private Integer malePrice;

    private Integer femalePrice;

    private String activityStatement;

    private String dataUserId;

    private String dataOfficeId;

    private String dataType;

    private String dataAreaId;

    private String delFlag;

    private Date createAt;

    private String createBy;

    private Date updateAt;

    private String updateBy;

    private String content;

    private String[] statusArr;

    private String keyword;

    public String getRequireIdCard() {
        return requireIdCard;
    }

    public void setRequireIdCard(String requireIdCard) {
        this.requireIdCard = requireIdCard;
    }

    public Integer getHeadcountMale() {
        return headcountMale;
    }

    public void setHeadcountMale(Integer headcountMale) {
        this.headcountMale = headcountMale;
    }

    public Integer getHeadcountFemale() {
        return headcountFemale;
    }

    public void setHeadcountFemale(Integer headcountFemale) {
        this.headcountFemale = headcountFemale;
    }

    public String getHeadcountRule() {
        return headcountRule;
    }

    public void setHeadcountRule(String headcountRule) {
        this.headcountRule = headcountRule;
    }

    public String getHeadcountDesc() {
        return headcountDesc;
    }

    public void setHeadcountDesc(String headcountDesc) {
        this.headcountDesc = headcountDesc;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String[] getStatusArr() {
        if(StringUtils.isNotBlank(status)){
            return status.split(",");
        }

        return statusArr;
    }

    public void setStatusArr(String[] statusArr) {
        this.statusArr = statusArr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getHeadcount() {
        return headcount;
    }

    public void setHeadcount(Integer headcount) {
        this.headcount = headcount;
    }

    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public Integer getMalePrice() {
        return malePrice;
    }

    public void setMalePrice(Integer malePrice) {
        this.malePrice = malePrice;
    }

    public Integer getFemalePrice() {
        return femalePrice;
    }

    public void setFemalePrice(Integer femalePrice) {
        this.femalePrice = femalePrice;
    }

    public String getActivityStatement() {
        return activityStatement;
    }

    public void setActivityStatement(String activityStatement) {
        this.activityStatement = activityStatement;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}