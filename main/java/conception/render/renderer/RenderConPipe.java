package conception.render.renderer;

import org.lwjgl.opengl.GL11;

import conception.mainRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderConPipe implements ISimpleBlockRenderingHandler {
	int renderType = 0;
	double sinTheta = 0;
	double cosTheta = 0;
	
	public RenderConPipe(int r) {
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
		IIcon sideIron = mainRegistry.blockThatchRoof.getIcon(0,part);
		tessellator.setBrightness(lightValue-3);
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		
    	boolean north = check(world.getBlock(x, y, z-1));
    	boolean south = check(world.getBlock(x, y, z+1));
    	boolean east = check(world.getBlock(x+1, y, z));
    	boolean west = check(world.getBlock(x-1, y, z));
    	
    	boolean upper = check(world.getBlock(x+1, y+1, z));
    	
		float scale = 2*part;
		
		/**
		 *  north 0
		 *  south  84.822
		 *  east 84.822/2
		 *  west 84.822+84.822/2
		 **/
		double theta =0;

		sinTheta = Math.sin(theta);
		cosTheta = Math.cos(theta);
			//------------------next cachel
		addVertex(tessellator,0-(1*scale), 0, 0.5,x,y,z,2, sideIron.getMaxU(), sideIron.getMaxV());
		addVertex(tessellator,0-(1*scale), 3*scale, 0.5,x,y,z,2, sideIron.getMaxU(), sideIron.getMinV());
		addVertex(tessellator,1+(1*scale),3*scale, 0.5,x,y,z,2, sideIron.getMinU(), sideIron.getMinV());
		addVertex(tessellator,1+(1*scale), 0, 0.5,x,y,z,2, sideIron.getMinU(), sideIron.getMaxV());
		theta = 84.822;
		sinTheta = Math.sin(theta);
		cosTheta = Math.cos(theta);
		addVertex(tessellator,0-(1*scale), 0, 0.5,x+1,y,z+1,2, sideIron.getMaxU(), sideIron.getMaxV());
		addVertex(tessellator,0-(1*scale), 3*scale, 0.5,x+1,y,z+1,2, sideIron.getMaxU(), sideIron.getMinV());
		addVertex(tessellator,1+(1*scale),3*scale, 0.5,x+1,y,z+1,2, sideIron.getMinU(), sideIron.getMinV());
		addVertex(tessellator,1+(1*scale), 0, 0.5,x+1,y,z+1,2, sideIron.getMinU(), sideIron.getMaxV());
		theta = 84.822/2;
		sinTheta = Math.sin(theta);
		cosTheta = Math.cos(theta);
		addVertex(tessellator,0-(1*scale), 0, 0.5,x+1,y,z,2, sideIron.getMaxU(), sideIron.getMaxV());
		addVertex(tessellator,0-(1*scale), 3*scale, 0.5,x+1,y,z,2, sideIron.getMaxU(), sideIron.getMinV());
		addVertex(tessellator,1+(1*scale),3*scale, 0.5,x+1,y,z,2, sideIron.getMinU(), sideIron.getMinV());
		addVertex(tessellator,1+(1*scale), 0, 0.5,x+1,y,z,2, sideIron.getMinU(), sideIron.getMaxV());
		theta = 84.822+84.822/2;
		sinTheta = Math.sin(theta);
		cosTheta = Math.cos(theta);
		addVertex(tessellator,0-(1*scale), 0, 0.5,x,y,z+1,2, sideIron.getMaxU(), sideIron.getMaxV());
		addVertex(tessellator,0-(1*scale), 3*scale, 0.5,x,y,z+1,2, sideIron.getMaxU(), sideIron.getMinV());
		addVertex(tessellator,1+(1*scale),3*scale, 0.5,x,y,z+1,2, sideIron.getMinU(), sideIron.getMinV());
		addVertex(tessellator,1+(1*scale), 0, 0.5,x,y,z+1,2, sideIron.getMinU(), sideIron.getMaxV());


		return true;
	}

	private boolean check(Block block) {
		// TODO Auto-generated method stub
		return block == mainRegistry.blockThatchRoof || block.getMaterial() == Material.gourd;
	}
	
	/**
	 * adds a vertex for the rotation ax Z = 0 , X = 1, Y= 2
	 * @param tessellator
	 * @param x
	 * @param y
	 * @param z
	 * @param theta
	 * @param ax
	 * @param ax 
	 * @param iconX 
	 * @param iconY 
	 * @param icon
	 */
	private void addVertex(Tessellator tessellator,double x ,double y,double z,double positionx,double positiony,double positionz, int ax, float iconX, float iconY) {

		double posx = x;
		double posy = y;
		double posz = z;
			if(ax == 0) {
				 posx = x * cosTheta - y * sinTheta;
				 posy = y * cosTheta + x * sinTheta;
			}else if(ax == 1) {
				 posy = y * cosTheta - z * sinTheta;
				 posz = z * cosTheta + y * sinTheta;
			}else if(ax == 2) {
				 posx = x * cosTheta + z * sinTheta;
				 posz = z * cosTheta - x * sinTheta;		
			}
			System.out.println("x: "+x+"  y: "+y+"  z: "+z);
			tessellator.addVertexWithUV(positionx+posx, positiony+posy, positionz+posz, iconX,iconY);   
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