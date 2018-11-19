package de.thejotanic.myPlugin.countdown;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import de.thejotanic.myPlugin.TextDrawer;
import de.thejotanic.myPlugin.main.Main;

public class Scheduler implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		// player only
		if(!(sender instanceof Player)) {
			return false;
		}
		Player p = (Player) sender;

		// myPlugins.countdown only
		if(!p.hasPermission("myPlugins.countdown")) {
			p.sendMessage("§cDazu hast du keine Rechte!");
			return false;
		}

		// dont place the countdown inside the player
		Location cd_loc = p.getLocation().clone();
		cd_loc.add(0, 0, 3);

		CountdownDrawer drawer = new CountdownDrawer(cd_loc);
		// 20 ticks are 1 second
		BukkitTask task = Bukkit.getScheduler().runTaskTimer(Main.getPlugin(), drawer, 1, 20);
		drawer.task = task;

		return true;
	}

	public static class CountdownDrawer implements Runnable {

		private Location at;
		private int seconds;
		private BukkitTask task = null;

		public CountdownDrawer(Location at) {
			seconds = 100;
			this.at = at;
		}


		@Override
		public void run() {
			if(task == null) return;

			int mins = seconds/60;
			int secs = seconds%60;

			String mins_str = Integer.toString(mins);
			String secs_str = Integer.toString(secs);

			// add starting zero if necessary
			if(mins_str.length() < 2) mins_str = "0" + mins_str;
			if(secs_str.length() < 2) secs_str = "0" + secs_str;

			TextDrawer.writeString(at, mins_str + ":" + secs_str);
			Bukkit.broadcastMessage("[debug] countdown at " + seconds + "s");


			seconds--;

			if(seconds == 0) {
				TextDrawer.writeString(at, "--:--");
				Bukkit.broadcastMessage("countdown has finished!");
				task.cancel();
			}
		}
	}
}
