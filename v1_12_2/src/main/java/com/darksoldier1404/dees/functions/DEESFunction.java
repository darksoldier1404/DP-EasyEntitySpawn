package com.darksoldier1404.dees.functions;

import com.darksoldier1404.dees.EasyEntitySpawn;
import com.darksoldier1404.dppc.api.inventory.DInventory;
import com.darksoldier1404.dppc.utils.NBT;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("all")
public class DEESFunction {
    private static final EasyEntitySpawn plugin = EasyEntitySpawn.getInstance();
    private static final String prefix = EasyEntitySpawn.prefix;
    public static final Map<UUID, DInventory> currentInv = new HashMap<>();

    public static void openGUI(Player p) {
        DInventory inv = new DInventory(null, "§aDEES 엔티티 선택", 54, true, plugin);
        ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemStack prev = NBT.setStringTag(new ItemStack(Material.INK_SACK, 1, (short) 5), "prev", "true");
        ItemMeta im = prev.getItemMeta();
        im.setDisplayName("§a§l이전");
        prev.setItemMeta(im);
        ItemStack next = NBT.setStringTag(new ItemStack(Material.INK_SACK, 1, (short) 6), "next", "true");
        im = next.getItemMeta();
        im.setDisplayName("§b§l다음");
        next.setItemMeta(im);
        inv.setPageTools(new ItemStack[]{pane, pane, prev, pane, pane, pane, next, pane, pane});
        ItemStack item = new ItemStack(Material.MONSTER_EGG);
        int page = 0;
        int count = 0;
        ItemStack[] items = new ItemStack[45];
        for(EntityType type : EntityType.values()) {
            if(type.isSpawnable()) {
                if(count == 45) {
                    inv.addPageContent(items);
                    page++;
                    count = 0;
                    items = new ItemStack[45];
                }
                im = item.getItemMeta();
                im.setDisplayName("§b§l" + type.name() + " 스폰");
                im.setLore(Arrays.asList("§6클릭시 엔티티를 스폰합니다."));
                item.setItemMeta(im);
                items[count] = NBT.setStringTag(item, "spawn", type.name()).clone();
                System.out.println(type.name() + count);
                count++;
            }
        }
        inv.setPageContent(page, items);
        inv.update();
        p.openInventory(inv);
        currentInv.put(p.getUniqueId(), inv);
    }
}
