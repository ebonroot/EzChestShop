package me.deadlight.ezchestshop.GUIs;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import me.deadlight.ezchestshop.Data.LanguageManager;
import me.deadlight.ezchestshop.Enums.LogType;
import me.deadlight.ezchestshop.Utils.Objects.TransactionLogObject;
import me.deadlight.ezchestshop.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

public class LogsGUI {

  static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

  public LogsGUI() {}

  public void showGUI(
    Player player,
    PersistentDataContainer data,
    Block containerBlock,
    LogType type,
    boolean isAdmin
  ) {
    LanguageManager lm = new LanguageManager();
    String guititle;
    if (type == LogType.TRANSACTION) {
      guititle = lm.transactionButtonTitle();
    } else {
      guititle = "&aAction logs";
    }

    Gui gui = new Gui(6, Utils.colorify(guititle));

    ItemStack door = new ItemStack(Material.DARK_OAK_DOOR, 1);
    ItemMeta doorMeta = door.getItemMeta();
    doorMeta.setDisplayName(lm.backToSettingsButton());
    door.setItemMeta(doorMeta);
    gui.setDefaultClickAction(event -> {
      event.setCancelled(true);
    });
    GuiItem doorItem = new GuiItem(
      door,
      event -> {
        event.setCancelled(true);
        SettingsGUI settingsGUI = new SettingsGUI();
        settingsGUI.showGUI(player, containerBlock, isAdmin);
      }
    );

    if (type == LogType.TRANSACTION) {
      //trans
      List<TransactionLogObject> transLogs = Utils.getListOfTransactions(
        containerBlock
      );
      Collections.reverse(transLogs);

      for (int count = 1; count <= transLogs.size(); count++) {
        ItemStack paperItem = new ItemStack(Material.PAPER);
        ItemMeta paperMeta = paperItem.getItemMeta();
        //set kardane etelaat
        TransactionLogObject thelog = transLogs.get(count - 1);
        if (thelog.type.equalsIgnoreCase("buy")) {
          paperMeta.setDisplayName(lm.transactionPaperTitleBuy(thelog.pname));
          paperMeta.setLore(generateLore(thelog, lm));
        } else {
          paperMeta.setDisplayName(lm.transactionPaperTitleSell(thelog.pname));
          paperMeta.setLore(generateLore(thelog, lm));
        }
        paperItem.setItemMeta(paperMeta);
        GuiItem paper = new GuiItem(
          paperItem,
          event -> {
            event.setCancelled(true);
          }
        );
        gui.setItem(count, paper);
      }
    }

    //until slot 53 there is space
    gui.setItem(0, doorItem);
    gui.open(player);
  }

  private List<String> generateLore(
    TransactionLogObject log,
    LanguageManager lm
  ) {
    List<String> lore;

    LocalDateTime time = LocalDateTime.parse(log.time, formatter);

    if (log.type.equalsIgnoreCase("buy")) {
      lore =
        lm.transactionPaperLoreBuy(
          log.price,
          log.count,
          getTimeString(time, lm)
        );
      return lore;
    } else {
      lore =
        lm.transactionPaperLoreSell(
          log.price,
          log.count,
          getTimeString(time, lm)
        );
      return lore;
    }
  }

  private String getTimeString(LocalDateTime time, LanguageManager lm) {
    String finalString;

    long minutes = ChronoUnit.MINUTES.between(time, LocalDateTime.now());
    long hours = ChronoUnit.HOURS.between(time, LocalDateTime.now());

    if (minutes < 1) {
      finalString = lm.lessthanminute();
    } else {
      if (hours < 1) {
        finalString = lm.minutesago(minutes);
      } else {
        if (hours < 24) {
          finalString = lm.hoursago(hours);
        } else {
          int days = (int) hours / 24;
          finalString = lm.daysago(days);
        }
      }
    }

    return finalString;
  }
}
