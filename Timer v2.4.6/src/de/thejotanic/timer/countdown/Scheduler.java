package de.thejotanic.timer.countdown;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import de.thejotanic.timer.locations.TextDrawer;
import de.thejotanic.timer.main.Main;

public class Scheduler implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Location cd_loc;
		int time;
		boolean active = false;
		
		if(!(sender instanceof Player)) {
			return false;
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("myPlugin.countdown")) {
			p.sendMessage("§cDazu hast du keine Rechte!");
			return false;
		}
		
		if(!(CountdownDrawer.task == null && CountdownDrawer.loc == null && CountdownDrawer.str == null)) {
			active = true;
		}
		
		if(!active) {
			if(args.length == 4) {
				try {
					
					int x = Integer.valueOf(args[0]);
					int y = Integer.valueOf(args[1]);
					int z = Integer.valueOf(args[2]);
					time = Integer.valueOf(args[3]);
					
					cd_loc = new Location(p.getWorld(), x, y, z);
					
					if(!((time < 6000) && (time >= 0))) {
						p.sendMessage("§cMaximal 5999, mindestens 0!");
						return false;
					}
					
				} catch (Exception e) {
					p.sendMessage("§cFehler beim übergeben der Werte!");
					p.sendMessage("§cBitte benutze §6/countdown <x> <y> <z> <sekunden>\n§cund gib ganze Zahlen an!");
					return false;
				}
				CountdownDrawer drawer = new CountdownDrawer(cd_loc, time);
				BukkitTask task = Bukkit.getScheduler().runTaskTimer(Main.getPlugin(), drawer, 100, 20);
				CountdownDrawer.task = task;
				Bukkit.broadcastMessage("§aTimer beginnt in 5 Sekunden!");
				
				return true;
			}
			else {
				p.sendMessage("§cBitte benutze §6/countdown <x> <y> <z> <sekunden>§c, um einen\nCountdown zu aktivieren!");
				return false;
			}
		}
		else if(active) {
			if(args.length == 1 && args[0].equalsIgnoreCase("continue")) {
				CountdownDrawer drawer = new CountdownDrawer(CountdownDrawer.getLoc(), CountdownDrawer.getSeconds());
				BukkitTask task = Bukkit.getScheduler().runTaskTimer(Main.getPlugin(), drawer, 0, 20);
				CountdownDrawer.task = task;
				p.sendMessage("§aDer Countdown wurde fortgesetzt!");
				return true;
			}
			else if(args.length == 1 && args[0].equalsIgnoreCase("stop")) {
				CountdownDrawer.task.cancel();
				p.sendMessage("§aCountdown wurde angehalten.\nDu kannst ihn nun mit §6/countdown clear §aentfernen\noder mit §6/countdown continue §afortsetzen!");
				return true;
			}
			else if(args.length == 1 && args[0].equalsIgnoreCase("clear")) {
				CountdownDrawer.countdownDelete();
				p.sendMessage("§aDer Countdown wurde angehalten und entfernt!");
				return true;
			}
			else {
				p.sendMessage("§cBitte benutze §6/countdown stop§c, §6/countdown continue\n§coder §6/countdown clear§c, um den Countdown anzuhalten,\nfortzusetzen oder zu löschen.");
				return false;
			}
		}
		else {
			p.sendMessage("§cEs kam zu einen Fehler! Bitte versuche es erneut.");
			return false;
		}
	}
		
	public static class CountdownDrawer extends TextDrawer implements Runnable {
		
		private static Location loc;
		private static int seconds;
		private static BukkitTask task = null;
		private static String str;
		
		public CountdownDrawer(Location loc, int time) {
			seconds = time;
			CountdownDrawer.loc = loc;
			str = getMin_str() + ":" + getSec_str();
			
			TextDrawer.writeString(loc, str);
		}
		
		@Override
		public void run() {
			if(task == null) return;
			
			if(seconds >= 0) {
				str = getMin_str() + ":" + getSec_str();
				TextDrawer.writeString(loc, str);
				
				if(seconds == 0) Bukkit.broadcastMessage("§aDer Countdown ist abgelaufen!");
			}
			
			else if(seconds <= -10) {
				countdownDelete();
			}
			seconds--;
		}
		
		private String getMin_str() {
			if(!(seconds >= 0)) return null;
			
			int mins = seconds/60;
			String min_str = Integer.toString(mins);
			if(min_str.length() < 2) min_str = "0" + min_str;
			
			return min_str;
		}
		
		private String getSec_str() {
			if(!(seconds >= 0)) return null;
			
			int secs = CountdownDrawer.seconds%60;
			String sec_str = Integer.toString(secs);
			if(sec_str.length() < 2) sec_str = "0" + sec_str;
			
			return sec_str;
		}
		
		private static void countdownDelete() {
			task.cancel();
			TextDrawer.clearString(loc, TextDrawer.writeString(loc, str));
			task = null;
			loc = null;
			str = null;
		}
		
		public static int getSeconds() {
			return seconds;
		}
		
		public static Location getLoc() {
			return loc;
		}
		
	}
	
}