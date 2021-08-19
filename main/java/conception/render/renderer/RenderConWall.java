package conception.render.renderer;

import conception.mainRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderConWall implements ISimpleBlockRenderingHandler {
	int renderType = 0;
	
	public RenderConWall(int r) {
		renderType = r;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		switch(metadata) {
		case 0:{ renderer.renderBlockAsItem(Blocks.planks, 0, 1.0F);   break;}
		case 1:{ renderer.renderBlockAsItem(Blocks.planks, 1, 1.0F);   break;}
		case 2:{ renderer.renderBlockAsItem(Blocks.planks, 2, 1.0F);   break;}
		case 3:{ renderer.renderBlockAsItem(Blocks.planks, 3, 1.0F);   break;}
		case 4:{ renderer.renderBlockAsItem(Blocks.planks, 4, 1.0F);   break;}
		case 5:{ renderer.renderBlockAsItem(Blocks.planks, 5, 1.0F);   break;}
		case 6:{ renderer.renderBlockAsItem(Blocks.sandstone, 1, 1.0F);   break;}
		case 7:{ renderer.renderBlockAsItem(Blocks.double_stone_slab, 0, 1.0F);   break;}
		case 8:{ renderer.renderBlockAsItem(Blocks.quartz_block, 1, 1.0F);   break;}
		case 9:{ renderer.renderBlockAsItem(Blocks.nether_brick, 0, 1.0F);   break;}
		case 10:{ renderer.renderBlockAsItem(Blocks.iron_block, 0, 1.0F);   break;}
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {


		Tessellator tessellator = Tessellator.instance;
		int lightValue = block.getMixedBrightnessForBlock(world, x, y, z);
		int part = world.getBlockMetadata(x, y, z);
		IIcon sideIron = Blocks.double_stone_slab.getIcon(0,0);
		tessellator.setBrightness(lightValue-2);
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		
    	boolean north = check(world.getBlock(x, y, z-1));
    	boolean south = check(world.getBlock(x, y, z+1));
    	boolean east = check(world.getBlock(x+1, y, z));
    	boolean west = check(world.getBlock(x-1, y, z));
    	boolean up = check(world.getBlock(x, y+1, z));
    	boolean down = check(world.getBlock(x, y-1, z));
    	boolean isPillar = false;

		if((!north && !south && !east && west)||(!north && south && east && west)||(north && south && east && !west)||(north && south && !east && west)||(north && !south && east && west)||(!north && !south && east && !west)||(!north && south && !east && !west)||(north && !south && !east && !west)||(!north && !south && !east && !west)||(north && south && east && west)) {isPillar = true;}
		if((!north && south && !east && west)||(!north && south && east && !west)||(north && !south && east && !west)||(north && !south && !east && west)) {isPillar =  true;}
    	if(up||down) {
    		isPillar =  true;
    	}
		switch(part) {
		case 0:{ sideIron = Blocks.planks.getIcon(0,0);    break;}
		case 1:{ sideIron = Blocks.planks.getIcon(0,1);    break;}
		case 2:{ sideIron = Blocks.planks.getIcon(0,2);    break;}
		case 3:{ sideIron = Blocks.planks.getIcon(0,3);    break;}
		case 4:{ sideIron = Blocks.planks.getIcon(0,4);    break;}
		case 5:{ sideIron = Blocks.planks.getIcon(0,5);    break;}
		case 6:{ sideIron = Blocks.sandstone.getIcon(0,1);    break;}
		case 7:{ sideIron = Blocks.double_stone_slab.getIcon(0,0);    break;}
		case 8:{ sideIron = Blocks.quartz_block.getIcon(0,1);    break;}
		case 9:{ sideIron = Blocks.nether_brick.getIcon(0,0);    break;}
		case 10:{ sideIron = Blocks.iron_block.getIcon(0,0);    break;}
		}
		if(isPillar) {
			

			if(north) {

				tessellator.addVertexWithUV(x+0.27, y+0.95, z+0.0, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+0.27, y+0.95, z+0.25, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.95, z+0.25, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.95, z+0, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x+0.27, y+0.65, z+0.25, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+0.27, y+0.95, z+0.25, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.27, y+0.95, z+0, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.27, y+0.65, z+0, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x+0.73, y+0.65, z+0, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+0.73, y+0.95, z+0, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.95, z+0.25, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.65, z+0.25, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x+0.27, y+0.65, z+0, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+0.27, y+0.95, z+0, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.95, z+0, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.65, z+0, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x+0.27, y+0.65, z+0.25, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+0.27, y+0.65, z+0, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.65, z+0, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.65, z+0.25, sideIron.getMaxU(), sideIron.getMinV());
				
			}
			if(south) {

				tessellator.addVertexWithUV(x+0.27, y+0.95, z-0.25+1.0, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+0.27, y+0.95, z-0.25+1.25, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.95, z-0.25+1.25, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.95, z-0.25+1, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x+0.27, y+0.65, z-0.25+1.25, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+0.27, y+0.95, z-0.25+1.25, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.27, y+0.95, z-0.25+1, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.27, y+0.65, z-0.25+1, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x+0.73, y+0.65, z-0.25+1, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+0.73, y+0.95, z-0.25+1, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.95, z-0.25+1.25, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.65, z-0.25+1.25, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x+0.27, y+0.65, z-0.25+1.25, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+0.27, y+0.65, z-0.25+1, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.65, z-0.25+1, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.73, y+0.65, z-0.25+1.25, sideIron.getMaxU(), sideIron.getMinV());

				tessellator.addVertexWithUV(x+0.73, y+0.65, z+1, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+0.73, y+0.95, z+1, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.27, y+0.95, z+1, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.27, y+0.65, z+1, sideIron.getMaxU(), sideIron.getMinV());
				
			}
			if(east) {


				tessellator.addVertexWithUV(x+0.75, y+0.95, z+0.73, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+1, y+0.95,	z+0.73, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+1, y+0.95,	z+0.27, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.75, y+0.95, z+0.27, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x+0.75, y+0.65, z+0.27, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+1, y+0.65,	z+0.27, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+1, y+0.65,	z+0.73, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.75, y+0.65, z+0.73, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x+1, y+0.65, z+0.73, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+1, y+0.95,	z+0.73, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.75, y+0.95,	z+0.73, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+0.75, y+0.65, z+0.73, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x+0.75, y+0.65, z+0.27, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+0.75, y+0.95,	z+0.27, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+1, y+0.95,	z+0.27, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+1, y+0.65, z+0.27, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x+1, y+0.65, z+0.27, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x+1, y+0.95,	z+0.27, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+1, y+0.95,	z+0.73, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x+1, y+0.65, z+0.73, sideIron.getMaxU(), sideIron.getMinV());
				
			}
			if(west) {


				tessellator.addVertexWithUV(x-0.75+0.75, y+0.95, z+0.73, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x-0.75+1, y+0.95,	z+0.73, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x-0.75+1, y+0.95,	z+0.27, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x-0.75+0.75, y+0.95, z+0.27, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x-0.75+0.75, y+0.65, z+0.27, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x-0.75+1, y+0.65,	z+0.27, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x-0.75+1, y+0.65,	z+0.73, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x-0.75+0.75, y+0.65, z+0.73, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x-0.75+1, y+0.65, z+0.73, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x-0.75+1, y+0.95,	z+0.73, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x-0.75+0.75, y+0.95,	z+0.73, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x-0.75+0.75, y+0.65, z+0.73, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x-0.75+0.75, y+0.65, z+0.27, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x-0.75+0.75, y+0.95,	z+0.27, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x-0.75+1, y+0.95,	z+0.27, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x-0.75+1, y+0.65, z+0.27, sideIron.getMaxU(), sideIron.getMinV());
				
				tessellator.addVertexWithUV(x-0.75+0.75, y+0.65, z+0.73, sideIron.getMinU(), sideIron.getMinV());
				tessellator.addVertexWithUV(x-0.75+0.75, y+0.95,	z+0.73, sideIron.getMinU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x-0.75+0.75, y+0.95,	z+0.27, sideIron.getMaxU(), sideIron.getMaxV());
				tessellator.addVertexWithUV(x-0.75+0.75, y+0.65, z+0.27, sideIron.getMaxU(), sideIron.getMinV());
				
			}
			
			
			
			/**
			 * Render pillar
			 */
		tessellator.addVertexWithUV(x+0.25, y, z+0.25, sideIron.getMinU(), sideIron.getMinV());
		tessellator.addVertexWithUV(x+0.25, y+1, z+0.25, sideIron.getMinU(), sideIron.getMaxV());
		tessellator.addVertexWithUV(x+0.75, y+1, z+0.25, sideIron.getMaxU(), sideIron.getMaxV());
		tessellator.addVertexWithUV(x+0.75, y, z+0.25, sideIron.getMaxU(), sideIron.getMinV());
		
		tessellator.addVertexWithUV(x+0.25, y, z+0.75, sideIron.getMinU(), sideIron.getMinV());
		tessellator.addVertexWithUV(x+0.25, y+1, z+0.75, sideIron.getMinU(), sideIron.getMaxV());
		tessellator.addVertexWithUV(x+0.25, y+1, z+0.25, sideIron.getMaxU(), sideIron.getMaxV());
		tessellator.addVertexWithUV(x+0.25, y, z+0.25, sideIron.getMaxU(), sideIron.getMinV());

		tessellator.addVertexWithUV(x+0.75, y, z+0.75, sideIron.getMinU(), sideIron.getMinV());
		tessellator.addVertexWithUV(x+0.75, y+1, z+0.75, sideIron.getMinU(), sideIron.getMaxV());
		tessellator.addVertexWithUV(x+0.25, y+1, z+0.75, sideIron.getMaxU(), sideIron.getMaxV());
		tessellator.addVertexWithUV(x+0.25, y, z+0.75, sideIron.getMaxU(), sideIron.getMinV());
		
		tessellator.addVertexWithUV(x+0.75, y, z+0.25, sideIron.getMinU(), sideIron.getMinV());
		tessellator.addVertexWithUV(x+0.75, y+1, z+0.25, sideIron.getMinU(), sideIron.getMaxV());
		tessellator.addVertexWithUV(x+0.75, y+1, z+0.75, sideIron.getMaxU(), sideIron.getMaxV());
		tessellator.addVertexWithUV(x+0.75, y, z+0.75, sideIron.getMaxU(), sideIron.getMinV());
		
		tessellator.addVertexWithUV(x+0.25, y+1, z+0.25, sideIron.getMinU(), sideIron.getMinV());
		tessellator.addVertexWithUV(x+0.25, y+1, z+0.75, sideIron.getMinU(), sideIron.getMaxV());
		tessellator.addVertexWithUV(x+0.75, y+1, z+0.75, sideIron.getMaxU(), sideIron.getMaxV());
		tessellator.addVertexWithUV(x+0.75, y+1, z+0.25, sideIron.getMaxU(), sideIron.getMinV());
		
		tessellator.addVertexWithUV(x+0.25, y, z+0.75, sideIron.getMinU(), sideIron.getMinV());
		tessellator.addVertexWithUV(x+0.25, y, z+0.25, sideIron.getMinU(), sideIron.getMaxV());
		tessellator.addVertexWithUV(x+0.75, y, z+0.25, sideIron.getMaxU(), sideIron.getMaxV());
		tessellator.addVertexWithUV(x+0.75, y, z+0.75, sideIron.getMaxU(), sideIron.getMinV());
		}else {
			
		
		/**
		 * render Big connection
		 */
		
		if(north && south && !east && !west) {

			//top
			tessellator.addVertexWithUV(x+0.27, y+0.95, z, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.27, y+0.95, z-0.25+1.25, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.73, y+0.95, z-0.25+1.25, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.73, y+0.95, z, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+0.27, y+0.65, z-0.25+1.25, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.27, y+0.95, z-0.25+1.25, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.27, y+0.95, z ,sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.27, y+0.65, z ,sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+0.73, y+0.65, z, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.73, y+0.95, z, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.73, y+0.95, z-0.25+1.25, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.73, y+0.65, z-0.25+1.25, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+0.27, y+0.65, z-0.25+1.25, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.27, y+0.65, z ,sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.73, y+0.65, z, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.73, y+0.65, z-0.25+1.25, sideIron.getMaxU(), sideIron.getMinV());

			tessellator.addVertexWithUV(x+0.73, y+0.65, z+1, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.73, y+0.95, z+1, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.27, y+0.95, z+1, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.27, y+0.65, z+1, sideIron.getMaxU(), sideIron.getMinV());
		
			}
		if(!north && !south && east && west) {

			tessellator.addVertexWithUV(x, y+0.95, z+0.73, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+1, y+0.95,	z+0.73, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+1, y+0.95,	z+0.27, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x, y+0.95, z+0.27, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x, y+0.65, z+0.27, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+1, y+0.65,	z+0.27, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+1, y+0.65,	z+0.73, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x, y+0.65, z+0.73, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+1, y+0.65, z+0.73, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+1, y+0.95,	z+0.73, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x, y+0.95,	z+0.73, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x, y+0.65, z+0.73, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x, y+0.65, z+0.27, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x, y+0.95,	z+0.27, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+1, y+0.95,	z+0.27, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+1, y+0.65, z+0.27, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+1, y+0.65, z+0.27, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+1, y+0.95,	z+0.27, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+1, y+0.95,	z+0.73, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+1, y+0.65, z+0.73, sideIron.getMaxU(), sideIron.getMinV());

			}
		}
		if(south) {


			tessellator.addVertexWithUV(x+0.35, y, z+0.9, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.35, y+0.65, z+0.9, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.35, y+0.65, z+0.6, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.35, y, z+0.6, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+0.65, y, z+0.9, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.65, y+0.65, z+0.9, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.35, y+0.65, z+0.9, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.35, y, z+0.9, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+0.65, y, z+0.6, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.65, y+0.65, z+0.6, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.65, y+0.65, z+0.9, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.65, y, z+0.9, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+0.35, y, z+0.6, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.35, y+0.65, z+0.6, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.65, y+0.65, z+0.6, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.65, y, z+0.6, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+0.35, y, z+0.9, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.35, y, z+0.6, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.65, y, z+0.6, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.65, y, z+0.9, sideIron.getMaxU(), sideIron.getMinV());
		}
		if(north) {

			tessellator.addVertexWithUV(x+0.35, y, z+0.4, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.35, y+0.65, z+0.4, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.35, y+0.65, z+0.1, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.35, y, z+0.1, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+0.65, y, z+0.4, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.65, y+0.65, z+0.4, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.35, y+0.65, z+0.4, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.35, y, z+0.4, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+0.65, y, z+0.1, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.65, y+0.65, z+0.1, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.65, y+0.65, z+0.4, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.65, y, z+0.4, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+0.35, y, z+0.1, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.35, y+0.65, z+0.1, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.65, y+0.65, z+0.1, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.65, y, z+0.1, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+0.35, y, z+0.4, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+0.35, y, z+0.1, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.65, y, z+0.1, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+0.65, y, z+0.4, sideIron.getMaxU(), sideIron.getMinV());
			
		}
		if(east) {
			float f1 = 0.25F;
			float f2 = 0.25F;
			tessellator.addVertexWithUV(x+f1+0.35, y, z+f2+0.4, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+f1+0.35, y+0.65, z+f2+0.4, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.35, y+0.65, z+f2+0.1, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.35, y, z+f2+0.1, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+f1+0.65, y, z+f2+0.4, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+f1+0.65, y+0.65, z+f2+0.4, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.35, y+0.65, z+f2+0.4, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.35, y, z+f2+0.4, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+f1+0.65, y, z+f2+0.1, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+f1+0.65, y+0.65, z+f2+0.1, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.65, y+0.65, z+f2+0.4, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.65, y, z+f2+0.4, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+f1+0.35, y, z+f2+0.1, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+f1+0.35, y+0.65, z+f2+0.1, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.65, y+0.65, z+f2+0.1, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.65, y, z+f2+0.1, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+f1+0.35, y, z+f2+0.4, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+f1+0.35, y, z+f2+0.1, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.65, y, z+f2+0.1, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.65, y, z+f2+0.4, sideIron.getMaxU(), sideIron.getMinV());
			}
			if(west) {
				float f1 = -0.25F;
				float f2 = 0.25F;
			tessellator.addVertexWithUV(x+f1+0.35, y, z+f2+0.4, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+f1+0.35, y+0.65, z+f2+0.4, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.35, y+0.65, z+f2+0.1, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.35, y, z+f2+0.1, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+f1+0.65, y, z+f2+0.4, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+f1+0.65, y+0.65, z+f2+0.4, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.35, y+0.65, z+f2+0.4, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.35, y, z+f2+0.4, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+f1+0.65, y, z+f2+0.1, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+f1+0.65, y+0.65, z+f2+0.1, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.65, y+0.65, z+f2+0.4, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.65, y, z+f2+0.4, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+f1+0.35, y, z+f2+0.1, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+f1+0.35, y+0.65, z+f2+0.1, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.65, y+0.65, z+f2+0.1, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.65, y, z+f2+0.1, sideIron.getMaxU(), sideIron.getMinV());
			
			tessellator.addVertexWithUV(x+f1+0.35, y, z+f2+0.4, sideIron.getMinU(), sideIron.getMinV());
			tessellator.addVertexWithUV(x+f1+0.35, y, z+f2+0.1, sideIron.getMinU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.65, y, z+f2+0.1, sideIron.getMaxU(), sideIron.getMaxV());
			tessellator.addVertexWithUV(x+f1+0.65, y, z+f2+0.4, sideIron.getMaxU(), sideIron.getMinV());
			}
		return true;
	}

	private boolean check(Block block) {
		// TODO Auto-generated method stub
		return block == mainRegistry.blockWall || block.getMaterial() == Material.gourd;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return renderType;
	}
}