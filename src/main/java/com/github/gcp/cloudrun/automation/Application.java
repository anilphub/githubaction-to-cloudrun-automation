package com.github.gcp.cloudrun.automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {
//test
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@RestController
	static class DemoContainer{
		@GetMapping("/{name}")
		public ResponseEntity<String> confirmCloudRunAutomation(@PathVariable(value="name") String name){
			return ResponseEntity.ok("Hey "+name+", Well done!! You automated the deployment from github to GCP Cloud Run.");
		}
	}
}
