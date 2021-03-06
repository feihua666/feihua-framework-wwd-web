package com.wwd.service.modules.wwd.po;

import feihua.jdbc.api.pojo.BasePo;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2018-04-25 11:24:07
 *
 * This class corresponds to the database table wwd_user_area
 * @mbg.generated do_not_delete_during_merge 2018-04-25 11:24:07
*/
public class WwdUserAreaPo extends feihua.jdbc.api.pojo.BasePo<String> {
    /**
     * Database Column Remarks:
     *   汪汪队用户信息id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.wwd_user_id
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String wwdUserId;

    /**
     * Database Column Remarks:
     *   当前所在省id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.now_province_id
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String nowProvinceId;

    /**
     * Database Column Remarks:
     *   当前所在省名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.now_province_name
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String nowProvinceName;

    /**
     * Database Column Remarks:
     *   当前所在城市id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.now_city_id
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String nowCityId;

    /**
     * Database Column Remarks:
     *   当前所在城市名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.now_city_name
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String nowCityName;

    /**
     * Database Column Remarks:
     *   当前所在区县id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.now_district_id
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String nowDistrictId;

    /**
     * Database Column Remarks:
     *   当前所在区县名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.now_district_name
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String nowDistrictName;

    /**
     * Database Column Remarks:
     *   当前所在乡镇id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.now_town_id
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String nowTownId;

    /**
     * Database Column Remarks:
     *   当前所在乡镇名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.now_town_name
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String nowTownName;

    /**
     * Database Column Remarks:
     *   家乡所在省id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.home_province_id
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String homeProvinceId;

    /**
     * Database Column Remarks:
     *   家乡所在省名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.home_province_name
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String homeProvinceName;

    /**
     * Database Column Remarks:
     *   家乡所在城市id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.home_city_id
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String homeCityId;

    /**
     * Database Column Remarks:
     *   家乡所在城市名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.home_city_name
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String homeCityName;

    /**
     * Database Column Remarks:
     *   家乡所在区县id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.home_district_id
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String homeDistrictId;

    /**
     * Database Column Remarks:
     *   家乡所在区县名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.home_district_name
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String homeDistrictName;

    /**
     * Database Column Remarks:
     *   家乡所在乡镇id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.home_town_id
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String homeTownId;

    /**
     * Database Column Remarks:
     *   家乡所在乡镇名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wwd_user_area.home_town_name
     *
     * @mbg.generated 2018-04-25 11:24:07
     */
    private String homeTownName;

    public String getWwdUserId() {
        return wwdUserId;
    }

    public void setWwdUserId(String wwdUserId) {
        this.wwdUserId = wwdUserId;
    }

    public String getNowProvinceId() {
        return nowProvinceId;
    }

    public void setNowProvinceId(String nowProvinceId) {
        this.nowProvinceId = nowProvinceId;
    }

    public String getNowProvinceName() {
        return nowProvinceName;
    }

    public void setNowProvinceName(String nowProvinceName) {
        this.nowProvinceName = nowProvinceName;
    }

    public String getNowCityId() {
        return nowCityId;
    }

    public void setNowCityId(String nowCityId) {
        this.nowCityId = nowCityId;
    }

    public String getNowCityName() {
        return nowCityName;
    }

    public void setNowCityName(String nowCityName) {
        this.nowCityName = nowCityName;
    }

    public String getNowDistrictId() {
        return nowDistrictId;
    }

    public void setNowDistrictId(String nowDistrictId) {
        this.nowDistrictId = nowDistrictId;
    }

    public String getNowDistrictName() {
        return nowDistrictName;
    }

    public void setNowDistrictName(String nowDistrictName) {
        this.nowDistrictName = nowDistrictName;
    }

    public String getNowTownId() {
        return nowTownId;
    }

    public void setNowTownId(String nowTownId) {
        this.nowTownId = nowTownId;
    }

    public String getNowTownName() {
        return nowTownName;
    }

    public void setNowTownName(String nowTownName) {
        this.nowTownName = nowTownName;
    }

    public String getHomeProvinceId() {
        return homeProvinceId;
    }

    public void setHomeProvinceId(String homeProvinceId) {
        this.homeProvinceId = homeProvinceId;
    }

    public String getHomeProvinceName() {
        return homeProvinceName;
    }

    public void setHomeProvinceName(String homeProvinceName) {
        this.homeProvinceName = homeProvinceName;
    }

    public String getHomeCityId() {
        return homeCityId;
    }

    public void setHomeCityId(String homeCityId) {
        this.homeCityId = homeCityId;
    }

    public String getHomeCityName() {
        return homeCityName;
    }

    public void setHomeCityName(String homeCityName) {
        this.homeCityName = homeCityName;
    }

    public String getHomeDistrictId() {
        return homeDistrictId;
    }

    public void setHomeDistrictId(String homeDistrictId) {
        this.homeDistrictId = homeDistrictId;
    }

    public String getHomeDistrictName() {
        return homeDistrictName;
    }

    public void setHomeDistrictName(String homeDistrictName) {
        this.homeDistrictName = homeDistrictName;
    }

    public String getHomeTownId() {
        return homeTownId;
    }

    public void setHomeTownId(String homeTownId) {
        this.homeTownId = homeTownId;
    }

    public String getHomeTownName() {
        return homeTownName;
    }

    public void setHomeTownName(String homeTownName) {
        this.homeTownName = homeTownName;
    }

    public com.wwd.service.modules.wwd.api.ApiWwdUserAreaPoService service() {
        return com.feihua.utils.spring.SpringContextHolder.getBean(com.wwd.service.modules.wwd.api.ApiWwdUserAreaPoService.class);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wwdUserId=").append(wwdUserId);
        sb.append(", nowProvinceId=").append(nowProvinceId);
        sb.append(", nowProvinceName=").append(nowProvinceName);
        sb.append(", nowCityId=").append(nowCityId);
        sb.append(", nowCityName=").append(nowCityName);
        sb.append(", nowDistrictId=").append(nowDistrictId);
        sb.append(", nowDistrictName=").append(nowDistrictName);
        sb.append(", nowTownId=").append(nowTownId);
        sb.append(", nowTownName=").append(nowTownName);
        sb.append(", homeProvinceId=").append(homeProvinceId);
        sb.append(", homeProvinceName=").append(homeProvinceName);
        sb.append(", homeCityId=").append(homeCityId);
        sb.append(", homeCityName=").append(homeCityName);
        sb.append(", homeDistrictId=").append(homeDistrictId);
        sb.append(", homeDistrictName=").append(homeDistrictName);
        sb.append(", homeTownId=").append(homeTownId);
        sb.append(", homeTownName=").append(homeTownName);
        sb.append("]");
        return sb.toString();
    }
}