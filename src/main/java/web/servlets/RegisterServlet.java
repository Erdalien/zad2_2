package web.servlets;

import domain.User;
import repository.DummyUserRepository;
import repository.UserRepository;
import validate.Validator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "register", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserRepository repository;

    public void init(ServletConfig config) throws ServletException {
        repository = new DummyUserRepository();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new Validator().retrieveUserFromRequest(request);

        if (user.getuName().equals("Admin") && user.getPassword().equals("123456*a")) {
            user.setIsAdmin(true);
        } else {
            user.setIsAdmin(false);
        }
        user.setIsPremium(false);

        repository.add(user);
        response.sendRedirect("login.jsp");
    }
}