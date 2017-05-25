package com.forest.fsmart.server.control;


import com.forest.fsmart.server.api.TimelineMetricService;
import com.forest.fsmart.server.orm.bean.TimelineMetrics;
import com.forest.fsmart.server.query.PhoenixHBaseAccessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.sql.SQLException;

public class MetricStoreIml implements MetricsStore{

  private static final Log LOG = LogFactory.getLog(MetricStoreIml.class);
  private PhoenixHBaseAccessor hBaseAccessor;

  @Override
  public boolean putMetrics(TimelineMetrics metrics)
      throws SQLException, IOException {

    hBaseAccessor.insertMetricRecords(metrics);
    return false;
  }
}
