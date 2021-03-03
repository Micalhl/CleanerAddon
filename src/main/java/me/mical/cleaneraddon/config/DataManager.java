package me.mical.cleaneraddon.config;

import me.mical.cleaneraddon.CleanerAddon;
import me.mical.cleaneraddon.utils.DebugPrintUtil;
import me.mical.cleaneraddon.utils.MapUtil;
import me.mical.cleaneraddon.utils.TempStorage;
import org.bukkit.inventory.ItemStack;
import org.serverct.parrot.parrotx.api.ParrotXAPI;
import org.serverct.parrot.parrotx.config.PConfig;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoload;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoloadGroup;
import org.serverct.parrot.parrotx.utils.TimeUtil;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

import java.util.*;

@PAutoloadGroup
@PAutoload
public class DataManager extends PConfig {

    private static DataManager instance;

    public DataManager() {
        super(ParrotXAPI.getPlugin(CleanerAddon.class), "data", "数据文件");
    }

    public static DataManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DataManager();
        }
        return instance;
    }

    @PAutoload("TimeMap")
    public static Map<Integer, Long> timeMap;

    @PAutoload("TimeFormatMap")
    public static Map<Integer, String> timeFormatMap;

    @PAutoload("ItemMap")
    public static Map<Integer, List<ItemStack>> itemMap;

    public int newDataInt() {
        return timeMap.size() + 1;
    }

    public void newData(Long timestamp, List<ItemStack> itemMap) {
        final int data = newDataInt();
        TempStorage.timeMap.put(data, timestamp);
        TempStorage.formatTimeMap.put(data, TimeUtil.getDefaultFormatDate(new Date(timestamp)));
        TempStorage.itemMap.put(data, itemMap);
        DebugPrintUtil.printItemStackList(plugin, data);
    }

    @Override
    public void load() {
        super.load();
        try {
            TempStorage.timeMap = timeMap;
            MapUtil.formatMap(timeMap, "timeMap").forEach(s -> lang.log.debug("{0}", s));
            TempStorage.formatTimeMap = timeFormatMap;
            MapUtil.formatMap(timeFormatMap, "timeFormatMap").forEach(s -> lang.log.debug("{0}", s));
            TempStorage.itemMap = itemMap;
            MapUtil.formatMap(itemMap, "itemMap").forEach(s -> lang.log.debug("{0}", s));
            lang.log.debug("已成功从临时文件中加载全部数据.");
        } catch (Throwable e) {
            lang.log.error(I18n.LOAD, name(), e, null);
        }
    }

    @Override
    public void save() {
        getConfig().set("TimeMap", TempStorage.timeMap);
        getConfig().set("TimeFormatMap", TempStorage.formatTimeMap);
        getConfig().set("ItemMap", TempStorage.itemMap);
        super.save();
        lang.log.debug("已成功保存全部数据至临时文件.");
    }
}