package com.doctorlabel.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
* 
* <p>This class is a Configuration for CORS</p>
* 
* <p>There is 1 dependency related with this Rest Controller and it is Injectabled by SpringBoot by tag @Autowired <p>
* 
* <ol>
* 	<li>allowedOrigins</li>
* </ol>
* 
* <p> Working with cross application front and back-end, to allows the microservice back-end comunicate with front-end we need
* to configure the application to allow origins requests</p> 
* <p> With that, we only need to put on application.properties a variable with name allowed.origin</p>
* 
* @author Igor Melão (igormelao@gmail.com)
* @Date:  14-03-2021
* @since  0.0.1-SNAPSHOT
* 
*/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${allowed.origin}")
    private String allowedOrigins;

    /**
	 * <p>This is a Spring Boot method that it's responsible to configurate CORS mapping</p>
	 * <p>Adding mapping for all resources for allowedOrigins and all methods types with all headers allows</p>
	 * 
	 * <p>Example of allow request</p>
	 * 
	 * <p>request get for http://localhost:4200/cases/</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @since 0.0.1-SNAPSHOT
	 */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(allowedOrigins)
            .allowedMethods("*")
            .allowedHeaders("*");
    }

}
