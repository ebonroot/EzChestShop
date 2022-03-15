package me.deadlight.ezchestshop.Data.SQLite;

import java.util.logging.Level;
import me.deadlight.ezchestshop.EzChestShop;

public class Error {

  public static void close(EzChestShop plugin, Exception ex) {
    plugin
      .getLogger()
      .log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
  }
}
