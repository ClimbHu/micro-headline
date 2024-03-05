package com.headline.filters;

import com.headline.common.Result;
import com.headline.common.ResultCodeEnum;
import com.headline.util.JwtHelper;
import com.headline.util.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author: ClimbHu
 * @date: 2024年03月05日 16:39
 */
@WebFilter("/headline/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        boolean flag = null != token && (!JwtHelper.isExpiration(token));
        if (flag) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            WebUtil.writeJson(response, Result.build(null, ResultCodeEnum.NOTLOGIN));
        }
    }
}
