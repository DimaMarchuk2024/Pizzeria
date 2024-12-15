package com.dima.jdbc.starter.filter;

import com.dima.jdbc.starter.dto.UserPizzeriaDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.dima.jdbc.starter.util.UrlPath.*;

@WebFilter({INGREDIENTS})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserPizzeriaDto userPizzeriaDto = (UserPizzeriaDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("userPizzeriaDto");

        if (userPizzeriaDto != null && userPizzeriaDto.getRoleName().equals("Admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : PIZZAS);
        }
    }
}
