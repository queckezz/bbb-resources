/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbbaden.guestbook;

import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author fabia
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

  /**
   * Creates a new instance of LoginBean
   */
  private String inputUsername;
  private String inputPassword;
  private boolean loggedIn = false;
  ArrayList<User> users = new ArrayList<User>();
  private User user;

  public LoginBean() throws JDOMException, IOException {
    this.users = getUsersFromXML();
  }
  
  public ArrayList<User> getUsersFromXML () throws JDOMException, IOException {
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ArrayList<User> users = new ArrayList<User>();
    SAXBuilder saxBuilder = new SAXBuilder();
    
    File inputFile = new File(ec.getRealPath("/") + "WEB-INF\\users.xml");
    Document document = saxBuilder.build(inputFile);
    Element root = document.getRootElement();
    List<Element> usersList = root.getChildren();

    for (Element user : usersList) {
      users.add(new User(
        Integer.valueOf(user.getChild("id").getText()),
        user.getChild("username").getText(),
        user.getChild("password").getText()
      ));
    }
    
    return users;
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
      return "/secured/guestbook.xhtml?faces-redirect=true";
    }
  }
  
  public String logout (){
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.invalidateSession();  
    return "/index.xhtml";
  }
  
  public User check (String username, String password) {
    User currentUser = null;
    
    for (User user : this.users) {
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
