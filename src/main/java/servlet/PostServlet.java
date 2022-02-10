package servlet;

import model.Post;
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
 */
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException {
        req.setAttribute("posts", Store.instOf().findAllPosts());
        req.getRequestDispatcher("posts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("UTF-8");
        Store.instOf().savePost(
                new Post(Integer.valueOf(req.getParameter("id")),
                        req.getParameter("name")));
        resp.sendRedirect(req.getContextPath() + "/posts.do");
    }
}