package com.vieiraatelier.demostore.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.vieiraatelier.demostore.services.exceptions.FileException;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Value("${s3.bucket}")
	private String bucketName;

	@Autowired
	private AmazonS3 s3Customer;

	public URI uploadFile(MultipartFile multiPartFile) {
		try {
			String fileName = multiPartFile.getOriginalFilename();
			InputStream inputStream = multiPartFile.getInputStream();
			String contentType = multiPartFile.getContentType();
			return uploadFile(inputStream, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("Error of IO: " + e.getMessage());
		}
	}

	public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("Starting upload");
			s3Customer.putObject(bucketName, fileName, inputStream, meta);
			LOG.info("Upload successfully ended");
			return s3Customer.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Error when converting URL to URI");
		}
	}
}
