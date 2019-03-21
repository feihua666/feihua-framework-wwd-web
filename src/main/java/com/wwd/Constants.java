package com.wwd;

/**
 * Created by yangwei
 * Created at 2018/7/24 19:13
 */
public class Constants {
    // 汪汪队图片类型
    public enum WwdUserPicType {
        main,      // 主图
        normal     //非主图
    }

    // 汪汪队活动参与1/正常，2/替补，3/退出
    public enum WwdParticipateStatus {
        NORMAL("1", "正常"),
        ALTERNATE("2", "替补"),
        QUIT("3", "退出");

        private String code;
        private String name;

        WwdParticipateStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static WwdParticipateStatus getEnumBy(String key) {
            for (WwdParticipateStatus e : WwdParticipateStatus.values()) {
                if (e.getCode().equals(key) || e.getName().equals(key)) {
                    return e;
                }
            }
            return null;
        }
    }


    /**
     * 活动审核（未审核，1，审核成功，2，审核失败，3）
     */
    public enum ActivityReview {
        NONE("1", "未审核"),
        SUCCESS("2", "审核成功"),
        FAIL("3", "审核失败");

        private String code;
        private String name;

        ActivityReview(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static ActivityReview getEnumBy(String key) {
            for (ActivityReview e : ActivityReview.values()) {
                if (e.getCode().equals(key) || e.getName().equals(key)) {
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * 活动状态（0,编辑中,1，报名中，2，名额满，3，已结束）
     */
    public enum ActivityStatus {
        EDIT("0", "编辑中"),
        APPLYING("1", "报名中"),
        QUOTA_FULL("2", "名额满"),
        END("3", "已结束");

        private String code;
        private String name;

        ActivityStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static ActivityStatus getEnumBy(String key) {
            for (ActivityStatus e : ActivityStatus.values()) {
                if (e.getCode().equals(key) || e.getName().equals(key)) {
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * 支付状态
     */
    public enum PayStatus {
        // 已支付
        paid,
        // 未支付
        no_pay,
        // 已退款
        refund,
        // 退款中
        refunding
    }
    /**
     * 支付方式
     */
    public enum PayType {
        // 微信
        wx,
        // 支付宝
        zfb
    }
}
