package com.wwd.web.modules.wwd.mvc;

import com.feihua.framework.base.modules.group.api.ApiBaseUserGroupPoService;
import com.feihua.framework.base.modules.loginclient.api.ApiBaseLoginClientPoService;
import com.feihua.framework.base.modules.rel.api.ApiBaseUserUserGroupRelPoService;
import com.feihua.framework.base.modules.rel.dto.BaseUserUserGroupRelDto;
import com.feihua.framework.base.modules.role.dto.BaseRoleDto;
import com.feihua.framework.base.modules.user.api.ApiBaseUserPoService;
import com.feihua.framework.constants.DictEnum;
import com.feihua.framework.message.api.*;
import com.feihua.framework.message.dto.*;
import com.feihua.framework.message.po.BaseMessagePo;
import com.feihua.framework.rest.ResponseJsonRender;
import com.feihua.framework.rest.interceptor.RepeatFormValidator;
import com.feihua.framework.rest.modules.common.mvc.BaseController;
import com.feihua.utils.calendar.CalendarUtils;
import com.feihua.utils.http.httpServletResponse.ResponseCode;
import com.feihua.utils.json.JSONUtils;
import com.feihua.utils.collection.CollectionUtils;
import com.wwd.Constants;
import com.wwd.service.modules.wwd.api.ApiWwdActivityService;
import com.wwd.service.modules.wwd.api.ApiWwdParticipateService;
import com.wwd.service.modules.wwd.api.ApiWwdUserPoService;
import com.wwd.service.modules.wwd.dto.*;
import com.wwd.service.modules.wwd.po.WwdActivity;
import com.wwd.web.modules.wwd.dto.AddWwdActivity;
import com.wwd.web.modules.wwd.dto.UpdateWwdActivity;
import feihua.jdbc.api.pojo.BasePo;
import feihua.jdbc.api.pojo.Page;
import feihua.jdbc.api.pojo.PageAndOrderbyParamDto;
import feihua.jdbc.api.pojo.PageResultDto;
import feihua.jdbc.api.utils.OrderbyUtils;
import feihua.jdbc.api.utils.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 汪汪队活动管理
 * Created by yangwei
 */
@RestController
@RequestMapping("/wwd")
@SuppressWarnings("all")
public class WwdActivityController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(WwdActivityController.class);

    @Autowired
    private ApiWwdActivityService apiWwdActivityService;

    @Autowired
    private ApiWwdParticipateService apiWwdParticipateService;

    @Autowired
    private ApiWwdUserPoService apiWwdUserPoService;
    @Autowired
    private ApiBaseUserGroupPoService apiBaseUserGroupPoService;
    @Autowired
    private ApiBaseUserUserGroupRelPoService apiBaseUserUserGroupRelPoService;


    @Autowired
    private MessageSendHelper messageSendHelper;
    /**
     * 单资源，添加
     *
     * @param dto
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:activity:add")
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody AddWwdActivity dto) {
        logger.info("添加汪汪队活动开始");
        logger.info("当前登录用户id:{}", getLoginUser().getId());
        ResponseJsonRender resultData = new ResponseJsonRender();
        // 表单值设置
        WwdActivity basePo = new WwdActivity();
        basePo.setId(dto.getId());
        basePo.setTitle(dto.getTitle());
        basePo.setTitleUrl(dto.getTitleUrl());
        basePo.setAuthor(dto.getAuthor());
        basePo.setStartTime(dto.getStartTime());
        basePo.setEndTime(dto.getEndTime());
        basePo.setAddr(dto.getAddr());
        basePo.setContact(dto.getContact());
        basePo.setSequence(dto.getSequence());
        basePo.setType(dto.getType());
        basePo.setRequireIdCard(dto.getRequireIdCard());
        basePo.setStatus(dto.getStatus());
        basePo.setHeadcount(dto.getHeadcount());
        basePo.setHeadcountMale(dto.getHeadcountMale());
        basePo.setHeadcountFemale(dto.getHeadcountFemale());
        basePo.setHeadcountDesc(dto.getHeadcountDesc());
        basePo.setIntroduced(dto.getIntroduced());
        basePo.setHeadcountRule(dto.getHeadcountRule());
        basePo.setMalePrice(dto.getMalePrice());
        basePo.setFemalePrice(dto.getFemalePrice());
        basePo.setActivityStatement(dto.getActivityStatement());
        basePo.setDataUserId(dto.getDataUserId());
        basePo.setDataOfficeId(dto.getDataOfficeId());
        basePo.setDataType(dto.getDataType());
        basePo.setDataAreaId(dto.getDataAreaId());
        basePo.setDelFlag(dto.getDelFlag());
        basePo.setCreateAt(dto.getCreateAt());
        basePo.setCreateBy(dto.getCreateBy());
        basePo.setUpdateAt(dto.getUpdateAt());
        basePo.setUpdateBy(dto.getUpdateBy());
        basePo.setContent(dto.getContent());
        basePo.setPayType(dto.getPayType());
        basePo.setMutualElectionStatus(dto.getMutualElectionStatus());
        basePo.setManageUserGroupId(dto.getManageUserGroupId());

        basePo = apiWwdActivityService.preInsert(basePo, getLoginUser().getId());
        WwdActivityDto r = apiWwdActivityService.insert(basePo);
        if (r == null) {
            // 添加失败
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
            logger.info("添加汪汪队活动结束，失败");
            return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
        } else {
            // 添加成功，返回添加的数据
            resultData.setData(r);
            logger.info("添加汪汪队活动id:{}", r.getId());
            logger.info("添加汪汪队活动结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }

    /**
     * 单资源，删除
     *
     * @param id
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:activity:delete")
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id) {
        logger.info("删除汪汪队活动开始");
        logger.info("用户id:{}", getLoginUser().getId());
        logger.info("汪汪队活动id:{}", id);
        ResponseJsonRender resultData = new ResponseJsonRender();

        int r = apiWwdActivityService.deleteFlagByPrimaryKeyWithUpdateUser(id, getLoginUser().getId());
        if (r <= 0) {
            // 删除失败，可能没有找到资源
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
            logger.info("删除汪汪队活动结束，失败");
            return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
        } else {
            // 删除成功
            logger.info("删除的汪汪队活动id:{}", id);
            logger.info("删除汪汪队活动结束，成功");
            return new ResponseEntity(resultData, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * 单资源，更新
     *
     * @param id
     * @param dto
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:activity:update")
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable String id, @RequestBody UpdateWwdActivity dto) {
        logger.info("更新汪汪队活动开始");
        logger.info("当前登录用户id:{}", getLoginUser().getId());
        logger.info("汪汪队活动id:{}", id);
        ResponseJsonRender resultData = new ResponseJsonRender();
        // 表单值设置
        WwdActivity basePo = new WwdActivity();
        // id
        basePo.setId(id);
        basePo.setId(dto.getId());
        basePo.setTitle(dto.getTitle());
        basePo.setTitleUrl(dto.getTitleUrl());
        basePo.setAuthor(dto.getAuthor());
        basePo.setStartTime(dto.getStartTime());
        basePo.setEndTime(dto.getEndTime());
        basePo.setAddr(dto.getAddr());
        basePo.setContact(dto.getContact());
        basePo.setSequence(dto.getSequence());
        basePo.setType(dto.getType());
        basePo.setRequireIdCard(dto.getRequireIdCard());
        basePo.setStatus(dto.getStatus());
        basePo.setHeadcount(dto.getHeadcount());
        basePo.setHeadcountMale(dto.getHeadcountMale());
        basePo.setHeadcountFemale(dto.getHeadcountFemale());
        basePo.setHeadcountDesc(dto.getHeadcountDesc());
        basePo.setIntroduced(dto.getIntroduced());
        basePo.setHeadcountRule(dto.getHeadcountRule());
        basePo.setMalePrice(dto.getMalePrice());
        basePo.setFemalePrice(dto.getFemalePrice());
        basePo.setActivityStatement(dto.getActivityStatement());
        basePo.setDataUserId(dto.getDataUserId());
        basePo.setDataOfficeId(dto.getDataOfficeId());
        basePo.setDataType(dto.getDataType());
        basePo.setDataAreaId(dto.getDataAreaId());
        basePo.setDelFlag(dto.getDelFlag());
        basePo.setCreateAt(dto.getCreateAt());
        basePo.setCreateBy(dto.getCreateBy());
        basePo.setUpdateAt(dto.getUpdateAt());
        basePo.setUpdateBy(dto.getUpdateBy());
        basePo.setContent(dto.getContent());
        basePo.setPayType(dto.getPayType());
        basePo.setMutualElectionStatus(dto.getMutualElectionStatus());
        basePo.setManageUserGroupId(dto.getManageUserGroupId());
        // 用条件更新，乐观锁机制
        WwdActivity basePoCondition = new WwdActivity();
        basePoCondition.setId(id);
        basePoCondition.setDelFlag(BasePo.YesNo.N.name());
        basePoCondition.setUpdateAt(dto.getUpdateTime());
        basePo = apiWwdActivityService.preUpdate(basePo, getLoginUser().getId());
        int r = apiWwdActivityService.updateSelective(basePo, basePoCondition);
        if (r <= 0) {
            // 更新失败，资源不存在
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
            logger.info("更新汪汪队活动结束，失败");
            return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
        } else {
            // 更新成功，已被成功创建
            logger.info("更新的汪汪队活动id:{}", id);
            logger.info("更新汪汪队活动结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }


    /**
     * 单资源，复制活动
     *
     * @param id
     * @return
     */
    @RequiresPermissions("wwd:activity:copy")
    @RequestMapping(value = "/activity/copy/{id}", method = RequestMethod.GET)
    public ResponseEntity copy(@PathVariable String id) {
        logger.info("复制汪汪队活动开始");
        logger.info("当前登录用户id:{}", getLoginUser().getId());
        logger.info("汪汪队活动id:{}", id);
        ResponseJsonRender resultData = new ResponseJsonRender();

        WwdActivityDto dto = apiWwdActivityService.selectByPrimaryKey(id);
        // 表单值设置
        WwdActivity basePo = new WwdActivity();
        // id
        basePo.setId(null);
        basePo.setTitle("[复制] " + dto.getTitle());
        basePo.setTitleUrl(dto.getTitleUrl());
        basePo.setAuthor(dto.getAuthor());
        basePo.setStartTime(null);
        basePo.setEndTime(null);
        basePo.setAddr(dto.getAddr());
        basePo.setContact(dto.getContact());
        basePo.setSequence(dto.getSequence());
        basePo.setType(dto.getType());
        basePo.setStatus(Constants.ActivityStatus.EDIT.getCode());
        basePo.setHeadcount(dto.getHeadcount());
        basePo.setHeadcountRule(dto.getHeadcountRule());
        basePo.setHeadcountMale(dto.getHeadcountMale());
        basePo.setHeadcountFemale(dto.getHeadcountFemale());
        basePo.setHeadcountDesc(dto.getHeadcountDesc());
        basePo.setIntroduced(dto.getIntroduced());
        basePo.setMalePrice(dto.getMalePrice());
        basePo.setFemalePrice(dto.getFemalePrice());
        basePo.setActivityStatement(dto.getActivityStatement());
        basePo.setDataUserId(dto.getDataUserId());
        basePo.setDataOfficeId(dto.getDataOfficeId());
        basePo.setDataType(dto.getDataType());
        basePo.setDataAreaId(dto.getDataAreaId());
        basePo.setContent(dto.getContent());
        basePo.setRequireIdCard(dto.getRequireIdCard());
        basePo.setPayType(dto.getPayType());
        basePo.setMutualElectionStatus(Constants.MutualElectionStatus.no_start.name());
        basePo.setManageUserGroupId(dto.getManageUserGroupId());
        basePo = apiWwdActivityService.preInsert(basePo, getLoginUser().getId());
        WwdActivityDto r = apiWwdActivityService.insert(basePo);
        if (r == null) {
            // 更新失败，资源不存在
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
            logger.info("复制汪汪队活动结束，失败");
            return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
        } else {
            // 更新成功，已被成功创建
            logger.info("复制的汪汪队活动id:{}", id);
            logger.info("复制汪汪队活动结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }


    /**
     * 单资源，修改状态
     *
     * @param id
     * @param status
     * @return
     */
    @RequiresPermissions("wwd:activity:edit:status")
    @RequestMapping(value = "/activity/{id}/edit/{status}", method = RequestMethod.PUT)
    public ResponseEntity editStatus(@PathVariable String id,@PathVariable String status) {
        logger.info("汪汪队活动修改状态开始");
        logger.info("当前登录用户id:{}", getLoginUser().getId());
        logger.info("汪汪队活动id:{}", id);
        ResponseJsonRender resultData = new ResponseJsonRender();

        WwdActivity wwdActivity = apiWwdActivityService.selectByPrimaryKeySimple(id);
        int r = 0;
        if(wwdActivity != null && StringUtils.isNotEmpty(status) && !wwdActivity.getStatus().equals(status)
                && Constants.ActivityStatus.getEnumBy(status) != null ){
            wwdActivity.setStatus(status);

            // 用条件更新，乐观锁机制
            WwdActivity basePoCondition = new WwdActivity();
            basePoCondition.setId(id);
            basePoCondition.setDelFlag(BasePo.YesNo.N.name());
            basePoCondition.setUpdateAt(wwdActivity.getUpdateAt());
            wwdActivity = apiWwdActivityService.preUpdate(wwdActivity, getLoginUser().getId());
             r = apiWwdActivityService.updateSelective(wwdActivity, basePoCondition);
        }

        if (r <= 0) {
            // 更新失败，资源不存在
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
            logger.info("汪汪队活动修改状态结束，失败");
            return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
        } else {
            // 更新成功，已被成功创建
            logger.info("更新的汪汪队活动id:{}", id);
            logger.info("汪汪队活动修改状态结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }

    /**
     * 单资源，修改互选状态
     *
     * @param id
     * @param status
     * @return
     */
    @RequiresPermissions("wwd:activity:edit:mutualElectionStatus")
    @RequestMapping(value = "/activity/{id}/mutualElection/{status}", method = RequestMethod.PUT)
    public ResponseEntity editMutualElectionStatus(@PathVariable String id,@PathVariable String status) {
        logger.info("汪汪队活动修改互选状态开始");
        logger.info("当前登录用户id:{}", getLoginUser().getId());
        logger.info("汪汪队活动id:{}", id);
        ResponseJsonRender resultData = new ResponseJsonRender();

        WwdActivity wwdActivity = apiWwdActivityService.selectByPrimaryKeySimple(id);
        int r = 0;
        if(wwdActivity != null && StringUtils.isNotEmpty(status) ){
            wwdActivity.setMutualElectionStatus(status);

            // 用条件更新，乐观锁机制
            WwdActivity basePoCondition = new WwdActivity();
            basePoCondition.setId(id);
            basePoCondition.setDelFlag(BasePo.YesNo.N.name());
            basePoCondition.setUpdateAt(wwdActivity.getUpdateAt());
            wwdActivity = apiWwdActivityService.preUpdate(wwdActivity, getLoginUser().getId());
            r = apiWwdActivityService.updateSelective(wwdActivity, basePoCondition);
        }

        if (r <= 0) {
            // 更新失败，资源不存在
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
            logger.info("汪汪队活动修改互选状态结束，失败");
            return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
        } else {
            // 更新成功，已被成功创建
            logger.info("更新的汪汪队活动id:{}", id);
            logger.info("汪汪队活动修改互选状态结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }
    /**
     * 单资源，获取id汪汪队活动
     *
     * @param id
     * @return
     */
    @RequiresPermissions("wwd:activity:getById")
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable String id) {

        ResponseJsonRender resultData = new ResponseJsonRender();
        WwdActivityDto baseDataScopeDto = apiWwdActivityService.selectByPrimaryKey(id);
        if (baseDataScopeDto != null) {
            resultData.setData(baseDataScopeDto);
            // 是否具有管理功能,目前在h5上用
            if (StringUtils.isNotEmpty(baseDataScopeDto.getManageUserGroupId())) {
                BaseUserUserGroupRelDto userUserGroupRelDto = apiBaseUserUserGroupRelPoService.selectByUserIdAndUserGroupId(getLoginUserId(),baseDataScopeDto.getManageUserGroupId());
                if (userUserGroupRelDto != null) {
                    resultData.addData("activityManage",true);
                }
            }
            return new ResponseEntity(resultData, HttpStatus.OK);
        } else {
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
        }
    }
    @Autowired
    private ApiBaseMessagePoService apiBaseMessagePoService;

    @Autowired
    private ApiBaseUserPoService apiBaseUserPoService;
    @Autowired
    private ApiMessageService apiMessageService;
    @Autowired
    private ApiBaseMessageUserPoService apiBaseMessageUserPoService;
    @Autowired
    private ApiBaseLoginClientPoService apiBaseLoginClientPoService;
    @Autowired
    private ApiBaseMessageTemplatePoService apiBaseMessageTemplatePoService;
    @Autowired
    private ApiBaseMessageThirdPoService apiBaseMessageThirdPoService;
    @InitBinder  //类初始化是调用的方法注解
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowNestedPaths(true);
        //给这个controller配置接收list的长度100000，仅在这个controller有效
        binder.setAutoGrowCollectionLimit(10000);

    }
    /**
     * 单资源，创建并发送消息
     * @param
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("message:newsend")
    @RequestMapping(value = "/message/newsend/{messageTemplateId}",method = RequestMethod.POST)
    public ResponseEntity newsend(@PathVariable String messageTemplateId, MessageSendFormDto formDto){
        logger.info("发送消息开始");
        logger.info("当前登录用户id:{}",getLoginUser().getId());
        String currentUserId = getLoginUser().getId();
        ResponseJsonRender resultData = new ResponseJsonRender();


        BaseMessageTemplateDto

                messageTemplateDto = apiBaseMessageTemplatePoService.selectByPrimaryKey(messageTemplateId);

        CreateMessageParamsDto createMessageParamsDto = new CreateMessageParamsDto();
        createMessageParamsDto.setTitle(messageTemplateDto.getTitle());
        createMessageParamsDto.setProfile(messageTemplateDto.getProfile());
        createMessageParamsDto.setContent(messageTemplateDto.getContent());
        createMessageParamsDto.setMsgType(messageTemplateDto.getMsgType());
        createMessageParamsDto.setMsgLevel(messageTemplateDto.getMsgLevel());
        createMessageParamsDto.setMsgState(DictEnum.MessageState.to_be_sended.name());
        createMessageParamsDto.setMessageTemplateId(messageTemplateId);
        try {
            createMessageParamsDto.setTemplateParams(getTemplateParams(formDto.getTemplateParams()));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return returnBadRequest("templateParam","templateParam is invalid",resultData);
        }
        createMessageParamsDto.setCurrentUserId(currentUserId);
        createMessageParamsDto.setCurrentRoleId(getLoginUserRoleId());
        createMessageParamsDto.setCurrentPostId(getLoginUserPostId());
        BaseMessagePo r = apiBaseMessagePoService.addMessage(createMessageParamsDto);


        return sendMessage(resultData,r.getId(),formDto,currentUserId,getLoginUserRoleId(),getLoginUserPostId());

    }

    private Map<String,String> getTemplateParams(String templateParam) throws Exception {
        if (StringUtils.isNotEmpty(templateParam)) {

            Map<String,Object> params = JSONUtils.json2map(templateParam);
            if(params != null && !params.isEmpty()){
                Map<String,String> _params = new HashMap<>();
                for (String key : params.keySet()) {
                    _params.put(key, (String) params.get(key));
                }
                return (_params);
            }

        }
        return null;
    }

    private ResponseEntity sendMessage(ResponseJsonRender resultData,String messageId,MessageSendFormDto formDto,String currentUserId,String currentRoleId,String currentPostId){
        // 发送参数
        BaseMessageSendParamsDto baseMessageSendParamsDto = new BaseMessageSendParamsDto();
        baseMessageSendParamsDto.setMessageId(messageId);
        // 客户端发送参数
        BaseMessageSendClientParamDto clientParamDto = new BaseMessageSendClientParamDto();
        clientParamDto.setTargetType(formDto.getTargetType());
        clientParamDto.setTargetValues(formDto.getTargetValues());
        List<String> clientIds = formDto.getClientIds();
        if (clientIds != null && !clientIds.isEmpty()) {
            clientParamDto.setClients(apiBaseLoginClientPoService.selectByPrimaryKeys(clientIds,false));
        }
        baseMessageSendParamsDto.setClientParamDto(clientParamDto);
        // 虚拟客户端发送参数
        List<MessageVSendFormDto> vSendFormDtos = formDto.getvSendFormDtos();
        if (vSendFormDtos!=null ) {
            // 虚拟客户端容器
            List<BaseMessageSendVClientParamDto> vClientParamDtos = new ArrayList<>();
            BaseMessageSendVClientParamDto vClientParamDto = null;
            for (MessageVSendFormDto vSendFormDto : vSendFormDtos) {
                vClientParamDto = new BaseMessageSendVClientParamDto();
                vClientParamDto.setVclient(apiBaseLoginClientPoService.selectByPrimaryKey(vSendFormDto.getClientId(),false));
                vClientParamDto.setVtargetType(vSendFormDto.getvTargetType());
                vClientParamDto.setVtargetValues(CollectionUtils.StringToList(vSendFormDto.getvTargetValues(),","));
                vClientParamDtos.add(vClientParamDto);
            }
            baseMessageSendParamsDto.setvClientParamDtos(vClientParamDtos);
        }
        baseMessageSendParamsDto.setCurrentUserId(currentUserId);
        baseMessageSendParamsDto.setCurrentRoleId(currentRoleId);
        baseMessageSendParamsDto.setCurrentPostId(currentPostId);

        try {
            apiMessageService.messageSend(baseMessageSendParamsDto,true);
        }catch (Exception e){
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}",resultData.getCode(),resultData.getMsg());
            logger.info("发送消息结束，失败");
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }
        logger.info("发送消息id:{}",messageId);
        logger.info("发送消息结束，成功");
        return new ResponseEntity(resultData, HttpStatus.CREATED);
    }

    /**
     * 复数资源，搜索汪汪队活动
     *
     * @param dto
     * @return
     */
    @RequiresPermissions("wwd:activity:myactivitys")
    @RequestMapping(value = "/myActivitys", method = RequestMethod.GET)
    public ResponseEntity myActivitys(SearchWwdActivitysConditionDto dto,boolean isParticipate) {
        ResponseJsonRender resultData = new ResponseJsonRender();
        PageAndOrderbyParamDto pageAndOrderbyParamDto = new PageAndOrderbyParamDto(PageUtils.getPageFromThreadLocal(), OrderbyUtils.getOrderbyFromThreadLocal());
        // 设置当前登录用户id
        dto.setCurrentUserId(getLoginUser().getId());
        dto.setCurrentRoleId(((BaseRoleDto) getLoginUser().getRole()).getId());
        if(isParticipate){
            WwdUserDto wwdUserDto = apiWwdUserPoService.selectByUserId(getLoginUserId());
            dto.setDataUserId(wwdUserDto.getId());
        }

        PageResultDto<WwdActivityDto> list = apiWwdActivityService.myActivitysMultiTable(dto, pageAndOrderbyParamDto);
        List<WwdActivityDto> data = list.getData();
        if (data != null && data.size() > 0) {
            for (WwdActivityDto activityDto : data) {
                SearchWwdParticipatesConditionDto query = new SearchWwdParticipatesConditionDto();
                query.setStatusArr(new String[]{Constants.WwdParticipateStatus.NORMAL.getCode(), Constants.WwdParticipateStatus.ALTERNATE.getCode()});
                query.setPayStatusArr(new String[]{Constants.PayStatus.paid.name(),Constants.PayStatus.offline_pay.name()});

                query.setWwdActivityId(activityDto.getId());
                final PageResultDto<WwdParticipateDto> wwdParticipateDtoPageResultDto = apiWwdParticipateService.searchWwdParticipatesDsf(query, null);
                activityDto.setWwdParticipateDtos(wwdParticipateDtoPageResultDto.getData());
            }
        }
        if (!CollectionUtils.isEmpty(list.getData())) {
            resultData.setData(data);
            resultData.setPage(list.getPage());
            return new ResponseEntity(resultData, HttpStatus.OK);
        } else {
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 复数资源，搜索汪汪队活动
     *
     * @param dto
     * @return
     */
    @RequiresPermissions("wwd:activity:search")
    @RequestMapping(value = "/activitys", method = RequestMethod.GET)
    public ResponseEntity search(SearchWwdActivitysConditionDto dto) {
        ResponseJsonRender resultData = new ResponseJsonRender();
        PageAndOrderbyParamDto pageAndOrderbyParamDto = new PageAndOrderbyParamDto(PageUtils.getPageFromThreadLocal(), OrderbyUtils.getOrderbyFromThreadLocal());
        // 设置当前登录用户id
        dto.setCurrentUserId(getLoginUser().getId());
        dto.setCurrentRoleId(((BaseRoleDto) getLoginUser().getRole()).getId());
        PageResultDto<WwdActivityDto> list = apiWwdActivityService.searchWwdActivitysDsf(dto, pageAndOrderbyParamDto);
        List<WwdActivityDto> data = list.getData();
        if (data != null && data.size() > 0) {
            for (WwdActivityDto activityDto : data) {
                SearchWwdParticipatesConditionDto query = new SearchWwdParticipatesConditionDto();
                query.setStatusArr(new String[]{Constants.WwdParticipateStatus.NORMAL.getCode(), Constants.WwdParticipateStatus.ALTERNATE.getCode()});
                query.setWwdActivityId(activityDto.getId());
                query.setPayStatusArr(new String[]{Constants.PayStatus.paid.name(),Constants.PayStatus.offline_pay.name()});
                final PageResultDto<WwdParticipateDto> wwdParticipateDtoPageResultDto = apiWwdParticipateService.searchWwdParticipatesDsf(query, null);
                activityDto.setWwdParticipateDtos(wwdParticipateDtoPageResultDto.getData());
            }
        }
        if (!CollectionUtils.isEmpty(list.getData())) {
            resultData.setData(data);
            resultData.setPage(list.getPage());
            return new ResponseEntity(resultData, HttpStatus.OK);
        } else {
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
        }
    }
}
