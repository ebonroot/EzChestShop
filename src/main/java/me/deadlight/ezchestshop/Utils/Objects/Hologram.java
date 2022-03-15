package me.deadlight.ezchestshop.Utils.Objects;

import java.util.ArrayList;
import java.util.List;
import me.deadlight.ezchestshop.EzChestShop;
import me.deadlight.ezchestshop.Utils.ASHologram;
import me.deadlight.ezchestshop.Utils.FloatingItem;
import org.bukkit.Location;

public class Hologram {

  //Holo maps for Text and Items
  private List<ASHologram> holoTextMap = new ArrayList<>();
  private List<FloatingItem> holoItemMap = new ArrayList<>();

  public Hologram(List<ASHologram> texts, List<FloatingItem> items) {
    this.holoTextMap = texts;
    this.holoItemMap = items;
  }

  //Add a individual spawn/destroy method for Look and Load holograms.
  public Hologram spawnLoaded() {
    for (FloatingItem item : holoItemMap) {
      item.spawn();
    }
    return this;
  }

  public Hologram spawnLook() {
    for (ASHologram text : holoTextMap) {
      text.spawn();
    }
    return this;
  }

  public Hologram destroyLoaded() {
    for (FloatingItem item : holoItemMap) {
      item.destroy();
    }
    //holoItemMap.clear();
    return this;
  }

  public Hologram destroyLook() {
    for (ASHologram text : holoTextMap) {
      text.destroy();
      EzChestShop.logDebug("Destroyed Text");
    }
    return this;
  }

  public void updateTexts(List<String> text) {
    if (holoTextMap.size() == text.size()) {
      int i = 0;
      for (String txt : text) {
        holoTextMap.get(i).setName(txt);
        i++;
      }
    }
  }

  public void updateLocations(List<Location> locs) {
    if (holoTextMap.size() == locs.size()) {
      int i = 0;
      for (Location loc : locs) {
        ASHologram holo = holoTextMap.get(i);
        holo.setLocation(loc);
        holoTextMap.set(i, holo);
        i++;
      }
    }
  }
}
