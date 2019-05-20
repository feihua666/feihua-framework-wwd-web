package com.wwd.service.impl;

import com.feihua.framework.base.modules.config.api.ApiBaseConfigService;
import com.feihua.framework.base.modules.config.po.BaseConfig;
import com.feihua.utils.xml.XmlUtils;
import com.feihua.wechat.publicplatform.PublicUtils;
import com.feihua.wechat.publicplatform.api.MsgTypeHandler;
import com.feihua.wechat.publicplatform.dto.RequestMessage;
import com.feihua.wechat.publicplatform.dto.ResponseTextMessage;
import com.wwd.service.modules.wwd.api.ApiWwdUserPoService;
import com.wwd.service.modules.wwd.dto.WwdUserDto;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理扫描带参数二维码
 * Created by yangwei
 * Created at 2018/7/20 11:42
 */

/**
 * 扫描带参数二维码事件处理，事件来源分两种，一种是未关注，一种是已关注，参数不同，请注意
 */
@Service("wx_public_event_SCAN")
public class WwdScanQuSceneMsgHandler implements MsgTypeHandler {
    private static final String qrScene_pre = "qrscene_";
    private static final String card_wwdUserId_pre = "card_wwdUserId_";
    private static final String share_wwd_user_detail_uni_app_url = "share_wwd_user_detail_uni_app_url";
    private static final String wwdUserId_param = "{{wwdUserId}}";
    private static final String url_param = "{{url}}";
    private static final String name_param = "{{name}}";
    private static final String subscribe = "subscribe";
    private static final String share_wwd_user_detail_uni_app_msg_content = "share_wwd_user_detail_uni_app_msg_content";

    @Autowired
    private ApiWwdUserPoService apiWwdUserPoService;

    @Autowired
    private ApiBaseConfigService apiBaseConfigService;

    public String handleMsg( String postXmlData, String which) {
        Document document = XmlUtils.stringToDocument(postXmlData);
        RequestMessage requestSubscribeMessage = (RequestMessage)PublicUtils.xmlToMessage(postXmlData, new RequestMessage());

        String eventKey = XmlUtils.getElementText("EventKey", document);
        String event = XmlUtils.getElementText("Event", document);
        if (StringUtils.isNotEmpty(eventKey)) {

            String sceneStr = eventKey;
            if(eventKey.startsWith(qrScene_pre)){
                sceneStr = eventKey.substring(qrScene_pre.length());
            }
            // 回复查看汪汪队用户详情消息
            if(sceneStr.startsWith(card_wwdUserId_pre)){
                String wwdUserId = sceneStr.replace(card_wwdUserId_pre,"");
                WwdUserDto wwdUserDto = apiWwdUserPoService.selectByPrimaryKey(wwdUserId);
                if (wwdUserDto != null) {
                    String name = wwdUserDto.getName();
                    if (StringUtils.isEmpty(name)) {
                        name = wwdUserDto.getNickname();
                    }

                    String url = "http://api.51match.cn/uni-app/#/pages/detail/detail?wwdUserId={{wwdUserId_param}}";
                    BaseConfig baseConfig = apiBaseConfigService.selectByConfigKey(share_wwd_user_detail_uni_app_url);
                    if (baseConfig != null && StringUtils.isNotEmpty(baseConfig.getConfigValue())) {
                        url = baseConfig.getConfigValue();
                    }
                    url = url.replace(wwdUserId_param,wwdUserId);
                    String r = "";

                    if(subscribe.equals(event)){
                        String subMessage = PublicUtils.getWxMessage(which);
                        if (StringUtils.isNotEmpty(subMessage)) {
                            r += subMessage + "\n";
                        }

                    }
                    BaseConfig content = apiBaseConfigService.selectByConfigKey(share_wwd_user_detail_uni_app_msg_content);
                    r += content.getConfigValue().replace(name_param,name).replace(url_param,url);
                    ResponseTextMessage responseTextMessage = new ResponseTextMessage();
                    responseTextMessage.setContent(r);
                    PublicUtils.userChange(requestSubscribeMessage, responseTextMessage);
                    return PublicUtils.messageToXml(responseTextMessage, true);
                }

            }else {
                // todo 其它场景值请在这里添加
            }
        }

        return "";
    }
}
