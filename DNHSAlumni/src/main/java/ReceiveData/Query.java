package ReceiveData;
import java.io.File;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashMap;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
public class Query {
	
	public static void main(String[] args)
	{
	
	Scanner scan = new Scanner(System.in);
	String name = scan.nextLine();
	
	
	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withRegion(Regions.US_WEST_2).build();
			DynamoDB dynamoDB = new DynamoDB(client);
			Table table = dynamoDB.getTable("DNSeniors");
			QuerySpec spec = new QuerySpec()
			    .withKeyConditionExpression(name = "Anya Chandorkar")
			    .withValueMap(new ValueMap()
			        .withString(name, "Amazon DynamoDB#DynamoDB Thread 1"));
			ItemCollection<QueryOutcome> items = table.query(spec);
			Iterator<Item> iterator = items.iterator();
			Item item = null;

			while (iterator.hasNext()) {
			    item = iterator.next();
			    System.out.println(item.get("College"));
			}
}

}
