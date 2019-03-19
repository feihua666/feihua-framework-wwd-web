package com.wwd.service.modules.wwd.dto;

import com.feihua.framework.base.modules.user.dto.BaseUserDto;
import feihua.jdbc.api.pojo.BaseDbDto;
import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table wwd_participate
 *
 * @mbg.generated 2019-03-12 09:34:55
*/
public class WwdParticipateDto extends BaseDbDto<String> {
    private String id;

    private String wwdActivityId;

    private String wwdUserId;

    private String type;

    private String payStatus;

    private String status;

    private String remarks;

    private String dataUserId;

    private String dataOfficeId;

    private String dataType;

    private String dataAreaId;

    private String delFlag;

    private Date createAt;

    private String createBy;

    private Date updateAt;

    private String updateBy;

    private WwdUserDto wwdUserDto;

    private BaseUserDto baseUserDto;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BaseUserDto getBaseUserDto() {
        return baseUserDto;
    }

    public void setBaseUserDto(BaseUserDto baseUserDto) {
        this.baseUserDto = baseUserDto;
    }

    public WwdUserDto getWwdUserDto() {
        return wwdUserDto;
    }

    public void setWwdUserDto(WwdUserDto wwdUserDto) {
        this.wwdUserDto = wwdUserDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWwdActivityId() {
        return wwdActivityId;
    }

    public void setWwdActivityId(String wwdActivityId) {
        this.wwdActivityId = wwdActivityId;
    }

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

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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