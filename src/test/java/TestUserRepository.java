import database.ConnectionDatabase;
import domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import repository.UserRepository;
import web.filters.RegisterFilter;
import web.servlets.LoginServlet;
import web.servlets.RegisterServlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class TestUserRepository {

    @RunWith(MockitoJUnitRunner.class)
    public class TestLoginServlet extends Mockito {

        @Spy
        UserRepository repository = mock(UserRepository.class);

        @InjectMocks
        LoginServlet servlet;

        @Test
        public void servletShouldWriteInfoAboutNewRegisterUserIntoSession() throws IOException, ServletException {
            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);
            HttpSession session = mock(HttpSession.class);
            when(request.getSession()).thenReturn(session);
            servlet.doPost(request, response);
            verify(session).setAttribute(eq("profile"), Mockito.any(User.class));
        }
    }
    public class TestRegisterServlet extends Mockito {

        @Spy
        UserRepository repository = mock(UserRepository.class);

        @InjectMocks
        RegisterServlet servlet;

    }
    public class TestConnectionDatabase extends Mockito {

        @Test
        public void shouldReturnConnectionDatabase() throws Exception {
            Connection con;
            con = ConnectionDatabase.connect();
            Assert.assertNotNull(con);
            if (con != null) {
                con.close();
            }
        }
    }
    public class TestRegisterFilter extends Mockito {

        @InjectMocks
        RegisterFilter filter;

        @Test
        public void filterShouldCheckExpressions() throws IOException, ServletException {
            ServletRequest request = mock(ServletRequest.class);
            ServletResponse response = mock(ServletResponse.class);
            FilterChain chain = mock(FilterChain.class);
            when(request.getParameter("registerBtn")).thenReturn("/register.jsp");
            filter.doFilter(request, response, chain);
            verify(response).equals("/register.jsp");
        }
    }
}