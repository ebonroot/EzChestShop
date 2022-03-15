package me.deadlight.ezchestshop.Utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.Util;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Registry;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.WrappedDataWatcherObject;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import me.deadlight.ezchestshop.Packets.PlayEntityDestory_1_17_1;
import me.deadlight.ezchestshop.Packets.WrapperPlayServerEntityMetadata;
import me.deadlight.ezchestshop.Packets.WrapperPlayServerEntityTeleport;
import me.deadlight.ezchestshop.Packets.WrapperPlayServerSpawnEntity;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ASHologram {

  private int entityID;
  private WrapperPlayServerSpawnEntity spawn;
  private WrapperPlayServerEntityMetadata meta;
  //private WrapperPlayServerEntityDestroy destroy;
  private String name;
  private Player handler;

  public ASHologram(
    Player p,
    String name,
    EntityType type,
    Location loc,
    boolean isGlowing
  ) {
    UUID uuid = UUID.randomUUID();

    this.name = name;
    byte meta;
    byte armorMeta = 0x01 | 0x10;
    if (isGlowing) {
      meta = 0x20 | 0x40;
    } else {
      meta = 0x20;
    }
    this.entityID = (int) (Math.random() * Integer.MAX_VALUE);
    this.handler = p;
    this.spawn = new WrapperPlayServerSpawnEntity();
    this.meta = new WrapperPlayServerEntityMetadata();
    //this.destroy = new WrapperPlayServerEntityDestroy();
    this.spawn.setType(type);
    this.spawn.setEntityID(entityID);
    this.spawn.setUniqueId(uuid);
    this.spawn.setX(loc.getX());
    this.spawn.setY(loc.getY());
    this.spawn.setZ(loc.getZ());
    WrappedChatComponent nick = WrappedChatComponent.fromText(name);
    //1.17 = 15 | 1.16 and lower 14
    int armorstandindex = 14;
    if (Utils.family1_17) {
      armorstandindex = 15;
    }

    List<WrappedWatchableObject> obj = Util.asList(
      new WrappedWatchableObject(
        new WrappedDataWatcherObject(armorstandindex, Registry.get(Byte.class)),
        armorMeta
      ),
      new WrappedWatchableObject(
        new WrappedDataWatcherObject(0, Registry.get(Byte.class)),
        meta
      ),
      new WrappedWatchableObject(
        new WrappedDataWatcherObject(3, Registry.get(Boolean.class)),
        true
      ),
      new WrappedWatchableObject(
        new WrappedDataWatcherObject(
          2,
          Registry.getChatComponentSerializer(true)
        ),
        Optional.of(nick.getHandle())
      )
    );
    this.meta = new WrapperPlayServerEntityMetadata();
    this.meta.setEntityID(entityID);
    this.meta.setMetadata(obj);
  }

  public void spawn() {
    this.spawn.sendPacket(handler);
    this.meta.sendPacket(handler);
  }

  public void setLocation(Location loc) {
    WrapperPlayServerEntityTeleport teleport = new WrapperPlayServerEntityTeleport();
    teleport.setEntityID(entityID);
    //
    teleport.setX(loc.getX());
    teleport.setY(loc.getY());
    teleport.setZ(loc.getZ());
    //
    teleport.sendPacket(handler);
  }

  public void setName(String name) {
    WrappedChatComponent nick = WrappedChatComponent.fromText(
      ChatColor.translateAlternateColorCodes('&', name)
    );
    this.name = name;
    this.meta.getMetadata().get(3).setValue(Optional.of(nick.getHandle()));
    meta.sendPacket(handler);
  }

  public void destroy() {
    PacketContainer destroyEntityPacket = new PacketContainer(
      PacketType.Play.Server.ENTITY_DESTROY
    );
    if (Utils.is1_17) {
      destroyEntityPacket.getIntegers().writeSafely(0, entityID);
    } else if (Utils.is1_17_1) {
      PlayEntityDestory_1_17_1.destroy(this.handler, entityID);
    } else {
      destroyEntityPacket.getIntegers().writeSafely(0, 1);
      destroyEntityPacket
        .getIntegerArrays()
        .writeSafely(0, new int[] { entityID });
    }
    try {
      ProtocolLibrary
        .getProtocolManager()
        .sendServerPacket(handler, destroyEntityPacket);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setHandler(Player p) {
    this.handler = p;
  }

  public String getName() {
    return name;
  }
}
