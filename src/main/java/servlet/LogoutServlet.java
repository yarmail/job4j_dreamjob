package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * мы получаем сессию в следующей строке
 * HttpSession session = req.getSession();
 * а потом у этой сессии вызываем метод
 * invalidate() который очищает сессию
 * и удаляет все добавленные ранее в нее атрибуты.
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
