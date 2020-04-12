/**
  * Copyright 2020 bejson.com 
  */
package com.wenwu.pm.goson.hos;
import java.util.List;

/**
 * Auto-generated: 2020-04-12 0:58:51
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Suggestion {

    private List<String> keywords;

    private List<String> cities;

    public void setKeywords(List<String> keywords){
        this.keywords = keywords;
    }
    public List<String> getKeywords(){
        return this.keywords;
    }
    public void setCities(List<String> cities){
        this.cities = cities;
    }
    public List<String> getCities(){
        return this.cities;
    }
}