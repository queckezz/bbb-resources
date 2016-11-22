/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbbaden.helloworldapp;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author fabia
 */
@Named(value = "helloWorldBean")
@Dependent
public class HelloWorldBean {

  /**
   * Creates a new instance of HelloWorldBean
   */
  public HelloWorldBean() {
  }
  
  public String getText () {
    return "Hello World!";
  }
}
