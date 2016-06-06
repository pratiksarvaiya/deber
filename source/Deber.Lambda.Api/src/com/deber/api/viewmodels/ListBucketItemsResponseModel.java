package com.deber.api.viewmodels;

public class ListBucketItemsResponseModel {
	
	public class BucketItem
	{
		String name;
		String lastModified;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLastModified() {
			return lastModified;
		}
		public void setLastModified(String lastModified) {
			this.lastModified = lastModified;
		}
		
		
	}
	
	private BucketItem[] objects;

	public BucketItem[] getObjects() {
		return objects;
	}

	public void setObjects(BucketItem[] objects) {
		this.objects = objects;
	}
	
}
