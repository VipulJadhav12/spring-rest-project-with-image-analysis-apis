package com.example.springdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.springdemo")
@PropertySource("classpath:config.properties")
public class SpringAppWithImageAnalysisApiConfig {
	
	@Value( "${aws.access.key}" )
	public String AWS_ACCESS_KEY;

	@Value( "${aws.secret.key}" )
	public String AWS_SECRET_KEY;
	
	@Value( "${aws.region}" )
	public String AWS_REGION;
	
	@Value( "${aws.bucket.name}" )
	public String AWS_BUCKET_NAME;
	
	@Value( "${aws.dev.work.dir}" )
	public String AWS_DEV_WORK_DIR;
	
	@Value( "${aws.production.work.dir}" )
	public String AWS_PRODUCTION_WORK_DIR;
	
	@Value( "${imagga.tags.base.uri}" )
	public String IMAGGA_TAGS_BASE_URI;
	
	@Value( "${imagga.categories.base.uri}" )
	public String IMAGGA_CATEGORIES_BASE_URI;
	
	@Value( "${imagga.authorization.key}" )
	public String IMAGGA_AUTHORIZATION_KEY;
	
	@Bean
	public S3Client s3Client() {
		StaticCredentialsProvider credentialsProvider = 
				StaticCredentialsProvider.create(AwsBasicCredentials.create(this.AWS_ACCESS_KEY, 
						this.AWS_SECRET_KEY));
		
		return S3Client.builder()
						.region(Region.of(this.AWS_REGION))
						.credentialsProvider(credentialsProvider)
						.build();
	}

}
