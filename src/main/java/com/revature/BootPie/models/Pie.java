package com.revature.BootPie.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Pie {
  
  @Id
  @Column(name = "pie_name")
  private String pieName;

  private int calories;

  @Column(name = "slices_available")
  private int slicesAvailable;

  // No args constructor
  public Pie() {

  }

  // Parameterized constructor
  public Pie(String pieName, int calories, int slicesAvailable) {
    this.pieName = pieName;
    this.calories = calories;
    this.slicesAvailable = slicesAvailable;
  }

  public String getPieName() {
    return pieName;
  }

  public void setPieName(String pieName) {
    this.pieName = pieName;
  }

  public int getCalories() {
    return calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }

  public int getSlicesAvailable() {
    return slicesAvailable;
  }

  public void setSlicesAvailable(int slicesAvailable) {
    this.slicesAvailable = slicesAvailable;
  }

  
  
}
