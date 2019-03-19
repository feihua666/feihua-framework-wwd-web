package com.wwd.service.modules.wwd.api;

import com.wwd.service.modules.wwd.dto.WwdUserInvitationDto;
import com.wwd.service.modules.wwd.po.WwdUserInvitationPo;
import feihua.jdbc.api.pojo.PageAndOrderbyParamDto;
import feihua.jdbc.api.pojo.PageResultDto;

import java.util.List;

/**
 * This class was generated by MyBatis Generator.
 * @author yangwei 2018-04-27 09:40:48
 */
public interface ApiWwdUserInvitationPoService extends feihua.jdbc.api.service.ApiBaseService<WwdUserInvitationPo, WwdUserInvitationDto, String> {

    /**
     * 根据汪汪队用户id查询
     * @param wwdUserId
     * @return
     */
    public List<WwdUserInvitationDto> selectByWwdUserId(String wwdUserId);

    /**
     * 根据汪汪队用户id查询未使用的
     * @param wwdUserId
     * @return
     */
    public List<WwdUserInvitationDto> selectUnUsedByWwdUserId(String wwdUserId);

    /**
     * 查询已邀请的
     * @param wwdUserId
     * @return
     */
    public List<WwdUserInvitationDto> selectUsedByWwdUserId(String wwdUserId);
    public PageResultDto<WwdUserInvitationDto> selectUsedByWwdUserId(String wwdUserId, PageAndOrderbyParamDto pageAndOrderbyParamDto);

    /**
     * 生成一个邀请码
     * @param wwdUserId
     * @return
     */
    public WwdUserInvitationDto generateForWwdUserId(String wwdUserId);

    /**
     * 根据code查询
     * @param invitationCode
     * @return
     */
    public WwdUserInvitationDto selectByCode(String invitationCode);

    /**
     * 查询未使用的code
     * @param invitationCode
     * @return
     */
    public WwdUserInvitationDto selectUnUsedByCode(String invitationCode);

    /**
     * 根据被邀请wwd userid查询，主要用来判断是否被邀请过
     * @param invitedWWdUserId
     * @return
     */
    public WwdUserInvitationDto selectByInvitedWWdUserId(String invitedWWdUserId);
}