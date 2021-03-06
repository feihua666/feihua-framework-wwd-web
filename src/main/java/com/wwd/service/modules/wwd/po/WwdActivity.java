package com.wwd.service.modules.wwd.po;

import feihua.jdbc.api.pojo.BasePo;
import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2019-06-21 10:03:51
 * Database Table Remarks:
 *   活动
 *
 * This class corresponds to the database table wwd_activity
 * @mbg.generated do_not_delete_during_merge 2019-06-21 10:03:51
*/
public class WwdActivity extends feihua.jdbc.api.pojo.BasePo<String> {
    /**
     * Database Column Remarks:
     *   活动标题
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.title
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String title;

    /**
     * Database Column Remarks:
     *   标题图
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.title_url
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String titleUrl;

    /**
     * Database Column Remarks:
     *   作者
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.author
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String author;

    /**
     * Database Column Remarks:
     *   开始时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.start_time
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private Date startTime;

    /**
     * Database Column Remarks:
     *   结束时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.end_time
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private Date endTime;

    /**
     * Database Column Remarks:
     *   活动地点
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.addr
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String addr;

    /**
     * Database Column Remarks:
     *   联系方式
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.contact
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String contact;

    /**
     * Database Column Remarks:
     *   序号，从小到大排序
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.sequence
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private Integer sequence;

    /**
     * Database Column Remarks:
     *   类型(室内、户外)
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.type
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String type;

    /**
     * Database Column Remarks:
     *   Y=需要证件，身份证 N=不需要，这在报名的时候会判断添加
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.require_id_card
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String requireIdCard;

    /**
     * Database Column Remarks:
     *   活动状态（0,编辑中，1，报名中，2，名额满，3，已结束）
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.status
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String status;

    /**
     * Database Column Remarks:
     *   活动人数
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.headcount
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private Integer headcount;

    /**
     * Database Column Remarks:
     *   活动人数，男
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.headcount_male
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private Integer headcountMale;

    /**
     * Database Column Remarks:
     *   活动人数，女
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.headcount_female
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private Integer headcountFemale;

    /**
     * Database Column Remarks:
     *   人数说明
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.headcount_desc
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String headcountDesc;

    /**
     * Database Column Remarks:
     *   活动简介
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.introduced
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String introduced;

    /**
     * Database Column Remarks:
     *   人数比例规则：unlimited、不限制，custom 、自定义男女比例
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.headcount_rule
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String headcountRule;

    /**
     * Database Column Remarks:
     *   男价格
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.male_price
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private Integer malePrice;

    /**
     * Database Column Remarks:
     *   女价格
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.female_price
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private Integer femalePrice;

    /**
     * Database Column Remarks:
     *   支付类型 线上支付 online_pay/线下支付 offline_pay
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.pay_type
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String payType;

    /**
     * Database Column Remarks:
     *   活动声明
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.activity_statement
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String activityStatement;

    /**
     * Database Column Remarks:
     *   互选状态，未开始，进行中，已结束
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.mutual_election_status
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String mutualElectionStatus;

    /**
     * Database Column Remarks:
     *   管理的用户组，该用户组下的人员可以进行h5活动管理，目前是为控制互选而生
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.manage_user_group_id
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String manageUserGroupId;

    /**
     * Database Column Remarks:
     *   活动内容
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity.content
     *
     * @mbg.generated 2019-06-21 10:03:51
     */
    private String content;

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

    public String getRequireIdCard() {
        return requireIdCard;
    }

    public void setRequireIdCard(String requireIdCard) {
        this.requireIdCard = requireIdCard;
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

    public String getHeadcountDesc() {
        return headcountDesc;
    }

    public void setHeadcountDesc(String headcountDesc) {
        this.headcountDesc = headcountDesc;
    }

    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public String getHeadcountRule() {
        return headcountRule;
    }

    public void setHeadcountRule(String headcountRule) {
        this.headcountRule = headcountRule;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getActivityStatement() {
        return activityStatement;
    }

    public void setActivityStatement(String activityStatement) {
        this.activityStatement = activityStatement;
    }

    public String getMutualElectionStatus() {
        return mutualElectionStatus;
    }

    public void setMutualElectionStatus(String mutualElectionStatus) {
        this.mutualElectionStatus = mutualElectionStatus;
    }

    public String getManageUserGroupId() {
        return manageUserGroupId;
    }

    public void setManageUserGroupId(String manageUserGroupId) {
        this.manageUserGroupId = manageUserGroupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public com.wwd.service.modules.wwd.api.ApiWwdActivityService service() {
        return com.feihua.utils.spring.SpringContextHolder.getBean(com.wwd.service.modules.wwd.api.ApiWwdActivityService.class);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", title=").append(title);
        sb.append(", titleUrl=").append(titleUrl);
        sb.append(", author=").append(author);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", addr=").append(addr);
        sb.append(", contact=").append(contact);
        sb.append(", sequence=").append(sequence);
        sb.append(", type=").append(type);
        sb.append(", requireIdCard=").append(requireIdCard);
        sb.append(", status=").append(status);
        sb.append(", headcount=").append(headcount);
        sb.append(", headcountMale=").append(headcountMale);
        sb.append(", headcountFemale=").append(headcountFemale);
        sb.append(", headcountDesc=").append(headcountDesc);
        sb.append(", introduced=").append(introduced);
        sb.append(", headcountRule=").append(headcountRule);
        sb.append(", malePrice=").append(malePrice);
        sb.append(", femalePrice=").append(femalePrice);
        sb.append(", payType=").append(payType);
        sb.append(", activityStatement=").append(activityStatement);
        sb.append(", mutualElectionStatus=").append(mutualElectionStatus);
        sb.append(", manageUserGroupId=").append(manageUserGroupId);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}