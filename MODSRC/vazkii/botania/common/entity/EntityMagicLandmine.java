/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 * 
 * File Created @ [Jul 12, 2014, 7:59:00 PM (GMT)]
 */
package vazkii.botania.common.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;

public class EntityMagicLandmine extends Entity {

	public EntityDoppleganger summoner;

	public EntityMagicLandmine(World par1World) {
		super(par1World);
		setSize(0F, 0F);
	}

	@Override
	public void onUpdate() {
		motionX = 0;
		motionY = 0;
		motionZ = 0;
		super.onUpdate();

		float range = 2.5F;

		float r = 0.2F;
		float g = 0F;
		float b = 0.2F;

		Botania.proxy.wispFX(worldObj, posX, posY, posZ, r, g, b, 0.6F, -0.2F, 1);
		for(int i = 0; i < 4; i++)
			Botania.proxy.wispFX(worldObj, posX - range + Math.random() * range * 2, posY, posZ - range + Math.random() * range * 2, r, g, b, 0.4F, -0.06F, 1);

		if(ticksExisted >= 45) {
			worldObj.playSoundEffect(posX, posY, posZ, "random.explode", 0.6F, (1F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F) * 1.4F);

			float m = 0.35F;
			g = 0.4F;
			for(int i = 0; i < 25; i++)
				Botania.proxy.wispFX(worldObj, posX, posY + 5, posZ, r, g, b, 0.5F, (float) (Math.random() - 0.5F) * m, (float) (Math.random() - 0.5F) * m, (float) (Math.random() - 0.5F) * m);

			if(!worldObj.isRemote) {
				List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(posX - range, posY - range, posZ - range, posX + range, posY + range, posZ + range));
				for(EntityPlayer player : players) {
					player.attackEntityFrom(summoner == null ? DamageSource.generic : DamageSource.causeMobDamage(summoner), 8);
					player.addPotionEffect(new PotionEffect(Potion.blindness.id, 80, 0));
					player.addPotionEffect(new PotionEffect(Potion.wither.id, 60, 3));
				}
			}

			setDead();
		}
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
	}

}