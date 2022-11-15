package com.example.springdemo.entity;

import org.springframework.stereotype.Component;

@Component
public class ImaggaImageAnalysisApisResult {
	
	private String imageName;
	private String imageUrl;
	private String result;
	
	public ImaggaImageAnalysisApisResult() {}

	public ImaggaImageAnalysisApisResult(String imageName, String imageUrl, String result) {
		super();
		this.imageName = imageName;
		this.imageUrl = imageUrl;
		this.result = result;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ImaggaImageAnalysisApisResult [imageName=" + imageName + ", imageUrl=" + imageUrl + ", result=" + result
				+ "]";
	}
	
}
