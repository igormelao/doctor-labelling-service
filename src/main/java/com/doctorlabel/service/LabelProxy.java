package com.doctorlabel.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.doctorlabel.model.Label;

/**
 * 
 * Interface class responsible to comunicate with a third service DoctorLabel Label service application.
 * 
 * Configurations:
 * 
 * <ol>
 * 	<li>name = This is the name of the service that will be call</li>
 *  <li>url = This is the url resource that we will connect to retrieve data</li>
 * </ol>
* @author Igor Melão (igormelao@gmail.com)
* @Date:  14-03-2021
* @since  0.0.1-SNAPSHOT
 */
@FeignClient(name="doctor-label-service", url="localhost:8080")
public interface LabelProxy {
	
	/**
	 * <p>This is a resource to return Label informations by your ID accessing a third party application</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param
	 * @return Label @see(com.doctorlabel.model.Label)
	 * @since 0.0.1-SNAPSHOT
	 */
	@GetMapping("/labels/{id}")
	public Label retrieveLabel(@PathVariable String id);

}
