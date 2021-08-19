package conception;

import conception.Blocks.BlockConSmoke;
import conception.Blocks.BlockConWall;
import conception.Blocks.ItemBlockConSmoke;
import conception.Blocks.ItemBlockConWall;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class mainRegistry {

	public static Block blockWall,blockConSmoke;
	
	public mainRegistry() {
		BlockRegistry();
		addCraftingRecipes();
		
		
	}
		private void addCraftingRecipes() {
			GameRegistry.addRecipe(new ItemStack(blockWall,5,0), new Object[] {"BBB","BBB","B B",'B', new ItemStack(Blocks.planks,0,0)});
			GameRegistry.addRecipe(new ItemStack(blockWall,5,1), new Object[] {"BBB","BBB","B B",'B', new ItemStack(Blocks.planks,0,1)});
			GameRegistry.addRecipe(new ItemStack(blockWall,5,2), new Object[] {"BBB","BBB","B B",'B', new ItemStack(Blocks.planks,0,2)});
			GameRegistry.addRecipe(new ItemStack(blockWall,5,3), new Object[] {"BBB","BBB","B B",'B', new ItemStack(Blocks.planks,0,3)});
			GameRegistry.addRecipe(new ItemStack(blockWall,5,4), new Object[] {"BBB","BBB","B B",'B', new ItemStack(Blocks.planks,0,4)});
			GameRegistry.addRecipe(new ItemStack(blockWall,5,5), new Object[] {"BBB","BBB","B B",'B', new ItemStack(Blocks.planks,0,5)});

			GameRegistry.addRecipe(new ItemStack(blockWall,5,6), new Object[] {"BBB","BBB","B B",'B', new ItemStack(Blocks.sandstone,0,1)});
			GameRegistry.addRecipe(new ItemStack(blockWall,5,7), new Object[] {"BBB","BBB","B B",'B', new ItemStack(Blocks.double_stone_slab,0,0)});
			GameRegistry.addRecipe(new ItemStack(blockWall,5,8), new Object[] {"BBB","BBB","B B",'B', new ItemStack(Blocks.quartz_block,0,1)});
			GameRegistry.addRecipe(new ItemStack(blockWall,5,9), new Object[] {"BBB","BBB","B B",'B', new ItemStack(Blocks.nether_brick,0,0)});
			GameRegistry.addRecipe(new ItemStack(blockWall,5,10), new Object[] {"BBB","BBB","B B",'B', new ItemStack(Blocks.iron_block,0,0)});
	}
		public void BlockRegistry() {
			blockWall = new BlockConWall("ConWall").setBlockName("ConWall");
			GameRegistry.registerBlock(blockWall, ItemBlockConWall.class, "ItemBlockConWall");
			
			blockConSmoke = new BlockConSmoke("BlockConSmoke").setBlockName("BlockConSmoke");
			GameRegistry.registerBlock(blockConSmoke, ItemBlockConSmoke.class, "ItemBlockConSmoke");

		}

		private void regiBlock(Block serverBlock2) {

		GameRegistry.registerBlock(serverBlock2,serverBlock2.getUnlocalizedName());
		
	}
}