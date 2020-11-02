package wxx.java.appraise.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;


public class TimeInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);

    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在
     */

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);


        if (handler instanceof HandlerMethod) {

            StringBuilder sb = new StringBuilder(1000);
            sb.append("--------------------------------").append("time").append("------------------------\n");
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            sb.append("Controller:").append(handlerMethod.getBean().getClass().getName()).append("\n");
            sb.append("Method:").append(handlerMethod.getMethod().getName()).append("\n");
            sb.append("param:").append(getParamString(request.getParameterMap())).append("\n");
            sb.append("URL:").append(request.getRequestURL());
            logger.debug(sb.toString());

        }

        return true;
    }


    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> e : map.entrySet()) {
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if (value != null && value.length == 1) {
                sb.append(value[0]).append("\t");
            } else {
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }

    /**
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之
     * 后，也就是在Controller的方法调用之后执行
     */

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();

        long executeTime = endTime - startTime;

        if (handler instanceof HandlerMethod) {
            StringBuilder stringBuilder = new StringBuilder(1000);
            stringBuilder.append("CostTime:").append(executeTime).append("\n");
            logger.debug(stringBuilder.toString());
        }

    }

    /**
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
