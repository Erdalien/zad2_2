package validate;

import domain.User;
import repository.DummyUserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Validator {

    public String checkData(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html; charset=UTF-8");

        String uName = request.getParameter("name");
        String pass = request.getParameter("pass");
        String cPass = request.getParameter("cPass");
        String email = request.getParameter("mail");

        uName =  uName != null ? uName.trim() : "";
        pass = pass != null ? pass.trim() : "";
        cPass = cPass != null ? pass.trim() : "";
        email = email != null ? email.trim() : "";

        if (uName.equals("") || pass.equals("") || cPass.equals("") || email.equals("")) {
            return "<font color=red>Pelase, fill all filds first.</font>";
        }
        if (!pass.equals(cPass)) {
            return "<font color=red>Please, type the same password.</font>";
        }
        if (new DummyUserRepository().getUserByName(uName) != null) {
            return "<font color=red>This username already exist. Type another nickname</font>";
        }
        return "Registration complete";
    }

    public String checkNameAndPass(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html; charset=UTF-8");

        String uName = request.getParameter("name");
        String pass = request.getParameter("pass");
        uName = uName != null ? uName.trim() : "";
        pass = pass != null ? pass.trim() : "";

        if (uName.equals("") || pass.equals("")) {
            return "<font color=red>Pelase, fill all filds first</font>";
        }

        if (!(new DummyUserRepository().checkPass(uName, pass))) {
            return "<font color=red>User or password is incorect, if you don't have an account, register. .</font>";
        }
        return "You are logged";
    }

    public User retrieveUserFromRequest(HttpServletRequest request) {
        User result = new User();
        result.setuName(request.getParameter("name"));
        result.setPassword(request.getParameter("pass"));
        result.setEmail(request.getParameter("mail"));
        return result;
    }
}