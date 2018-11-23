package de.thejotanic.myPlugin.countdown;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Scheduler implements CommandExecutor {
	
	private Location loc;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			return false;
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("myPlugin.countdown")) {
			p.sendMessage("§cDazu hast du keine Rechte!");
			return false;
		}
		
		if(args.length != 3) {
			p.sendMessage("§cBitte benutze §6/scheduler <x> <y> <z>§c!");
			return false;
		}
		
		try {
			
			int x = Integer.valueOf(args[0]);
			int y = Integer.valueOf(args[1]);
			int z = Integer.valueOf(args[2]);
			
			this.loc = new Location(p.getWorld(), x, y, z);
			
		} catch (Exception e) {
			p.sendMessage("§cFehler beim Übergeben der Werte!");
			p.sendMessage("§cBitte benutze Zahlen!");
		}
		
		CountdownDrawer drawer = new CountdownDrawer(loc);
		BukkitTask task = Bukkit.getScheduler().runTaskTimer(Main.getPlugin(), drawer, 100, 20);
		drawer.task = task;
		
		return true;
	}
	
	public static class CountdownDrawer implements Runnable {
		
		private Location loc;
		private int seconds;
		private BukkitTask task = null;
		
		public CountdownDrawer(Location loc) {
			seconds = 300;
			this.loc = loc;
		}

		@Override
		public void run() {
			
			if(task == null) return;
			
			int minutes = seconds/60;
			int seconds = this.seconds%60;
			
			String min_str = Integer.toString(minutes);
			String sec_str = Integer.toString(seconds);
			
			if(min_str.length() < 2) min_str = "0" + min_str;
			if(sec_str.length() < 2) sec_str = "0" + sec_str;
			
			TextDrawer.writeString(loc, min_str + ":" + sec_str);
			Bukkit.broadcastMessage("§8[debug] countdown at §e" + seconds + "§8sec!");
			
			seconds--;
			
			if(seconds <= 0) {
				TextDrawer.writeString(loc, "     ");
				Bukkit.broadcastMessage("§aDer Countdown ist abgelaufen!");
				task.cancel();
			}
			
		}

	}
	
}
