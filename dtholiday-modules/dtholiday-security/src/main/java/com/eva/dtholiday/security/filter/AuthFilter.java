package com.eva.dtholiday.security.filter;


import com.alibaba.fastjson.JSON;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
import com.eva.dtholiday.commons.exception.BusinessException;
import com.eva.dtholiday.security.entity.DtHolidayUser;
import com.eva.dtholiday.security.utils.TokenCache;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/20 15:58
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Component
@AllArgsConstructor
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    public static final List<String> ignoreHttpUrls = Lists.newArrayList("/login", "/portal/", "/swagger-ui.html", "/swagger-ui.html/",
            "/swagger-resources/", "/webjars/", "/v2/api-docs", "/portal", "/swagger-resources");

    /**
     * 1.判断是否为登录接口
     * 2.判断是否为放行接口
     * 3.判断token合法性
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (isLoginRequest(request)) {
            //登录额外业务处理（登录锁定，验证码等）
            filterChain.doFilter(request, response);
            return;
        } else {
            //判断是否为放行接口
            if (!isIgnoreHttpUrls(request)) {
                String token = request.getHeader("token");
                // 如果携带token，则校验token的合法性
                if (StringUtils.hasText(token)) {
                    // 从缓存中获取token和userId的键值对，如果有的话说明token有效
                    if (TokenCache.getUserIdByToken(token) != null) {
                        try {
                            DtHolidayUser tUser = TokenCache.getUserIdByToken(token);
                            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(tUser, null, tUser.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        } catch (BusinessException e) {
                            logger.error("parse error", e);
                        }
                        filterChain.doFilter(request, response);
                        return;
                    } else {
                        try {
                            throw new BusinessException(BusinessErrorCodeEnum.UNAUTHORIZED.getMessageCN(), BusinessErrorCodeEnum.UNAUTHORIZED.getCode());
                        } catch (BusinessException e) {
                            log.error("token info is error : request path is {},token is :{}", request.getServletPath(), token);
                            buildResponse(response, BusinessErrorCodeEnum.UNAUTHORIZED.getMessageCN(), e.getErrorCode(), e.getMessage());
                            return;
                        }
                    }
                } else {
                    try {
                        throw new BusinessException(BusinessErrorCodeEnum.UNAUTHORIZED.getMessageCN(), BusinessErrorCodeEnum.UNAUTHORIZED.getCode());
                    } catch (BusinessException e) {
                        log.error("token info is error : request path is {},token is :{}", request.getServletPath(), token);
                        buildResponse(response, BusinessErrorCodeEnum.UNAUTHORIZED.getMessageCN(), e.getErrorCode(), e.getMessage());
                        return;
                    }
                }
            }
            filterChain.doFilter(request, response);
        }
    }

    private boolean isIgnoreHttpUrls(HttpServletRequest request) {
        boolean rt = false;
        String requestPath = request.getServletPath();
        for (String ihu : ignoreHttpUrls) {
            if (requestPath.contains(ihu)) {
                rt = true;
                break;
            }
        }
        return rt;
    }

    private boolean isLoginRequest(HttpServletRequest request) {
        return "POST".equals(request.getMethod()) && "/login".equals(request.getServletPath());
    }

    private void buildResponse(HttpServletResponse response, Object data, String code, String message) {
        response.setStatus(HttpStatus.OK.value());
        ResponseApi r = ResponseApi.error(data, code, message);
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        try {
            response.getWriter().print(JSON.toJSONString(r));
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

