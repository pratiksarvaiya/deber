package com.deber.data.dynamodb;

public abstract interface DDBMapped<DataModel>
{
  public abstract DataModel convertToDataModel();
  public abstract void copyDataModel(DataModel paramDataModel);
}