package com.wwd.service.impl;

import com.github.pagehelper.Page;
import com.wwd.service.modules.wwd.api.ApiWwdActivityOrderService;
import com.wwd.service.modules.wwd.dto.WwdActivityOrderDto;
import com.wwd.service.modules.wwd.po.WwdActivityOrder;
import feihua.jdbc.api.pojo.BasePo;
import feihua.jdbc.api.pojo.PageResultDto;
import feihua.jdbc.api.service.impl.ApiBaseServiceImpl;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class was generated by MyBatis Generator.
 * @author revolver 2019-03-20 13:52:35
 */
@Service
public class ApiWwdActivityOrderServiceImpl extends ApiBaseServiceImpl<WwdActivityOrder, WwdActivityOrderDto, String> implements ApiWwdActivityOrderService {
    @Autowired
    com.wwd.service.mapper.WwdActivityOrderMapper WwdActivityOrderMapper;

    public ApiWwdActivityOrderServiceImpl() {
        super(WwdActivityOrderDto.class);
    }

    @Override
    public PageResultDto<WwdActivityOrderDto> searchWwdActivityOrdersDsf(com.wwd.service.modules.wwd.dto.SearchWwdActivityOrdersConditionDto dto, feihua.jdbc.api.pojo.PageAndOrderbyParamDto pageAndOrderbyParamDto) {
        Page p = super.pageAndOrderbyStart(pageAndOrderbyParamDto);
        List<com.wwd.service.modules.wwd.dto.WwdActivityOrderDto> list = this.wrapDtos(WwdActivityOrderMapper.searchWwdActivityOrders(dto));
        return new PageResultDto(list, this.wrapPage(p));
    }

    @Override
    public WwdActivityOrder selectByParticipateIdAndUserId(String participateId, String userId) {
        if(StringUtils.isAnyEmpty(participateId,userId)) return null;
        WwdActivityOrder wwdActivityOrder = new WwdActivityOrder();
        wwdActivityOrder.setParticipateId(participateId);
        wwdActivityOrder.setUserId(userId);
        wwdActivityOrder.setDelFlag(BasePo.YesNo.N.name());
        return selectOneSimple(wwdActivityOrder);
    }

    @Override
    public WwdActivityOrder selectByOrderNo(String orderNo) {
        if(StringUtils.isAnyEmpty(orderNo)) return null;
        WwdActivityOrder wwdActivityOrder = new WwdActivityOrder();
        wwdActivityOrder.setOrderNo(orderNo);
        wwdActivityOrder.setDelFlag(BasePo.YesNo.N.name());
        return selectOneSimple(wwdActivityOrder);
    }

    @Override
    public WwdActivityOrderDto wrapDto(WwdActivityOrder po) {
        if (po == null) { return null; }
        WwdActivityOrderDto dto = new WwdActivityOrderDto();
        dto.setId(po.getId());
        dto.setOrderNo(po.getOrderNo());
        dto.setActivityTitle(po.getActivityTitle());
        dto.setActivityUrl(po.getActivityUrl());
        dto.setParticipateId(po.getParticipateId());
        dto.setUserId(po.getUserId());
        dto.setPayType(po.getPayType());
        dto.setPayStatus(po.getPayStatus());
        dto.setRemarks(po.getRemarks());
        dto.setDataUserId(po.getDataUserId());
        dto.setDataOfficeId(po.getDataOfficeId());
        dto.setDataType(po.getDataType());
        dto.setDataAreaId(po.getDataAreaId());
        dto.setUpdateAt(po.getUpdateAt());
        return dto;
    }
}