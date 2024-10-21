package cool.tch.exception;

import cool.tch.common.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static cool.tch.common.ResponseCode.ERROR;
import static cool.tch.common.ResponseCode.ERROR_DEFAULT;

/**
 * @author blue
 * @date 2022/3/11
 * @apiNote
 */
@ControllerAdvice(basePackages = "cool.tch")
public class GlobalException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    /**
     * Assert业务异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseResult AssertExceptionHandler(IllegalArgumentException ex) {
        logger.error( " msg : " + ex.getMessage(), ex);
        if(StringUtils.isBlank(ex.getLocalizedMessage())){
            return ResponseResult.error(ERROR.getCode(),ERROR.getMessage());
        }
        return ResponseResult.error(ex.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseResult BusinessExceptionHandler(BusinessException ex) {
        if (ex.getCode() != -1) {
            logger.error("code : " + ex.getCode() + " msg : " + ex.getMessage(), ex);
        }
        if(StringUtils.isBlank(ex.getLocalizedMessage())||StringUtils.isBlank(ex.getMessage())){
            return ResponseResult.error(ERROR.getCode(), ERROR.getMessage());
        }
        return ResponseResult.error(ex.getCode(), ex.getMessage());
    }

    /**
     * java异常异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult ExceptionHandler(Exception ex) {
        logger.error( " msg : " + ex.getMessage(), ex);
        if(StringUtils.isBlank(ex.getLocalizedMessage())){
            return ResponseResult.error(ERROR.getCode(),ERROR.getMessage());
        }
        return ResponseResult.error(ERROR_DEFAULT.getCode(), ERROR_DEFAULT.getMessage());
    }
}
