/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbbaden.samichlaus;

import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author fabia
 */
@Named(value = "rootBean")
@SessionScoped
public class RootBean implements Serializable {
  private List<Product> products = new ArrayList<Product>();
  private Product selectedProduct;
  
  public RootBean() throws JDOMException, IOException {
    this.products = getProductsFromXML();
  }
  
  public List<Product> getProductsFromXML () throws JDOMException, IOException {
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    List<Product> products = new ArrayList<Product>();
    SAXBuilder saxBuilder = new SAXBuilder();
    
    File inputFile = new File(ec.getRealPath("/") + "WEB-INF\\products.xml");
    Document document = saxBuilder.build(inputFile);
    Element root = document.getRootElement();
    List<Element> productsList = root.getChildren();

    for (Element product : productsList) {
      products.add(new Product(
        product.getChild("name").getText(),
        product.getChild("link").getText(),
        product.getChild("description").getText()
      ));
    }
    
    return products;
  }
  
  public String openProduct (Product product) {
    selectedProduct = product;
    return "/product.xhtml?faces-redirect=true";
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<Product> products) {
    this.products = products;
  }

  public Product getSelectedProduct() {
    return selectedProduct;
  }

  public void setSelectedProduct(Product selectedProduct) {
    this.selectedProduct = selectedProduct;
  }
}
