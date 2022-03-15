package me.deadlight.ezchestshop.Utils;

import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class v1_17_R1 extends VersionUtils {

  /**
   * Convert a Item to a Text Compount. Used in Text Component Builders to show
   * items in chat.
   *
   * @category ItemUtils
   * @param itemStack
   * @return
   */
  @Override
  String ItemToTextCompoundString(ItemStack itemStack) {
    // First we convert the item stack into an NMS itemstack
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
      itemStack
    );
    NBTTagCompound compound = new NBTTagCompound();
    compound = nmsItemStack.save(compound);

    return compound.toString();
  }

  @Override
  int getArmorStandIndex() {
    return 15;
  }

  @Override
  int getItemIndex() {
    return 8;
  }

  @Override
  void destroyEntity(Player player, int entityID) {
    ((org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer) player).getHandle()
      .b.sendPacket(
        new net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy(
          entityID
        )
      );
  }
}
