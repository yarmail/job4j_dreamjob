package servlet;

import model.Post;
import store.DbStore;
import store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. Servlet. Web.xml [#6866]
 * JSP используется для отображения информации.
 * Обрабатывать в них запросы не удобно.
 * Servlet позволяет обработать запрос
 * клиента и отправить ему ответ.
 *
 * Сервлет это обычный Java класс, который должен
 * наследоваться от абстрактного класса HttpServlet.
 *
 * В сервлете нам доступны два объекта HttpServletRequest
 * и HttpServletResponse. Через них мы можем получить
 * данные и отправить.
 *
 * Метод getParameter позволяет получить значения в запросе.
 * (получить параметр name у запроса)
 * req.getParameter("name")
 *
 * А как edit.jsp узнает куда отправлять данные?
 * Чтобы JSP отправляла данные на сервер нужно в теге form
 * указать адрес сервлета.
 *
 * После того, как сервлет выполнил свою работу
 * мы просим сервер отправить другой запрос,
 * но уже к странице posts.jsp.
 * resp.sendRedirect(req.getContextPath() + "/posts.jsp");
 *
 * В методе doGet мы загружаем в request список вакансий.
 * req.setAttribute("posts", Store.instOf().findAllPosts());
 *
 * Обратите внимание в методе doPost тоже изменен адрес.
 * resp.sendRedirect(req.getContextPath() + "/posts.do");
 *
 * (есть тесты c использованием Mockito)
 *
 */
public class PostServlet extends HttpServlet {

    private final Store store = DbStore.instOf();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String edit = req.getParameter("edit");
        String path = edit != null ? "/post/edit.jsp" : "posts.jsp";
        req.setAttribute("user", req.getSession().getAttribute("user"));
        if (edit == null) {
            req.setAttribute("posts", store.findAllPosts());
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Post post = new Post(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name")
        );
        store.savePost(post);
        resp.sendRedirect(req.getContextPath() + "/posts.do");
    }
}