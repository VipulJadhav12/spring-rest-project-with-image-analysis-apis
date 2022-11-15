package com.example.springdemo.service.Iservice;

import com.example.springdemo.entity.RawImage;

public interface ImageLoader {
	
	public RawImage loadAllImages();
	
	public RawImage loadImageByName(String imageName);

}
