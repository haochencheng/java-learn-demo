package pers.cc.crm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import pers.cc.crm.util.ResopnseUtil;

/**
 * 全局异常处理器
 * 
 * @author 郝晨成
 * @company china-soa
 * @createTime 2016年11月19日
 * @projectName SoaMdm
 * @className CustomExceptionResolver
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mav = new ModelAndView();
        // handler就是处理器适配器要执行Handler对象（只有一个method）
        // 解析出异常类型
        // 如果该异常类型是系统自定义的异常，直接取出异常信息，在错误页面展示
        LoginException loginException = null;
        CommonException commonException = null;
        JSONObject result = new JSONObject();
        String message = "";
        if (ex instanceof LoginException) {
            loginException = (LoginException) ex;
            mav.addObject(request.getAttribute("user"));
            message = loginException.getMessage();
            // 输出错误信息到登陆页面
            mav.addObject("errorInfo", message);
            mav.setViewName("login");
            return mav;
        } else if (ex instanceof CommonException) {
            commonException = (CommonException) ex;
            message = commonException.getMessage();
            result.put("errorMsg", message);
            ResopnseUtil.write(response, result);
        }
        message = ex.getMessage();
        mav.addObject("errorMsg", message);
        mav.setViewName("error/error");
        return mav;
    }

}
