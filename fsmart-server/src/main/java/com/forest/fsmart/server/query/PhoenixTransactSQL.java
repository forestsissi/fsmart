package com.forest.fsmart.server.query;


public class PhoenixTransactSQL {

  public static final String METRICS_RECORD_TABLE_NAME = "METRIC_RECORD";

  /**
   * Insert into metric records table.
   */
  public static final String UPSERT_METRICS_SQL = "UPSERT INTO %s " +
      "(METRIC_NAME, HOSTNAME, APP_ID, INSTANCE_ID, SERVER_TIME, START_TIME, " +
      "UNITS, " +
      "METRIC_SUM, " +
      "METRIC_MAX, " +
      "METRIC_MIN, " +
      "METRIC_COUNT, " +
      "METRICS) VALUES " +
      "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

}
