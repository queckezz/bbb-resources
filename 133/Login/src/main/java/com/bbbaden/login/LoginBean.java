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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fabia
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
  private String inputUsername;
  private String inputPassword;
  private boolean loggedIn = false;
  private User user;
  
  ArrayList<User> users = new ArrayList<User>();
  
  public LoginBean() {
    users.add(new User("admin", "pass"));
    users.add(new User("user", "pass"));
  }
  
  public String login () {
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    HttpServletRequest req = (HttpServletRequest)ec.getRequest();
    
    User user = check(this.inputUsername, this.inputPassword);
    this.user = user;
        
    if (user == null) {
      loggedIn = false;
      return "/index.xhtml";
    } else {
      req.changeSessionId();
      loggedIn = true;
      return "/secured/welcome.xhtml?faces-redirect=true";
    }
  }
  
  public String logout (){
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.invalidateSession();  
    return "/index.xhtml";
  }
    
  
  public User check (String username, String password) {
    User currentUser = null;
    
    for (User user : users) {
      if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
        currentUser = user;
        break;
      }
    }

    return currentUser;
  }
  
  public Boolean isLoggedIn () {
    return loggedIn;
  }

  public User getUser() {
    return user;
  }

  public String getInputUsername() {
    return inputUsername;
  }

  public String getInputPassword() {
    return inputPassword;
  }

  public void setInputUsername(String inputUsername) {
    this.inputUsername = inputUsername;
  }

  public void setInputPassword(String inputPassword) {
    this.inputPassword = inputPassword;
  }
}
