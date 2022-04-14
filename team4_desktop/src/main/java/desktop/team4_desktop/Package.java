package desktop.team4_desktop;


import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Package {

//  private long packageId;
//  private String pkgName;
//  private java.sql.Timestamp pkgStartDate;
//  private java.sql.Timestamp pkgEndDate;
//  private String pkgDesc;
//  private double pkgBasePrice;
//  private double pkgAgencyCommission;

  private SimpleIntegerProperty packageId;
  private SimpleStringProperty pkgName;
  private SimpleDateFormat pkgStartDate;
  private SimpleDateFormat pkgEndDate;
  private SimpleStringProperty pkgDesc;
  private SimpleFloatProperty pkgBasePrice;
  private SimpleFloatProperty pkgAgencyCommission;

  public Package(int packageId, String pkgName, String pkgStartDate, String pkgEndDate, String pkgDesc,
                 Float pkgBasePrice, Float pkgAgencyCommission) {
    this.packageId = new SimpleIntegerProperty(packageId);
    this.pkgName = new SimpleStringProperty(pkgName);
    this.pkgStartDate = new SimpleDateFormat(pkgStartDate);
    this.pkgEndDate = new SimpleDateFormat(pkgEndDate);
    this.pkgDesc = new SimpleStringProperty(pkgDesc);
    this.pkgBasePrice = new SimpleFloatProperty(pkgBasePrice);
    this.pkgAgencyCommission = new SimpleFloatProperty(pkgAgencyCommission);
  }

  public int getPackageId() {
    return packageId.get();
  }

  public SimpleIntegerProperty packageIdProperty() {
    return packageId;
  }

  public void setPackageId(int packageId) {
    this.packageId.set(packageId);
  }

  public String getPkgName() {
    return pkgName.get();
  }

  public SimpleStringProperty pkgNameProperty() {
    return pkgName;
  }

  public void setPkgName(String pkgName) {
    this.pkgName.set(pkgName);
  }

  public SimpleDateFormat getPkgStartDate() {
    return pkgStartDate;
  }

  public void setPkgStartDate(SimpleDateFormat pkgStartDate) {
    this.pkgStartDate = pkgStartDate;
  }

  public SimpleDateFormat getPkgEndDate() {
    return pkgEndDate;
  }

  public void setPkgEndDate(SimpleDateFormat pkgEndDate) {
    this.pkgEndDate = pkgEndDate;
  }

  public String getPkgDesc() {
    return pkgDesc.get();
  }

  public SimpleStringProperty pkgDescProperty() {
    return pkgDesc;
  }

  public void setPkgDesc(String pkgDesc) {
    this.pkgDesc.set(pkgDesc);
  }

  public float getPkgBasePrice() {
    return pkgBasePrice.get();
  }

  public SimpleFloatProperty pkgBasePriceProperty() {
    return pkgBasePrice;
  }

  public void setPkgBasePrice(float pkgBasePrice) {
    this.pkgBasePrice.set(pkgBasePrice);
  }

  public float getPkgAgencyCommission() {
    return pkgAgencyCommission.get();
  }

  public SimpleFloatProperty pkgAgencyCommissionProperty() {
    return pkgAgencyCommission;
  }

  public void setPkgAgencyCommission(float pkgAgencyCommission) {
    this.pkgAgencyCommission.set(pkgAgencyCommission);
  }
}
