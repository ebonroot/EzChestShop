package me.deadlight.ezchestshop.Data.SQLite;

public class Errors {

  public static String sqlConnectionExecute() {
    return "Couldn't execute MySQL statement: ";
  }

  public static String sqlConnectionClose() {
    return "Failed to close MySQL connection: ";
  }
}
