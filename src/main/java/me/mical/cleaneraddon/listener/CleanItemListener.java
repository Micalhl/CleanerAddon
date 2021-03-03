package me.mical.cleaneraddon.listener;

import me.mical.cleaneraddon.config.DataManager;
import me.zhanshi123.advancedcleaner.event.CleanItemEvent;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoload;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@PAutoload
public class CleanItemListener implements Listener {

    @EventHandler
    public void onCleanItem(CleanItemEvent event) {
        final Set<Item> items = event.getClearItem();
        final List<ItemStack> itemStackList = new ArrayList<>();
        items.forEach(item -> {
            final ItemStack itemStack = item.getItemStack();
            itemStackList.add(itemStack);
        });
        final long timestamp = System.currentTimeMillis();
        DataManager.getInstance().newData(timestamp, itemStackList);
    }
}
