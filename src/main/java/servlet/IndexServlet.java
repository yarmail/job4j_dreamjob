package servlet;

import store.DbStore;
import store.Store;

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
 * ----
 * Подготовил в БД соотвествующие методы, для показа
 * последних кандидатов и вакакнсии
 * findLastCandidates() и findLastPosts()
 * В учебных и тестовых целях заменил их на показ всех
 * кандидатов и вакансий
 */

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final Store store = DbStore.instOf();
        req.setAttribute("lastCandidates", store.findAllCandidates());
        req.setAttribute("lastPosts", store.findAllPosts());
        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }
}