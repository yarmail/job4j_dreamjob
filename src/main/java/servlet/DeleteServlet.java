package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File fileToDelete = null;
        String nameForDelete = req.getParameter("id");
        System.out.printf("******" + nameForDelete);
        for (File file: new File("c:/images/").listFiles()) {
            if (nameForDelete.equals(file.getName())) {
                fileToDelete = file.getAbsoluteFile();
            }
            break;
        }
        Files.delete(fileToDelete.toPath());
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}