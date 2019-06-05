package com.wwd.web.modules.wwd.mvc;

import com.feihua.framework.base.modules.role.dto.BaseRoleDto;
import com.feihua.framework.base.modules.user.api.ApiBaseUserPoService;
import com.feihua.framework.base.modules.user.dto.BaseUserDto;
import com.feihua.framework.constants.DictEnum;
import com.feihua.framework.rest.ResponseJsonRender;
import com.feihua.framework.rest.interceptor.RepeatFormValidator;
import com.feihua.framework.rest.modules.common.mvc.BaseController;
import com.feihua.utils.http.httpServletResponse.ResponseCode;
import com.wwd.Constants;
import com.wwd.service.modules.wwd.api.ApiWwdActivityService;
import com.wwd.service.modules.wwd.api.ApiWwdParticipateService;
import com.wwd.service.modules.wwd.api.ApiWwdUserPoService;
import com.wwd.service.modules.wwd.dto.SearchWwdParticipatesConditionDto;
import com.wwd.service.modules.wwd.dto.WwdActivityDto;
import com.wwd.service.modules.wwd.dto.WwdParticipateDto;
import com.wwd.service.modules.wwd.dto.WwdUserDto;
import com.wwd.service.modules.wwd.po.WwdParticipate;
import com.wwd.service.modules.wwd.po.WwdUserPo;
import com.wwd.web.modules.wwd.dto.AddWwdParticipate;
import com.wwd.web.modules.wwd.dto.UpdateWwdParticipate;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 汪汪队活动参与管理
 * Created by yangwei
 */
@RestController
@RequestMapping("/wwd")
public class WwdParticipateController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(WwdParticipateController.class);

    @Autowired
    private ApiWwdParticipateService apiWwdParticipateService;
    @Autowired
    private ApiWwdUserPoService apiWwdUserPoService;
    @Autowired
    private ApiBaseUserPoService apiBaseUserPoService;
    @Autowired
    private ApiWwdActivityService apiWwdActivityService;

    /**
     * 单资源，添加
     *
     * @param dto
     * @return
     */
    @RepeatFormValidator
    @RequiresPermissions("wwd:participate:add")
    @RequestMapping(value = "/participate", method = RequestMethod.POST)
    public ResponseEntity add(AddWwdParticipate dto) {
        logger.info("添加汪汪队活动参与开始");
        logger.info("当前登录用户id:{}", getLoginUser().getId());
        ResponseJsonRender resultData = new ResponseJsonRender();
        // 表单值设置
        WwdParticipate basePo = new WwdParticipate();
        basePo.setId(dto.getId());
        basePo.setWwdActivityId(dto.getWwdActivityId());
        basePo.setWwdUserId(dto.getWwdUserId());
        basePo.setType(dto.getType());
        basePo.setPayStatus(dto.getPayStatus());
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

        basePo = apiWwdParticipateService.preInsert(basePo, getLoginUser().getId());
        WwdParticipateDto r = apiWwdParticipateService.insert(basePo);
        if (r == null) {
            // 添加失败
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
            logger.info("添加汪汪队活动参与结束，失败");
            return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
        } else {
            // 添加成功，返回添加的数据
            resultData.setData(r);
            logger.info("添加汪汪队活动参与id:{}", r.getId());
            logger.info("添加汪汪队活动参与结束，成功");
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
    @RequiresPermissions("wwd:participate:delete")
    @RequestMapping(value = "/participate/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id) {
        logger.info("删除汪汪队活动参与开始");
        logger.info("用户id:{}", getLoginUser().getId());
        logger.info("汪汪队活动参与id:{}", id);
        ResponseJsonRender resultData = new ResponseJsonRender();

        int r = apiWwdParticipateService.deleteFlagByPrimaryKeyWithUpdateUser(id, getLoginUser().getId());
        if (r <= 0) {
            // 删除失败，可能没有找到资源
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
            logger.info("删除汪汪队活动参与结束，失败");
            return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
        } else {
            // 删除成功
            logger.info("删除的汪汪队活动参与id:{}", id);
            logger.info("删除汪汪队活动参与结束，成功");
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
    @RequiresPermissions("wwd:participate:update")
    @RequestMapping(value = "/participate/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable String id, UpdateWwdParticipate dto) {
        logger.info("更新汪汪队活动参与开始");
        logger.info("当前登录用户id:{}", getLoginUser().getId());
        logger.info("汪汪队活动参与id:{}", id);
        ResponseJsonRender resultData = new ResponseJsonRender();
        // 表单值设置
        WwdParticipate basePo = new WwdParticipate();
        // id
        basePo.setId(id);
        basePo.setId(dto.getId());
        basePo.setWwdActivityId(dto.getWwdActivityId());
        basePo.setWwdUserId(dto.getWwdUserId());
        basePo.setType(dto.getType());
        basePo.setPayStatus(dto.getPayStatus());
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
        WwdParticipate basePoCondition = new WwdParticipate();
        basePoCondition.setId(id);
        basePoCondition.setDelFlag(BasePo.YesNo.N.name());
        basePoCondition.setUpdateAt(dto.getUpdateTime());
        basePo = apiWwdParticipateService.preUpdate(basePo, getLoginUser().getId());
        int r = apiWwdParticipateService.updateSelective(basePo, basePoCondition);
        if (r <= 0) {
            // 更新失败，资源不存在
            resultData.setCode(ResponseCode.E404_100001.getCode());
            resultData.setMsg(ResponseCode.E404_100001.getMsg());
            logger.info("code:{},msg:{}", resultData.getCode(), resultData.getMsg());
            logger.info("更新汪汪队活动参与结束，失败");
            return new ResponseEntity(resultData, HttpStatus.NOT_FOUND);
        } else {
            // 更新成功，已被成功创建
            logger.info("更新的汪汪队活动参与id:{}", id);
            logger.info("更新汪汪队活动参与结束，成功");
            return new ResponseEntity(resultData, HttpStatus.CREATED);
        }
    }

    /**
     * 单资源，获取id汪汪队活动参与
     *
     * @param id
     * @return
     */
    @RequiresPermissions("wwd:participate:getById")
    @RequestMapping(value = "/participate/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable String id) {

        ResponseJsonRender resultData = new ResponseJsonRender();
        WwdParticipateDto baseDataScopeDto = apiWwdParticipateService.selectByPrimaryKey(id, false);
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
     * 复数资源，搜索汪汪队活动参与
     *
     * @param dto
     * @return
     */
    @RequiresPermissions("wwd:participate:search")
    @RequestMapping(value = "/participates", method = RequestMethod.GET)
    public ResponseEntity search(SearchWwdParticipatesConditionDto dto) {

        ResponseJsonRender resultData = new ResponseJsonRender();
        PageAndOrderbyParamDto pageAndOrderbyParamDto = new PageAndOrderbyParamDto(PageUtils.getPageFromThreadLocal(), OrderbyUtils.getOrderbyFromThreadLocal());
        // 设置当前登录用户id
        dto.setCurrentUserId(getLoginUser().getId());
        dto.setCurrentRoleId(((BaseRoleDto) getLoginUser().getRole()).getId());
        PageResultDto<WwdParticipateDto> list = apiWwdParticipateService.searchWwdParticipatesDsf(dto, pageAndOrderbyParamDto);
        List<WwdParticipateDto> data = list.getData();
        if (data != null && data.size() > 0) {
            for (WwdParticipateDto wwdParticipateDto : data) {
                WwdUserDto wwdUserDto = apiWwdUserPoService.selectByPrimaryKey(wwdParticipateDto.getWwdUserId());
                BaseUserDto baseUserDto = apiBaseUserPoService.selectByPrimaryKey(wwdUserDto.getUserId());
                wwdParticipateDto.setWwdUserDto(wwdUserDto);
                wwdParticipateDto.setBaseUserDto(baseUserDto);
            }
        }
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

	/**
	 * 当前登录用户报名
	 *
	 * @param activityId
	 * @param name       报名人姓名
	 * @param mobile     报名人手机号
	 * @return
	 */
	@RepeatFormValidator
	@RequiresPermissions("wwd:participate:participate")
	@RequestMapping(value = "/participate/user/current", method = RequestMethod.POST)
	public ResponseEntity participate(String activityId, String name, String mobile, String idCardNo, String saveToInfo,@RequestParam("gender") String gender) {
		logger.info("报名活动开始");
		logger.info("当前登录用户id:{}", getLoginUser().getId());
		ResponseJsonRender resultData = new ResponseJsonRender();
		// 查询活动信息
		WwdActivityDto wwdActivityDto = apiWwdActivityService.selectByPrimaryKey(activityId);
		WwdUserDto wwdUserDto = apiWwdUserPoService.selectByUserId(getLoginUser().getId());
		//更新性别
		if (StringUtils.isNotEmpty(gender) && (StringUtils.isEmpty(wwdUserDto.getGender()) || !gender.equals(wwdUserDto.getGender()))) {
			WwdUserPo wwdUserPo = new WwdUserPo();
			wwdUserPo.setId(wwdUserDto.getId());
			wwdUserPo.setGender(gender);
			apiWwdUserPoService.updateByPrimaryKeySelective(wwdUserPo);
			wwdUserDto.setGender(gender);
		}

		if (wwdActivityDto == null) {
			return super.returnDto(null, resultData);
		}
		if (Constants.HeadCountRule.unlimited.name().equals(wwdActivityDto.getHeadcountRule())) {
			if (!new Integer(0).equals(wwdActivityDto.getHeadcount())) {
				// 查询报名人数
				int headcount = apiWwdParticipateService.selectCountPaidParticipate(activityId);
				// 报名人数已满
				if (headcount >= wwdActivityDto.getHeadcount()) {
					resultData.setCode("headcount=enough");
					resultData.setMsg("headcount=enough");
					return new ResponseEntity(resultData, HttpStatus.CONFLICT);
				}
			}
		} else if (Constants.HeadCountRule.custom.name().equals(wwdActivityDto.getHeadcountRule())) {
			int headcountSex = apiWwdParticipateService.selectCountPaidParticipate(activityId, wwdUserDto.getGender());
			int headcountGender = 0;
			if (DictEnum.Gender.female.name().equals(wwdUserDto.getGender())) {
				headcountGender = wwdActivityDto.getHeadcountFemale();
			} else if (DictEnum.Gender.male.name().equals(wwdUserDto.getGender())) {
				headcountGender = wwdActivityDto.getHeadcountMale();

			}
			// 报名人数已满
			if (headcountSex >= headcountGender) {
				resultData.setCode("headcount=enough");
				resultData.setMsg("headcount=enough");
				return new ResponseEntity(resultData, HttpStatus.CONFLICT);
			}
		} else {
			resultData.setCode("headcountRule=invalie");
			resultData.setMsg("headcountRule=invalie");
			return new ResponseEntity(resultData, HttpStatus.CONFLICT);
		}

		// 查询是否已报名
		// 查询参与信息
		WwdParticipate wwdParticipate = null;
		List<WwdParticipate> wwdParticipates = apiWwdParticipateService.selectByActivityIdAndWwdUserId(activityId, wwdUserDto.getId());
		if (wwdParticipates != null) {
			for (WwdParticipate participate : wwdParticipates) {
				if (Constants.PayStatus.paid.name().equals(participate.getPayStatus()) || Constants.PayStatus.no_pay.name().equals(participate.getPayStatus())
						|| Constants.PayStatus.offline_pay.name().equals(participate.getPayStatus())) {
					wwdParticipate = participate;
					break;
				}
			}
		}

		// 如果没有参与信息，添加一条
		if (wwdParticipate == null) {
			WwdParticipate wwdParticipateBeInsert = new WwdParticipate();
			wwdParticipateBeInsert.setWwdUserId(wwdUserDto.getId());
			wwdParticipateBeInsert.setWwdActivityId(activityId);
			wwdParticipateBeInsert.setName(name);
			wwdParticipateBeInsert.setMobile(mobile);
			wwdParticipateBeInsert.setIdCardNo(idCardNo);
			// 未支付

			wwdParticipateBeInsert.setPayStatus(Constants.PayStatus.no_pay.name());
			wwdParticipateBeInsert.setType(BasePo.YesNo.N.name());
			wwdParticipateBeInsert.setStatus(Constants.WwdParticipateStatus.NORMAL.getCode());
			wwdParticipate = apiWwdParticipateService.preInsert(wwdParticipateBeInsert, getLoginUser().getId());
			wwdParticipate = apiWwdParticipateService.insertSimple(wwdParticipate);
		} else {
			wwdParticipate.setName(name);
			wwdParticipate.setMobile(mobile);
			wwdParticipate.setIdCardNo(idCardNo);
			wwdParticipate = apiWwdParticipateService.preUpdate(wwdParticipate, getLoginUserId());
			apiWwdParticipateService.updateByPrimaryKeySelective(wwdParticipate);
		}

		// 判断是否已支付,如果
		if (Constants.PayStatus.paid.name().equals(wwdParticipate.getPayStatus()) || Constants.PayStatus.offline_pay.name().equals(wwdParticipate.getPayStatus())) {
			resultData.setCode("payStatus=paid");
			resultData.setMsg("payStatus=paid");
			return new ResponseEntity(resultData, HttpStatus.CONFLICT);
		}

		if (BasePo.YesNo.Y.name().equals(saveToInfo) && (StringUtils.isNotEmpty(name) || StringUtils.isNotEmpty(mobile) || StringUtils.isNotEmpty(idCardNo))) {
			WwdUserPo wwdUserPo = new WwdUserPo();
			wwdUserPo.setId(wwdUserDto.getId());
			if (StringUtils.isNotEmpty(name)) {
				wwdUserPo.setName(name);
			}
			if (StringUtils.isNotEmpty(mobile)) {
				wwdUserPo.setMobile(mobile);
			}
			if (StringUtils.isNotEmpty(idCardNo)) {
				wwdUserPo.setIdCardNo(idCardNo);
			}
			apiWwdUserPoService.updateByPrimaryKeySelective(wwdUserPo);
		}

		return returnDto(wwdParticipate, resultData);
	}
}
