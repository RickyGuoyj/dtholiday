package com.eva.dtholiday.security.filter;


import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
import com.eva.dtholiday.commons.exception.BusinessException;
import com.eva.dtholiday.security.entity.DtHolidayUser;
import com.eva.dtholiday.security.utils.TokenCache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
                        } catch (Exception e) {
                            logger.error("parse error", e);
                        }
                        filterChain.doFilter(request, response);
                        return;
                    } else {
                        throw new BusinessException(BusinessErrorCodeEnum.UNAUTHORIZED.getMessageCN(), BusinessErrorCodeEnum.UNAUTHORIZED.getCode());
                    }
                }
            }
            filterChain.doFilter(request, response);
        }
    }

    private boolean isIgnoreHttpUrls(HttpServletRequest request) {
        return request.getServletPath().contains("portal");
    }

    private boolean isLoginRequest(HttpServletRequest request) {
        return "POST".equals(request.getMethod()) && "/login".equals(request.getServletPath());
    }
}

