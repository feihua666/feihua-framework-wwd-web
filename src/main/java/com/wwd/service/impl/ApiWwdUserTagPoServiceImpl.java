package com.wwd.service.impl;

import com.wwd.service.mapper.WwdUserTagPoMapper;
import com.wwd.service.modules.wwd.api.ApiWwdUserTagPoService;
import com.wwd.service.modules.wwd.dto.WwdUserTagDto;
import com.wwd.service.modules.wwd.po.WwdUserTagPo;
import feihua.jdbc.api.pojo.BasePo;
import feihua.jdbc.api.service.impl.ApiBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2018-04-25 11:25:16
 */
@Service
public class ApiWwdUserTagPoServiceImpl extends ApiBaseServiceImpl<WwdUserTagPo, WwdUserTagDto, String> implements ApiWwdUserTagPoService {
    @Autowired
    WwdUserTagPoMapper wwdUserTagPoMapper;

    public ApiWwdUserTagPoServiceImpl() {
        super(WwdUserTagDto.class);
    }

    @Override
    public List<WwdUserTagDto> selectByWwdUserId(String wwdUserId) {
        WwdUserTagPo wwdUserTagPo = new WwdUserTagPo();
        wwdUserTagPo.setWwdUserId(wwdUserId);
        wwdUserTagPo.setDelFlag(BasePo.YesNo.N.name());
        return this.selectList(wwdUserTagPo);
    }

    @Override
    public WwdUserTagDto selectByWwdUserIdAndType(String wwdUserId, String type) {
        WwdUserTagPo wwdUserTagPo = new WwdUserTagPo();
        wwdUserTagPo.setWwdUserId(wwdUserId);
        wwdUserTagPo.setDelFlag(BasePo.YesNo.N.name());
        wwdUserTagPo.setType(type);
        return this.selectOne(wwdUserTagPo);
    }

    @Override
    public WwdUserTagDto wrapDto(WwdUserTagPo po) {
        if (po == null) {
            return null;
        }
        WwdUserTagDto wwdUserTagDto = new WwdUserTagDto();
        wwdUserTagDto.setDataUserId(po.getDataUserId());
        wwdUserTagDto.setUpdateAt(po.getUpdateAt());
        wwdUserTagDto.setContent(po.getContent());
        wwdUserTagDto.setId(po.getId());
        wwdUserTagDto.setWwdUserId(po.getWwdUserId());
        wwdUserTagDto.setDataAreaId(po.getDataAreaId());
        wwdUserTagDto.setDataType(po.getDataType());
        wwdUserTagDto.setType(po.getType());
        wwdUserTagDto.setSelfContent(po.getSelfContent());
        wwdUserTagDto.setDataOfficeId(po.getDataOfficeId());
        return wwdUserTagDto;
    }
}