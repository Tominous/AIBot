/*******************************************************************************
 *     Copyright (C) 2015 Jordan Dalton (jordan.8474@gmail.com)
 *
 *     This program is free software; you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation; either version 2 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along
 *     with this program; if not, write to the Free Software Foundation, Inc.,
 *     51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *******************************************************************************/
package ovh.tgrhavoc.aibot.protocol.v61.packets;

import java.io.*;

import ovh.tgrhavoc.aibot.protocol.*;
import ovh.tgrhavoc.aibot.world.*;

public class Packet9Respawn extends AbstractPacket implements ReadablePacket {
	public Dimension respawnDimension;
	public Difficulty difficulty;
	public GameMode gameMode;
	public WorldType worldType;

	public int worldHeight;

	public Packet9Respawn() {
	}

	public void readData(DataInputStream in) throws IOException {
		respawnDimension = Dimension.getDimensionById(in.readInt());
		difficulty = Difficulty.getDifficultyById(in.readByte());
		gameMode = GameMode.getGameModeById(in.readByte());
		worldHeight = in.readShort();
		String s = readString(in, 16);
		worldType = WorldType.parseWorldType(s);

		if(worldType == null)
			worldType = WorldType.DEFAULT;
	}

	public int getId() {
		return 9;
	}
}