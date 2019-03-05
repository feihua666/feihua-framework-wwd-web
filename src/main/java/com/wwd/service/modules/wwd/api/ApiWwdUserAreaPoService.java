package com.wwd.service.modules.wwd.api;

import com.wwd.service.modules.wwd.dto.WwdUserAreaDto;
import com.wwd.service.modules.wwd.po.WwdUserAreaPo;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2018-04-25 11:24:07
 */
public interface ApiWwdUserAreaPoService extends feihua.jdbc.api.service.ApiBaseService<WwdUserAreaPo, WwdUserAreaDto, String> {

    /**
     * 根据用户id查询区域
     * @param wwdUserId
     * @return
     */
    public WwdUserAreaDto selectByWwdUserId(String wwdUserId);
}