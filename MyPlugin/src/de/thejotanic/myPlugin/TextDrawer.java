package de.thejotanic.myPlugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import static de.thejotanic.myPlugin.TextDrawer.BlockType.*;

public class TextDrawer {
	public enum BlockType {
		A, B;
	}

	private static BlockType[][][] blocks = new BlockType[256][0][0];

	static {
		blocks[':'] = new BlockType[][] {
				{A, A, A, A, A, A},
				{A, A, B, B, A, A},
				{A, A, B, B, A, A},
				{A, A, A, A, A, A},
				{A, A, B, B, A, A},
				{A, A, B, B, A, A},
				{A, A, A, A, A, A},
		};
		blocks[' '] = new BlockType[][] {
				{A, A, A, A, A, A},
				{A, A, A, A, A, A},
				{A, A, A, A, A, A},
				{A, A, A, A, A, A},
				{A, A, A, A, A, A},
				{A, A, A, A, A, A},
		};
		blocks['-'] = new BlockType[][] {
				{A, A, A, A, A, A},
				{A, A, A, A, A, A},
				{A, B, B, B, B, A},
				{A, B, B, B, B, A},
				{A, A, A, A, A, A},
				{A, A, A, A, A, A},
		};
		blocks['0'] = new BlockType[][] {
				{A, A, B, B, A, A},
				{A, B, A, A, B, A},
				{A, B, A, A, B, A},
				{A, B, A, A, B, A},
				{A, B, A, A, B, A},
				{A, A, B, B, A, A},
		};
		blocks['1'] = new BlockType[][] {
				{A, A, A, B, A, A},
				{A, A, B, B, A, A},
				{A, B, A, B, A, A},
				{A, A, A, B, A, A},
				{A, A, A, B, A, A},
				{A, A, A, B, A, A},
		};
		blocks['2'] = new BlockType[][] {
				{A, A, B, B, A, A},
				{A, B, A, A, B, A},
				{A, B, A, A, B, A},
				{A, A, A, B, A, A},
				{A, A, B, A, A, A},
				{A, B, B, B, B, A},
		};
		//TODO more numbers, maybe even letters
	}

	public static void writeString(Location at, String str) {
		int len = str.length();
		Location char_loc = at.clone();
		for(int i = 0;i != len;i++) {
			writeChar(char_loc.clone(), str.charAt(i));
			// our characters are 6 blocks wide
			char_loc.add(6, 0, 0);
		}
	}

	private static void writeChar(Location at, char c) {
		if(blocks[c].length == 0) c = ' '; // just write a whitespace if this character is not yet defined

		// the last line is on the bottom
		for(int y = 5;y >= 0;y--) {
			Location line_loc = at.clone();
			for(int x = 0;x != 6;x++) {
				Block b = line_loc.getBlock();
				switch(blocks[c][y][x]) {
					case A:
						b.setType(Material.AIR);
						break;
					case B:
						b.setType(Material.GOLD_BLOCK);
						break;
				}

				line_loc.add(1, 0, 0);
			}
			at.add(0, 1, 0);
		}
	}
}
