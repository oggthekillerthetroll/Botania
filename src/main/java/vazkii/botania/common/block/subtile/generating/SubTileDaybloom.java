/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 25, 2014, 3:09:35 PM (GMT)]
 */
package vazkii.botania.common.block.subtile.generating;

import java.awt.Color;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISubTileContainer;
import vazkii.botania.common.Botania;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibMisc;

public class SubTileDaybloom extends SubTilePassiveGenerating {

	@Override
	public int getColor() {
		return 0xFFFF00;
	}





	public int getSurroundingFlowers() {
		int flowers = 0;
		for(ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
			TileEntity tile = supertile.getWorldObj().getTileEntity(supertile.xCoord + dir.offsetX, supertile.yCoord, supertile.zCoord + dir.offsetZ);
			if(tile != null && tile instanceof ISubTileContainer) {
				ISubTileContainer flower = (ISubTileContainer) tile;
				if(flower.getSubTile() != null && flower.getSubTile().getClass() == getClass()) {
					flowers++;

					Color color = new Color(getColor());
					float r = color.getRed() / 255F;
					float g = color.getGreen() / 255F;
					float b = color.getBlue() / 255F;

					float m = 0.045F;
					if(ticksExisted % 10 == 0)
						Botania.proxy.wispFX(supertile.getWorldObj(), supertile.xCoord + 0.5, supertile.yCoord + 0.05, supertile.zCoord + 0.5, r, g, b, 0.1F, dir.offsetX * m, dir.offsetY * m, dir.offsetZ * m);
				}
			}
		}

		return flowers;
	}

	@Override
	public boolean shouldSyncPassiveGeneration() {
		return true;
	}

	@Override
	public LexiconEntry getEntry() {
		return LexiconData.daybloom;
	}

}
