package com.wwd.service.impl;

import com.wwd.service.modules.wwd.api.ApiWwdUserParamQrcodePoService;
import com.wwd.service.modules.wwd.dto.WwdUserParamQrcodeDto;
import com.wwd.service.modules.wwd.po.WwdUserParamQrcodePo;
import feihua.jdbc.api.pojo.BasePo;
import feihua.jdbc.api.service.impl.ApiBaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2019-05-17 10:57:54
 */
@Service
public class ApiWwdUserParamQrcodePoServiceImpl extends ApiBaseServiceImpl<WwdUserParamQrcodePo, WwdUserParamQrcodeDto, String> implements ApiWwdUserParamQrcodePoService {
    @Autowired
    com.wwd.service.mapper.WwdUserParamQrcodePoMapper WwdUserParamQrcodePoMapper;

    public ApiWwdUserParamQrcodePoServiceImpl() {
        super(WwdUserParamQrcodeDto.class);
    }

    @Override
    public WwdUserParamQrcodeDto wrapDto(WwdUserParamQrcodePo po) {
        if (po == null) { return null; }
        WwdUserParamQrcodeDto dto = new WwdUserParamQrcodeDto();
        dto.setId(po.getId());
        dto.setWwdUserId(po.getWwdUserId());
        dto.setTicket(po.getTicket());
        dto.setExpireSeconds(po.getExpireSeconds());
        dto.setContent(po.getContent());
        dto.setUrl(po.getUrl());
        dto.setIsLimit(po.getIsLimit());
        dto.setDataUserId(po.getDataUserId());
        dto.setDataOfficeId(po.getDataOfficeId());
        dto.setDataType(po.getDataType());
        dto.setDataAreaId(po.getDataAreaId());
        dto.setUpdateAt(po.getUpdateAt());
        return dto;
    }

    @Override
    public WwdUserParamQrcodePo selectByWwdUserIdAndIsLimit(String wwdUserId, BasePo.YesNo yesNo) {
        if (StringUtils.isEmpty(wwdUserId) || yesNo == null) {
            return null;
        }
        WwdUserParamQrcodePo wwdUserParamQrcodePo = new WwdUserParamQrcodePo();
        wwdUserParamQrcodePo.setWwdUserId(wwdUserId);
        wwdUserParamQrcodePo.setIsLimit(yesNo.name());
        wwdUserParamQrcodePo.setDelFlag(BasePo.YesNo.N.name());
        return selectOneSimple(wwdUserParamQrcodePo);
    }
}