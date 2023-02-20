package com.pku.dormitory.interceptor;

import com.alibaba.fastjson.JSON;
import com.pku.dormitory.mapper.SysMapper;
import com.pku.dormitory.mapper.UserMapper;
import com.pku.dormitory.mapper.UserRoomMapper;
import com.pku.dormitory.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 如果禁止组队或者用户已经选取宿舍则拦截请求
 *
 * @author Yonmin
 * @date 2022/12/20 10:53
 */
public class TeamInterceptor implements HandlerInterceptor {

    @Resource
    SysMapper sysMapper;

    @Resource
    UserRoomMapper userRoomMapper;

    @Resource
    UserMapper userMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ("1".equals(sysMapper.getTeamLimit())) return generateErrorResponse(response, "不允许组队");

        String header = request.getHeader("Authorization");
        System.out.println(header);

        System.out.println((String) request.getAttribute("access_token"));

        String username = JwtUtils.getClaimsByToken((String) request.getAttribute("access_token")).getSubject();
        Integer userId = userMapper.getIdByUsername(username);
        if (userRoomMapper.existsUserId(userId)) return generateErrorResponse(response, "已分配宿舍，禁止使用队伍功能");

        return true;
    }

    private boolean generateErrorResponse(HttpServletResponse response, String description) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        Map<String, String> result = new HashMap<>(1);
        result.put("error_description", description);

        response.getWriter().write(JSON.toJSONString(result));
        return false;
    }
}
