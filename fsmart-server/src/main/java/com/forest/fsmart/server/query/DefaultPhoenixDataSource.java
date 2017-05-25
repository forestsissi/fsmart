package com.forest.fsmart.server.query;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.hadoop.conf.Configuration;

public class DefaultPhoenixDataSource implements ConnectionProvider{

  static final Log LOG = LogFactory.getLog(DefaultPhoenixDataSource.class);

  private static final String ZOOKEEPER_CLIENT_PORT =
      "hbase.zookeeper.property.clientPort";
  private static final String ZOOKEEPER_QUORUM = "hbase.zookeeper.quorum";
  private static final String ZNODE_PARENT = "zookeeper.znode.parent";

  private static final String connectionUrl = "jdbc:phoenix:%s:%s:%s";
  private final String url;

  public DefaultPhoenixDataSource(Configuration hbaseConf) {
    String zookeeperClientPort = hbaseConf.getTrimmed(ZOOKEEPER_CLIENT_PORT,
        "2181");
    String zookeeperQuorum = hbaseConf.getTrimmed(ZOOKEEPER_QUORUM);
    String znodeParent = hbaseConf.getTrimmed(ZNODE_PARENT, "/hbase");
    if (zookeeperQuorum == null || zookeeperQuorum.isEmpty()) {
      throw new IllegalStateException("Unable to find Zookeeper quorum to " +
          "access HBase store using Phoenix.");
    }

    url = String.format(connectionUrl,
        zookeeperQuorum,
        zookeeperClientPort,
        znodeParent);
  }

  public Connection getConnection() throws SQLException {

    LOG.info("Metric store connection url: " + url);
    try {
      return DriverManager.getConnection(url);
    } catch (SQLException e) {
      LOG.warn("Unable to connect to HBase store using Phoenix.", e);

      throw e;
    }
  }
}
