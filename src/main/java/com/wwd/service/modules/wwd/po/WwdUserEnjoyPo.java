package com.wwd.service.modules.wwd.po;

import feihua.jdbc.api.pojo.BasePo;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2018-05-19 15:53:51
 *
 * This class corresponds to the database table wwd_user_enjoy
 * @mbg.generated do_not_delete_during_merge 2018-05-19 15:53:51
*/
public class WwdUserEnjoyPo extends feihua.jdbc.api.pojo.BasePo<String> {
    /**
     * Database Column Remarks:
     *   用户id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_enjoy.wwd_user_id
     *
     * @mbg.generated 2018-05-19 15:53:51
     */
    private String wwdUserId;

    /**
     * Database Column Remarks:
     *   有意思用户id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_enjoy.enjoyed_wwd_user_id
     *
     * @mbg.generated 2018-05-19 15:53:51
     */
    private String enjoyedWwdUserId;

    /**
     * Database Column Remarks:
     *   添加有意思时，可以写一句话
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_enjoy.message
     *
     * @mbg.generated 2018-05-19 15:53:51
     */
    private String message;

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

    public com.wwd.service.modules.wwd.api.ApiWwdUserEnjoyPoService service() {
        return com.feihua.utils.spring.SpringContextHolder.getBean(com.wwd.service.modules.wwd.api.ApiWwdUserEnjoyPoService.class);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wwdUserId=").append(wwdUserId);
        sb.append(", enjoyedWwdUserId=").append(enjoyedWwdUserId);
        sb.append(", message=").append(message);
        sb.append("]");
        return sb.toString();
    }
}