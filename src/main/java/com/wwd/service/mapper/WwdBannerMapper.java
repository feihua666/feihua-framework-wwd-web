package com.wwd.service.mapper;

import com.wwd.service.modules.wwd.po.WwdBanner;
import feihua.jdbc.api.dao.CrudDao;
import org.apache.ibatis.annotations.Param;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2019-11-06 09:49:13
 */
public interface WwdBannerMapper extends feihua.jdbc.api.dao.CrudDao<WwdBanner, String> {
    java.util.List<WwdBanner> searchWwdBanners(@Param("entity") com.wwd.service.modules.wwd.dto.SearchWwdBannersConditionDto dto);
}