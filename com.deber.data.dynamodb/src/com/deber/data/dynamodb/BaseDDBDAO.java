package com.deber.data.dynamodb;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class BaseDDBDAO {

	private AmazonDynamoDBClient ddbClient;
	private DynamoDBMapper mapper;
	protected BaseDDBDAO() {
		this.ddbClient = new AmazonDynamoDBClient();
		this.ddbClient.withRegion(Regions.AP_NORTHEAST_1);
		this.mapper = new DynamoDBMapper(this.ddbClient);
	}

	protected DynamoDBMapper getMapper() {
		return mapper;
	}

	protected static <E, DdbM extends DDBMapped<E>> List<E> convertAllToDataModel(List<DdbM> lst) {
		List<E> retVal = new ArrayList<>();
		for (DdbM item : lst) {
			retVal.add(item.convertToDataModel());
		}
		return retVal;
	}

	protected static <E, DDBM extends DDBMapped<E>> List<DDBM> convertAllToDdbModel(List<E> lst, Class<DDBM> clazz) {
		List<DDBM> retVal = new ArrayList<>();
		for (E item : lst) {
			DDBM instance = null;
			try {
				instance = (DDBM) clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			instance.copyDataModel(item);
			retVal.add(instance);
		}
		return retVal;
	}
}
