import magneto.Mutant;

import java.io.IOException;
import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;
import java.net.URI;
import java.net.URISyntaxException;

import com.google.gson.Gson;

public class Main extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
      if (!req.getRequestURI().endsWith("/ismutant")) {
          showHome(req,resp);
          return;
      } 
      isMutant(req,resp);
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.getWriter().print("Invalid endpoint");
    }

    private void isMutant(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.setContentType("application/json");
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();

        Mutant mutant = new Mutant();
        Response r = new Response();
        DNA obj;
        //FIXME: there is another way to ensure decoder?
        try {
            obj = gson.fromJson(reader, DNA.class);
            obj.getDnaLen();
        } catch (Exception e) {
            r.status = "Invalid request, missing dna";
            r.match = false;
            resp.getWriter().print(gson.toJson(r));
            return;
        } 

        if(mutant.checkIsMutant(obj.dna)){
          r.status = "ok";
          r.match = true;
          resp.getWriter().print(gson.toJson(r));
          return;
        }

        r.status = "ok";
        r.match = false;
        resp.getWriter().print(gson.toJson(r));
    }


    public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new Main()),"/*");
        server.start();
        server.join();
    }
}

class DNA{
  public String[] dna;

  public int getDnaLen(){
    return dna.length;
  }
}

class Response{
  public String status;
  public boolean match;
}
