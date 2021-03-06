package com.wwd.service.modules.wwd.po;

import feihua.jdbc.api.pojo.BasePo;

/**
 * This class was generated by MyBatis Generator.
 * @author revolver 2019-04-30 13:46:44
 *
 * This class corresponds to the database table wwd_activity_order
 * @mbg.generated do_not_delete_during_merge 2019-04-30 13:46:44
*/
public class WwdActivityOrder extends feihua.jdbc.api.pojo.BasePo<String> {
    /**
     * Database Column Remarks:
     *   订单编号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity_order.order_no
     *
     * @mbg.generated 2019-04-30 13:46:44
     */
    private String orderNo;

    /**
     * Database Column Remarks:
     *   活动标题
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity_order.activity_title
     *
     * @mbg.generated 2019-04-30 13:46:44
     */
    private String activityTitle;

    /**
     * Database Column Remarks:
     *   活动图片
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity_order.activity_url
     *
     * @mbg.generated 2019-04-30 13:46:44
     */
    private String activityUrl;

    /**
     * Database Column Remarks:
     *   活动参与ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity_order.participate_id
     *
     * @mbg.generated 2019-04-30 13:46:44
     */
    private String participateId;

    /**
     * Database Column Remarks:
     *   用户ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity_order.user_id
     *
     * @mbg.generated 2019-04-30 13:46:44
     */
    private String userId;

    /**
     * Database Column Remarks:
     *   订单支付方式：微信、支付宝
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity_order.pay_type
     *
     * @mbg.generated 2019-04-30 13:46:44
     */
    private String payType;

    /**
     * Database Column Remarks:
     *   支付状态
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity_order.pay_status
     *
     * @mbg.generated 2019-04-30 13:46:44
     */
    private String payStatus;

    /**
     * Database Column Remarks:
     *   订单金额
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity_order.price
     *
     * @mbg.generated 2019-04-30 13:46:44
     */
    private String price;

    /**
     * Database Column Remarks:
     *   订单备注
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_activity_order.remarks
     *
     * @mbg.generated 2019-04-30 13:46:44
     */
    private String remarks;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public String getParticipateId() {
        return participateId;
    }

    public void setParticipateId(String participateId) {
        this.participateId = participateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public com.wwd.service.modules.wwd.api.ApiWwdActivityOrderService service() {
        return com.feihua.utils.spring.SpringContextHolder.getBean(com.wwd.service.modules.wwd.api.ApiWwdActivityOrderService.class);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderNo=").append(orderNo);
        sb.append(", activityTitle=").append(activityTitle);
        sb.append(", activityUrl=").append(activityUrl);
        sb.append(", participateId=").append(participateId);
        sb.append(", userId=").append(userId);
        sb.append(", payType=").append(payType);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", price=").append(price);
        sb.append(", remarks=").append(remarks);
        sb.append("]");
        return sb.toString();
    }
}