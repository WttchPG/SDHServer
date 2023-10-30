package com.wttch.sdh.sdhserver.entity;

public class WordDictionary {
  private Integer id;

  private String name;

  private Integer system;

  private String lastVersion;

  private Integer createBy;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSystem() {
    return system;
  }

  public void setSystem(Integer system) {
    this.system = system;
  }

  public String getLastVersion() {
    return lastVersion;
  }

  public void setLastVersion(String lastVersion) {
    this.lastVersion = lastVersion;
  }

  public Integer getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Integer createBy) {
    this.createBy = createBy;
  }
}
