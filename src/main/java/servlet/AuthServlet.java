package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Примечания
 * Сервлет проверяет почту и пароль, если они совпадают,
 * то переходит на страницу вакансий.
 * Если нет, то возвращает обратно на страницу Login.
 * ---------
 *
 * Когда браузер отправляет запрос в tomcat
 * создается объект HttpSession.
 * Этот объект связан с работой текущего пользователя.
 * Если вы открете другой браузер и сделаете новый запрос,
 * то tomcat создаст еще один объект HttpSession,
 * который уже будет связан с другим пользователем.
 * В объекте HttpSession
 * можно хранить информацию о текущем пользователе.
 *
 * Создадим класс User
 * Доработаем сервлет AuthServlet
 * Если пользователь ввел верную почту и пароль,
 * if ("root@local".equals(email) && "root".equals(password))
 * то мы записываем в HttpSession детали этого пользователя.
 * Теперь эти данные можно получить на другой странице.
 * ---------------
 * Переделываем форму, чтобы любой мог войти,
 * но по идее надо добавлять доп. метод в DbStore
 * по поиску через email
 * Было:
 * if ("root@local".equals(email) && "root".equals(password))
 * Стало:
 * if (email != null && password != null)
 */
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email != null && password != null) {
            HttpSession sc = req.getSession();
            User user = new User();
            user.setName(email);
            user.setEmail(email);
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/posts.do");
        } else {
            req.setAttribute("error", "Неверный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}