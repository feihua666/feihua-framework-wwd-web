package com.wwd.frameworksupport;

import com.feihua.wechat.common.api.ApiWeixinUserListener;
import com.feihua.wechat.common.dto.WeixinUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实现用户发发现监听，这里不处理
 * Created by yangwei
 * Created at 2018/7/24 18:22
 */
@Service
public class ApiWeixinUserListenerImpl implements ApiWeixinUserListener{


    @Autowired
    private UserAuthHelper UserAuthHelper;
    @Override
    public void onAddWexinUser(WeixinUserDto weixinUserDto,String fromClientId) {
        UserAuthHelper.generateUserAuth(weixinUserDto,fromClientId);
    }

    @Override
    public void onUpdateWexinUser(WeixinUserDto weixinUserDto,String fromClientId) {
        UserAuthHelper.generateUserAuth(weixinUserDto,null);
    }

}
