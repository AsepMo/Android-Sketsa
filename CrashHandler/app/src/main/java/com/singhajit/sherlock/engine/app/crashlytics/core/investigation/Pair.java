package com.singhajit.sherlock.engine.app.crashlytics.core.investigation;

class Pair {
  private String key;
  private String val;

  public Pair(String key, String val) {
    this.key = key;
    this.val = val;
  }

  public String getKey() {
    return key;
  }

  public String getVal() {
    return val;
  }
}
