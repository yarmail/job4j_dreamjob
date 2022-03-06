package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Предположим, что на страницу posts.do может переходить
 * только авторизованный пользователь. Тогда в нашем
 * приложении дырка в безопасности.
 *
 * Чтобы её устранить мы будем фильтровать
 * все запросы. Если запрос не связан с пользователем,
 * то будем перенаправлять такой запрос
 * на страницу авторизации.
 * Чтобы этого добиться в Java используется
 * javax.servlet.Filter интерфейс.
 *
 * Интерфейс Filter содержит метод doFilter.
 * Через этот метод будут проходить запросы к сервлетам.
 * В url-pattern можно указать полный путь или маску поиска.
 * * - все запросы.
 * *.html - все запросы с расширением html.
 * Например, index.html, users.html
 * /static/* - запросы содержащие в URL блок /static/.
 * Используется для кеширования css, js, html.
 *
 * Здесь используется маска *.do. *.do -
 * это дань старому фреймворку Struts.
 * Традиция именовать контроллеры осталась.
 * Почему нельзя фильтровать все запросы через * ?
 * В этом случае мы не сможем обрабатывать статические
 * страницы, картинки, таблицы стилей.
 * Для них можно использовать другой фильтр.
 *
 * Так же вспомните принцип единой ответственности SRP.
 * Фильтр должен выполнять задачи для одной группы пользователей.
 * Все запросы с расширением *.do будут обрабатываться
 * нашим фильтром.
 *
 * Если запрос идет на сервлет авторизации,
 * то проверку делать не будем.
 * if (uri.endsWith("auth.do"))
 *
 * Для всех остальных запросов мы проверяем текущего пользователя.
 * Если его нет, то отправляем на страницу авторизации.
 * if (req.getSession().getAttribute("user") == null)
 *
 *
 */
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sreq, ServletResponse sresp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sreq;
        HttpServletResponse resp = (HttpServletResponse) sresp;
        String uri = req.getRequestURI();
        if (uri.endsWith("auth.do") || uri.endsWith("reg.do")) {
            chain.doFilter(sreq, sresp);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        chain.doFilter(sreq, sresp);
    }

    @Override
    public void destroy() {
    }
}