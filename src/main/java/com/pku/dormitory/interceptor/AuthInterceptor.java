package com.pku.dormitory.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pku.dormitory.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 继承HandlerInterceptorAdapter类为过时方法
 * 因此选择实现HandlerInterceptor接口
 *
 * @author Yonmin
 * @date 2022/12/6 20:31
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * 拦截于请求刚进入时，进行判断
     * 需要boolean返回值，如果返回true将继续执行，如果返回false，将不进行执行
     * 一般用于登录校验。
     *
     * @param request  请求
     * @param response 响应
     * @param handler  响应的处理器，即自定义Controller
     * @return true
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获取请求头
        String header = request.getHeader("Authorization");
        // 请求头不为空进行解析
        if (StringUtils.isNotBlank(header)) {
            // 按照我们和前端约定的格式进行处理
            if (header.startsWith("Bearer ")) {
                // 得到令牌
                String accessToken = header.substring(7);

                // 验证令牌
                try { // 令牌的解析这里一定的try起来,因为它解析错误的令牌时,会报错
                    // 当然你也可以在自定义的jwtUtil中把异常 try起来,这里就不用写了
                    JwtUtils.getClaimsByToken(accessToken).getSubject();
                    request.setAttribute("access_token", accessToken);
                    return true;
                } catch (Exception e) {
                    response.setStatus(401); //无权限访问
                    return false;
                }
            }
        }
        return true;
    }

}
