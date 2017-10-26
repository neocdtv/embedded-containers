package io.neocdtv.embedded.containers.jetty.boundary;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by xix on 26.10.17.
 */
@ApplicationScoped
public class TestCDI {
  public String get() {
    return "blup";
  }
}
