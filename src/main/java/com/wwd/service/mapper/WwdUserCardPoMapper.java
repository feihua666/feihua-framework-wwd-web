package com.wwd.service.mapper;

import com.wwd.service.modules.wwd.po.WwdUserCardPo;
import feihua.jdbc.api.dao.CrudDao;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2018-11-01 10:44:36
 */
public interface WwdUserCardPoMapper extends feihua.jdbc.api.dao.CrudDao<WwdUserCardPo, String> {
    java.util.List<WwdUserCardPo> searchWwdUserCards(com.wwd.service.modules.wwd.dto.SearchWwdUserCardsConditionDto dto);
}