package dev.sanket.main;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main
{
    private static int port = 8080;
    private static final String WEBAPP_DIR = "src/main/webapp/";

    public static void main(String[] args) throws Exception
    {
        if (args.length == 1)
        {
            port = Integer.parseInt(args[0]);
        }
        
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        String contextPath = "/";
        Context context = tomcat.addWebapp(contextPath, new File(WEBAPP_DIR).getAbsolutePath());

        File configFile = new File(WEBAPP_DIR + "WEB-INF/web.xml");
        context.setConfigFile(configFile.toURI().toURL());

        // Additions to make @WebServlet work
        String buildPath = "target/classes";
        String webAppMount = "/WEB-INF/classes";

        File additionalWebInfClasses = new File(buildPath);
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, webAppMount, additionalWebInfClasses.getAbsolutePath(), contextPath));
        context.setResources(resources);
        
        tomcat.start();
        tomcat.getServer().await();
    }
}
