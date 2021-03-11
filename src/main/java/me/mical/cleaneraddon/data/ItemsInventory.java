package me.mical.cleaneraddon.data;

import me.mical.cleaneraddon.CleanerAddon;
import me.mical.cleaneraddon.utils.TempStorage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.serverct.parrot.parrotx.api.ParrotXAPI;
import org.serverct.parrot.parrotx.data.inventory.FileDefinedInventory;
import org.serverct.parrot.parrotx.data.inventory.PInventory;
import org.serverct.parrot.parrotx.data.inventory.element.InventoryButton;
import org.serverct.parrot.parrotx.data.inventory.element.InventoryTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsInventory extends PInventory<List<ItemStack>> {

    public ItemsInventory(Player viewer) {
        super(
                ParrotXAPI.getPlugin(CleanerAddon.class),
                TempStorage.getAllItemsFromItemMap(),
                viewer,
                ParrotXAPI.getPlugin(CleanerAddon.class).getFileWithResource("Gui.yml")
        );

        final FileDefinedInventory base = (FileDefinedInventory) getBase();
        for (String key : base.getItemMap().keySet()) {
            switch (key) {
                case "Template":
                    addElement(new InventoryTemplate.InventoryTemplateBuilder<ItemStack>()
                            .base(InventoryButton.builder()
                                    .base(base.get(key, 1, user -> true))
                                    .onClick(inventoryClickEvent -> {
                                        final HashMap<Integer, ItemStack> out = viewer.getInventory().addItem(inventoryClickEvent.getCurrentItem());
                                        if (!out.isEmpty()) {
                                            for (Map.Entry<Integer, ItemStack> entry : out.entrySet()) {
                                                viewer.getWorld().dropItem(viewer.getLocation(), entry.getValue());
                                            }
                                        }
                                    })
                                    .build())
                            .applyTemple((itemStack, itemStack2) -> itemStack2)
                            .contents(data)
                            .build());
                    break;
                case "Next":
                    addElement(InventoryButton.builder()
                            .base(base.get(key, 2, user -> InventoryTemplate.get(this, "Template").getMaxPage() > 1))
                            .onClick(inventoryClickEvent -> InventoryTemplate.get(this, "Template").nextPage(this))
                            .build());
                    break;
                case "Previous":
                    addElement(InventoryButton.builder()
                            .base(base.get(key, 2, user -> InventoryTemplate.get(this, "Template").getMaxPage() > 1))
                            .onClick(inventoryClickEvent -> InventoryTemplate.get(this, "Template").previousPage(this))
                            .build());
                    break;
                default:
                    addElement(base.get(key, 0, user -> true));
                    break;
            }
        }
    }
}
