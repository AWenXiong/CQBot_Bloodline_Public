package com.cq.httpapi.demo.exception.SZJException;

public enum SZJErrorCode {

    OPENID_ERROR(1, "登录码不存在"),
    ARGUMENT_NULL(2, "参数为空"),
    ARGUMENT_INVALID(3, "参数不合法"),

    ACCOUNT_ALREADY_EXIST(4, "用户名已存在"),

    GET_ALL_CARD_INFO_FAILURE(5, "获取全部卡牌信息失败"),
    GET_ALL_CARD_NICKNAME_FAILURE(6, "获取全部卡牌别名失败"),
    GET_CARD_EXT_INFO_FAILURE(7, "获取卡牌扩展信息失败"),
    GET_ALL_SPELL_INFO_FAILURE(8, "获取全部法阵信息失败"),
    GET_ENEMY_INFO_FAILURE(9, "获取敌阵容信息失败"),
    ENEMY_INFO_ID_LOST(10, "敌阵容主键缺失"),
    GET_ENEMY_LEVEL_FAILURE(11, "获取敌阵容关失败"),
    GET_ENEMY_CARD_FAILURE(12, "获取敌阵容卡信息失败"),

    ACCOUNT_EMPTY(13, "账号为空"),
    PASSWORD_EMPTY(14, "密码为空"),
    ACCOUNT_NOT_EXIST(15, "用户不存在"),
    LOG_IN_FAILURE(16, "用户名或密码错误"),
    GET_USER_INFO_FAILURE(17, "获取用户信息失败"),
    Wechat_Already_Binded(35, "用户已绑定微信"),

    USER_CARD_GROUP_ID_LOST(18, "用户卡组主键缺失"),
    USER_CARD_ID_LOST(19, "用户卡牌主键缺失"),
    USER_CARD_INFO_LOST(20, "卡牌数据缺失"),
    USER_SPELL_INFO_LOST(21, "法阵数据缺失"),

    QUEUE_INFO_ID_LOST(22, "配队阵容主键缺失"),

    INITIAL_LEVEL_LOST(23, "原始关数缺失"),
    INITIAL_POSITION_LOST(24, "原始位置缺失"),
    EDITING_CARD_ID_LOST(25, "主键缺失"),

    GET_ALL_GROUPS_FAILURE(26, "获取全部卡组失败"),
    CREATE_GROUP_FAILURE(27, "创建卡组失败"),
    EDIT_GROUP_FAILURE(28, "编辑卡组失败"),
    GET_GROUP_INFO_FAILURE(29, "获取卡组信息失败"),
    DELETE_GROUP_FAILURE(30, "删除卡组失败"),
    INVITATION_CODE_ERROR(31, "邀请码不存在"),

    INSERT_CARD_INFO_ERROR(32, "插入卡牌信息失败"),
    INIT_CARD_INFO_ERROR(33, "初始化卡牌信息失败"),
    DELETE_CARD_INFO_ERROR(34, "删除卡牌信息失败"),

    BATCH_ADD_DELETE_QUEUE_INFO_ERROR(35, "数据迁移过程删除原卡组配队失败"),
    WECHAT_HAVE_BEEN_BIND(36, "此微信已被绑定"),

    UNKNOWN_EXCEPTION(99, "未知异常");

    private int errorCode;
    private String message;

    private SZJErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static SZJErrorCode valueOf(int errorCode) {
        for (SZJErrorCode szjErrorCode : values()) {
            if (szjErrorCode.getErrorCode() == errorCode) {
                return szjErrorCode;
            }
        }
        return null;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
