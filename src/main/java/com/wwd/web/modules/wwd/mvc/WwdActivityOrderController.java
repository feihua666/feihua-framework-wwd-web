package com.wwd.web.modules.wwd.mvc;

import com.feihua.framework.base.modules.pay.api.ApiPayService;
import com.feihua.framework.base.modules.pay.wxpay.WxUnifiedOrderForInnerParam;
import com.feihua.framework.base.modules.pay.wxpay.sdk.MyWxPayConfig;
import com.feihua.framework.base.modules.pay.wxpay.sdk.WXPayConstants;
import com.feihua.framework.base.modules.pay.wxpay.sdk.WXPayUtil;
import com.feihua.framework.base.modules.role.dto.BaseRoleDto;
import com.feihua.framework.base.modules.user.api.ApiBaseUserPoService;
import com.feihua.framework.base.modules.user.dto.BaseUserDto;
import com.feihua.framework.constants.DictEnum;
import com.feihua.framework.message.api.MessageSendHelper;
import com.feihua.framework.message.dto.MessageSendForUserParamsDto;
import com.feihua.framework.rest.ResponseJsonRender;
import com.feihua.framework.rest.interceptor.RepeatFormValidator;
import com.feihua.framework.rest.modules.common.mvc.BaseController;
import com.feihua.utils.calendar.CalendarUtils;
import com.feihua.utils.http.httpServletRequest.RequestUtils;
import com.feihua.utils.http.httpServletResponse.ResponseCode;
import com.wwd.Constants;
import com.wwd.service.modules.wwd.api.ApiWwdActivityService;
import com.wwd.service.modules.wwd.api.ApiWwdParticipateService;
import com.wwd.service.modules.wwd.api.ApiWwdUserPoService;
import com.wwd.service.modules.wwd.dto.*;
import com.wwd.service.modules.wwd.po.WwdActivity;
import com.wwd.service.modules.wwd.po.WwdParticipate;
import feihua.jdbc.api.pojo.BasePo;
import feihua.jdbc.api.pojo.PageAndOrderbyParamDto;
import feihua.jdbc.api.pojo.PageResultDto;
import feihua.jdbc.api.utils.OrderbyUtils;
import feihua.jdbc.api.utils.PageUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.wwd.service.modules.wwd.api.ApiWwdActivityOrderService;
import com.wwd.web.modules.wwd.dto.AddWwdActivityOrder;
import com.wwd.web.modules.wwd.dto.UpdateWwdActivityOrder;
import com.wwd.service.modules.wwd.po.WwdActivityOrder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 汪汪队活动订单管理
 * Created by yangwei
 */
@RestController
@RequestMapping("/wwd")
public class WwdActivityOrderController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(WwdActivityOrderController.class);

    @Autowired
    private ApiWwdActivityOrderService apiWwdActivityOrderService;

    @Autowired
    private ApiBaseUserPoService apiBaseUserPoService;

    @Autowired
    private ApiWwdActivityService apiWwdActivityService;

    @Autowired
    private ApiWwdParticipateService apiWwdParticipateService;
    @Autowired
    private ApiWwdUserPoService apiWwdUserPoService;
    @Autowired
    private ApiPayService<WxUnifiedOrderForInnerParam> apiWxPayForInnerService;

    @Autowired
    private MessageSendHelper messageSendHelper;
    /**
     * 单资源，添加
     * @param dto
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:activity:order:add")
    @RequestMapping(value = "/activity/order",method = RequestMethod.POST)
    public ResponseEntity add(AddWwdActivityOrder dto){
        logger.info("添加汪汪队活动订单开始");
        logger.info("当前登录用户id:{}",getLoginUser().getId());
        ResponseJsonRender resultData=new ResponseJsonRender();
        // 表单值设置
        WwdActivityOrder basePo = new WwdActivityOrder();
        basePo.setId(dto.getId());
        basePo.setOrderNo(dto.getOrderNo());
        basePo.setActivityTitle(dto.getActivityTitle());
        basePo.setActivityUrl(dto.getActivityUrl());
        basePo.setParticipateId(dto.getParticipateId());
        basePo.setUserId(dto.getUserId());
        basePo.setPayType(dto.getPayType());
        basePo.setPayStatus(dto.getPayStatus());
        basePo.setRemarks(dto.getRemarks());
        basePo.setDataUserId(dto.getDataUserId());
        basePo.setDataOfficeId(dto.getDataOfficeId());
        basePo.setDataType(dto.getDataType());
        basePo.setDataAreaId(dto.getDataAreaId());
        basePo.setDelFlag(dto.getDelFlag());
        basePo.setCreateAt(dto.getCreateAt());
        basePo.setCreateBy(dto.getCreateBy());
        basePo.setUpdateAt(dto.getUpdateAt());
        basePo.setUpdateBy(dto.getUpdateBy());

        basePo = apiWwdActivityOrderService.preInsert(basePo,getLoginUser().getId());
        WwdActivityOrderDto r = apiWwdActivityOrderService.insert(basePo);
        if (r == null) {
            // 添加失败
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}",resultData.getCode(),resultData.getMsg());
            logger.info("添加汪汪队活动订单结束，失败");
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }else{
            // 添加成功，返回添加的数据
            resultData.setData(r);
            logger.info("添加汪汪队活动订单id:{}",r.getId());
            logger.info("添加汪汪队活动订单结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }

    /**
     * 单资源，删除
     * @param id
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:activity:order:delete")
    @RequestMapping(value = "/activity/order/{id}",method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id){
        logger.info("删除汪汪队活动订单开始");
        logger.info("用户id:{}",getLoginUser().getId());
        logger.info("汪汪队活动订单id:{}",id);
        ResponseJsonRender resultData=new ResponseJsonRender();

            int r = apiWwdActivityOrderService.deleteFlagByPrimaryKeyWithUpdateUser(id,getLoginUser().getId());
            if (r <= 0) {
                // 删除失败，可能没有找到资源
                resultData.setCode(ResponseCode.E404_100001.getCode());
                resultData.setMsg(ResponseCode.E404_100001.getMsg());
                logger.info("code:{},msg:{}",resultData.getCode(),resultData.getMsg());
                logger.info("删除汪汪队活动订单结束，失败");
                return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
            }else{
                // 删除成功
                logger.info("删除的汪汪队活动订单id:{}",id);
                logger.info("删除汪汪队活动订单结束，成功");
                return new ResponseEntity(resultData,HttpStatus.NO_CONTENT);
            }
    }

    /**
     * 单资源，更新
     * @param id
     * @param dto
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:activity:order:update")
    @RequestMapping(value = "/activity/order/{id}",method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable String id, UpdateWwdActivityOrder dto){
        logger.info("更新汪汪队活动订单开始");
        logger.info("当前登录用户id:{}",getLoginUser().getId());
        logger.info("汪汪队活动订单id:{}",id);
        ResponseJsonRender resultData=new ResponseJsonRender();
        // 表单值设置
        WwdActivityOrder basePo = new WwdActivityOrder();
        // id
        basePo.setId(id);
        basePo.setId(dto.getId());
        basePo.setOrderNo(dto.getOrderNo());
        basePo.setActivityTitle(dto.getActivityTitle());
        basePo.setActivityUrl(dto.getActivityUrl());
        basePo.setParticipateId(dto.getParticipateId());
        basePo.setUserId(dto.getUserId());
        basePo.setPayType(dto.getPayType());
        basePo.setPayStatus(dto.getPayStatus());
        basePo.setRemarks(dto.getRemarks());
        basePo.setDataUserId(dto.getDataUserId());
        basePo.setDataOfficeId(dto.getDataOfficeId());
        basePo.setDataType(dto.getDataType());
        basePo.setDataAreaId(dto.getDataAreaId());
        basePo.setDelFlag(dto.getDelFlag());
        basePo.setCreateAt(dto.getCreateAt());
        basePo.setCreateBy(dto.getCreateBy());
        basePo.setUpdateAt(dto.getUpdateAt());
        basePo.setUpdateBy(dto.getUpdateBy());

        // 用条件更新，乐观锁机制
        WwdActivityOrder basePoCondition = new WwdActivityOrder();
        basePoCondition.setId(id);
        basePoCondition.setDelFlag(BasePo.YesNo.N.name());
        basePoCondition.setUpdateAt(dto.getUpdateTime());
        basePo = apiWwdActivityOrderService.preUpdate(basePo,getLoginUser().getId());
        int r = apiWwdActivityOrderService.updateSelective(basePo,basePoCondition);
        if (r <= 0) {
            // 更新失败，资源不存在
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}",resultData.getCode(),resultData.getMsg());
            logger.info("更新汪汪队活动订单结束，失败");
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }else{
            // 更新成功，已被成功创建
            logger.info("更新的汪汪队活动订单id:{}",id);
            logger.info("更新汪汪队活动订单结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }


    /**
     * 单资源，更新
     * @param id
     * @param status
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:activity:order:edit:status")
    @RequestMapping(value = "/activity/order/{id}/edit/{status}",method = RequestMethod.PUT)
    public ResponseEntity updateStatus(@PathVariable String id, @PathVariable String status){
        logger.info("更新汪汪队活动订单状态开始");
        logger.info("当前登录用户id:{}",getLoginUser().getId());
        logger.info("汪汪队活动订单状态id:{}",id);
        ResponseJsonRender resultData=new ResponseJsonRender();
        // 表单值设置
        WwdActivityOrder wwdActivityOrder = apiWwdActivityOrderService.selectByPrimaryKeySimple(id);
        int r = 0;
        if(StringUtils.isNotEmpty(status) && !wwdActivityOrder.getPayStatus().equals(status)
                && Constants.PayStatus.contains(status)){
            WwdActivityOrder basePo = new WwdActivityOrder();
            // id
            basePo.setId(id);
            basePo.setPayStatus(status);

            // 用条件更新，乐观锁机制
            WwdActivityOrder basePoCondition = new WwdActivityOrder();
            basePoCondition.setId(id);
            basePoCondition.setDelFlag(BasePo.YesNo.N.name());
            basePoCondition.setUpdateAt(wwdActivityOrder.getUpdateAt());
            basePo = apiWwdActivityOrderService.preUpdate(basePo,getLoginUser().getId());
            r = apiWwdActivityOrderService.updateSelective(basePo,basePoCondition);
        }
        if (r <= 0) {
            // 更新失败，资源不存在
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}",resultData.getCode(),resultData.getMsg());
            logger.info("更新汪汪队活动订单状态结束，失败");
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }else{
            // 更新成功，已被成功创建
            logger.info("更新的汪汪队活动订单状态id:{}",id);
            logger.info("更新汪汪队活动订单状态结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }

    /**
     * 单资源，获取id汪汪队活动订单
     * @param id
     * @return
     */
    @RequiresPermissions("wwd:activity:order:getById")
    @RequestMapping(value = "/activity/order/{id}",method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable String id){

        ResponseJsonRender resultData=new ResponseJsonRender();
        WwdActivityOrderDto baseDataScopeDto = apiWwdActivityOrderService.selectByPrimaryKey(id,false);
        if(baseDataScopeDto != null){
            resultData.setData(baseDataScopeDto);
            return new ResponseEntity(resultData, HttpStatus.OK);
        }else{
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 复数资源，搜索汪汪队活动订单
     * @param dto
     * @return
     */
    @RequiresPermissions("wwd:activity:order:search")
    @RequestMapping(value = "/activity/orders",method = RequestMethod.GET)
    public ResponseEntity search(SearchWwdActivityOrdersConditionDto dto){

        ResponseJsonRender resultData=new ResponseJsonRender();
        PageAndOrderbyParamDto pageAndOrderbyParamDto = new PageAndOrderbyParamDto(PageUtils.getPageFromThreadLocal(), OrderbyUtils.getOrderbyFromThreadLocal());
        // 设置当前登录用户id
        dto.setCurrentUserId(getLoginUser().getId());
        dto.setCurrentRoleId(((BaseRoleDto) getLoginUser().getRole()).getId());
        PageResultDto<WwdActivityOrderDto> list = apiWwdActivityOrderService.searchWwdActivityOrdersDsf(dto,pageAndOrderbyParamDto);
        List<WwdActivityOrderDto> data = list.getData();
        if (data != null && data.size() > 0) {
            for (WwdActivityOrderDto wwdActivityOrderDto : data) {
                BaseUserDto baseUserDto = apiBaseUserPoService.selectByPrimaryKey(wwdActivityOrderDto.getUserId());
                wwdActivityOrderDto.setBaseUserDto(baseUserDto);
            }
        }
        if(CollectionUtils.isNotEmpty(list.getData())){
            resultData.setData(list.getData());
            resultData.setPage(list.getPage());
            return new ResponseEntity(resultData, HttpStatus.OK);
        }else{
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }
    }


    /**
     * 报名并支付
     * 说明，参与信息和活动订单信息表中未支付和已支付状态一个人只能有一条数据，其它状态可以有多条
     * @param participateId 参与ID
     * @param which 公众号类型
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:activity:order:order")
    @RequestMapping(value = "/activity/participate/order", method = RequestMethod.POST)
    public ResponseEntity order( String participateId,String which,String desc,String notifyUrl) {

        ResponseJsonRender resultData = new ResponseJsonRender();


        WwdParticipate wwdParticipate = apiWwdParticipateService.selectByPrimaryKeySimple(participateId);
        WwdActivityDto wwdActivityDto = apiWwdActivityService.selectByPrimaryKey(wwdParticipate.getWwdActivityId());
        WwdUserDto wwdUserDto = apiWwdUserPoService.selectByUserId(getLoginUser().getId());
        String fee = null;
        if(DictEnum.Gender.male.name().equals(wwdUserDto.getGender())){
            fee = wwdActivityDto.getMalePrice().toString();
        }else if(DictEnum.Gender.female.name().equals(wwdUserDto.getGender())){
            fee = wwdActivityDto.getFemalePrice().toString();
        }
        if(StringUtils.isEmpty(fee)){
            resultData.setCode("fee_no");
            resultData.setMsg("fee_no,make sure gender exist");
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }
        // 判断是否已满
        if(Constants.HeadCountRule.unlimited.name().equals(wwdActivityDto.getHeadcountRule())){
            if (!new Integer(0).equals(wwdActivityDto.getHeadcount())) {
                // 查询报名人数
                int headcount = apiWwdParticipateService.selectCountPaidParticipate(wwdActivityDto.getId());
                // 报名人数已满
                if( headcount >= wwdActivityDto.getHeadcount()){
                    resultData.setCode("headcount=enough");
                    resultData.setMsg("headcount=enough");
                    return new ResponseEntity(resultData,HttpStatus.CONFLICT);
                }
            }
        }else if(Constants.HeadCountRule.custom.name().equals(wwdActivityDto.getHeadcountRule())){
            int headcountSex = apiWwdParticipateService.selectCountPaidParticipate(wwdActivityDto.getId(),wwdUserDto.getGender());
            int headcountGender = 0;
            if(DictEnum.Gender.female.name().equals(wwdUserDto.getGender())){
                headcountGender = wwdActivityDto.getHeadcountFemale();
            }else if(DictEnum.Gender.male.name().equals(wwdUserDto.getGender())){
                headcountGender = wwdActivityDto.getHeadcountMale();

            }
            // 报名人数已满
            if( headcountSex >= headcountGender){
                resultData.setCode("headcount=enough");
                resultData.setMsg("headcount=enough");
                return new ResponseEntity(resultData,HttpStatus.CONFLICT);
            }
        }

        // 判断订单是否已支付
        // 查询订单
        WwdActivityOrder wwdActivityOrder = apiWwdActivityOrderService.selectByParticipateIdAndUserId(participateId,getLoginUserId());
        // 如果订单信息不存在，添加一条
        if(wwdActivityOrder == null){
            WwdActivityOrder wwdActivityOrder1 = new WwdActivityOrder();
            wwdActivityOrder1.setUserId(getLoginUserId());
            wwdActivityOrder1.setParticipateId(participateId);
            wwdActivityOrder1.setActivityTitle(wwdActivityDto.getTitle());
            wwdActivityOrder1.setActivityUrl(wwdActivityDto.getTitleUrl());
            wwdActivityOrder1.setPayStatus(Constants.PayStatus.no_pay.name());
            wwdActivityOrder1.setPayType(Constants.PayType.online_pay.name());
            wwdActivityOrder1.setRemarks(fee);
            wwdActivityOrder1.setPrice(fee);
            wwdActivityOrder1.setOrderNo("HD" + CalendarUtils.dateToString(new Date(), CalendarUtils.DateStyle.YYYYMMDDHHMMSS) + RandomStringUtils.randomNumeric(6));
            wwdActivityOrder = apiWwdActivityOrderService.preInsert(wwdActivityOrder1,getLoginUserId());
            wwdActivityOrder = apiWwdActivityOrderService.insertSimple(wwdActivityOrder);
        }

        WxUnifiedOrderForInnerParam wxUnifiedOrderForInnerParam = new WxUnifiedOrderForInnerParam();
        wxUnifiedOrderForInnerParam.setBody(wwdActivityDto.getTitle());
        wxUnifiedOrderForInnerParam.setOutTradeNo(wwdActivityOrder.getOrderNo());
        wxUnifiedOrderForInnerParam.setSpbillCreateIp(RequestUtils.getRemoteAddr(RequestUtils.getRequest()));
        //附加参数
        wxUnifiedOrderForInnerParam.setAttach(wwdActivityDto.getId());
        String openid = (String) SecurityUtils.getSubject().getSession().getAttribute("publickplatform_openid_"+which);
        // 如果没有openid只能重新授权
        if(StringUtils.isEmpty(openid)){
            resultData.setCode("wxopenid_no");
            resultData.setMsg("wxopenid_no");
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }
        wxUnifiedOrderForInnerParam.setOpenid(openid);

        wxUnifiedOrderForInnerParam.setTotalFee(fee);
        wxUnifiedOrderForInnerParam.setNotifyUrl(notifyUrl);
        wxUnifiedOrderForInnerParam.setDetail(StringUtils.trimToEmpty(desc));
        Map<String,String> resData = apiWxPayForInnerService.unifiedOrder(wxUnifiedOrderForInnerParam,which);
        // 查询订单信息

        // 查询是否已支付
        return returnDto(resData,resultData);
    }


    /**
     * 线下支付报名生成订单
     * 说明，参与信息和活动订单信息表中未支付和已支付状态一个人只能有一条数据，其它状态可以有多条
     * @param participateId 参与ID
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:activity:order:offlineOrder")
    @RequestMapping(value = "/activity/participate/offlineOrder", method = RequestMethod.POST)
    public ResponseEntity offlineOrder( String participateId) {

        ResponseJsonRender resultData = new ResponseJsonRender();


        WwdParticipate wwdParticipate = apiWwdParticipateService.selectByPrimaryKeySimple(participateId);
        WwdActivityDto wwdActivityDto = apiWwdActivityService.selectByPrimaryKey(wwdParticipate.getWwdActivityId());
        WwdUserDto wwdUserDto = apiWwdUserPoService.selectByUserId(getLoginUser().getId());
        String fee = null;
        if(DictEnum.Gender.male.name().equals(wwdUserDto.getGender())){
            fee = wwdActivityDto.getMalePrice().toString();
        }else if(DictEnum.Gender.female.name().equals(wwdUserDto.getGender())){
            fee = wwdActivityDto.getFemalePrice().toString();
        }
        if(StringUtils.isEmpty(fee)){
            resultData.setCode("fee_no");
            resultData.setMsg("fee_no,make sure gender exist");
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }
        // 判断是否已满
        if(Constants.HeadCountRule.unlimited.name().equals(wwdActivityDto.getHeadcountRule())){
            if (!new Integer(0).equals(wwdActivityDto.getHeadcount())) {
                // 查询报名人数
                int headcount = apiWwdParticipateService.selectCountPaidParticipate(wwdActivityDto.getId());
                // 报名人数已满
                if( headcount >= wwdActivityDto.getHeadcount()){
                    resultData.setCode("headcount=enough");
                    resultData.setMsg("headcount=enough");
                    return new ResponseEntity(resultData,HttpStatus.CONFLICT);
                }
            }
        }else if(Constants.HeadCountRule.custom.name().equals(wwdActivityDto.getHeadcountRule())){
            int headcountSex = apiWwdParticipateService.selectCountPaidParticipate(wwdActivityDto.getId(),wwdUserDto.getGender());
            int headcountGender = 0;
            if(DictEnum.Gender.female.name().equals(wwdUserDto.getGender())){
                headcountGender = wwdActivityDto.getHeadcountFemale();
            }else if(DictEnum.Gender.male.name().equals(wwdUserDto.getGender())){
                headcountGender = wwdActivityDto.getHeadcountMale();

            }
            // 报名人数已满
            if( headcountSex >= headcountGender){
                resultData.setCode("headcount=enough");
                resultData.setMsg("headcount=enough");
                return new ResponseEntity(resultData,HttpStatus.CONFLICT);
            }
        }

        // 判断订单是否已支付
        // 查询订单
        WwdActivityOrder wwdActivityOrder = apiWwdActivityOrderService.selectByParticipateIdAndUserId(participateId,getLoginUserId());
        // 如果订单信息不存在，添加一条
        if(wwdActivityOrder == null){
            WwdActivityOrder wwdActivityOrder1 = new WwdActivityOrder();
            wwdActivityOrder1.setUserId(getLoginUserId());
            wwdActivityOrder1.setParticipateId(participateId);
            wwdActivityOrder1.setActivityTitle(wwdActivityDto.getTitle());
            wwdActivityOrder1.setActivityUrl(wwdActivityDto.getTitleUrl());
            wwdActivityOrder1.setPayStatus(Constants.PayStatus.no_pay.name());
            wwdActivityOrder1.setPayType(Constants.PayType.offline_pay.name());
            wwdActivityOrder1.setRemarks(fee);
            wwdActivityOrder1.setPrice(fee);
            wwdActivityOrder1.setOrderNo("HD" + CalendarUtils.dateToString(new Date(), CalendarUtils.DateStyle.YYYYMMDDHHMMSS) + RandomStringUtils.randomNumeric(6));
            wwdActivityOrder = apiWwdActivityOrderService.preInsert(wwdActivityOrder1,getLoginUserId());
            wwdActivityOrder = apiWwdActivityOrderService.insertSimple(wwdActivityOrder);
        }
        WwdParticipate wwdParticipateUpdate = new WwdParticipate();
        wwdParticipateUpdate.setId(wwdParticipate.getId());
        wwdParticipateUpdate.setPayStatus(Constants.PayStatus.offline_pay.name());
        apiWwdParticipateService.updateByPrimaryKeySelective(wwdParticipateUpdate);

        checkActivityStatus(wwdActivityDto, wwdParticipate);

        // 发送消息
        MessageSendForUserParamsDto messageSendForUserParamsDto = new MessageSendForUserParamsDto();
        messageSendForUserParamsDto.setClientCode("h5");
        messageSendForUserParamsDto.setTemplateCode("wwd_activity_participate_success");
        messageSendForUserParamsDto.setUserId(getLoginUserId());
        messageSendForUserParamsDto.setCurrentUserId(getLoginUserId());
        messageSendForUserParamsDto.setCurrentRoleId(getLoginUserRole().getId());
        Map<String,String> templateParams = new HashMap<>();
        templateParams.put("activity_first","您好，你已成功报名了活动");
        templateParams.put("activity_name",wwdActivityDto.getTitle());
        templateParams.put("activity_time",CalendarUtils.dateToString(wwdActivityDto.getStartTime(), CalendarUtils.DateStyle.YYYY_MM_DD_HH_MM_SS));
        templateParams.put("activity_address",wwdActivityDto.getAddr());
        templateParams.put("activity_remark","感谢您的参与");
        messageSendForUserParamsDto.setTemplateParams(templateParams);
        messageSendHelper.messageSendForUser(messageSendForUserParamsDto);
        // 查询是否已支付
        return returnDto(wwdActivityOrder,resultData);
    }

    /**
     * 报名完处理活动状态
     * @param wwdActivityDto
     * @param wwdParticipateDb
     */
    private void checkActivityStatus(WwdActivityDto wwdActivityDto, WwdParticipate wwdParticipateDb) {
        if (Constants.HeadCountRule.unlimited.name().equals(wwdActivityDto.getHeadcountRule())) {
            if (!new Integer(0).equals(wwdActivityDto.getHeadcount())) {
                // 修改活动是否已满状态
                // 查询报名人数
                int headcount = apiWwdParticipateService.selectCountPaidParticipate(wwdParticipateDb.getWwdActivityId());
                // 报名人数已满
                if (headcount >= wwdActivityDto.getHeadcount()) {
                    WwdActivity wwdActivity = new WwdActivity();
                    wwdActivity.setId(wwdActivityDto.getId());
                    wwdActivity.setStatus(Constants.ActivityStatus.QUOTA_FULL.getCode());
                    apiWwdActivityService.updateByPrimaryKeySelective(wwdActivity);
                }
            }
        } else if (Constants.HeadCountRule.custom.name().equals(wwdActivityDto.getHeadcountRule())) {
            int headcount_female = apiWwdParticipateService.selectCountPaidParticipate(wwdParticipateDb.getWwdActivityId(), DictEnum.Gender.female.name());
            int headcount_male = apiWwdParticipateService.selectCountPaidParticipate(wwdParticipateDb.getWwdActivityId(), DictEnum.Gender.male.name());
            // 报名人数已满
            if (headcount_female >= wwdActivityDto.getHeadcountFemale() && headcount_male >= wwdActivityDto.getHeadcountMale()) {
                WwdActivity wwdActivity = new WwdActivity();
                wwdActivity.setId(wwdActivityDto.getId());
                wwdActivity.setStatus(Constants.ActivityStatus.QUOTA_FULL.getCode());
                apiWwdActivityService.updateByPrimaryKeySelective(wwdActivity);
            }
        }
    }

    /**
     * 查询当前登录用户订单状态
     * @param id
     * @return
     */
    @RequiresPermissions("wwd:activity:participate:get")
    @RequestMapping(value = "/activity/{id}/participate", method = RequestMethod.GET)
    public ResponseEntity orderGet(@PathVariable String id) {
        ResponseJsonRender resultData = new ResponseJsonRender();
        // 查询活动信息
        WwdActivityDto wwdActivityDto = apiWwdActivityService.selectByPrimaryKey(id);
        if (wwdActivityDto == null) {
            return super.returnDto(null, resultData);
        }
        WwdUserDto wwdUserDto = apiWwdUserPoService.selectByUserId(getLoginUser().getId());
        // 查询是否已报名
        // 查询参与信息
        WwdParticipate wwdParticipate = new WwdParticipate();
        List<WwdParticipate> wwdParticipates = apiWwdParticipateService.selectByActivityIdAndWwdUserId(id, wwdUserDto.getId());
        if (wwdParticipates != null) {
            for (WwdParticipate participate : wwdParticipates) {
                if(Constants.PayStatus.offline_pay.name().equals(participate.getPayStatus()) ||Constants.PayStatus.paid.name().equals(participate.getPayStatus()) || Constants.PayStatus.no_pay.name().equals(participate.getPayStatus())){
                    wwdParticipate = participate;
                    break;
                }
            }
        }

        return returnDto(wwdParticipate,resultData);
    }
    // 完成支付
    @RequestMapping(value = "/activity/participate/order/success", produces = {"application/xml;charset=UTF-8"})
    public ResponseEntity unifiedOrder(@RequestBody String xml){
        try {
           Map<String,String> requestData =  WXPayUtil.xmlToMap(xml);
           // 订单成功修改订单状态
           if("SUCCESS".equals(requestData.get("result_code"))){

               // 验证签名
               String sign = requestData.get("sign");
               MyWxPayConfig myWxPayConfig = new MyWxPayConfig();
               String newsign = WXPayUtil.generateSignature(requestData, myWxPayConfig.getKey(), WXPayConstants.SignType.HMACSHA256); //签名
               if(newsign.equals(sign)){

                   // 设置订单已完成，
                   WwdActivityOrder wwdActivityOrder = new WwdActivityOrder();
                   wwdActivityOrder.setPayStatus(Constants.PayStatus.paid.name());
                   WwdActivityOrder wwdActivityOrderCondition = new WwdActivityOrder();
                   wwdActivityOrderCondition.setOrderNo(requestData.get("out_trade_no"));
                   wwdActivityOrderCondition.setDelFlag(BasePo.YesNo.N.name());
                   apiWwdActivityOrderService.updateSelective(wwdActivityOrder,wwdActivityOrderCondition);
                   // 设置参与者支付结果已支付
                   WwdActivityOrder wwdActivityOrderDb = apiWwdActivityOrderService.selectByOrderNo(requestData.get("out_trade_no"));
                   WwdParticipate wwdParticipate = new WwdParticipate();
                   wwdParticipate.setPayStatus(Constants.PayStatus.paid.name());
                   wwdParticipate.setId(wwdActivityOrderDb.getParticipateId());
                   apiWwdParticipateService.updateByPrimaryKeySelective(wwdParticipate);

                   WwdParticipate wwdParticipateDb = apiWwdParticipateService.selectByPrimaryKeySimple(wwdActivityOrderDb.getParticipateId());
                   WwdActivityDto wwdActivityDto = apiWwdActivityService.selectByPrimaryKey(wwdParticipateDb.getWwdActivityId());
                   //
                   checkActivityStatus(wwdActivityDto, wwdParticipateDb);

                   // 发送消息

               }else {
                   logger.error("sign error sign={},newsign={}",sign,newsign);
               }

           }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("return_code","SUCCESS");
        resultMap.put("return_msg","OK");
        String returnXml = null;
        try {
            returnXml = WXPayUtil.mapToXml(resultMap);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return new ResponseEntity(returnXml, HttpStatus.OK);

    }
}
