package mx.albo.test.marvellibraryservice.controller;


import mx.albo.test.marvellibraryservice.service.LibrarySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping("/marvel")
public class MarvelLibraryController {

	@Autowired
	private LibrarySevice librarySevice;
	
	@RequestMapping (value = "/colaborators/{name}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<?> getCreators(@PathVariable("name") String name){
		ResponseEntity responseEntity = librarySevice.getCreators(name);
		return responseEntity;
	}

	@RequestMapping (value = "/characters/{name}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<?> getCoWorkers(@PathVariable("name") String name){
		ResponseEntity responseEntity = librarySevice.getCoWorkers(name);
		return responseEntity;
	}
	
}
