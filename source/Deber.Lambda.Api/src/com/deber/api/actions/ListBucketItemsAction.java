package com.deber.api.actions;

import java.util.Iterator;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.deber.api.viewmodels.ListBucketItemsRequestModel;
import com.deber.api.viewmodels.ListBucketItemsResponseModel;
import com.deber.api.viewmodels.ListBucketItemsResponseModel.BucketItem;

public class ListBucketItemsAction extends AbstractAction {

	public ListBucketItemsAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String handle(String model, Context context) {
		ListBucketItemsRequestModel requestModel = getGson().fromJson(model, ListBucketItemsRequestModel.class);
		AmazonS3Client s3Client = new AmazonS3Client();
		List<S3ObjectSummary> list = s3Client.listObjects(requestModel.bucketName).getObjectSummaries();
		s3Client.withRegion(Regions.AP_NORTHEAST_1);
		Iterator<S3ObjectSummary> iterator = list.iterator();
		BucketItem[] items = new BucketItem[list.size()];
		
		ListBucketItemsResponseModel retVal = new ListBucketItemsResponseModel();
		
		for(int i = 0;i< items.length;i++)
		{
			S3ObjectSummary objectSummary = iterator.next();
			BucketItem item = retVal.new BucketItem();
			item.setName(objectSummary.getKey());
			item.setLastModified(objectSummary.getLastModified().toString());
			items[i] = item;
		}
		
		retVal.setObjects(items);
		
		return getGson().toJson(retVal, ListBucketItemsResponseModel.class);
	}

}
