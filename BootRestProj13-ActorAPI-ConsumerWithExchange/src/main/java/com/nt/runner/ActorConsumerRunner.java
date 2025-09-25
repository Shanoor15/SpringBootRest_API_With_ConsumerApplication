package com.nt.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ActorConsumerRunner implements CommandLineRunner {

	@Autowired
	private RestTemplate template;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		//define url
		/*String url="http://localhost:8082/BootRestProj10-ActorAPI-SwaggerAPI//actor-api/find/{id}";
		//invoke service method
		ResponseEntity<String> response=template.exchange(url,HttpMethod.GET,null, String.class,21);
		System.out.println("Response Body::"+response.getBody());
		System.out.println("Response Status Code::"+response.getStatusCode());
		System.out.println("Response Header::"+response.getHeaders());*/
		
		/*String url="http://localhost:8082/BootRestProj10-ActorAPI-SwaggerAPI//actor-api/all";
		ResponseEntity<String> response=template.exchange(url, HttpMethod.GET,null,String.class);
		System.out.println("Response Body::"+response.getBody());
		System.out.println("Response Status Code::"+response.getStatusCode());
		System.out.println("Response Header::"+response.getHeaders());*/
		
		String url="http://localhost:8082/BootRestProj10-ActorAPI-SwaggerAPI//actor-api/save";
		//prepare JSON data (request body)
		String jsonBody = "{"
			    + "\"aname\": \"Prabhas\","
			    + "\"category\": \"Hero\","
			    + "\"addrs\": \"Hyderabad\","
			    + "\"remunaration\": 5000000.0"
			    + "}";

		//prepare Header
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON); //When you send data (like JSON) in an HTTP request (for example, via POST or PUT), the server needs to know what type of data you are sending so it can process it correctly.
		//prepare httpEntity object having header and body
		HttpEntity<String> entity=new HttpEntity<String>(jsonBody,headers);
		ResponseEntity<String> response=template.exchange(url, HttpMethod.POST,entity,String.class);
		System.out.println("Response Body::"+response.getBody());
		System.out.println("Response Status Code::"+response.getStatusCode());
		System.out.println("Response Header::"+response.getHeaders());
	}

}
