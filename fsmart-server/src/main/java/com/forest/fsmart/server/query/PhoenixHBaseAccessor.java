package com.forest.fsmart.server.query;


import com.forest.fsmart.server.configuration.MetricsConfiguration;
import com.forest.fsmart.server.orm.bean.TimelineMetric;
import com.forest.fsmart.server.orm.bean.TimelineMetrics;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class PhoenixHBaseAccessor {

  private static final Log LOG = LogFactory.getLog(PhoenixHBaseAccessor.class);

  private final ConnectionProvider dataSource;

  static final long DEFAULT_OUT_OF_BAND_TIME_ALLOWANCE = 300000;

  public PhoenixHBaseAccessor(Configuration hbaseConf,
                              Configuration metricsConf,
                              ConnectionProvider dataSource) {

    try {
      Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
    } catch (ClassNotFoundException e) {
      LOG.error("Phoenix client jar not found in the classpath.", e);
      throw new IllegalStateException(e);
    }
    this.dataSource = dataSource;

  }

  public void insertMetricRecords(TimelineMetrics metrics) throws SQLException, IOException {

    List<TimelineMetric> timelineMetrics = metrics.getMetrics();
    if (timelineMetrics == null || timelineMetrics.isEmpty()) {
      LOG.info("Empty metrics insert request.");
      return;
    }

    Connection conn = getConnection();
    PreparedStatement metricRecordStmt = null;
    long currentTime = System.currentTimeMillis();

    try {
      metricRecordStmt = conn.prepareStatement(String.format(
          PhoenixTransactSQL.UPSERT_METRICS_SQL, PhoenixTransactSQL.METRICS_RECORD_TABLE_NAME));

      for (TimelineMetric metric : timelineMetrics) {


        metricRecordStmt.clearParameters();

        metricRecordStmt.setString(1, metric.getMetricName());

        try {
          metricRecordStmt.executeUpdate();
        } catch (SQLException sql) {
          LOG.error(sql);
        }
      }

      // commit() blocked if HBase unavailable
      conn.commit();

    } finally {
      if (metricRecordStmt != null) {
        try {
          metricRecordStmt.close();
        } catch (SQLException e) {
          // Ignore
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException sql) {
          // Ignore
        }
      }
    }
  }


  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }


}
