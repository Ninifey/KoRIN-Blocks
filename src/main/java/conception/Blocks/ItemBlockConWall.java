package conception.Blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlockConWall extends ItemBlock {

public ItemBlockConWall(Block cable) {
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
}