package servlet;

import model.User;
import store.DbStore;
import store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static java.util.Objects.nonNull;

public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        if (nonNull(DbStore.instOf().findByEmailUser(email))) {
            req.setAttribute("error", "Пользователь с данным паролем уже зарегистрирован");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        } else {
            User user = new User(0, name, email, password);
            Store store = DbStore.instOf();
            store.saveUser(user);
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}