package io.github.coachluck.commands;

import io.github.coachluck.EssentialServer;
import io.github.coachluck.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Vanish implements CommandExecutor {

    private EssentialServer plugin;
    private String vanishOtherMsg;
    private String vanishOtherOffMsg;
    private String vanishMsg;
    private String vanishOffMsg;
    private String offlinePlayer;
    private boolean enableMsg;

    public Vanish(EssentialServer ins) {
        this.plugin = ins;
        vanishOtherMsg = plugin.getConfig().getString("vanish.other-on-message");
        vanishOtherOffMsg = plugin.getConfig().getString("vanish.other-off-message");
        enableMsg = plugin.getConfig().getBoolean("vanish.message-enable");
        vanishMsg = plugin.getConfig().getString("vanish.on-message");
        vanishOffMsg = plugin.getConfig().getString("vanish.off-message");
        offlinePlayer = ins.getConfig().getString("offline-player");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0 && sender.hasPermission("essentialserver.vanish")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                vanishCheck(p.getUniqueId());
            } else ChatUtils.logMsg("&cYou must be a player to execute this command!");
        }
        else if (args.length == 1 && sender.hasPermission("essentialserver.vanish.others")) {
            Player tP = Bukkit.getPlayerExact(args[0]);
            if(tP == null) {
                ChatUtils.msg(sender,offlinePlayer.replaceAll("%player%", args[0]));
                return true;
            }
            UUID target = tP.getUniqueId();
            vanishCheck(target);
            if (enableMsg && !sender.getName().equals(tP.getName())) {
                if (plugin.vanish_players.contains(target))
                    ChatUtils.msg(sender, vanishOtherMsg.replaceAll("%player%", tP.getDisplayName()));
                 else if (!plugin.vanish_players.contains(target))
                    ChatUtils.msg(sender, vanishOtherOffMsg.replaceAll("%player%", tP.getDisplayName()));
            }
        }
        else if (args.length > 1)
            ChatUtils.msg(sender, "&cToo many arguments! Try /vanish <player> or /vanish.");

        return true;
    }

    private void vanishCheck(UUID pUUID) {
        Player player = Bukkit.getServer().getPlayer(pUUID);
        assert player != null;
        if (plugin.vanish_players.contains(pUUID)) {
            for(Player people : Bukkit.getServer().getOnlinePlayers()) {
                people.showPlayer(plugin, player);
            }
            plugin.vanish_players.remove(pUUID);
            player.setInvulnerable(false);
            if (enableMsg) ChatUtils.msg(player, vanishOffMsg.replace("%player%", player.getDisplayName()));
        } else if (!plugin.vanish_players.contains(pUUID)) {
            for(Player people : Bukkit.getServer().getOnlinePlayers()) {
                people.hidePlayer(plugin, player);
            }
            plugin.vanish_players.add(pUUID);
            player.setInvulnerable(true);
            if (enableMsg) ChatUtils.msg(player, vanishMsg.replace("%player%", player.getDisplayName()));
        }
    }
}
