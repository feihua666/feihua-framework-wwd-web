package com.wwd.service.modules.wwd.po;

import feihua.jdbc.api.pojo.BasePo;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2019-05-17 10:57:54
 *
 * This class corresponds to the database table wwd_user_param_qrcode
 * @mbg.generated do_not_delete_during_merge 2019-05-17 10:57:54
*/
public class WwdUserParamQrcodePo extends feihua.jdbc.api.pojo.BasePo<String> {
    /**
     * Database Column Remarks:
     *   汪汪队用户id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_param_qrcode.wwd_user_id
     *
     * @mbg.generated 2019-05-17 10:57:54
     */
    private String wwdUserId;

    /**
     * Database Column Remarks:
     *   二维码tiket
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_param_qrcode.ticket
     *
     * @mbg.generated 2019-05-17 10:57:54
     */
    private String ticket;

    /**
     * Database Column Remarks:
     *   过期时长，秒
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_param_qrcode.expire_seconds
     *
     * @mbg.generated 2019-05-17 10:57:54
     */
    private Integer expireSeconds;

    /**
     * Database Column Remarks:
     *   二维码内容
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_param_qrcode.content
     *
     * @mbg.generated 2019-05-17 10:57:54
     */
    private String content;

    /**
     * Database Column Remarks:
     *   二维码图片地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_param_qrcode.url
     *
     * @mbg.generated 2019-05-17 10:57:54
     */
    private String url;

    /**
     * Database Column Remarks:
     *   是否永久，Y=是,N=否
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_param_qrcode.is_limit
     *
     * @mbg.generated 2019-05-17 10:57:54
     */
    private String isLimit;

    public String getWwdUserId() {
        return wwdUserId;
    }

    public void setWwdUserId(String wwdUserId) {
        this.wwdUserId = wwdUserId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Integer expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(String isLimit) {
        this.isLimit = isLimit;
    }

    public com.wwd.service.modules.wwd.api.ApiWwdUserParamQrcodePoService service() {
        return com.feihua.utils.spring.SpringContextHolder.getBean(com.wwd.service.modules.wwd.api.ApiWwdUserParamQrcodePoService.class);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wwdUserId=").append(wwdUserId);
        sb.append(", ticket=").append(ticket);
        sb.append(", expireSeconds=").append(expireSeconds);
        sb.append(", content=").append(content);
        sb.append(", url=").append(url);
        sb.append(", isLimit=").append(isLimit);
        sb.append("]");
        return sb.toString();
    }
}