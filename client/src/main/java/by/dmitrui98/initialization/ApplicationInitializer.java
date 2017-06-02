package by.dmitrui98.initialization;


import by.dmitrui98.config.WebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Администратор on 02.04.2017.
 */
public class ApplicationInitializer implements WebApplicationInitializer {

    private final static String DISPATCHER = "dispatcher";

    // TODO !!! подключить к серверу папку с ресурсами
    // <Context docBase="/server/file_storage/images" path="/images" reloadable="true"/>

    // TODO !!! правильная обработка исключения удаления

    // TODO перевести время на час вперед
    // TODO изменить формат вывода даты: "yyyy/MM/dd HH:mm:ss"
    // TODO перехватить исключения, выбрасываемые на браузер
    // TODO сделать <jsp:include> для title
    // TODO страница comeIn только для неаутентифицированных пользователей



    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        System.out.println("start filter registration");
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
        System.out.println("end filter registration");

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        ctx.register(WebConfig.class);
        //ctx.register(DatabaseConfig.class);
        //ctx.register(SecurityConfig.class);

        ctx.setServletContext(servletContext);


        servletContext.addListener(new ContextLoaderListener(ctx));

        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER, new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

    }

}
