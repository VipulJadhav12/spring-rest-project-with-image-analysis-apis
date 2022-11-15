package com.example.springdemo.entity;

public class RawImage {
	
	private String imageName;
	private String imageUrl;
	
	public RawImage() {}

	public RawImage(String imageName, String imageUrl) {
		super();
		this.imageName = imageName;
		this.imageUrl = imageUrl;
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

	@Override
	public String toString() {
		return "RawImage [imageName=" + imageName + ", imageUrl=" + imageUrl + "]";
	}
	
}
