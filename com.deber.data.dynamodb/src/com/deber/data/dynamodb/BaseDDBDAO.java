package com.deber.data.dynamodb;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class BaseDDBDAO {

	private AmazonDynamoDBClient ddbClient;
	  
	  protected BaseDDBDAO()
	  {
	    this.ddbClient = new AmazonDynamoDBClient();
	    this.ddbClient.withRegion(Regions.AP_NORTHEAST_1);
	  }
	  
	  protected DynamoDBMapper getMapper()
	  {
	    return new DynamoDBMapper(this.ddbClient);
	  }
	  
	  protected static <E, DdbM extends DDBModel<E>> List<E> convertAllToDataModel(List<DdbM> lst)
	  {
	    List<E> retVal = new ArrayList<>();
	    for (DdbM item : lst) {
	      retVal.add(item.convertToDataModel());
	    }
	    return retVal;
	  }
	  
	  protected static <E, DDBM extends DDBModel<E>> List<DDBM> convertAllToDdbModel(List<E> lst, Class<DDBM> clazz)
	  {
	    List<DDBM> retVal = new ArrayList<>();
	    for (E item : lst)
	    {
	    	DDBM instance = null;
	      try
	      {
	        instance = (DDBM)clazz.newInstance();
	      }
	      catch (InstantiationException|IllegalAccessException e)
	      {
	        e.printStackTrace();
	      }
	      instance.copyDataModel(item);
	      retVal.add(instance);
	    }
	    return retVal;
	  }
}
