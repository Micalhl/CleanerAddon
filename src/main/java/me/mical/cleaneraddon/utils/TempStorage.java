package me.mical.cleaneraddon.utils;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempStorage {

    public static Map<Integer, Long> timeMap = new HashMap<>();

    public static Map<Integer, String> formatTimeMap = new HashMap<>();

    public static Map<Integer, List<ItemStack>> itemMap = new HashMap<>();

    public static List<ItemStack> getAllItemsFromItemMap() {
        final List<ItemStack> result = new ArrayList<>();
        itemMap.values().forEach(result::addAll);
        return result;
    }
}
