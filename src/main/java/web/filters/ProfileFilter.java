package web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "ProfileFilter", servletNames = "profile")
public class ProfileFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        httpResponse.setContentType("text/html; charset=UTF-8");

        if (session.getAttribute("profile") == null) {
            request.setAttribute("errorMessage", "<font color=red>Access denied, log in for first.</font>");
            RequestDispatcher rs = request.getRequestDispatcher("/");
            rs.forward(request,response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/profile.jsp");
        }
    }
    public void destroy() {

    }
    public void init(FilterConfig fConfig) throws ServletException {

    }
}