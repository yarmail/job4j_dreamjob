package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import settings.Settings;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Метод doGet отображает список доступных файлов.
 * Метод doPost загружает выбранный файл на сервер в папку c:\\images\\
 * Порядок загрузки файла на сервер.
 *
 * 1. Вначале мы создаем фабрику по которой можем понять,
 * какие данные есть в запросы. Данные могу быть: поля или файлы.
 *
 * 2. Получаем список всех данных в запросе.
 * List<FileItem> items = upload.parseRequest(req);
 *
 * 3. Если элемент не поле, то это файл и из него
 * можно прочитать весь входной поток и записать
 * его в файл или напрямую в базу данных.
 *    File file = new File(folder + File.separator + item.getName());
 *    try (FileOutputStream out = new FileOutputStream(file)) {
 *    out.write(item.getInputStream().readAllBytes());
 *    }
 *
 * 4. После этого мы переходим в метод doGet,
 * где отдаем список всех файлов.
 * В этом списке содержатся только имена.
 *    for (File name : new File("c:\\images\\").listFiles()) {
 *        images.add(name.getName());
 *    }
 *         req.setAttribute("images", images);
 */
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> images = new ArrayList<>();
        for (File name : new File("c:\\images\\").listFiles()) {
            images.add(name.getName());
        }
        req.setAttribute("images", images);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/upload.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File(Settings.getImagePath());
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    File file = new File(folder + File.separator + item.getName());
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }
}