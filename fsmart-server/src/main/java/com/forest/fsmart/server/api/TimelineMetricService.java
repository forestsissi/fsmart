package com.forest.fsmart.server.api;

import com.forest.fsmart.server.control.MetricsStore;
import com.forest.fsmart.server.orm.bean.TimelineMetric;
import com.forest.fsmart.server.orm.bean.TimelineMetrics;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Component
@RestController
@EnableAutoConfiguration
@Path("/fsmart")
public class TimelineMetricService {

  private static final Log LOG = LogFactory.getLog(TimelineMetricService.class);

  private List<TimelineMetric> allMetrics = new ArrayList<TimelineMetric>();

  @Autowired
  private MetricsStore metricsStore;
  /**
   * Store metrics into the hbase.
   */
  @Path("/metrics")
  @POST
  @Consumes({ MediaType.APPLICATION_JSON })
  public Boolean postMetrics(
      @Context HttpServletRequest req,
      @Context HttpServletResponse res,
      TimelineMetrics metrics) {

    if (metrics == null) {

    }

    try {

      metricsStore.putMetrics(metrics);
      return null;

    } catch (Exception e) {

    }
    return null;
  }

}
