package web.filters;

import validate.Validator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(servletNames = "login",filterName = "LoginFilter")
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        httpResponse.setContentType("text/html; charset=UTF-8");

        if(httpRequest.getParameter("loginBtn") == null){
            if(session.getAttribute("profile") == null) {
                ((HttpServletResponse) response).sendRedirect("/login.jsp");
            } else {
                ((HttpServletResponse) response).sendRedirect("/profile.jsp");
            }
        } else {
            String checkResult = new Validator().checkNameAndPass(httpRequest, httpResponse);
            if (checkResult.equals("Good job!!!")) {
                chain.doFilter(request, response);
            } else {
                request.setAttribute("errorMessage", checkResult);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}