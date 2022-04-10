package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 6. CORS [#305092]
 * 1. Простой запрос CORS.
 * В соответствии со спецификацией CORS,
 * HTTP-запрос является простым CORS-запросом,
 * если метод запроса GET, POST или HEAD.
 * Поля заголовка запроса могут быть любыми из следующих:
 * - Accept;  - Accept-Language;  - Content-Language;
 * - Content-Type.
 * При этом важно, что допустимыми значениями
 * заголовка Content-Type являются:
 * - application/x-www-form-urlencoded;
 * - multipart/form-data;
 * - text/plain.
 * Когда клиент отправляет простые запросы CORS на сервер с
 * разных доменов, клиент включает Origin заголовок с именем
 * хоста клиента в качестве значения. Если сервер разрешает
 * источник, сервер включает Access-Control-Allow-Origin
 * в ответ клиенту заголовок со списком разрешенных
 * источников или звездочку (*). Звездочка указывает,
 * что всем источникам разрешен доступ к конечной точке на сервере.
 *
 * 2. Предварительный запрос CORS.
 * Предварительные запросы сначала отправляют HTTP-запрос
 * методом OPTIONS к ресурсу на другом домене, чтобы определить,
 * является ли фактический запрос безопасным для отправки.
 * Междоменные запросы предварительно просматриваются таким образом,
 * так как они могут быть причастны к пользовательским данным.
 * Предварительный запрос просматривается, если
 * выполняется любое из условий:
 * 1. В запросе используется любой из следующих методов:
 * PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH;
 * 2. Если запрос включает любые заголовки, отличные от тех,
 * которые были упомянуты для простых CORS-запросов выше;
 * 3. Если заголовок Content-Type содержит значение,
 * которое отлично от тех, которые были упомянуты для
 * простых CORS-запросов выше.
 *
 * в Header ответа мы добавляем заголовок, разрешено
 * выполнять междоменные запросы для всех доменов, т.е.
 * мы работаем без исключения.
 * ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
 *
 * в Header ответа мы добавляем заголовок
 * в котором указываем те методы, которые разрешены при доступе к ресурсу.
 * ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
 *
 *  проверяем метод, который содержит наш запрос,
 *  и если это OPTIONS (как мы помним с таким методом отправляется
 *  предварительный CORS-запрос) и если условие выполняется -
 *  мы просто отправляем запрос со статусом ACCEPTED.
 * if (request.getMethod().equals("OPTIONS"))
 */

public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");

        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (request.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }

        chain.doFilter(request, servletResponse);
    }
}