package com.mutant.magneto.dao;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public abstract class DynamoDAO {
	protected AmazonDynamoDB client;
	protected DynamoDB dynamoDB;
	protected DynamoDBMapper mapper;
	
	public DynamoDAO() {
	    //client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
	    String serviceEndpoint = "dynamodb.us-east-2.amazonaws.com";
	    String signingRegion = "us-east-2";
	    String accessKey = "AKIAZARQOEQ5HANEKZWT";
	    String secretKey = "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";
	    client = AmazonDynamoDBClientBuilder.standard()
			.withEndpointConfiguration(new AmazonDynamoDBClientBuilder.EndpointConfiguration(serviceEndpoint, signingRegion))
			.withCredentials (new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey))).build();
	    mapper = new DynamoDBMapper(client);
	}
}
