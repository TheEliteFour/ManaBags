package net.aesircraft.ManaBags.Commands;

import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class whatisthis implements CommandExecutor
{
	public static ManaBags plugin;
	 public whatisthis(ManaBags instance){
		 plugin=instance;
	 }
	@Override
	public boolean onCommand(CommandSender player, Command command, String cmd,
			String[] comA) {
		Player p=(Player) player;
		if (p.getItemInHand()==null){
		    p.sendMessage("§4Hold an item then do this!");
		    return true;
		}
		p.sendMessage("§eID: §b"+p.getItemInHand().getTypeId()+"§e Data: §b"+p.getItemInHand().getDurability()+"§e '§b"+p.getItemInHand().getTypeId()+"-"+p.getItemInHand().getDurability()+"§e'");
		return true;
	}
}
