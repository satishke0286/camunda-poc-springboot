package com.citizen.camunda.poc.model;

public class Data {
  private String source;
  private String data;
  private long timestamp;
  private String state;

  public Data(String source, String data, long timestamp, String state) {
    this.source = source;
    this.data = data;
    this.timestamp = timestamp;
    this.state = state;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
