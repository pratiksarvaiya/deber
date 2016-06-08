package com.deber.data.dynamodb;

public abstract interface DDBModel<DataModel>
{
  public abstract DataModel convertToDataModel();
  public abstract void copyDataModel(DataModel paramDataModel);
}