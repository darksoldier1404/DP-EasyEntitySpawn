package com.darksoldier1404.dees.events;

import com.darksoldier1404.dees.EasyEntitySpawn;
import com.darksoldier1404.dppc.api.inventory.DInventory;
import com.darksoldier1404.dppc.utils.NBT;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class DEESEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getClickedInventory() != null) {
            Inventory inv = e.getClickedInventory();
            if(inv instanceof DInventory) {
                DInventory di = (DInventory) inv;
                if(di.isValidHandler(EasyEntitySpawn.getInstance())) {
                    e.setCancelled(true);
                    if(e.getCurrentItem() != null) {
                        if (NBT.hasTagKey(e.getCurrentItem(), "prev")) {
                            di.prevPage();
                            return;
                        }
                        if (NBT.hasTagKey(e.getCurrentItem(), "next")) {
                            di.nextPage();
                            return;
                        }
                        if (NBT.hasTagKey(e.getCurrentItem(), "spawn")) {
                            String name = NBT.getStringTag(e.getCurrentItem(), "spawn");
                            e.getWhoClicked().getWorld().spawnEntity(e.getWhoClicked().getLocation(), EntityType.valueOf(name));
                        }
                    }
                }
            }
        }
    }
}
