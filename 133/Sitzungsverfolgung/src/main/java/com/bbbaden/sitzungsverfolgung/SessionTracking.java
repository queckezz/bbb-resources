  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbbaden.sitzungsverfolgung;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author fabia
 */
@Named(value = "session_tracking")
@SessionScoped
public class SessionTracking implements Serializable { 
  
  private String name = null;
  private String firstName = null;
  
  public String getName() {
    return name;
  }
  
  public void setName (String name) {
    this.name = name;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String saveName () {
    FacesContext context = FacesContext.getCurrentInstance();
    context.getExternalContext().getSessionMap().put("name", this.name);
    return "index-part-2.xhtml";
  }
  
  public String saveFirstName () {
    FacesContext context = FacesContext.getCurrentInstance();
    context.getExternalContext().getSessionMap().put("first_name", this.firstName);
    return "index-part-3.xhtml";
  }
}
