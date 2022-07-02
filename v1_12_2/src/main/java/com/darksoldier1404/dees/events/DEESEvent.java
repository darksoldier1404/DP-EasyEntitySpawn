package com.darksoldier1404.dees.events;

import com.darksoldier1404.dees.EasyEntitySpawn;
import com.darksoldier1404.dees.functions.DEESFunction;
import com.darksoldier1404.dppc.api.inventory.DInventory;
import com.darksoldier1404.dppc.utils.NBT;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class DEESEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getRawSlot() < 54) {
            if(DEESFunction.currentInv.containsKey(e.getWhoClicked().getUniqueId())) {
                DInventory di = DEESFunction.currentInv.get(e.getWhoClicked().getUniqueId());
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

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e) {
        if(DEESFunction.currentInv.containsKey(e.getPlayer().getUniqueId())) {
            DEESFunction.currentInv.remove(e.getPlayer().getUniqueId());
        }
    }
}
