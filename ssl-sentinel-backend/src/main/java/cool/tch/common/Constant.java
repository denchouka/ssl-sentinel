package cool.tch.common;

/**
 * @author denchouka
 * @description 一些常量
 * @date 2024/10/26 21:39
 */
public class Constant {

    /**
     * token密钥
     */
    public static final String TOKEN_SECRET_KEY = "tianchaohuaweiyueying";

    /**
     * token AES加密密钥
     * AES加密默认支持128(16字节)位密钥长度，在十六进制编码下就是32个字符
     */
    public static final String TOKEN_SECRET_AES_KEY = "tianchaohua1994weiyueying1997mmd";

    /**
     * token加密  salt
     */
    public static final String TOKEN_SECRET_SALT = "DENCHOUKAhelen";

    /**
     * token过期时间（1H）
     */
    public static final long TOKEN_EXPIRE_DATE = 60 * 60;

    /**
     * 失效token清理的cron表达式（每小时1次）
     */
    public static final String TOKEN_REVOKE_CRON = "0 0 * * * ?";

    /**
     * 请求的url:login
     */
    public static final String REQUEST_URL_LOGIN = "/login";

    /**
     * 请求的url:logout
     */
    public static final String REQUEST_URL_LOGOUT = "/logout";

    /**
     * 请求的url:error
     */
    public static final String REQUEST_URL_ERROR = "/error";

    /**
     * 不需要校验token的请求url
     */
    public static final String[] REQUEST_URL_NO_VERIFY = {
            REQUEST_URL_LOGIN,
            REQUEST_URL_LOGOUT,
            REQUEST_URL_ERROR
    };

    /**
     * 请求头 Content-Type
     */
    public static final String REQUEST_HEADER_CONTENT_TYPE = "content-type";

    /**
     * 请求头 Access-Token
     */
    public static final String REQUEST_HEADER_ACCESS_TOKEN = "access-token";

    /**
     * 请求头 Authorization
     */
    public static final String REQUEST_HEADER_AUTHORIZATION = "authorization";

    /**
     * 预检请求头
     */
    public static final String REQUEST_HEADER_PRE_FLIGHT = "access-control-request-headers";

    /**
     * 预检请求方法
     */
    public static final String REQUEST_METHOD_PRE_FLIGHT = "OPTIONS";

    /**
     * 请求头 User-Name
     */
    public static final String REQUEST_HEADER_USER_NAME = "user-name";

    /**
     * 请求源 localhost
     */
    public static final String REQUEST_ORIGINS_LOCALHOST = "http://localhost:5173";

    /**
     * 请求源 域名
     */
    public static final String REQUEST_ORIGINS_DOMAIN = "https://ssl.tch.cool";

    /**
     * 请求方法 GET
     */
    public static final String REQUEST_METHOD_GET = "GET";

    /**
     * 请求方法 POST
     */
    public static final String REQUEST_METHOD_POST = "POST";

    /**
     * 跨域的响应报头
     */
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "access-control-expose-headers";
}
