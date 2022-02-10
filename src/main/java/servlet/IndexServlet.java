package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 0. MVC в Servlet. [#6868]
 * В сервлете переопределен метод doGet,
 * а не doPost как было в сервлете PostServlet.
 *
 * Когда в браузере открывается любая ссылка,
 * он отправляет http запрос с типом GET.
 *
 * Когда в браузере отправляется form нужно
 * использовать метод doPost.
 *
 * ServletException возник в методе doGet
 * .forward
 *
 * В самом теле метода, мы перенаправляем запрос в index.jsp.
 * req.getRequestDispatcher("index.jsp").forward(req, resp);
 *
 * Сначала пишем сервлет, а потом прописываем его
 * в web.xml
 *
 * В request и response можно добавить информацию,
 * для отображения на JSP. Мы это покажем ниже.
 *
 *
 */
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}