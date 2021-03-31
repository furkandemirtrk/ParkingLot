package com.huawei.parkinglot.enums;


public enum VehicleType{
  SEDAN(Values.SEDAN),SUV(Values.SUV),MINIVAN(Values.MINIVAN);

  private final String name;

  private VehicleType(String name){
    this.name = name;
  }

  public static  String getName(VehicleType vehicleType){
    return vehicleType.name;
  }

  public static class Values {
    private Values(){
    }

    public static final String SEDAN = "SEDAN";
    public static final String MINIVAN = "MINIVAN";
    public static final String SUV = "SUV";
  }
}
