package com.dima.jdbc.starter.filter;

import com.dima.jdbc.starter.dto.UserPizzeriaDto;
import com.dima.jdbc.starter.util.UrlPath;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.dima.jdbc.starter.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION, LOCALE, PIZZAS);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
                filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse)servletResponse).sendRedirect(LOGIN);
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        UserPizzeriaDto userPizzeriaDto = (UserPizzeriaDto) ((HttpServletRequest) servletRequest)
                .getSession()
                .getAttribute("userPizzeriaDto");
        return userPizzeriaDto != null;
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }


}
