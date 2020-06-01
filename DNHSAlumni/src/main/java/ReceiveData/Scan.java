package ReceiveData;

	import java.io.File;
	import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
	import com.amazonaws.AmazonClientException;
	import com.amazonaws.auth.profile.ProfileCredentialsProvider;
	import com.amazonaws.client.builder.AwsClientBuilder;
	import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
	import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
	import com.amazonaws.services.dynamodbv2.document.DynamoDB;
	import com.amazonaws.services.dynamodbv2.document.Item;
	import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
	import com.amazonaws.services.dynamodbv2.model.ScanResult;
	import com.fasterxml.jackson.core.JsonFactory;
	import com.fasterxml.jackson.core.JsonParser;
	import com.fasterxml.jackson.databind.JsonNode;
	import com.fasterxml.jackson.databind.ObjectMapper;
	import com.fasterxml.jackson.databind.node.ObjectNode;
	
	
	public class Scan {
		
		public static void main(String[] args)
		{
			ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		    try {
		      credentialsProvider.getCredentials();
		    } catch (Exception e) {
		      throw new AmazonClientException(
		          "Cannot load the credentials from the credential profiles file. " +
		          "Please make sure that your credentials file is at the correct " +
		          "location (/Users/johnmortensen/.aws/credentials), and is in valid format.",
		          e);
		    }
		    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
		    	.withCredentials(credentialsProvider)
		      .withRegion("us-west-2")
		      .build();
		    DynamoDB dynamoDB = new DynamoDB(client);
		//    Table table = dynamoDB.getTable("members");
		ScanRequest scanRequest = new ScanRequest()
		    .withTableName("DNSeniors");
		ScanResult result = client.scan(scanRequest);
		
		for (Map<String, AttributeValue> item : result.getItems()){
			// add specification: getStudentName()
			// print the Student name only-- when clicked, goes to page with ALL of student's info
				System.out.println(item.get("College"));
	}
		}}
		

