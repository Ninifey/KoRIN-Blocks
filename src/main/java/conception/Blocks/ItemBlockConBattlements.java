package conception.Blocks;

import java.util.List;

import conception.CONMAIN;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlockConBattlements extends ItemBlock {

public ItemBlockConBattlements(Block cable) {
	super(cable);
	this.setHasSubtypes(true);
	this.setUnlocalizedName(cable.getUnlocalizedName());
}

@Override
public int getMetadata (int meta) {
	return meta;
}

@Override
public String getUnlocalizedName (ItemStack stack) {
	return this.getUnlocalizedName() + "." + stack.getItemDamage();
}

@Override
public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
	int meta = stack.getItemDamage();

	list.add(EnumChatFormatting.BLUE+"Kingdome of Rin | Blocks"); //This was a tooltip test.
}

@Override
@SideOnly(Side.CLIENT)
public void registerIcons(IIconRegister par1IconRegister) {
  par1IconRegister.registerIcon(CONMAIN.MODID+":battlements");
}
}