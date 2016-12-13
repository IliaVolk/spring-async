package com.springapp.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

public class Initializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        servletContext.addListener(new ContextLoaderListener(ctx));
        ctx.register(WebAppConfig.class);
        ctx.register(DataSourceConfig.class);
        ctx.register(HibernateConfig.class);


        ctx.setServletContext(servletContext);

        Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
                new DispatcherServlet(ctx));
        servlet.setAsyncSupported(true);
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);


    }
}
