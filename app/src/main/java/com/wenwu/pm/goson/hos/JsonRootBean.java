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
public class JsonRootBean {

    private String status;

    private String count;

    private String info;

    private String infocode;

    private Suggestion suggestion;

    private List<Pois> pois;

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setCount(String count){
        this.count = count;
    }
    public String getCount(){
        return this.count;
    }
    public void setInfo(String info){
        this.info = info;
    }
    public String getInfo(){
        return this.info;
    }
    public void setInfocode(String infocode){
        this.infocode = infocode;
    }
    public String getInfocode(){
        return this.infocode;
    }
    public void setSuggestion(Suggestion suggestion){
        this.suggestion = suggestion;
    }
    public Suggestion getSuggestion(){
        return this.suggestion;
    }
    public void setPois(List<Pois> pois){
        this.pois = pois;
    }
    public List<Pois> getPois(){
        return this.pois;
    }
}