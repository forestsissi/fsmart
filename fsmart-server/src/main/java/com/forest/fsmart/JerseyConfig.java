package com.forest.fsmart;


import com.forest.fsmart.server.api.TimelineMetrics;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

  public JerseyConfig() {
    register(TimelineMetrics.class);

  }


}
