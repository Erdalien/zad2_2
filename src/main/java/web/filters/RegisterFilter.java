package web.filters;

import validate.Validator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(servletNames = "register", filterName = "RegisterFilter")
public class RegisterFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        if(httpRequest.getParameter("registerBtn") == null) {
            if(session.getAttribute("profile") == null) {
                ((HttpServletResponse) response).sendRedirect("/register.jsp");
            } else {
                ((HttpServletResponse) response).sendRedirect("/profile.jsp");
            }
        } else {
            String validationResult = new Validator().checkData(httpRequest, httpResponse);
            if (validationResult.equals("Good job!!!")) {
                chain.doFilter(request, response);
            } else {
                request.setAttribute("errorMessage", validationResult);
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
        }
    }
    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }
}