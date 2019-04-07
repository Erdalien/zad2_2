package web.servlets;

import domain.User;
import repository.DummyUserRepository;
import repository.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UserRepository repository;

    public void init(ServletConfig config) throws ServletException {
        repository = new DummyUserRepository();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uName = request.getParameter("name");
        User user = repository.getUserByName(uName);

        HttpSession session = request.getSession();
        session.setAttribute("profile", user);
        response.sendRedirect("/profile.jsp");
    }
}