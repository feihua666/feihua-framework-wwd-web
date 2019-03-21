package com.wwd.service.modules.wwd.api;

import com.wwd.service.modules.wwd.dto.WwdActivityOrderDto;
import com.wwd.service.modules.wwd.po.WwdActivityOrder;
import feihua.jdbc.api.pojo.PageResultDto;

/**
 * This class was generated by MyBatis Generator.
 * @author revolver 2019-03-20 13:52:35
 */
public interface ApiWwdActivityOrderService extends feihua.jdbc.api.service.ApiBaseService<WwdActivityOrder, WwdActivityOrderDto, String> {
    PageResultDto<WwdActivityOrderDto> searchWwdActivityOrdersDsf(com.wwd.service.modules.wwd.dto.SearchWwdActivityOrdersConditionDto dto, feihua.jdbc.api.pojo.PageAndOrderbyParamDto pageAndOrderbyParamDto);


    WwdActivityOrder selectByParticipateIdAndUserId(String participateId,String userId);
    WwdActivityOrder selectByOrderNo(String orderNo);
}