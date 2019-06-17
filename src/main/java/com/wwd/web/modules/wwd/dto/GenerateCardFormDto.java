package com.wwd.web.modules.wwd.dto;

import java.util.List;

/**
 * Created by yangwei
 * Created at 2019/6/17 14:33
 */
public class GenerateCardFormDto {
    private String userId;
    private String sceneStr;
    private String which;
    private List<String> mainPicSelectedIds;
    private List<String> normalPicSelectedIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSceneStr() {
        return sceneStr;
    }

    public void setSceneStr(String sceneStr) {
        this.sceneStr = sceneStr;
    }

    public String getWhich() {
        return which;
    }

    public void setWhich(String which) {
        this.which = which;
    }

    public List<String> getMainPicSelectedIds() {
        return mainPicSelectedIds;
    }

    public void setMainPicSelectedIds(List<String> mainPicSelectedIds) {
        this.mainPicSelectedIds = mainPicSelectedIds;
    }

    public List<String> getNormalPicSelectedIds() {
        return normalPicSelectedIds;
    }

    public void setNormalPicSelectedIds(List<String> normalPicSelectedIds) {
        this.normalPicSelectedIds = normalPicSelectedIds;
    }
}
