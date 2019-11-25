package com.wwd.web.modules.wwd.mvc;

import com.feihua.framework.base.modules.role.dto.BaseRoleDto;
import com.feihua.framework.rest.ResponseJsonRender;
import com.feihua.framework.rest.interceptor.RepeatFormValidator;
import com.feihua.framework.rest.mvc.SuperController;
import com.feihua.utils.http.httpServletResponse.ResponseCode;
import com.wwd.Constants;
import com.wwd.service.modules.wwd.api.ApiWwdActivityService;
import com.wwd.service.modules.wwd.dto.WwdActivityDto;
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
import com.wwd.service.modules.wwd.dto.WwdActivityUserMutualElectionDto;
import com.wwd.service.modules.wwd.dto.SearchWwdActivityUserMutualElectionsConditionDto;
import com.wwd.service.modules.wwd.api.ApiWwdActivityUserMutualElectionPoService;
import com.wwd.web.modules.wwd.dto.AddWwdActivityUserMutualElectionFormDto;
import com.wwd.web.modules.wwd.dto.UpdateWwdActivityUserMutualElectionFormDto;
import com.wwd.service.modules.wwd.po.WwdActivityUserMutualElectionPo;

import java.util.List;
import java.util.Map;

/**
 * 活动用户互选
 * Created by yangwei
 */
@RestController
@RequestMapping("/wwd")
public class WwdActivityUserMutualElectionController extends SuperController {

    private static Logger logger = LoggerFactory.getLogger(WwdActivityUserMutualElectionController.class);

    @Autowired
    private ApiWwdActivityUserMutualElectionPoService apiWwdActivityUserMutualElectionPoService;
    @Autowired
    private ApiWwdActivityService apiWwdActivityService;

    /**
     * 单资源，添加
     * @param dto
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:activity:userMutualElection:add")
    @RequestMapping(value = "/activityMutualElection/userMutualElection",method = RequestMethod.POST)
    public ResponseEntity add(AddWwdActivityUserMutualElectionFormDto dto){
        logger.info("添加活动用户互选开始");
        logger.info("当前登录用户id:{}",getLoginUser().getId());
        ResponseJsonRender resultData = new ResponseJsonRender();

        WwdActivityDto wwdActivityDto = apiWwdActivityService.selectByPrimaryKey(dto.getActivityId());
        if (wwdActivityDto == null || !Constants.MutualElectionStatus.ongoing.name().equals(wwdActivityDto.getMutualElectionStatus())) {
            return returnDto(null,resultData);
        }


        // 表单值设置
        WwdActivityUserMutualElectionPo basePo = new WwdActivityUserMutualElectionPo();
        basePo.setActivityId(dto.getActivityId());
        basePo.setWwdUserId(dto.getWwdUserId());
        basePo.setSelectedWwdUserId(dto.getSelectedWwdUserId());
        basePo.setLevel(dto.getLevel());

        basePo = apiWwdActivityUserMutualElectionPoService.preInsert(basePo,getLoginUser().getId());
        WwdActivityUserMutualElectionDto r = apiWwdActivityUserMutualElectionPoService.insert(basePo);
        if (r == null) {
            // 添加失败
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}",resultData.getCode(),resultData.getMsg());
            logger.info("添加活动用户互选结束，失败");
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }else{
            // 添加成功，返回添加的数据
            resultData.setData(r);
            logger.info("添加活动用户互选id:{}",r.getId());
            logger.info("添加活动用户互选结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }

    /**
     * 单资源，删除
     * @param id
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:activity:userMutualElection:delete")
    @RequestMapping(value = "/activityMutualElection/userMutualElection/{id}",method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id){
        logger.info("删除活动用户互选开始");
        logger.info("用户id:{}",getLoginUser().getId());
        logger.info("活动用户互选id:{}",id);
        ResponseJsonRender resultData = new ResponseJsonRender();

            int r = apiWwdActivityUserMutualElectionPoService.deleteFlagByPrimaryKeyWithUpdateUser(id,getLoginUser().getId());
            if (r <= 0) {
                // 删除失败，可能没有找到资源
                resultData.setCode(ResponseCode.E404_100001.getCode());
                resultData.setMsg(ResponseCode.E404_100001.getMsg());
                logger.info("code:{},msg:{}",resultData.getCode(),resultData.getMsg());
                logger.info("删除活动用户互选结束，失败");
                return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
            }else{
                // 删除成功
                logger.info("删除的活动用户互选id:{}",id);
                logger.info("删除活动用户互选结束，成功");
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
    @RequiresPermissions("wwd:activity:userMutualElection:update")
    @RequestMapping(value = "/activityMutualElection/userMutualElection/{id}",method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable String id, UpdateWwdActivityUserMutualElectionFormDto dto){
        logger.info("更新活动用户互选开始");
        logger.info("当前登录用户id:{}",getLoginUser().getId());
        logger.info("活动用户互选id:{}",id);
        ResponseJsonRender resultData = new ResponseJsonRender();

        WwdActivityDto wwdActivityDto = apiWwdActivityService.selectByPrimaryKey(dto.getActivityId());
        if (wwdActivityDto == null || !Constants.MutualElectionStatus.ongoing.name().equals(wwdActivityDto.getMutualElectionStatus())) {
            return returnDto(null,resultData);
        }

        // 表单值设置
        WwdActivityUserMutualElectionPo basePo = new WwdActivityUserMutualElectionPo();
        // id
        basePo.setId(id);
        basePo.setActivityId(dto.getActivityId());
        basePo.setWwdUserId(dto.getWwdUserId());
        basePo.setSelectedWwdUserId(dto.getSelectedWwdUserId());
        basePo.setLevel(dto.getLevel());
        // 用条件更新，乐观锁机制
        WwdActivityUserMutualElectionPo basePoCondition = new WwdActivityUserMutualElectionPo();
        basePoCondition.setId(id);
        basePoCondition.setDelFlag(BasePo.YesNo.N.name());
        basePoCondition.setUpdateAt(dto.getUpdateTime());
        basePo = apiWwdActivityUserMutualElectionPoService.preUpdate(basePo,getLoginUser().getId());
        int r = apiWwdActivityUserMutualElectionPoService.updateSelective(basePo,basePoCondition);
        if (r <= 0) {
            // 更新失败，资源不存在
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}",resultData.getCode(),resultData.getMsg());
            logger.info("更新活动用户互选结束，失败");
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }else{
            // 更新成功，已被成功创建
            logger.info("更新的活动用户互选id:{}",id);
            logger.info("更新活动用户互选结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }

    /**
     * 单资源，获取id活动用户互选
     * @param id
     * @return
     */
    @RequiresPermissions("wwd:activity:userMutualElection:getById")
    @RequestMapping(value = "/activityMutualElection/userMutualElection/{id}",method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable String id){

        ResponseJsonRender resultData = new ResponseJsonRender();
        WwdActivityUserMutualElectionDto baseDataScopeDto = apiWwdActivityUserMutualElectionPoService.selectByPrimaryKey(id,false);
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
     * 复数资源，搜索活动用户互选
     * @param dto
     * @return
     */
    @RequiresPermissions("wwd:activity:userMutualElection:search")
    @RequestMapping(value = "/activityMutualElection/userMutualElections",method = RequestMethod.GET)
    public ResponseEntity search(SearchWwdActivityUserMutualElectionsConditionDto dto){

        ResponseJsonRender resultData = new ResponseJsonRender();
        PageAndOrderbyParamDto pageAndOrderbyParamDto = new PageAndOrderbyParamDto(PageUtils.getPageFromThreadLocal(), OrderbyUtils.getOrderbyFromThreadLocal());
        // 设置当前登录用户id
        dto.setCurrentUserId(getLoginUser().getId());
        dto.setCurrentRoleId(((BaseRoleDto) getLoginUser().getRole()).getId());
        PageResultDto<WwdActivityUserMutualElectionDto> list = apiWwdActivityUserMutualElectionPoService.searchWwdActivityUserMutualElectionsDsf(dto,pageAndOrderbyParamDto);

        resultData.setPage(list.getPage());
        return returnList(list.getData(),resultData);

    }

    /**
     * 复数资源，搜索活动用户互选
     * @param activityId
     * @return
     */
    @RequiresPermissions("wwd:activity:userMutualElection:getSelectedResult")
    @RequestMapping(value = "/activityMutualElection/getSelectedResult/{activityId}",method = RequestMethod.GET)
    public ResponseEntity getSelectedResult(@PathVariable String activityId){
        ResponseJsonRender resultData = new ResponseJsonRender();
        List<Map<String, Object>> selectedResult = apiWwdActivityUserMutualElectionPoService.getSelectedResult(activityId);
        return returnList(selectedResult,resultData);
    }
}
