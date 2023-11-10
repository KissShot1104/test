package test.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String redirectURI = request.getRequestURI();
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/members/login?redirectURI=" + redirectURI);
            return false;
        }
        return true;
    }
}
