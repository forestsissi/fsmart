package com.forest.fsmart.server.orm.bean;


import java.util.TreeMap;

public class TimelineMetric implements Comparable<TimelineMetric> {

  private String metricName;

  // default
  public TimelineMetric() {

  }

  // copy constructor
  public TimelineMetric(TimelineMetric metric) {
    setMetricName(metric.getMetricName());
  }

  public String getMetricName() {
    return metricName;
  }

  public void setMetricName(String metricName) {
    this.metricName = metricName;
  }




  @Override
  public int compareTo(TimelineMetric other) {

    return 1;
  }
}
