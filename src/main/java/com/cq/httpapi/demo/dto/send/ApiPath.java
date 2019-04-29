package com.cq.httpapi.demo.dto.send;

public enum ApiPath {

    SEND_PRIVATE_MESSAGE("http://localhost:5700/send_private_msg"),
    SEND_GROUP_MESSAGE("http://localhost:5700/send_group_msg"),
    SEND_DISCUSS_MESSAGE("http://localhost:5700/send_discuss_msg"),
    SEND_MESSAGE("http://localhost:5700/send_msg"),
    DELETE_MESSAGE("http://localhost:5700/delete_msg"),
    SEND_LIKE("http://localhost:5700/send_like"),
    SET_GROUP_KICK("http://localhost:5700/set_group_kick"),
    SET_GROUP_BAN("http://localhost:5700/set_group_ban"),
    SET_GROUP_ANONYMOUS_BAN("http://localhost:5700/set_group_anonymous_ban"),
    SET_GROUP_WHOLE_BAN("http://localhost:5700/set_group_whole_ban"),
    SET_GROUP_ADMIN("http://localhost:5700/set_group_admin"),
    SET_GROUP_ANONYMOUS("http://localhost:5700/set_group_anonymous"),
    SET_GROUP_CARD("http://localhost:5700/set_group_card"),
    SET_GROUP_LEAVE("http://localhost:5700/set_group_leave"),
    SET_GROUP_SPECIAL_TITLE("http://localhost:5700/set_group_special_title"),
    SET_DISCUSS_LEAVE("http://localhost:5700/set_discuss_leave"),
    SET_FRIEND_ADD_REQUEST("http://localhost:5700/set_friend_add_request"),
    SET_GROUP_ADD_REQUEST("http://localhost:5700/set_group_add_request"),
    GET_LOGIN_INFO("http://localhost:5700/get_login_info"),
    GET_STRANGER_INFO("http://localhost:5700/get_stranger_info"),
    GET_GROUP_LIST("http://localhost:5700/get_group_list"),
    GET_GROUP_MEMBER_INFO("http://localhost:5700/get_group_member_info"),
    GET_GROUP_MEMBER_LIST("http://localhost:5700/get_group_member_list"),
    GET_COOKIES("http://localhost:5700/get_cookies"),
    GET_CSRF_TOKEN("http://localhost:5700/get_csrf_token"),
    GET_CREDENTIALS("http://localhost:5700/get_credentials"),
    GET_RECORD("http://localhost:5700/get_record"),
    GET_STATUS("http://localhost:5700/get_status"),
    GET_VERSION_INFO("http://localhost:5700/get_version_info"),
    SET_RESTART("http://localhost:5700/set_restart"),
    SET_RESTART_PLUGIN("http://localhost:5700/set_restart_plugin"),
    CLEAN_DATA_DIR("http://localhost:5700/clean_data_dir"),
    CLEAN_PLUGIN_LOG("http://localhost:5700/clean_plugin_log"),
    _GET_FRIEND_LIST("http://localhost:5700/_get_friend_list"),
    _GET_GROUP_INFO("http://localhost:5700/_get_group_info"),
    _GET_VIP_INFO("http://localhost:5700/_get_vip_info");

    private String urlPath;

    private ApiPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getUrlPath() {
        return urlPath;
    }
}
