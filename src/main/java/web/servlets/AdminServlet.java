package web.servlets;

import domain.User;
import repository.DummyUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@WebServlet(name = "admin", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("saveData") != null) {
            request.getParameterMap();
            String[] userList = request.getParameterValues("name");
            String[] checkedList = request.getParameterValues("isPremium") != null ? request.getParameterValues("isPremium") : new String [0];
            new DummyUserRepository().updatePremiumStatus(userList, checkedList);
        }

        List<User> userList = new DummyUserRepository().getAllUserData();
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}