/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbbaden.guestbook;

import java.util.Date;

/**
 *
 * @author fabia
 */
public class Entry {
  private int id;
  private String title;
  private Date createdAt;
  private String message;

  public Entry(int id, String title, Date createdAt, String message) {
    this.id = id;
    this.title = title;
    this.createdAt = createdAt;
    this.message = message;
  }

  public Entry(String title, Date createdAt, String message) {
    this.title = title;
    this.createdAt = createdAt;
    this.message = message;
  }
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public String getMessage() {
    return message;
  }
}
