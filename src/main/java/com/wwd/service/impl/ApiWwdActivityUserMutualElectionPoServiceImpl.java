package com.wwd.service.impl;

import com.github.pagehelper.Page;
import com.wwd.service.modules.wwd.api.ApiWwdActivityUserMutualElectionPoService;
import com.wwd.service.modules.wwd.dto.WwdActivityUserMutualElectionDto;
import com.wwd.service.modules.wwd.po.WwdActivityUserMutualElectionPo;
import feihua.jdbc.api.pojo.PageResultDto;
import feihua.jdbc.api.service.impl.ApiBaseServiceImpl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2019-06-21 12:59:13
 */
@Service
public class ApiWwdActivityUserMutualElectionPoServiceImpl extends ApiBaseServiceImpl<WwdActivityUserMutualElectionPo, WwdActivityUserMutualElectionDto, String> implements ApiWwdActivityUserMutualElectionPoService {
    @Autowired
    com.wwd.service.mapper.WwdActivityUserMutualElectionPoMapper WwdActivityUserMutualElectionPoMapper;

    public ApiWwdActivityUserMutualElectionPoServiceImpl() {
        super(WwdActivityUserMutualElectionDto.class);
    }

    @Override
    public PageResultDto<WwdActivityUserMutualElectionDto> searchWwdActivityUserMutualElectionsDsf(com.wwd.service.modules.wwd.dto.SearchWwdActivityUserMutualElectionsConditionDto dto, feihua.jdbc.api.pojo.PageAndOrderbyParamDto pageAndOrderbyParamDto) {
        Page p = super.pageAndOrderbyStart(pageAndOrderbyParamDto);
        List<com.wwd.service.modules.wwd.dto.WwdActivityUserMutualElectionDto> list = this.wrapDtos(WwdActivityUserMutualElectionPoMapper.searchWwdActivityUserMutualElections(dto));
        return new PageResultDto(list, this.wrapPage(p));
    }

    @Override
    public List<Map<String, Object>> getSelectedResult(String activityId) {
        return WwdActivityUserMutualElectionPoMapper.getSelectedResult(activityId);
    }

    @Override
    public WwdActivityUserMutualElectionDto wrapDto(WwdActivityUserMutualElectionPo po) {
        if (po == null) { return null; }
        WwdActivityUserMutualElectionDto dto = new WwdActivityUserMutualElectionDto();
        dto.setId(po.getId());
        dto.setActivityId(po.getActivityId());
        dto.setWwdUserId(po.getWwdUserId());
        dto.setSelectedWwdUserId(po.getSelectedWwdUserId());
        dto.setLevel(po.getLevel());
        dto.setDataUserId(po.getDataUserId());
        dto.setDataOfficeId(po.getDataOfficeId());
        dto.setDataType(po.getDataType());
        dto.setDataAreaId(po.getDataAreaId());
        dto.setUpdateAt(po.getUpdateAt());
        return dto;
    }
}