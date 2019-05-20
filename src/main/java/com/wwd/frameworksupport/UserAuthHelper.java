package com.wwd.frameworksupport;

import com.feihua.framework.base.modules.user.api.ApiBaseUserAuthPoService;
import com.feihua.framework.base.modules.user.api.ApiBaseUserPoService;
import com.feihua.framework.base.modules.user.dto.BaseUserAuthDto;
import com.feihua.framework.base.modules.user.po.BaseUserAuthPo;
import com.feihua.framework.base.modules.user.po.BaseUserPo;
import com.feihua.framework.constants.DictEnum;
import com.feihua.wechat.CommonConstants;
import com.feihua.wechat.common.api.ApiWeixinUserPoService;
import com.feihua.wechat.common.dto.WeixinUserDto;
import com.feihua.wechat.common.po.WeixinUserPo;
import feihua.jdbc.api.pojo.BasePo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户认证信息辅助工具类
 * Created by yangwei
 * Created at 2018/7/30 15:09
 */
@Service
public class UserAuthHelper {

    @Autowired
    private ApiBaseUserPoService apiBaseUserPoService;
    @Autowired
    private ApiBaseUserAuthPoService apiBaseUserAuthPoService;
    @Autowired
    private ApiWeixinUserPoService apiWeixinUserPoService;

    public BaseUserPo generateUserAuth(WeixinUserDto weixinUserDto,String fromClientId) {


        String loginType = null;
        if(DictEnum.WxAccountType.weixin_miniprogram.name().equals(weixinUserDto.getType())){
            loginType = DictEnum.LoginType.WX_MINIPROGRAM.name();
        }else if(DictEnum.WxAccountType.weixin_publicplatform.name().equals(weixinUserDto.getType())){
            loginType = DictEnum.LoginType.WX_PLATFORM.name();

        }

        // 如果已经存在直接返回
        BaseUserAuthDto baseUserAuthDto = apiBaseUserAuthPoService.selectByIdentifierAndType(weixinUserDto.getOpenid(),loginType);
        if (baseUserAuthDto != null) {
            // 更新用户信息，头像昵称等
            BaseUserPo baseUserPo = new BaseUserPo();
            baseUserPo.setNickname(weixinUserDto.getNickname());
            baseUserPo.setPhoto(weixinUserDto.getHeadImageUrl());
            baseUserPo.setGender(CommonConstants.genderMapping.get(weixinUserDto.getGender()));
            baseUserPo.setId(baseUserAuthDto.getUserId());
            if (StringUtils.isNotEmpty(fromClientId)){
                BaseUserPo userPo = apiBaseUserPoService.selectByPrimaryKeySimple(baseUserAuthDto.getUserId());
                if (userPo != null && StringUtils.isEmpty(userPo.getFromClientId())) {
                    baseUserPo.setFromClientId(fromClientId);
                }
            }
            apiBaseUserPoService.updateByPrimaryKeySelective(baseUserPo);

            return apiBaseUserPoService.selectByPrimaryKeySimple(baseUserAuthDto.getUserId());
        }


        BaseUserPo baseUserPo = new BaseUserPo();
        baseUserPo.setNickname(weixinUserDto.getNickname());
        baseUserPo.setPhoto(weixinUserDto.getHeadImageUrl());
        baseUserPo.setLocked(BasePo.YesNo.N.name());
        baseUserPo.setGender(CommonConstants.genderMapping.get(weixinUserDto.getGender()));
        baseUserPo.setFromClientId(fromClientId);
        apiBaseUserPoService.preInsert(baseUserPo,BasePo.DEFAULT_USER_ID);
        baseUserPo = apiBaseUserPoService.insertSimple(baseUserPo);

        BaseUserAuthPo baseUserAuthPo = new BaseUserAuthPo();
        baseUserAuthPo.setUserId(baseUserPo.getId());
        baseUserAuthPo.setIdentityType(loginType);
        baseUserAuthPo.setIdentifier(weixinUserDto.getOpenid());
        baseUserAuthPo.setVerified(BasePo.YesNo.Y.name());
        apiBaseUserAuthPoService.preInsert(baseUserAuthPo,BasePo.DEFAULT_USER_ID);
        apiBaseUserAuthPoService.insertSimple(baseUserAuthPo);

        //回写userid
        WeixinUserPo weixinUserPo = new WeixinUserPo();
        weixinUserPo.setId(weixinUserDto.getId());
        weixinUserPo.setUserId(baseUserPo.getId());
        apiWeixinUserPoService.preUpdate(weixinUserPo,BasePo.DEFAULT_USER_ID);
        apiWeixinUserPoService.updateByPrimaryKeySelective(weixinUserPo);

        return baseUserPo;
    }
}
