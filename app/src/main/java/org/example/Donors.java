package org.example;

import java.util.LinkedList;

public class Donors {
  private int id;
  private String uuid;
  private String bloodType;
  private String firstName;
  private String lastName;
  private String middleName;
  private int Age;

  Donors(int id ,String uuid , String bloodType, String firstName, String middleName, String lastName, int Age) {
    this.id = id;
    this.uuid = uuid;
    this.bloodType = bloodType;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.Age = Age; 
  }

  public int getId() {
    return this.id;
  }

  public String getUuid() {
    return this.uuid;
  }

  public String getBloodType() {
    return this.bloodType;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getMiddleName() {
    return this.middleName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public int getAge() {
    return this.Age;
  }

  public void setuuid(String value) {
    this.uuid = value;
  }

  void display() {

  System.out.println("uuid: " + this.uuid + "\n" + " BloodType: " + this.bloodType + "\n" + 
  " First Name: " + this.firstName + "\n" + "Middle Name: " + this.middleName + "\n" +  "Last Name: "  + this.lastName + "\n" + " Age: " + this.Age + "\n\n"
  );

  }

}
