package com.wwd.web.modules.wwd.mvc;

import com.feihua.framework.base.modules.config.api.ApiBaseConfigService;
import com.feihua.framework.base.modules.config.po.BaseConfig;
import com.feihua.framework.message.api.MessageSendHelper;
import com.feihua.framework.message.dto.MessageSendForUserParamsDto;
import com.feihua.framework.rest.ResponseJsonRender;
import com.feihua.framework.rest.interceptor.RepeatFormValidator;
import com.feihua.framework.rest.modules.common.mvc.BaseController;
import com.feihua.utils.http.httpServletResponse.ResponseCode;
import com.feihua.utils.json.JSONUtils;
import com.wwd.service.modules.wwd.api.ApiWwdUserEnjoyPoService;
import com.wwd.service.modules.wwd.api.ApiWwdUserPicPoService;
import com.wwd.service.modules.wwd.api.ApiWwdUserPoService;
import com.wwd.service.modules.wwd.dto.WwdUserDto;
import com.wwd.service.modules.wwd.dto.WwdUserEnjoyDto;
import com.wwd.service.modules.wwd.dto.WwdUserPicDto;
import com.wwd.service.modules.wwd.po.WwdUserEnjoyPo;
import feihua.jdbc.api.pojo.BasePo;
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
import tk.mybatis.orderbyhelper.OrderByHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 汪汪队用户管理
 * Created by yangwei
 */
@RestController
@RequestMapping("/wwd")
public class WwdEnjoyController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(WwdEnjoyController.class);

	@Autowired
	private ApiWwdUserPoService apiWwdUserPoService;
	@Autowired
	private ApiWwdUserPicPoService apiWwdUserPicPoService;
	@Autowired
	private ApiWwdUserEnjoyPoService apiWwdUserEnjoyPoService;


	@Autowired
	private MessageSendHelper messageSendHelper;

	@Autowired
	private ApiBaseConfigService apiBaseConfigService;

	/**
	 * 单资源，我是否对他有意思
	 *
	 * @return
	 */
	@RequiresPermissions("wwd:user:current:enjoy:getById")
	@RequestMapping(value = "/user/current/enjoy/{enjoyedWwdUserId}", method = RequestMethod.GET)
	public ResponseEntity getEnjoy(@PathVariable String enjoyedWwdUserId) {

		ResponseJsonRender resultData = new ResponseJsonRender();

		String userId = getLoginUser().getId();
		WwdUserDto userDto = apiWwdUserPoService.selectByUserId(userId);
		WwdUserEnjoyDto wwdUserEnjoyDto = apiWwdUserEnjoyPoService.selectEnjoyedFromTo(userDto.getId(), enjoyedWwdUserId);
		return super.returnDto(wwdUserEnjoyDto, resultData);
	}

	/**
	 * 单资源，我对他/她有意思
	 *
	 * @return
	 */
	@RepeatFormValidator
	@RequiresPermissions("wwd:user:current:enjoy:add")
	@RequestMapping(value = "/user/current/enjoy/{enjoyedWwdUserId}", method = RequestMethod.POST)
	public ResponseEntity addEnjoy(@PathVariable String enjoyedWwdUserId) {
		logger.info("汪汪队添加有意思开始");
		logger.info("当前登录用户id:{}", getLoginUser().getId());
		ResponseJsonRender resultData = new ResponseJsonRender();

		String userId = getLoginUser().getId();
		final WwdUserDto userDto = apiWwdUserPoService.selectByUserId(userId);

		BaseConfig configQuery = new BaseConfig();
		configQuery.setConfigKey("WWD_ENJOY_LIMIT");
		configQuery.setStatus(BasePo.YesNo.Y.name());
		configQuery.setDelFlag(BasePo.YesNo.N.name());
		BaseConfig baseConfig = apiBaseConfigService.selectOneSimple(configQuery);
		Boolean sendMsg = false;
		if (baseConfig != null && StringUtils.isNotBlank(baseConfig.getConfigValue())) {
			String enjoyLimit = baseConfig.getConfigValue();
			try {
				Map<String, Object> enjoyLimitMap = JSONUtils.json2map(enjoyLimit);
				String enabled = enjoyLimitMap.get("enabled").toString();
				if(StringUtils.isNotBlank(enabled) && !"true".equalsIgnoreCase(enabled)){
					// 添加失败
					resultData.setCode(ResponseCode.E409_100001.getCode());
					resultData.setMsg("该功能尚未开放！");
					logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
					logger.info("汪汪队添加有意思汪汪队添加有意思结束，失败");
					return new ResponseEntity(resultData, HttpStatus.CONFLICT);
				}
				List<WwdUserEnjoyDto> wwdUserEnjoyDtos = apiWwdUserEnjoyPoService.selectByWwdUserId(userDto.getId(), enjoyLimitMap.get("type").toString().toUpperCase(), enjoyLimitMap.get("typeLimit").toString());
				if (wwdUserEnjoyDtos != null && wwdUserEnjoyDtos.size() >= Integer.parseInt(enjoyLimitMap.get("limit").toString())) {
					// 添加失败
					resultData.setCode(ResponseCode.E409_100001.getCode());
					resultData.setMsg(enjoyLimitMap.get("msg").toString());
					logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
					logger.info("汪汪队添加有意思汪汪队添加有意思结束，失败");
					return new ResponseEntity(resultData, HttpStatus.CONFLICT);
				}
				String isSendMsg = enjoyLimitMap.get("isSendMsg").toString();
				if(StringUtils.isNotBlank(isSendMsg) && "true".equalsIgnoreCase(isSendMsg)){
					sendMsg = true;
				}
			} catch (Exception e) {
				logger.error("添加有意思限制", e);
			}
		}

		final WwdUserDto enjoyedWwdUser = apiWwdUserPoService.selectByPrimaryKey(enjoyedWwdUserId);
		WwdUserEnjoyPo wwdUserEnjoyPo = new WwdUserEnjoyPo();
		wwdUserEnjoyPo.setWwdUserId(userDto.getId());
		wwdUserEnjoyPo.setEnjoyedWwdUserId(enjoyedWwdUserId);
		apiWwdUserEnjoyPoService.preInsert(wwdUserEnjoyPo, getLoginUserId());
		WwdUserEnjoyDto r = apiWwdUserEnjoyPoService.insertSelective(wwdUserEnjoyPo);

		if (r == null) {
			// 添加失败
			resultData.setCode(ResponseCode.E404_100001.getCode());
			resultData.setMsg(ResponseCode.E404_100001.getMsg());
			logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
			logger.info("汪汪队添加有意思汪汪队添加有意思结束，失败");
			return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
		} else {

			if (sendMsg) {
				//对他她有意思，发送消息
				try {
					MessageSendForUserParamsDto messageSendForUserParamsDto = new MessageSendForUserParamsDto();
					messageSendForUserParamsDto.setClientCode("h5");
					messageSendForUserParamsDto.setTemplateCode("wwd_enjoy_success");
					messageSendForUserParamsDto.setUserId(enjoyedWwdUser.getUserId());
					messageSendForUserParamsDto.setCurrentUserId(getLoginUserId());
					messageSendForUserParamsDto.setCurrentRoleId(getLoginUserRole().getId());
					Map<String, String> templateParams = new HashMap<>();
					templateParams.put("nickname", userDto.getNickname());
					templateParams.put("wwdUserId", userDto.getId());
					messageSendForUserParamsDto.setTemplateParams(templateParams);
					messageSendHelper.messageSendForUser(messageSendForUserParamsDto);
					logger.info("对他她有意思成功，发送消息：{} TO {}", userDto.getNickname(), enjoyedWwdUser.getNickname());
				} catch (Exception e) {
					logger.error("有意思发消息异常：", e);
				}
			}

			// 添加成功，已被成功创建
			logger.info("添加的汪汪队用户id:{}", enjoyedWwdUserId);
			logger.info("汪汪队添加有意思结束，成功");
			resultData.setData(r);
			return new ResponseEntity(resultData, HttpStatus.CREATED);
		}
	}

	/**
	 * 单资源，我是否对他有意思
	 *
	 * @return
	 */
	@RequiresPermissions("wwd:user:current:enjoys:get")
	@RequestMapping(value = "/user/current/enjoys/{status}", method = RequestMethod.GET)
	public ResponseEntity selectEnjoys(@PathVariable String status) {

		ResponseJsonRender resultData = new ResponseJsonRender();

		String userId = getLoginUser().getId();
		WwdUserDto userDto = apiWwdUserPoService.selectByUserId(userId);
		List<WwdUserEnjoyDto> wwdUserEnjoyDtos = null;
		List<String> wwdUserIds = new ArrayList<>();
		Map enjoyedTime = new HashMap();
		OrderByHelper.orderBy("create_at desc");
		if ("1".equals(status)) {
			wwdUserEnjoyDtos = apiWwdUserEnjoyPoService.selectByWwdUserId(userDto.getId());
			if (wwdUserEnjoyDtos != null && wwdUserEnjoyDtos.size() > 0) {
				for (WwdUserEnjoyDto wwdUserEnjoyDto : wwdUserEnjoyDtos) {
					wwdUserIds.add(wwdUserEnjoyDto.getEnjoyedWwdUserId());
					enjoyedTime.put(wwdUserEnjoyDto.getEnjoyedWwdUserId(), wwdUserEnjoyDto.getCreateAt());
				}
			}
		} else if ("2".equals(status)) {
			wwdUserEnjoyDtos = apiWwdUserEnjoyPoService.selectByEnjoyedWwdUserId(userDto.getId());
			if (wwdUserEnjoyDtos != null && wwdUserEnjoyDtos.size() > 0) {
				for (WwdUserEnjoyDto wwdUserEnjoyDto : wwdUserEnjoyDtos) {
					wwdUserIds.add(wwdUserEnjoyDto.getWwdUserId());
					enjoyedTime.put(wwdUserEnjoyDto.getEnjoyedWwdUserId(), wwdUserEnjoyDto.getCreateAt());
				}
			}
		} else {
			wwdUserEnjoyDtos = apiWwdUserEnjoyPoService.selectEnjoyedByWwdUserId(userDto.getId());
			if (wwdUserEnjoyDtos != null && wwdUserEnjoyDtos.size() > 0) {
				for (WwdUserEnjoyDto wwdUserEnjoyDto : wwdUserEnjoyDtos) {
					wwdUserIds.add(wwdUserEnjoyDto.getEnjoyedWwdUserId());
					enjoyedTime.put(wwdUserEnjoyDto.getEnjoyedWwdUserId(), wwdUserEnjoyDto.getCreateAt());
				}
			}
		}
		List<WwdUserDto> wwdUserDtos = null;
		if (wwdUserIds != null && !wwdUserIds.isEmpty()) {
			wwdUserDtos = apiWwdUserPoService.selectByPrimaryKeys(wwdUserIds, false);
			if (wwdUserDtos != null && wwdUserDtos.size() > 0) {
				Map<String, String> picMap = null;
				List<Map<String, String>> picList = new ArrayList<>();
				for (WwdUserDto dto : wwdUserDtos) {
					if ("1".equals(status) || "1".equals(status)) {
						dto.setWechatNumber(null);
					}
					List<WwdUserPicDto> picDtos = apiWwdUserPicPoService.selectByWwdUserId(dto.getId());
					if (picDtos != null && !picDtos.isEmpty()) {
						for (WwdUserPicDto picDto : picDtos) {
							if ("main".equals(picDto.getType())) {
								picMap = new HashMap<>();
								picMap.put("picUrl", picDto.getPicOriginUrl());
								picMap.put("wwdUserId", dto.getId());
								picList.add(picMap);
								break;
							}
						}
					}
				}
				resultData.addData("pic", picList);
				resultData.addData("enjoyedTime", enjoyedTime);
			}
		}
		List<WwdUserDto> wwdUserDtos1 = new ArrayList<>();
		for (String wwdUserId : wwdUserIds) {
			for (WwdUserDto wwdUserDto : wwdUserDtos) {
				if (wwdUserId.equals(wwdUserDto.getId())) {
					wwdUserDtos1.add(wwdUserDto);
					break;
				}
			}
		}

		return super.returnList(wwdUserDtos1, resultData);
	}

}
