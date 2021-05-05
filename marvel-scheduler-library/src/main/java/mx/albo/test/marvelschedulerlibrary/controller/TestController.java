package mx.albo.test.marvelschedulerlibrary.controller;

import mx.albo.test.marvelschedulerlibrary.service.LibrarySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

@RestController
@EnableSwagger2
public class TestController {

	@Autowired
	private LibrarySevice librarySevice;
	
	@RequestMapping (value = "/test/albo/update", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<?> test(){
		String result = librarySevice.updateLibrary(new Date());
		return new ResponseEntity<String>(result, null, HttpStatus.OK);
	}
	
}
