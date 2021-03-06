package com.wwd.service.modules.wwd.api;

import com.wwd.service.modules.wwd.dto.SearchWwdActivitysConditionDto;
import com.wwd.service.modules.wwd.dto.WwdActivityDto;
import com.wwd.service.modules.wwd.po.WwdActivity;
import feihua.jdbc.api.pojo.PageAndOrderbyParamDto;
import feihua.jdbc.api.pojo.PageResultDto;

/**
 * This class was generated by MyBatis Generator.
 * @author revolver 2019-03-11 09:21:27
 */
public interface ApiWwdActivityService extends feihua.jdbc.api.service.ApiBaseService<WwdActivity, WwdActivityDto, String> {
    PageResultDto<WwdActivityDto> searchWwdActivitysDsf(com.wwd.service.modules.wwd.dto.SearchWwdActivitysConditionDto dto, feihua.jdbc.api.pojo.PageAndOrderbyParamDto pageAndOrderbyParamDto);

	PageResultDto<WwdActivityDto> myActivitysMultiTable(SearchWwdActivitysConditionDto dto, PageAndOrderbyParamDto pageAndOrderbyParamDto);
}