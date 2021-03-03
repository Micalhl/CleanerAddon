package me.mical.cleaneraddon.command.subcommands;

import me.mical.cleaneraddon.CleanerAddon;
import me.mical.cleaneraddon.data.ItemsInventory;
import org.serverct.parrot.parrotx.api.ParrotXAPI;
import org.serverct.parrot.parrotx.command.BaseCommand;
import org.serverct.parrot.parrotx.utils.InventoryUtil;

public class OpenCommand extends BaseCommand {

    public OpenCommand() {
        super(
                ParrotXAPI.getPlugin(CleanerAddon.class),
                "open",
                0
        );
        describe("打开所有清理的垃圾界面");
        mustPlayer(true);
        perm(".open");
    }

    @Override
    protected void call(String[] args) {
        final ItemsInventory inv = new ItemsInventory(user);
        InventoryUtil.openInventory(plugin, user, inv.getInventory());
        lang.log.debug("&7为玩家 {0} 打开所有垃圾界面.", user.getName());
    }
}
