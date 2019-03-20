package com.wwd.web.modules.wwd.mvc;

import com.feihua.framework.base.modules.role.dto.BaseRoleDto;
import com.feihua.framework.base.modules.user.api.ApiBaseUserPoService;
import com.feihua.framework.base.modules.user.dto.BaseUserDto;
import com.feihua.framework.rest.ResponseJsonRender;
import com.feihua.framework.rest.interceptor.RepeatFormValidator;
import com.feihua.framework.rest.modules.common.mvc.BaseController;
import com.feihua.utils.http.httpServletResponse.ResponseCode;
import com.wwd.service.modules.wwd.dto.WwdParticipateDto;
import com.wwd.service.modules.wwd.dto.WwdUserDto;
import feihua.jdbc.api.pojo.BasePo;
import feihua.jdbc.api.pojo.PageAndOrderbyParamDto;
import feihua.jdbc.api.pojo.PageResultDto;
import feihua.jdbc.api.utils.OrderbyUtils;
import feihua.jdbc.api.utils.PageUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wwd.service.modules.wwd.dto.WwdActivityOrderDto;
import com.wwd.service.modules.wwd.dto.SearchWwdActivityOrdersConditionDto;
import com.wwd.service.modules.wwd.api.ApiWwdActivityOrderService;
import com.wwd.web.modules.wwd.dto.AddWwdActivityOrder;
import com.wwd.web.modules.wwd.dto.UpdateWwdActivityOrder;
import com.wwd.service.modules.wwd.po.WwdActivityOrder;

import java.util.List;

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
        basePo.setType(dto.getType());
        basePo.setStatus(dto.getStatus());
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
        basePo.setType(dto.getType());
        basePo.setStatus(dto.getStatus());
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
     * 单资源，获取id汪汪队活动订单
     * @param id
     * @return
     */
    @RepeatFormValidator
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
    @RepeatFormValidator
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
}
