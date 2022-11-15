package com.example.springdemo.service;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springdemo.config.SpringAppWithImageAnalysisApiConfig;
import com.example.springdemo.entity.RawImage;
import com.example.springdemo.service.Iservice.ImageLoader;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class S3ImageLoaderImpl implements ImageLoader {
	
	@Autowired
	SpringAppWithImageAnalysisApiConfig imageAnalysisApiConfig;
	
	@Autowired
	S3Client s3Client;

	@Override
	public RawImage loadAllImages() {
		return null;
	}

	@Override
	public RawImage loadImageByName(String imageName) {
		
		// S3Client s3 = null;
		RawImage image = new RawImage();

		try {
			GetUrlRequest request = GetUrlRequest.builder()
	                .bucket(imageAnalysisApiConfig.AWS_BUCKET_NAME)
	                .key(imageAnalysisApiConfig.AWS_PRODUCTION_WORK_DIR 
	                		+ "/" + imageName)
	                .build();
			
			URL url = s3Client.utilities().getUrl(request);
	        System.out.println("The URL for  "+ imageName +" is "+ url);
	        
	        image.setImageName(imageName);
	        image.setImageUrl(url.toString());
	        
		} catch (S3Exception e) {
	        System.err.println(e.awsErrorDetails().errorMessage());
	    } finally {
	    	s3Client.close();
	    }
		
		return image;
	}

}
