package com.wwd.service.mapper;

import com.wwd.service.modules.wwd.po.WwdActivityOrder;
import feihua.jdbc.api.dao.CrudDao;

/**
 * This class was generated by MyBatis Generator.
 * @author revolver 2019-03-20 13:52:35
 */
public interface WwdActivityOrderMapper extends feihua.jdbc.api.dao.CrudDao<WwdActivityOrder, String> {
    java.util.List<WwdActivityOrder> searchWwdActivityOrders(com.wwd.service.modules.wwd.dto.SearchWwdActivityOrdersConditionDto dto);
}