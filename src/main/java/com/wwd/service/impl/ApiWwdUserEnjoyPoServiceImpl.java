package com.wwd.service.impl;

import com.wwd.service.modules.wwd.api.ApiWwdUserEnjoyPoService;
import com.wwd.service.modules.wwd.dto.WwdUserEnjoyDto;
import com.wwd.service.modules.wwd.po.WwdUserEnjoyPo;
import com.github.pagehelper.Page;
import feihua.jdbc.api.pojo.BasePo;
import feihua.jdbc.api.pojo.PageAndOrderbyParamDto;
import feihua.jdbc.api.pojo.PageResultDto;
import feihua.jdbc.api.service.impl.ApiBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2018-05-19 15:53:51
 */
@Service
public class ApiWwdUserEnjoyPoServiceImpl extends ApiBaseServiceImpl<WwdUserEnjoyPo, WwdUserEnjoyDto, String> implements ApiWwdUserEnjoyPoService {
    @Autowired
    com.wwd.service.mapper.WwdUserEnjoyPoMapper wwdUserEnjoyPoMapper;

    public ApiWwdUserEnjoyPoServiceImpl() {
        super(WwdUserEnjoyDto.class);
    }

    @Override
    public PageResultDto<WwdUserEnjoyDto> searchWwdUserEnjoysDsf(com.wwd.service.modules.wwd.dto.SearchWwdUserEnjoysConditionDto dto, feihua.jdbc.api.pojo.PageAndOrderbyParamDto pageAndOrderbyParamDto) {
        Page p = super.pageAndOrderbyStart(pageAndOrderbyParamDto);
        List<com.wwd.service.modules.wwd.dto.WwdUserEnjoyDto> list = this.wrapDtos(wwdUserEnjoyPoMapper.searchWwdUserEnjoys(dto));
        return new PageResultDto(list, this.wrapPage(p));
    }

    @Override
    public List<WwdUserEnjoyDto> selectByWwdUserId(String wwdUserId,String type,String typeLimit) {
        List<WwdUserEnjoyPo> wwdUserEnjoyPos = wwdUserEnjoyPoMapper.selectByWwdUserId(wwdUserId, type, typeLimit);
        return wrapDtos(wwdUserEnjoyPos);
    }

    @Override
    public List<WwdUserEnjoyDto> selectByWwdUserId(String wwdUserId) {
        WwdUserEnjoyPo wwdUserEnjoyPo = new WwdUserEnjoyPo();
        wwdUserEnjoyPo.setWwdUserId(wwdUserId);
        wwdUserEnjoyPo.setDelFlag(BasePo.YesNo.N.name());
        return this.selectList(wwdUserEnjoyPo);
    }

    @Override
    public PageResultDto<WwdUserEnjoyDto> selectByWwdUserId(String wwdUserId, PageAndOrderbyParamDto pageAndOrderbyParamDto) {
        Page p = super.pageAndOrderbyStart(pageAndOrderbyParamDto);
        WwdUserEnjoyPo wwdUserEnjoyPo = new WwdUserEnjoyPo();
        wwdUserEnjoyPo.setWwdUserId(wwdUserId);
        wwdUserEnjoyPo.setDelFlag(BasePo.YesNo.N.name());
        List<WwdUserEnjoyDto> list = this.selectList(wwdUserEnjoyPo);
        return new PageResultDto(list, this.wrapPage(p));
    }

    @Override
    public List<WwdUserEnjoyDto> selectByEnjoyedWwdUserId(String enjoyedWwdUserId) {
        WwdUserEnjoyPo wwdUserEnjoyPo = new WwdUserEnjoyPo();
        wwdUserEnjoyPo.setEnjoyedWwdUserId(enjoyedWwdUserId);
        wwdUserEnjoyPo.setDelFlag(BasePo.YesNo.N.name());
        return this.selectList(wwdUserEnjoyPo);
    }

    @Override
    public PageResultDto<WwdUserEnjoyDto> selectByEnjoyedWwdUserId(String enjoyedWwdUserId, PageAndOrderbyParamDto pageAndOrderbyParamDto) {
        Page p = super.pageAndOrderbyStart(pageAndOrderbyParamDto);
        WwdUserEnjoyPo wwdUserEnjoyPo = new WwdUserEnjoyPo();
        wwdUserEnjoyPo.setEnjoyedWwdUserId(enjoyedWwdUserId);
        wwdUserEnjoyPo.setDelFlag(BasePo.YesNo.N.name());
        List<WwdUserEnjoyDto> list = this.selectList(wwdUserEnjoyPo);
        return new PageResultDto(list, this.wrapPage(p));
    }

    @Override
    public List<WwdUserEnjoyDto> selectEnjoyedByWwdUserId(String enjoyedWwdUserId) {
        List<WwdUserEnjoyDto> list = this.wrapDtos(wwdUserEnjoyPoMapper.selectEnjoyedByWwdUserId(enjoyedWwdUserId));
        return list;
    }

    @Override
    public PageResultDto<WwdUserEnjoyDto> selectEnjoyedByWwdUserId(String wwdUserId, PageAndOrderbyParamDto pageAndOrderbyParamDto) {
        Page p = super.pageAndOrderbyStart(pageAndOrderbyParamDto);
        List<WwdUserEnjoyDto> list = this.wrapDtos(wwdUserEnjoyPoMapper.selectEnjoyedByWwdUserId(wwdUserId));
        return new PageResultDto(list, this.wrapPage(p));
    }

    @Override
    public WwdUserEnjoyDto selectEnjoyedFromTo(String wwdUserId, String enjoyedWwdUserId) {
        WwdUserEnjoyPo wwdUserEnjoyPo = new WwdUserEnjoyPo();
        wwdUserEnjoyPo.setWwdUserId(wwdUserId);
        wwdUserEnjoyPo.setEnjoyedWwdUserId(enjoyedWwdUserId);
        wwdUserEnjoyPo.setDelFlag(BasePo.YesNo.N.name());
        return this.selectOne(wwdUserEnjoyPo);
    }
}