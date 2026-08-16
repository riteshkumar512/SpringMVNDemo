package in.kumar;

import in.kumar.config.WebConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws LifecycleException {

        //Boiler Plate
        Tomcat tomcat=new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        String contextPath="";
        String baseDoc=new File("src/main/webapp").getAbsolutePath();
        Context context= tomcat.addContext(contextPath,baseDoc);

        //IOC Container Up
        AnnotationConfigWebApplicationContext springcontext =
                new AnnotationConfigWebApplicationContext();

        springcontext.register(WebConfig.class);

        //Dispatcher Servlet
        DispatcherServlet dispatcherServlet=
                new DispatcherServlet(springcontext);

        Tomcat.addServlet(context,
                "dispatcherServlet",dispatcherServlet);

        context.addServletMappingDecoded("/",
                "dispatcherServlet");

        tomcat.start();
        System.out.println("Tomcat started on port 8080");
        //tomcat server running
        tomcat.getServer().await();

    }
}