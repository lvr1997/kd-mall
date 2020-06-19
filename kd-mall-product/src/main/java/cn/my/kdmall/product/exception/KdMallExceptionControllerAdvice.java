package cn.my.kdmall.product.exception;

import cn.my.kdmall.common.exception.BizCode;
import cn.my.kdmall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 捕获异常
 */
@Slf4j
@RestControllerAdvice(basePackages = "cn.my.kdmall.product.controller")
public class KdMallExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验异常", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> map = new HashMap<>();
        if(bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach((item) -> {
                //获取到错误提示
                String message = item.getDefaultMessage();
                //获取错误属性的名字
                String field = item.getField();
                map.put(field, message);
            });

        }
        return R.error(BizCode.VAILD_EXCEPTION.getCode(), BizCode.VAILD_EXCEPTION.getMsg()).put("data", map);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException() {

        return R.error(BizCode.UNKNOW_EXCEPTION.getCode(), BizCode.UNKNOW_EXCEPTION.getMsg());
    }

}
