package ReceiveData;

import java.util.Iterator;
import java.util.Scanner;
import java.util.HashMap;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;

public class Query2 {
	  public static void main(String[] args) throws Exception {
		  
		  Query2 q = new Query2(); 
		  String AnyaInfo = q.getStudentInfo("Anika Sood"); //can be changed for any student on list as long as spelling is correct
		  System.out.print(AnyaInfo);
	  	
	  }
	  
	  public String getStudentInfo(String studentName) throws Exception 
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
		    Table table = dynamoDB.getTable("DNSeniors");
		    HashMap<String, String> nameMap = new HashMap<String, String>();
		    nameMap.put("#stu", "Student");
		    HashMap<String, Object> valueMap = new HashMap<String, Object>();
		    valueMap.put(":st", studentName);
		    QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#stu = :st").withNameMap(nameMap)
		      .withValueMap(valueMap);
		    ItemCollection<QueryOutcome> items = null;
		    Iterator<Item> iterator = null;
		    Item item = null;
		      String studentInfo = ""; 

		    try {
		      System.out.println("Student info:");
		      items = table.query(querySpec);
		      iterator = items.iterator();
		      
		      
		      while (iterator.hasNext()) {
		        item = iterator.next();
		        studentInfo = item.getString("Student") + "\n" + item.getString("College") + "\n" + item.getString("Major");
		      }
		      if(studentInfo.equals(""))
		      {
			      studentInfo = "Unable to find this student. Please check your spelling or verify that this is a Del Norte Senior.";

		      }
		      
		    }
		   
		    catch (Exception e) {
		      studentInfo = "Unable to find this student";
		      System.err.println(e.getMessage());
		    }
		    
		    return studentInfo; 
	  }
	  
	}