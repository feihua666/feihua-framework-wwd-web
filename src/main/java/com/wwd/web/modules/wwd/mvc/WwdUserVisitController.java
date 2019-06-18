package com.wwd.web.modules.wwd.mvc;

import com.feihua.framework.base.modules.role.dto.BaseRoleDto;
import com.feihua.framework.base.modules.user.api.ApiBaseUserPoService;
import com.feihua.framework.base.modules.user.dto.BaseUserDto;
import com.feihua.framework.rest.ResponseJsonRender;
import com.feihua.framework.rest.interceptor.RepeatFormValidator;
import com.feihua.framework.rest.modules.common.mvc.BaseController;
import com.feihua.framework.shiro.pojo.ShiroUser;
import com.feihua.utils.http.httpServletResponse.ResponseCode;
import com.wwd.service.modules.wwd.api.ApiWwdUserPoService;
import com.wwd.service.modules.wwd.api.ApiWwdUserVisitService;
import com.wwd.service.modules.wwd.dto.SearchWwdUserVisitsConditionDto;
import com.wwd.service.modules.wwd.dto.WwdUserDto;
import com.wwd.service.modules.wwd.dto.WwdUserVisitDto;
import com.wwd.service.modules.wwd.po.WwdUserVisit;
import com.wwd.web.modules.wwd.dto.AddWwdUserVisit;
import feihua.jdbc.api.pojo.BasePo;
import feihua.jdbc.api.pojo.PageAndOrderbyParamDto;
import feihua.jdbc.api.pojo.PageResultDto;
import feihua.jdbc.api.utils.OrderbyUtils;
import feihua.jdbc.api.utils.PageUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

import java.util.Map;

/**
 * 用户详情访问记录
 * Created by yangwei
 */
@RestController
@RequestMapping("/wwd")
public class WwdUserVisitController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(WwdUserVisitController.class);

	@Autowired
	private ApiWwdUserVisitService apiWwdUserVisitService;

	@Autowired
	private ApiWwdUserPoService apiWwdUserPoService;

	@Autowired
	private ApiBaseUserPoService apiBaseUserPoService;

	/**
	 * 单资源，添加
	 *
	 * @param dto
	 * @return
	 */
	@RepeatFormValidator
	@RequiresPermissions("wwd:user:visit:add")
	@RequestMapping(value = "/user/visit", method = RequestMethod.POST)
	public ResponseEntity add(AddWwdUserVisit dto) {
		logger.info("添加用户详情访问记录开始");
		logger.info("当前登录用户id:{}", getLoginUser().getId());
		ResponseJsonRender resultData = new ResponseJsonRender();
		// 表单值设置
		WwdUserVisit basePo = new WwdUserVisit();
		basePo.setId(dto.getId());
		basePo.setWwdUserId(dto.getWwdUserId());
		//
		ShiroUser loginUser = getLoginUser();

		//
		WwdUserDto visitWwdUserDto = apiWwdUserPoService.selectByUserId(loginUser.getId());
		WwdUserVisitDto r = null;
		//自己看自己就不用加了
		if (!visitWwdUserDto.getId().equals(dto.getWwdUserId())) {
			//访问的wwd用户
			basePo.setVisitWwdUserPic(loginUser.getPhoto());
			basePo.setVisitWwdUserId(visitWwdUserDto.getId());
			basePo.setVisitWwdUserName(visitWwdUserDto.getName());
			basePo.setVisitWwdUserGender(visitWwdUserDto.getGender());

			//被访问的wwd用户
			WwdUserDto toWwdUserDto = apiWwdUserPoService.selectByPrimaryKey(dto.getWwdUserId());
			BaseUserDto baseUserDto = apiBaseUserPoService.selectByPrimaryKey(toWwdUserDto.getUserId());

			basePo.setWwdUserGender(toWwdUserDto.getGender());
			basePo.setWwdUserName(toWwdUserDto.getName());
			basePo.setWwdUserPic(baseUserDto.getPhoto());
			//
			basePo.setVisitType(dto.getVisitType());
			basePo.setReadFlag(BasePo.YesNo.N.name());
			basePo.setDataUserId(dto.getDataUserId());
			basePo.setDataOfficeId(dto.getDataOfficeId());
			basePo.setDataType(dto.getDataType());
			basePo.setDataAreaId(dto.getDataAreaId());
			basePo.setDelFlag(dto.getDelFlag());
			basePo.setCreateAt(dto.getCreateAt());
			basePo.setCreateBy(dto.getCreateBy());
			basePo.setUpdateAt(dto.getUpdateAt());
			basePo.setUpdateBy(dto.getUpdateBy());

			basePo = apiWwdUserVisitService.preInsert(basePo, getLoginUser().getId());
			r = apiWwdUserVisitService.insert(basePo);
		}

		if (r == null) {
			// 添加失败
			resultData.setCode(ResponseCode.E404_100001.getCode());
			resultData.setMsg(ResponseCode.E404_100001.getMsg());
			logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
			logger.info("添加用户详情访问记录结束，失败");
			return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
		} else {
			// 添加成功，返回添加的数据
			resultData.setData(r);
			logger.info("添加用户详情访问记录id:{}", r.getId());
			logger.info("添加用户详情访问记录结束，成功");
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
	@RequiresPermissions("wwd:user:visit:delete")
	@RequestMapping(value = "/user/visit/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable String id) {
		logger.info("删除用户详情访问记录开始");
		logger.info("用户id:{}", getLoginUser().getId());
		logger.info("用户详情访问记录id:{}", id);
		ResponseJsonRender resultData = new ResponseJsonRender();

		int r = apiWwdUserVisitService.deleteFlagByPrimaryKeyWithUpdateUser(id, getLoginUser().getId());
		if (r <= 0) {
			// 删除失败，可能没有找到资源
			resultData.setCode(ResponseCode.E404_100001.getCode());
			resultData.setMsg(ResponseCode.E404_100001.getMsg());
			logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
			logger.info("删除用户详情访问记录结束，失败");
			return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
		} else {
			// 删除成功
			logger.info("删除的用户详情访问记录id:{}", id);
			logger.info("删除用户详情访问记录结束，成功");
			return new ResponseEntity(resultData, HttpStatus.NO_CONTENT);
		}
	}


	/**
	 * 多资源，更新查看状态
	 *
	 * @param wwdUserId
	 * @return
	 */
	@RepeatFormValidator
	@RequiresPermissions("wwd:user:visit:update")
	@RequestMapping(value = "/user/visit/read/{wwdUserId}", method = RequestMethod.PUT)
	public ResponseEntity updateReadFlag(@PathVariable String wwdUserId) {
		logger.info("更新用户详情访问记录查看状态开始");
		logger.info("当前登录用户id:{}", getLoginUser().getId());
		logger.info("用户详情访问记录wwdUserId:{}", wwdUserId);
		ResponseJsonRender resultData = new ResponseJsonRender();
		// 表单值设置
		WwdUserVisit basePo = new WwdUserVisit();
		// id
		basePo.setReadFlag(BasePo.YesNo.Y.name());

		// 用条件更新，乐观锁机制
		WwdUserVisit basePoCondition = new WwdUserVisit();
		basePoCondition.setWwdUserId(wwdUserId);
		basePoCondition.setDelFlag(BasePo.YesNo.N.name());
		basePoCondition.setReadFlag(BasePo.YesNo.N.name());
		int r = apiWwdUserVisitService.updateSelective(basePo, basePoCondition);
		if (r <= 0) {
			// 更新失败，资源不存在
			resultData.setCode(ResponseCode.E404_100001.getCode());
			resultData.setMsg(ResponseCode.E404_100001.getMsg());
			logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
			logger.info("更新用户详情访问记录查看状态结束，失败");
			return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
		} else {
			// 更新成功，已被成功创建
			logger.info("更新的用户详情访问记录wwdUserId:{}", wwdUserId);
			logger.info("更新用户详情访问记录查看状态结束，成功");
			return new ResponseEntity(resultData, HttpStatus.CREATED);
		}
	}

	/**
	 * 单资源，获取id用户详情访问记录
	 *
	 * @param id
	 * @return
	 */
	@RepeatFormValidator
	@RequiresPermissions("wwd:user:visit:getById")
	@RequestMapping(value = "/user/visit/{id}", method = RequestMethod.GET)
	public ResponseEntity getById(@PathVariable String id) {

		ResponseJsonRender resultData = new ResponseJsonRender();
		WwdUserVisitDto baseDataScopeDto = apiWwdUserVisitService.selectByPrimaryKey(id, false);
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
	 * 单资源，获取id用户详情访问记录统计
	 *
	 * @param wwdUserId
	 * @return
	 */
	@RepeatFormValidator
	@RequiresPermissions("wwd:user:visit:count")
	@RequestMapping(value = "/user/visit/count", method = RequestMethod.GET)
	public ResponseEntity visitCount(String wwdUserId) {
		if (StringUtils.isBlank(wwdUserId)) {
			String loginUserId = getLoginUserId();
			wwdUserId = apiWwdUserPoService.selectByUserId(loginUserId).getId();
		}
		ResponseJsonRender resultData = new ResponseJsonRender();

		Map map = apiWwdUserVisitService.selectVisitCountByWwdUserId(wwdUserId);
		resultData.setData(map);
		return new ResponseEntity(resultData, HttpStatus.OK);
	}

	/**
	 * 复数资源，搜索用户详情访问记录
	 *
	 * @param dto
	 * @return
	 */
	@RepeatFormValidator
	@RequiresPermissions("wwd:user:visit:search")
	@RequestMapping(value = "/user/visits", method = RequestMethod.GET)
	public ResponseEntity search(SearchWwdUserVisitsConditionDto dto) {

		ResponseJsonRender resultData = new ResponseJsonRender();
		PageAndOrderbyParamDto pageAndOrderbyParamDto = new PageAndOrderbyParamDto(PageUtils.getPageFromThreadLocal(), OrderbyUtils.getOrderbyFromThreadLocal());
		// 设置当前登录用户id
		dto.setCurrentUserId(getLoginUser().getId());
		dto.setCurrentRoleId(((BaseRoleDto) getLoginUser().getRole()).getId());
		PageResultDto<WwdUserVisitDto> list = apiWwdUserVisitService.searchWwdUserVisitsDsf(dto, pageAndOrderbyParamDto);

		if (CollectionUtils.isNotEmpty(list.getData())) {
			resultData.setData(list.getData());
			resultData.setPage(list.getPage());
			return new ResponseEntity(resultData, HttpStatus.OK);
		} else {
			resultData.setCode(ResponseCode.E404_100001.getCode());
			resultData.setMsg(ResponseCode.E404_100001.getMsg());
			return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
		}
	}
}
