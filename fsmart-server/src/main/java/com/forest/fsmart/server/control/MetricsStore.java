package com.forest.fsmart.server.control;


import com.forest.fsmart.server.api.TimelineMetricService;
import com.forest.fsmart.server.orm.bean.TimelineMetrics;

import java.io.IOException;
import java.sql.SQLException;

public interface MetricsStore {

  boolean putMetrics(TimelineMetrics metrics)
      throws SQLException, IOException;
}
