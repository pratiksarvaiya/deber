package com.deber.data.dynamodb.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.deber.data.CategoryNormalisedValue;
import com.deber.data.dynamodb.BaseDDBDAO;
import com.deber.data.user.UserDAO;
import static java.util.stream.Collectors.toList;

public class UserDDBDAO extends BaseDDBDAO implements UserDAO {

	@Override
	public List<CategoryNormalisedValue> getAffinitiesForUser(String userID) {
		
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":id", new AttributeValue().withS(userID));
        
		DynamoDBQueryExpression<AffinityDDB> expr = new DynamoDBQueryExpression<AffinityDDB>()
				.withKeyConditionExpression("UserId = :id")
				.withExpressionAttributeValues(eav);
		
		List<AffinityDDB> values = getMapper().query(AffinityDDB.class, expr);
		
		return values.stream().map((AffinityDDB r) -> {
				CategoryNormalisedValue retVal = new CategoryNormalisedValue();
				retVal.setCategoryId(r.getCategoryId());
				retVal.setNormalValue(r.getAffinity());
				return retVal;
			}).collect(toList());
	}

}
