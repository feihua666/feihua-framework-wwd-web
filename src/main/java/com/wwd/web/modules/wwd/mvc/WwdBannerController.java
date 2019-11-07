package com.wwd.web.modules.wwd.mvc;

import com.feihua.framework.base.modules.role.dto.BaseRoleDto;
import com.feihua.framework.rest.ResponseJsonRender;
import com.feihua.framework.rest.interceptor.RepeatFormValidator;
import com.feihua.framework.rest.modules.common.mvc.BaseController;
import com.feihua.utils.http.httpServletResponse.ResponseCode;
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
import com.wwd.service.modules.wwd.dto.WwdBannerDto;
import com.wwd.service.modules.wwd.dto.SearchWwdBannersConditionDto;
import com.wwd.service.modules.wwd.api.ApiWwdBannerService;
import com.wwd.web.modules.wwd.dto.AddWwdBanner;
import com.wwd.web.modules.wwd.dto.UpdateWwdBanner;
import com.wwd.service.modules.wwd.po.WwdBanner;
/**
 * 轮播图管理
 * Created by yangwei
 */
@RestController
@RequestMapping("/wwd")
public class WwdBannerController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(WwdBannerController.class);

    @Autowired
    private ApiWwdBannerService apiWwdBannerService;

    /**
     * 单资源，添加
     * @param dto
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:banner:add")
    @RequestMapping(value = "/banner",method = RequestMethod.POST)
    public ResponseEntity add(AddWwdBanner dto){
        logger.info("添加轮播图管理开始");
        logger.info("当前登录用户id:{}",getLoginUser().getId());
        ResponseJsonRender resultData=new ResponseJsonRender();
        // 表单值设置
        WwdBanner basePo = new WwdBanner();
        basePo.setId(dto.getId());
        basePo.setTitle(dto.getTitle());
        basePo.setTitleUrl(dto.getTitleUrl());
        basePo.setRedirectUrl(dto.getRedirectUrl());
        basePo.setType(dto.getType());
        basePo.setStatus(dto.getStatus());
        basePo.setDescp(dto.getDescp());
        basePo.setDataUserId(dto.getDataUserId());
        basePo.setDataOfficeId(dto.getDataOfficeId());
        basePo.setDataType(dto.getDataType());
        basePo.setDataAreaId(dto.getDataAreaId());
        basePo.setDelFlag(dto.getDelFlag());
        basePo.setCreateAt(dto.getCreateAt());
        basePo.setCreateBy(dto.getCreateBy());
        basePo.setUpdateAt(dto.getUpdateAt());
        basePo.setUpdateBy(dto.getUpdateBy());

        basePo = apiWwdBannerService.preInsert(basePo,getLoginUser().getId());
        WwdBannerDto r = apiWwdBannerService.insert(basePo);
        if (r == null) {
            // 添加失败
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}",resultData.getCode(),resultData.getMsg());
            logger.info("添加轮播图管理结束，失败");
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }else{
            // 添加成功，返回添加的数据
            resultData.setData(r);
            logger.info("添加轮播图管理id:{}",r.getId());
            logger.info("添加轮播图管理结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }

    /**
     * 单资源，删除
     * @param id
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:banner:delete")
    @RequestMapping(value = "/banner/{id}",method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id){
        logger.info("删除轮播图管理开始");
        logger.info("用户id:{}",getLoginUser().getId());
        logger.info("轮播图管理id:{}",id);
        ResponseJsonRender resultData=new ResponseJsonRender();

            int r = apiWwdBannerService.deleteFlagByPrimaryKeyWithUpdateUser(id,getLoginUser().getId());
            if (r <= 0) {
                // 删除失败，可能没有找到资源
                resultData.setCode(ResponseCode.E404_100001.getCode());
                resultData.setMsg(ResponseCode.E404_100001.getMsg());
                logger.info("code:{},msg:{}",resultData.getCode(),resultData.getMsg());
                logger.info("删除轮播图管理结束，失败");
                return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
            }else{
                // 删除成功
                logger.info("删除的轮播图管理id:{}",id);
                logger.info("删除轮播图管理结束，成功");
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
    @RequiresPermissions("wwd:banner:update")
    @RequestMapping(value = "/banner/{id}",method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable String id, UpdateWwdBanner dto){
        logger.info("更新轮播图管理开始");
        logger.info("当前登录用户id:{}",getLoginUser().getId());
        logger.info("轮播图管理id:{}",id);
        ResponseJsonRender resultData=new ResponseJsonRender();
        // 表单值设置
        WwdBanner basePo = new WwdBanner();
        // id
        basePo.setId(id);
        basePo.setId(dto.getId());
        basePo.setTitle(dto.getTitle());
        basePo.setTitleUrl(dto.getTitleUrl());
        basePo.setRedirectUrl(dto.getRedirectUrl());
        basePo.setType(dto.getType());
        basePo.setStatus(dto.getStatus());
        basePo.setDescp(dto.getDescp());
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
        WwdBanner basePoCondition = new WwdBanner();
        basePoCondition.setId(id);
        basePoCondition.setDelFlag(BasePo.YesNo.N.name());
        basePoCondition.setUpdateAt(dto.getUpdateTime());
        basePo = apiWwdBannerService.preUpdate(basePo,getLoginUser().getId());
        int r = apiWwdBannerService.updateSelective(basePo,basePoCondition);
        if (r <= 0) {
            // 更新失败，资源不存在
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}",resultData.getCode(),resultData.getMsg());
            logger.info("更新轮播图管理结束，失败");
            return new ResponseEntity(resultData,HttpStatus.NOT_FOUND);
        }else{
            // 更新成功，已被成功创建
            logger.info("更新的轮播图管理id:{}",id);
            logger.info("更新轮播图管理结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }

    /**
     * 单资源，获取id轮播图管理
     * @param id
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:banner:getById")
    @RequestMapping(value = "/banner/{id}",method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable String id){

        ResponseJsonRender resultData=new ResponseJsonRender();
        WwdBannerDto baseDataScopeDto = apiWwdBannerService.selectByPrimaryKey(id,false);
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
     * 复数资源，搜索轮播图管理
     * @param dto
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:banner:search")
    @RequestMapping(value = "/banners",method = RequestMethod.GET)
    public ResponseEntity search(SearchWwdBannersConditionDto dto){

        ResponseJsonRender resultData=new ResponseJsonRender();
        PageAndOrderbyParamDto pageAndOrderbyParamDto = new PageAndOrderbyParamDto(PageUtils.getPageFromThreadLocal(), OrderbyUtils.getOrderbyFromThreadLocal());
        // 设置当前登录用户id
        dto.setCurrentUserId(getLoginUser().getId());
        dto.setCurrentRoleId(((BaseRoleDto) getLoginUser().getRole()).getId());
        PageResultDto<WwdBannerDto> list = apiWwdBannerService.searchWwdBannersDsf(dto,pageAndOrderbyParamDto);

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
