package com.wwd.service.mapper;

import com.wwd.service.modules.wwd.po.WwdActivityUserMutualElectionPo;
import feihua.jdbc.api.dao.CrudDao;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2019-06-21 12:59:13
 */
public interface WwdActivityUserMutualElectionPoMapper extends feihua.jdbc.api.dao.CrudDao<WwdActivityUserMutualElectionPo, String> {
    java.util.List<WwdActivityUserMutualElectionPo> searchWwdActivityUserMutualElections(com.wwd.service.modules.wwd.dto.SearchWwdActivityUserMutualElectionsConditionDto dto);
}