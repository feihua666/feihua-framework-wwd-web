package com.wwd.service.modules.wwd.po;

import feihua.jdbc.api.pojo.BasePo;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2018-04-25 11:25:16
 *
 * This class corresponds to the database table wwd_user_tag
 * @mbg.generated do_not_delete_during_merge 2018-04-25 11:25:16
*/
public class WwdUserTagPo extends feihua.jdbc.api.pojo.BasePo<String> {
    /**
     * Database Column Remarks:
     *   用户id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_tag.wwd_user_id
     *
     * @mbg.generated 2018-04-25 11:25:16
     */
    private String wwdUserId;

    /**
     * Database Column Remarks:
     *   标签类型，字典，兴趣，性格，食物，旅游等
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_tag.type
     *
     * @mbg.generated 2018-04-25 11:25:16
     */
    private String type;

    /**
     * Database Column Remarks:
     *   对应标签的内容，字典
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_tag.content
     *
     * @mbg.generated 2018-04-25 11:25:16
     */
    private String content;

    /**
     * Database Column Remarks:
     *   对应标签的自定义内容，文本，直接展示
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_tag.self_content
     *
     * @mbg.generated 2018-04-25 11:25:16
     */
    private String selfContent;

    public String getWwdUserId() {
        return wwdUserId;
    }

    public void setWwdUserId(String wwdUserId) {
        this.wwdUserId = wwdUserId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSelfContent() {
        return selfContent;
    }

    public void setSelfContent(String selfContent) {
        this.selfContent = selfContent;
    }

    public com.wwd.service.modules.wwd.api.ApiWwdUserTagPoService service() {
        return com.feihua.utils.spring.SpringContextHolder.getBean(com.wwd.service.modules.wwd.api.ApiWwdUserTagPoService.class);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wwdUserId=").append(wwdUserId);
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", selfContent=").append(selfContent);
        sb.append("]");
        return sb.toString();
    }
}