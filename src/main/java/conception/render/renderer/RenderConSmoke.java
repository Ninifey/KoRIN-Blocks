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

public class RenderConSmoke implements ISimpleBlockRenderingHandler {
	int renderType = 0;
	double sinTheta = 0;
	double cosTheta = 0;
	
	public RenderConSmoke(int r) {
		renderType = r;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glTranslatef(-0.2F, -0.9F, -0.2F);
		GL11.glScalef(0.031f, 0.031f, 0.031f);
		int x = (int) (5f / 16f),y =  (int) (7f / 16f),z =  (int) (5f / 16f);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setBrightness(4535345);	
		tessellator.setNormal(0, 1, 0); 
		double theta =0;
		IIcon sideIron = mainRegistry.blockConSmoke.getIcon(0,metadata);
		float scale = 2*metadata;
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
		tessellator.draw();
		block.setBlockBoundsForItemRender();
		GL11.glScalef(1f / 1.5f, 1f / 1.5f, 1f / 1.5f);
		GL11.glTranslatef(0.5F, 1.2F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {


		
		Tessellator tessellator = Tessellator.instance;
		int lightValue = block.getMixedBrightnessForBlock(world, x, y, z);
		int part = world.getBlockMetadata(x, y, z);
		IIcon sideIron = mainRegistry.blockConSmoke.getIcon(0,part);
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
		return block == mainRegistry.blockConSmoke || block.getMaterial() == Material.gourd;
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