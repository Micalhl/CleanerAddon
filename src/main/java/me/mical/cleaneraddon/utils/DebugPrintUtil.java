package me.mical.cleaneraddon.utils;

import org.bukkit.inventory.meta.ItemMeta;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.utils.BasicUtil;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

import java.util.ArrayList;
import java.util.List;

public class DebugPrintUtil {

    public static void printItemStackList(PPlugin plugin, int data) {
        final List<String> result = new ArrayList<>();
        result.add("&7触发清理掉落物事件(CleanItemEvent).");
        result.add("&7时间: " + TempStorage.formatTimeMap.get(data) + ".");
        result.add("&7时间戳: " + TempStorage.timeMap.get(data) + ".");
        result.add("&7标识: &a" + data + "&7.");
        result.add("&7掉落物数量: &a" + TempStorage.itemMap.get(data).size() + "&7.");
        result.add("&7掉落物品:");
        TempStorage.itemMap.get(data).forEach(object -> {
            final String name = BasicUtil.thisOrElse(BasicUtil.canReturn(object.getItemMeta(), ItemMeta::getDisplayName), object.getType().toString());
            result.add(I18n.format("&9- &f物品 &c{0} &c* {1} &f(&9{2}&f)", name, object.getAmount(), "ItemStack.class"));
            BasicUtil.canDo(BasicUtil.canReturn(object.getItemMeta(), ItemMeta::getLore), strings -> strings.forEach(s -> result.add(I18n.format("&7|  {0}", s))));
        });
        result.forEach(plugin.getLang().log::debug);
    }
}
