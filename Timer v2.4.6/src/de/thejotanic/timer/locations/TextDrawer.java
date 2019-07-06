package de.thejotanic.timer.locations;

import static de.thejotanic.timer.locations.TextDrawer.BlockType.*;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Slab;

public class TextDrawer {
	
	public enum BlockType {
		A, B, U, D;
	}
	
	private static BlockType[][][] block = new BlockType[256][0][0];
	
	static {
		block[':'] = new BlockType[][] {
			{D},
			{U},
			{D},
			{U},
		};
		block[' '] = new BlockType[][] {
			{A},
			{A},
			{A},
			{A},
		};
		block['0'] = new BlockType[][] {
			{B, B, B},
			{B, A, B},
			{B, A, B},
			{B, B, B},
		};
		block['1'] = new BlockType[][] {
			{A, A, B},
			{A, A, B},
			{A, A, B},
			{A, A, B},
		};
		block['2'] = new BlockType[][] {
			{B, B, B},
			{D, D, B},
			{B, U, U},
			{B, B, B},
		};
		block['3'] = new BlockType[][] {
			{B, B, B},
			{D, D, B},
			{U, U, B},
			{B, B, B},
		};
		block['4'] = new BlockType[][] {
			{B, A, A},
			{B, A, B},
			{B, B, B},
			{A, A, B},
		};
		block['5'] = new BlockType[][] {
			{B, B, B},
			{B, D, D},
			{U, U, B},
			{B, B, B},
		};
		block['6'] = new BlockType[][] {
			{B, B, B},
			{B, D, D},
			{B, U, B},
			{B, B, B},
		};
		block['7'] = new BlockType[][] {
			{B, B, B},
			{A, A, B},
			{A, B, A},
			{A, B, A},
		};
		block['8'] = new BlockType[][] {
			{B, B, B},
			{B, D, B},
			{B, U, B},
			{B, B, B},
		};
		block['9'] = new BlockType[][] {
			{B, B, B},
			{B, D, B},
			{U, U, B},
			{B, B, B},
		};
		
	}
	
	public static BlockType[][][] getBlock() {
		return block;
	}
	
	public static int writeString(Location loc, String str) {
		int b_str_len = 0;
		Location char_loc = loc.clone();
		int str_len = str.length();
		
		for(int i = 0; i < str_len; i++) {
			char c = str.charAt(i);
			if(block[c].length == 0) c = ' ';
			int char_width = block[c][0].length;
			writeChar(char_loc.clone(), c, char_width);
			char_loc.add(0, 0, -(char_width+1));
			b_str_len += (char_width+1);
		}
		//-1 because don't count the last space!
		return (b_str_len-1);
	}
	
	public static void clearString(Location loc, int len) {
		Location clear_loc = loc.clone();
		int space_len = block[' '][0].length;
		for(int i = 0; i < len; i += space_len) {
			writeChar(clear_loc.clone(), ' ', space_len);
			clear_loc.add(0, 0, -space_len);
		}
	}
	
	private static void writeChar(Location loc, char c, int char_width) {
		for(int y = 0; y < 4; y++) {
			Location line_loc = loc.clone();
			for(int z = 0; z < char_width; z++) {
				Block b = line_loc.getBlock();
				
				switch(block[c][y][z]) {
					case A:
						b.setType(Material.AIR);
						break;
					case B:
						b.setType(Material.QUARTZ_BLOCK);
						break;
					case D:
						b.setType(Material.QUARTZ_SLAB);
						Slab slabbottom = (Slab) b.getBlockData();
						slabbottom.setType(Slab.Type.BOTTOM);
						b.setBlockData(slabbottom);
						break;
					case U:
						b.setType(Material.QUARTZ_SLAB);
						Slab slabtop = (Slab) b.getBlockData();
						slabtop.setType(Slab.Type.TOP);
						b.setBlockData(slabtop);
						break;
				}
				line_loc.add(0, 0, -1);
			}
			loc.add(0, -1, 0);
		}
		
	}

}