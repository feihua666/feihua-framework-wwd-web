package com.wwd.service.mapper;

import com.wwd.service.modules.wwd.dto.SearchWwdActivitysConditionDto;
import com.wwd.service.modules.wwd.po.WwdActivity;
import feihua.jdbc.api.dao.CrudDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * This class was generated by MyBatis Generator.
 * @author revolver 2019-03-11 09:21:27
 */
public interface WwdActivityMapper extends feihua.jdbc.api.dao.CrudDao<WwdActivity, String> {
    int updateByPrimaryKeyWithBLOBs(WwdActivity record);

    java.util.List<WwdActivity> searchWwdActivitys(@Param("entity") com.wwd.service.modules.wwd.dto.SearchWwdActivitysConditionDto dto);

	List<WwdActivity> myActivitysMultiTable(@Param("entity") SearchWwdActivitysConditionDto dto);
}