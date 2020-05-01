package util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myservlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Simulation sim = new Simulation();

        try {
            if (request.getParameter("button1") != null) {
                sim.startGame();
                System.out.println("Group Stage finished");
                response.sendRedirect("/Footsal_war_exploded/loadTable.jsp");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
