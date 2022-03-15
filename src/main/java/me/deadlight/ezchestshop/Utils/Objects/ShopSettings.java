package me.deadlight.ezchestshop.Utils.Objects;

import me.deadlight.ezchestshop.Data.Config;
import me.deadlight.ezchestshop.Data.SQLite.Database;
import me.deadlight.ezchestshop.EzChestShop;

public class ShopSettings {

  private String sloc;
  private boolean msgtoggle;
  private boolean dbuy;
  private boolean dsell;
  private String admins;
  private boolean shareincome;
  private String trans;
  private boolean adminshop;
  private String rotation;

  public ShopSettings(
    String sloc,
    boolean msgtoggle,
    boolean dbuy,
    boolean dsell,
    String admins,
    boolean shareincome,
    String trans,
    boolean adminshop,
    String rotation
  ) {
    this.sloc = sloc;
    this.msgtoggle = msgtoggle;
    this.dbuy = dbuy;
    this.dsell = dsell;
    this.admins = admins;
    this.shareincome = shareincome;
    this.trans = trans;
    this.adminshop = adminshop;
    this.rotation = rotation;
  }

  private ShopSettings(ShopSettings settings) {
    this.sloc = settings.sloc;
    this.msgtoggle = settings.msgtoggle;
    this.dbuy = settings.dbuy;
    this.dsell = settings.dsell;
    this.admins = settings.admins;
    this.shareincome = settings.shareincome;
    this.trans = settings.trans;
    this.adminshop = settings.adminshop;
    this.rotation = settings.rotation;
  }

  public ShopSettings clone() {
    return new ShopSettings(this);
  }

  public boolean isMsgtoggle() {
    return msgtoggle;
  }

  public ShopSettings setMsgtoggle(boolean msgtoggle) {
    this.msgtoggle = msgtoggle;
    //        Database db = EzChestShop.getPlugin().getDatabase();
    //        db.setBool("location", sloc,
    //                "msgToggle", "shopdata", msgtoggle);
    return this;
  }

  public boolean isDbuy() {
    return dbuy;
  }

  public ShopSettings setDbuy(boolean dbuy) {
    this.dbuy = dbuy;
    //        Database db = EzChestShop.getPlugin().getDatabase();
    //        db.setBool("location", sloc,
    //                "buyDisabled", "shopdata", dbuy);
    return this;
  }

  public boolean isDsell() {
    return dsell;
  }

  public ShopSettings setDsell(boolean dsell) {
    this.dsell = dsell;
    //        Database db = EzChestShop.getPlugin().getDatabase();
    //        db.setBool("location", sloc,
    //                "sellDisabled", "shopdata", dsell);
    return this;
  }

  public String getAdmins() {
    return admins;
  }

  public ShopSettings setAdmins(String admins) {
    this.admins = admins;
    //        Database db = EzChestShop.getPlugin().getDatabase();
    //        db.setString("location", sloc,
    //                "admins", "shopdata", admins);
    return this;
  }

  public boolean isShareincome() {
    return shareincome;
  }

  public ShopSettings setShareincome(boolean shareincome) {
    this.shareincome = shareincome;
    //        Database db = EzChestShop.getPlugin().getDatabase();
    //        db.setBool("location", sloc,
    //                "shareIncome", "shopdata", shareincome);
    return this;
  }

  public String getTrans() {
    return trans;
  }

  public ShopSettings setTrans(String trans) {
    this.trans = trans;
    //        Database db = EzChestShop.getPlugin().getDatabase();
    //        db.setString("location", sloc,
    //                "transactions", "shopdata", trans);
    return this;
  }

  public boolean isAdminshop() {
    return adminshop;
  }

  public ShopSettings setAdminshop(boolean adminshop) {
    this.adminshop = adminshop;
    //        Database db = EzChestShop.getPlugin().getDatabase();
    //        db.setBool("location", sloc,
    //                "adminshop", "shopdata", adminshop);
    return this;
  }

  public String getRotation() {
    return rotation == null ? Config.settings_defaults_rotation : rotation;
  }

  public ShopSettings setRotation(String rotation) {
    this.rotation = rotation;
    //        Database db = EzChestShop.getPlugin().getDatabase();
    //        db.setString("location", sloc,
    //                "rotation", "shopdata", rotation);
    return this;
  }
}
