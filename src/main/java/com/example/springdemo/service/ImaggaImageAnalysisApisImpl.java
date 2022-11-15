package com.example.springdemo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springdemo.config.SpringAppWithImageAnalysisApiConfig;
import com.example.springdemo.entity.ImaggaImageAnalysisApisResult;
import com.example.springdemo.entity.RawImage;
import com.example.springdemo.service.Iservice.ImageLoader;
import com.example.springdemo.service.Iservice.ImaggaImageAnalysisApis;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class ImaggaImageAnalysisApisImpl implements ImaggaImageAnalysisApis {

	@Autowired
	ImageLoader imageLoader;

	@Autowired
	SpringAppWithImageAnalysisApiConfig imageAnalysisApiConfig;

	@Override
	public ImaggaImageAnalysisApisResult getTags(String imageName) {
		return getImageAnalysisApisResult(imageAnalysisApiConfig.IMAGGA_TAGS_BASE_URI, imageName);
	}
	
	@Override
	public ImaggaImageAnalysisApisResult getCategories(String imageName) {
		return getImageAnalysisApisResult(imageAnalysisApiConfig.IMAGGA_CATEGORIES_BASE_URI, imageName);
	}

	private ImaggaImageAnalysisApisResult getImageAnalysisApisResult(String imaggaBaseUri, String imageName) {
		
		HttpResponse<String> response = null;
		ImaggaImageAnalysisApisResult imageAnalysisApisResult = null;

		RawImage image = imageLoader.loadImageByName(imageName);

		if(null != image) {
			try {
				response = Unirest.get(imaggaBaseUri + image.getImageUrl())
						.header("Authorization", imageAnalysisApiConfig.IMAGGA_AUTHORIZATION_KEY)
						.asString();

				if(response != null) {
					InputStream inputStream = response.getRawBody();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					imageAnalysisApisResult = new ImaggaImageAnalysisApisResult();
					imageAnalysisApisResult.setImageName(image.getImageName());
					imageAnalysisApisResult.setImageUrl(image.getImageUrl());
					imageAnalysisApisResult.setResult(reader.readLine());
				}

			} catch (UnirestException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		
		return imageAnalysisApisResult;

	}

}
