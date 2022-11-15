package com.example.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdemo.entity.ImaggaImageAnalysisApisResult;
import com.example.springdemo.exception.ImageNotFoundErrorResponse;
import com.example.springdemo.exception.ImageNotFoundException;
import com.example.springdemo.exception.UnknownImageRequestException;
import com.example.springdemo.service.Iservice.ImaggaImageAnalysisApis;

@RestController
@RequestMapping("/api/v1")
public class ImageAnalysisApiController {
	
	@Autowired
	ImaggaImageAnalysisApis imageAnalysisApis;
	
	@GetMapping("/")
	public ResponseEntity<String> showDefaultTagsStatus() {
		return new ResponseEntity<String>("Default HTTP Status: OK", HttpStatus.OK);
	}
	
	@GetMapping("/tags/{imageName}")
	public ImaggaImageAnalysisApisResult getImageTags(@PathVariable String imageName) {
		
		if(!(imageName.endsWith(".jpg") || imageName.endsWith(".png")
				|| imageName.endsWith(".JPG") || imageName.endsWith(".PNG"))) {
			throw new UnknownImageRequestException("Found unknown image name format. Entered image name should end with suffix like: .jpg or .png");
		}
		
		ImaggaImageAnalysisApisResult result = imageAnalysisApis.getTags(imageName);
		
		if(null != result)
			throw new ImageNotFoundException("Found invalid image name. Entered image name doesn't exists.");
			
		return result;
	}
	
	@GetMapping("/categories/{imageName}")
	public ImaggaImageAnalysisApisResult getImageCategories(@PathVariable String imageName) {
		
		if(!(imageName.endsWith(".jpg") || imageName.endsWith(".png")
				|| imageName.endsWith(".JPG") || imageName.endsWith(".PNG"))) {
			throw new UnknownImageRequestException("Invalid image name. Entered image name doesn't exists or doesn't end with valid suffix like: .jpg or .png");
		}
		
		ImaggaImageAnalysisApisResult result = imageAnalysisApis.getCategories(imageName);
		
		if(null != result)
			throw new ImageNotFoundException("Invalid image name. Entered image name doesn't exists or doesn't end with suffix like: .jpg or .png");
			
		return result;
	}
	
	@ExceptionHandler
	public ResponseEntity<ImageNotFoundErrorResponse> handleException(UnknownImageRequestException ex) {
		
		ImageNotFoundErrorResponse errorResponse = new ImageNotFoundErrorResponse();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ImageNotFoundErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ImageNotFoundErrorResponse> handleException(ImageNotFoundException ex) {
		
		ImageNotFoundErrorResponse errorResponse = new ImageNotFoundErrorResponse();
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ImageNotFoundErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
