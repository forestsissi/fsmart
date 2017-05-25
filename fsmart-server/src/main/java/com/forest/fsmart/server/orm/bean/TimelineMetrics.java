package com.forest.fsmart.server.orm.bean;


import java.util.ArrayList;
import java.util.List;

public class TimelineMetrics {

  private List<TimelineMetric> allMetrics = new ArrayList<TimelineMetric>();

  public TimelineMetrics() {}

  public List<TimelineMetric> getMetrics() {
    return allMetrics;
  }

  public void setMetrics(List<TimelineMetric> allMetrics) {
    this.allMetrics = allMetrics;
  }
}
