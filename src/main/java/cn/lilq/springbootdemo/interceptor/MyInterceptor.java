package cn.lilq.springbootdemo.interceptor;

import cn.lilq.springbootdemo.controller.BookCon;
import cn.lilq.springbootdemo.pojo.Book;
import cn.lilq.springbootdemo.pojo.Response;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/7 23:09
 */
public class MyInterceptor implements HandlerInterceptor {
    //logger
    private static Logger logger = LoggerFactory.getLogger(MyInterceptor.class);


    /**
     * 拦截器预处理 控制器方法未执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("拦截器预处理");
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            PrintWriter writer = null;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            try {
                writer = response.getWriter();
                writer.print(new Gson().toJson(new Response("error token",(List<Book>) null,null,null,null)));
            } catch (IOException e) {
            } finally {
                if (writer != null)
                    writer.close();
            }
            return false;
        }else{
            return true;
        }
    }

    /**
     * 控制器方法执行完毕
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("控制器方法执行完毕");
    }


    /**
     * 处理完成
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.debug("finish");
    }
}
