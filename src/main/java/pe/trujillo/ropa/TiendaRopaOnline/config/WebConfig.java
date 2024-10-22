import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.SessionCookieConfig;

@Configuration
public class WebConfig {

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addContextCustomizers(context -> {
            context.setSessionCookieConfig(new SessionCookieConfig() {{
                setName("sessionId");
                setMaxAge(1800); 
                setHttpOnly(true);
                setSecure(true);
            }});
        });
        return tomcat;
    }
}
