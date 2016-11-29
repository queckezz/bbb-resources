/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbbaden.samichlaus;

/**
 *
 * @author fabia
 */
public class Product {
  private String name;
  private String link;
  private String description;

  public Product(String name, String link, String description) {
    this.name = name;
    this.link = link;
    this.description = description;
  }

  public String getName() {
    return name;
  }
  
  public String getLink() {
    return link;
  }  

  public String getDescription() {
    return description;
  }
  
  
}
