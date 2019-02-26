package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Tomcat7以上（Servlet3.0）支持AbstractAnnotationConfigDispatcherServletInitializer配置DispatcherServlet
// AbstractAnnotationConfigDispatcherServletInitializer会创建DispatcherServlet和ContextLoaderListener
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    //将路径映射到DispatcherServlet上
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    @Override
    //创建包含Web组件的bean
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }
}