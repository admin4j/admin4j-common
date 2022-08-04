package com.admin4j.common.core.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author andanyang
 * @since 2022/8/4 13:30
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum implements IResponse {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 业务异常
     */
    FAILURE(400, "业务异常"),


    /**
     *
     */
    FAIL_AUTH_FORBIDDEN(402, "没有权限访问"),

    /**
     *
     */
    FAIL_AUTH(402, "登录失败，账号或者密码错误"),
    /**
     * token 认证失败
     */
    FAIL_AUTH_TOKEN(403, "登录状态已过期"),

    /**
     * token 认证失败
     */
    FAIL_NO_TOKEN(405, "没有访问令牌"),

    /**
     * 未授权
     */
    FAIL_UNAUTHORIZED(406, "未授权"),


    /**
     * 服务未找到
     */
    NOT_FOUND(404, "服务未找到"),

    /**
     * Too Many Requests
     */
    TOO_MANY_REQUESTS(429, "Too Many Requests"),

    /**
     * 验证失败
     */
    VERIFY_ERROR(4000, "VERIFY_ERROR"),

    /**
     * 断言空指针错误
     */
    ASSERT_NULL_ERROR(4001, "ASSERT_NULL_ERROR"),
    /**
     * 空指针错误
     */
    SERVICE_FAIL_NULL(4404, "Server.internal.error code:4404"),

    /**
     * 服务异常
     */
    ERROR(5000, "服务器内部错误"),


    THIRD_PARTY_API_ERROR(5001, "调用第三方接口失败"),
    DEMO_MODE_EXCEPTION(5002, "演示模式，不允许操作"),
    METHOD_ARGUMENT_NOTVALID_EXCEPTION(5003, "表单字段验证失败"),
    VALIDATED_BIND_EXCEPTION(5004, "接口字段绑定失败"),
    MAX_UPLOAD_SIZE_EXCEPTION(5005, "MAX_UPLOAD_SIZE_EXCEPTION"),
    ERROR_E(5006, "服务器内部错误 5006"),

    SERVICE_API_ERROR(5100, "服务调用错误"),
    SERVICE_404(5101, "服务未找到"),
    SERVICE_FALLBACK(5102, "服务降级"),


    BIZ_ERROR(5200, "服务器内部错误 5200"),


    ERROR_SPIDER(5300, "服务器内部错误 5300"),

    REQUEST_THROTTLED_EXCEPTION(5600, "请求速率受限"),
    /**
     * 获取当前用户失败
     */
    CURRENT_USER_FAIL(10001, "获取当前用户失败");

    /**
     * 状态码
     */
    final int code;
    /**
     * 消息内容
     */
    final String msg;

}
