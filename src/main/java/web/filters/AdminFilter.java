package web.filters;

import domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", servletNames = "admin")
public class AdminFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        httpResponse.setContentType("text/html; charset=UTF-8");

        if (session.getAttribute("profile") == null) {
            request.setAttribute("errorMessage", "<font color=red>Access denied, log in for first</font>");
            request.getRequestDispatcher("/").forward(request, response);
        } else {
            User user = (User) session.getAttribute("profile");
            if (user.getIsAdmin()) {
                chain.doFilter(request, response);
            } else {
                request.setAttribute("errorMessage", "<font color=red>Access denied, don't have permission</font>");
                request.getRequestDispatcher("/").forward(request, response);
            }
        }
    }
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}