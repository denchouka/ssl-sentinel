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
     * token加密  salt
     */
    public static final String TOKEN_SECRET_SALT = "DENCHOUKAhelen";

    /**
     * token过期时间（1H）
     */
    public static final long TOKEN_EXPIRE_DATE = 60 * 60;

    /**
     * 请求的url:login
     */
    public static final String REQUEST_URL_LOGIN = "/login";

    /**
     * 请求头 Content-Type
     */
    public static final String REQUEST_HEADER_CONTENT_TYPE = "Content-Type";

    /**
     * 请求头 Access-Token
     */
    public static final String REQUEST_HEADER_ACCESS_TOKEN = "Access-Token";

    /**
     * 请求头 Authorization
     */
    public static final String REQUEST_HEADER_AUTHORIZATION = "Authorization";

    /**
     * 预检请求头
     */
    public static final String REQUEST_HEADER_PRE_FLIGHT = "access-control-request-headers";

    /**
     * 请求头 User-Name
     */
    public static final String REQUEST_HEADER_USER_NAME = "User-Name";

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
}