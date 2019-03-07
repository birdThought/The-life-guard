package com.lifeshs;

import com.lifeshs.config.TestLYConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class LifedefenderOpenserviceApplication {
//	public static void main(String[] args) {
//		SpringApplication.run(LifedefenderOpenserviceApplication.class, args);
//	}
//}

@SpringBootApplication
public class LifedefenderOpenserviceApplication extends SpringBootServletInitializer {
    //	private static final Logger logger = LoggerFactory.getLogger(LifedefenderOpenserviceApplication.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    public static void main(String[] args) {
        SpringApplication.run(LifedefenderOpenserviceApplication.class, args);
    }
}
