/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbbaden.login;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author fabia
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
  private String username;
  private String password;
  private boolean loggedIn = false;
  private User currentUser;
  
  ArrayList<User> users = new ArrayList<User>();
  
  public LoginBean() {
    users.add(new User("admin", "pass"));
    users.add(new User("user", "pass"));
  }
  
  public String login () {
    for (User user : users) {
      if (this.username.equals(user.getUsername()) && this.password.equals(user.getPassword())) {
        loggedIn = true;
        currentUser = user;
        break;
      } else {
        loggedIn = false;
      }
    }
    
    if (loggedIn == true) {
      return "/secured/welcome.xhtml?faces-redirect=true";
    } else {
      return "/index.xhtml";
    }
  }

  public Boolean isLoggedIn () {
    return loggedIn;
  }
  
  public String getUsername() {
      return username;
  }

  public void setUsername(String username) {
      this.username = username;
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }
}
