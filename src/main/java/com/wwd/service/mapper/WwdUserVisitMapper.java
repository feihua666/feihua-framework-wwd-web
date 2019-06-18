package com.wwd.service.mapper;

import com.wwd.service.modules.wwd.po.WwdUserVisit;
import feihua.jdbc.api.dao.CrudDao;
import org.apache.ibatis.annotations.Param;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2019-06-17 13:29:29
 */
public interface WwdUserVisitMapper extends feihua.jdbc.api.dao.CrudDao<WwdUserVisit, String> {
    java.util.List<WwdUserVisit> searchWwdUserVisits(com.wwd.service.modules.wwd.dto.SearchWwdUserVisitsConditionDto dto);

    int selectVisitCountByWwdUserId(@Param("wwdUserId") String wwdUserId,@Param("readFlag") String readFlag);
}