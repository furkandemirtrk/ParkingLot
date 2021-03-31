package com.huawei.parkinglot.util;

public class ApiPaths{
  private ApiPaths(){
  }

  private static final String BASE_PATH = "/api"; //NOSONAR
  public static final class ParkingAreaController{
    private ParkingAreaController(){
    }

    public static final String CONTROLLER = BASE_PATH + "/parkingArea";
    public static final String FIND_BY_NAME = "/findByName";
    public static final String DAILY_INCOME = "/dailyIncome";
  }
  public static final class ParkController{
    private ParkController(){
    }

    public static final String CONTROLLER = BASE_PATH + "/park";
  }
}

