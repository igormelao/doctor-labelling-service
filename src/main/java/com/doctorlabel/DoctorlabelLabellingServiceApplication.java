package com.doctorlabel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* 
* <p>This is Spring Boot class and your responsible it to run the application</p>
* 
* 
* @author Igor Melão (igormelao@gmail.com)
* @Date:  14-03-2021
* @since  0.0.1-SNAPSHOT
* 
*/
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class DoctorlabelLabellingServiceApplication {

	/**
	 * <p>This is a method do run the DoctorlabeLabellingServiceApplication</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param
	 * @since 0.0.1-SNAPSHOT
	 */
	public static void main(String[] args) {
		SpringApplication.run(DoctorlabelLabellingServiceApplication.class, args);
	}

}
