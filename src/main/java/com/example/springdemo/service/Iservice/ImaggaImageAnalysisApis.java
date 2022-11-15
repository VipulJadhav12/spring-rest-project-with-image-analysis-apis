package com.example.springdemo.service.Iservice;

import com.example.springdemo.entity.ImaggaImageAnalysisApisResult;

public interface ImaggaImageAnalysisApis {
	
	public ImaggaImageAnalysisApisResult getTags(String imageName);

	public ImaggaImageAnalysisApisResult getCategories(String imageName);

}
