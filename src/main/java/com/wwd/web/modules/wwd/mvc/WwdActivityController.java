package com.wwd.web.modules.wwd.mvc;

import com.feihua.framework.base.modules.role.dto.BaseRoleDto;
import com.feihua.framework.rest.ResponseJsonRender;
import com.feihua.framework.rest.interceptor.RepeatFormValidator;
import com.feihua.framework.rest.modules.common.mvc.BaseController;
import com.feihua.utils.http.httpServletResponse.ResponseCode;
import com.wwd.Constants;
import com.wwd.service.modules.wwd.api.ApiWwdActivityService;
import com.wwd.service.modules.wwd.api.ApiWwdParticipateService;
import com.wwd.service.modules.wwd.api.ApiWwdUserPoService;
import com.wwd.service.modules.wwd.dto.*;
import com.wwd.service.modules.wwd.po.WwdActivity;
import com.wwd.web.modules.wwd.dto.AddWwdActivity;
import com.wwd.web.modules.wwd.dto.UpdateWwdActivity;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 汪汪队活动管理
 * Created by yangwei
 */
@RestController
@RequestMapping("/wwd")
public class WwdActivityController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(WwdActivityController.class);

    @Autowired
    private ApiWwdActivityService apiWwdActivityService;

    @Autowired
    private ApiWwdParticipateService apiWwdParticipateService;

    @Autowired
    private ApiWwdUserPoService apiWwdUserPoService;

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
        basePo.setHeadcountDesc(dto.getHeadcountDesc());
        basePo.setIntroduced(dto.getIntroduced());
        basePo.setPayRule(dto.getPayRule());
        basePo.setPrice(dto.getPrice());
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
        basePo.setHeadcountDesc(dto.getHeadcountDesc());
        basePo.setIntroduced(dto.getIntroduced());
        basePo.setPayRule(dto.getPayRule());
        basePo.setPrice(dto.getPrice());
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
        basePo.setHeadcountDesc(dto.getHeadcountDesc());
        basePo.setIntroduced(dto.getIntroduced());
        basePo.setPayRule(dto.getPayRule());
        basePo.setPrice(dto.getPrice());
        basePo.setMalePrice(dto.getMalePrice());
        basePo.setFemalePrice(dto.getFemalePrice());
        basePo.setActivityStatement(dto.getActivityStatement());
        basePo.setDataUserId(dto.getDataUserId());
        basePo.setDataOfficeId(dto.getDataOfficeId());
        basePo.setDataType(dto.getDataType());
        basePo.setDataAreaId(dto.getDataAreaId());
        basePo.setContent(dto.getContent());

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
     * 单资源，获取id汪汪队活动
     *
     * @param id
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:activity:getById")
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable String id) {

        ResponseJsonRender resultData = new ResponseJsonRender();
        WwdActivityDto baseDataScopeDto = apiWwdActivityService.selectByPrimaryKey(id, false);
        if (baseDataScopeDto != null) {
            resultData.setData(baseDataScopeDto);
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
    @RepeatFormValidator
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
                query.setPayStatus(Constants.PayStatus.paid.name());
                query.setWwdActivityId(activityDto.getId());
                final PageResultDto<WwdParticipateDto> wwdParticipateDtoPageResultDto = apiWwdParticipateService.searchWwdParticipatesDsf(query, null);
                activityDto.setWwdParticipateDtos(wwdParticipateDtoPageResultDto.getData());
            }
        }
        if (CollectionUtils.isNotEmpty(list.getData())) {
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
    @RepeatFormValidator
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
                query.setPayStatus(Constants.PayStatus.paid.name());
                final PageResultDto<WwdParticipateDto> wwdParticipateDtoPageResultDto = apiWwdParticipateService.searchWwdParticipatesDsf(query, null);
                activityDto.setWwdParticipateDtos(wwdParticipateDtoPageResultDto.getData());
            }
        }
        if (CollectionUtils.isNotEmpty(list.getData())) {
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
