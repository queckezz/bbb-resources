/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbbaden.guestbook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author fabia
 */
@Named(value = "guestBookBean")
@ViewScoped
public class GuestBookBean implements Serializable {
  private ArrayList<Entry> entries;

  /**
   * Creates a new instance of GuestBookBean
   */
  public GuestBookBean() throws JDOMException, IOException, ParseException {
    this.entries = getEntriesFromXML();

    saveEntries(new ArrayList<Entry>() {{
      add(new Entry("My Title", Date.from(Instant.now()), "A Message"));
    }});
  }
  
  private void saveEntries (ArrayList<Entry> entries) throws JDOMException, IOException {
    System.out.println("save entry");
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    SAXBuilder saxBuilder = new SAXBuilder();
    
    String filePath = ec.getRealPath("/") + "WEB-INF\\entries.xml";
    File inputFile = new File(filePath);
    Document document = saxBuilder.build(inputFile);
    Element root = document.getRootElement();
    List<Element> children = root.getChildren();
    
    for (int i = 0; i < entries.size(); i++) {
      Entry entry = entries.get(i);
      entry.setId(children.size() + i);
      Element newChild = new Element("entry");
      
      newChild.addContent(
        new Element("id").setText(Integer.toString(entry.getId()))
      );

      newChild.addContent(
        new Element("title").setText(entry.getTitle())
      );

      newChild.addContent(
        new Element("createdAt").setText(entry.getCreatedAt().toInstant().toString())
      );

      newChild.addContent(
        new Element("message").setText(entry.getMessage())
      );

      root.addContent(newChild);      
    }
    
    XMLOutputter xmlOutput = new XMLOutputter();
    xmlOutput.setFormat(Format.getPrettyFormat());
    xmlOutput.output(document, new FileWriter(filePath));
    System.out.println("File Saved!");
  }
  
  private ArrayList<Entry> getEntriesFromXML () throws JDOMException, IOException, ParseException {
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ArrayList<Entry> entries = new ArrayList<Entry>();
    SAXBuilder saxBuilder = new SAXBuilder();
    
    File inputFile = new File(ec.getRealPath("/") + "WEB-INF\\entries.xml");
    Document document = saxBuilder.build(inputFile);
    Element root = document.getRootElement();
    List<Element> children = root.getChildren();
     
    for (Element entry : children) {
      entries.add(new Entry(
        Integer.valueOf(entry.getChild("id").getText()),
        entry.getChild("title").getText(),
        Date.from(Instant.parse(entry.getChild("createdAt").getText())),
        entry.getChild("message").getText()
      ));
    }
    
    return entries;
  }

  public ArrayList<Entry> getEntries() {
    return entries;
  }
}
