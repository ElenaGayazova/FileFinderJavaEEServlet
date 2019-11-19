package servlet;

import course.DirectoryService;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/homeDir")
public class HomeDirServlet extends HttpServlet {

    @EJB
    DirectoryService service;

    private final String tag = "ol";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = response.getWriter();
        try {
            writer.println("<h2>Home Dir</h2>");
            writer.println("<p>Path=<b>"+System.getProperty("user.home")+"</b></p>");
                writer.println( Utils.wrapList( Utils.writeTree(service.getHomeDir(), tag), tag ) );
        } finally {
            writer.close();
        }
    }
}