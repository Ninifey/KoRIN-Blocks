package conception.render.renderer;

import org.lwjgl.opengl.GL11;

import conception.mainRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderTest implements ISimpleBlockRenderingHandler {
	
	/** RenderID which is registered in the Proxy */
	int renderType = 0;
	double sinTheta= 0,theta = 0,cosTheta = 0;
	private RenderSide SideOne = RenderSide.Ypositiv;
	private RenderSide SideTwo = RenderSide.Ynegativ;
	private RenderSide SideThree = RenderSide.Xnegativ;
	private RenderSide SideFour = RenderSide.Xpositiv;
	private RenderSide SideFive = RenderSide.Zpositiv;
	private RenderSide SideSix = RenderSide.Znegativ;
	
	public RenderTest(int r) {
		renderType = r;
	}


	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glTranslatef(-0.2F, -0.5F, -0.2F);
		GL11.glScalef(1.3f, 1.3f, 1.3f);

		IIcon testIcon = Blocks.iron_block.getIcon(0,0);
		IIcon testIcon_add = Blocks.iron_block.getIcon(0,0);
		IIcon testIcon_upper = Blocks.iron_block.getIcon(0,0);

		int x = (int) (5f / 16f),y =  (int) (7f / 16f),z =  (int) (5f / 16f);
		IIcon icon = Blocks.stonebrick.getIcon(0,0);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setBrightness(15728720);	
		tessellator.setNormal(0, 1, 0); 
		setRotation(RenderSide.RotateY,90);
		renderPipeDirection(tessellator,RenderSide.RotateY, x,y,z,testIcon,testIcon_add,0);
		tessellator.draw();
		block.setBlockBoundsForItemRender();
		GL11.glScalef(1f / 1.5f, 1f / 1.5f, 1f / 1.5f);
		GL11.glTranslatef(0.5F, 1.2F, 0.5F);
	}
	
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		
		Tessellator tessellator = Tessellator.instance;
		int metaa = world.getBlockMetadata(x, y, z);
		tessellator.setColorOpaque_F(0.75F, 0.75F, 0.75F);
		

		IIcon testIcon = Blocks.iron_block.getIcon(0,0);
		IIcon testIcon_add =  Blocks.iron_block.getIcon(0,0);

		int type = 0;
		switch(metaa) {
		case 0:{ setRotation(RenderSide.RotateY,0); type = 0;break;}
		case 1:{ setRotation(RenderSide.RotateY,90); type = 0;break;}
		case 2:{ setRotation(RenderSide.RotateY,90); type = 2;break;}
		case 3:{ setRotation(RenderSide.RotateY,0); type = 1;break;}
		}	
			updateTesselatorToSide(tessellator,world,block,x, y, z,SideOne);
			if(type == 0) {
				renderPipeDirection(tessellator,RenderSide.RotateY, x,y,z,testIcon,testIcon_add,0);
			}
			if(type == 1) {
				boolean  Xminus = world.getBlock(x-1, y, z)==mainRegistry.blockConPipe;
				boolean  Xplus = world.getBlock(x+1, y, z)==mainRegistry.blockConPipe;
				boolean  Yminus = world.getBlock(x, y-1, z)==mainRegistry.blockConPipe;
				boolean  Yplus = world.getBlock(x, y+1, z)==mainRegistry.blockConPipe;
				boolean  Zminus = world.getBlock(x, y, z-1)==mainRegistry.blockConPipe;
				boolean  Zplus = world.getBlock(x, y, z+1)==mainRegistry.blockConPipe;
				renderPipeCorner(tessellator,RenderSide.RotateY, x,y,z,testIcon,testIcon_add,Xminus,Xplus,Yminus,Yplus,Zminus,Zplus);
			}
			if(type == 2) {
				renderPipeDirection(tessellator,RenderSide.RotateZ, x,y,z,testIcon,testIcon_add,0);
			}
			
			RenderSide testSide = RenderSide.RotateY;
			
			/*
			textVertex(tessellator,0.1,0.2001,0.1,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(8), testIcon.getInterpolatedV(8),"testIcon.getInterpolatedU(8)", "testIcon.getInterpolatedV(8)");
			textVertex(tessellator,0.1,0.2001,-0.1,x+0.5,y+0.5,z+0.5,testSide,	testIcon.getInterpolatedU(8), testIcon.getInterpolatedV(4),"testIcon.getInterpolatedU(8)", "testIcon.getInterpolatedV(4)");
			textVertex(tessellator,-0.1,0.2001,-0.1,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(4), testIcon.getInterpolatedV(4),"testIcon.getInterpolatedU(4)", "testIcon.getInterpolatedV(4)");
			textVertex(tessellator,-0.1,0.2001,0.1,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(4), testIcon.getInterpolatedV(8),"testIcon.getInterpolatedU(4)", "testIcon.getInterpolatedV(8)");
			
			textVertex(tessellator,-0.1,-0.2001,0.1,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(8), testIcon.getInterpolatedV(8),"testIcon.getInterpolatedU(8)", "testIcon.getInterpolatedV(8)");
			textVertex(tessellator,-0.1,-0.2001,-0.1,x+0.5,y+0.5,z+0.5,testSide,	testIcon.getInterpolatedU(8), testIcon.getInterpolatedV(4),"testIcon.getInterpolatedU(8)", "testIcon.getInterpolatedV(4)");
			textVertex(tessellator,0.1,-0.2001,-0.1,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(4), testIcon.getInterpolatedV(4),"testIcon.getInterpolatedU(4)", "testIcon.getInterpolatedV(4)");
			textVertex(tessellator,0.1,-0.2001,0.1,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(4), testIcon.getInterpolatedV(8),"testIcon.getInterpolatedU(4)", "testIcon.getInterpolatedV(8)");
			
			textVertex(tessellator,-0.2,0.1,-0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(9), testIcon.getInterpolatedV(7),"testIcon.getInterpolatedU(9)", "testIcon.getInterpolatedV(7)");
			textVertex(tessellator,-0.1,0.2001,-0.1,x+0.5,y+0.5,z+0.5,testSide,	testIcon.getInterpolatedU(9), testIcon.getInterpolatedV(3),"testIcon.getInterpolatedU(9)", "testIcon.getInterpolatedV(3)");
			textVertex(tessellator,0.0,0.2001,-0.1,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(3), testIcon.getInterpolatedV(3),"testIcon.getInterpolatedU(3)", "testIcon.getInterpolatedV(3)");
			textVertex(tessellator,0.0,0.1,-0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(3), testIcon.getInterpolatedV(7),"testIcon.getInterpolatedU(3)", "testIcon.getInterpolatedV(7)");
			
			textVertex(tessellator,-0.2,-0.1,-0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(9), testIcon.getInterpolatedV(7),"testIcon.getInterpolatedU(9)", "testIcon.getInterpolatedV(7)");
			textVertex(tessellator,-0.2,0.1,-0.2,x+0.5,y+0.5,z+0.5,testSide,	testIcon.getInterpolatedU(9), testIcon.getInterpolatedV(3),"testIcon.getInterpolatedU(9)", "testIcon.getInterpolatedV(3)");
			textVertex(tessellator,0.0,0.1,-0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(3), testIcon.getInterpolatedV(3),"testIcon.getInterpolatedU(3)", "testIcon.getInterpolatedV(3)");
			textVertex(tessellator,0.0,-0.1,-0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(3), testIcon.getInterpolatedV(7),"testIcon.getInterpolatedU(3)", "testIcon.getInterpolatedV(7)");
			
			textVertex(tessellator,-0.1,-0.2001,-0.1,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(9), testIcon.getInterpolatedV(7),"testIcon.getInterpolatedU(9)", "testIcon.getInterpolatedV(7)");
			textVertex(tessellator,-0.2,-0.1,-0.2,x+0.5,y+0.5,z+0.5,testSide,	testIcon.getInterpolatedU(9), testIcon.getInterpolatedV(3),"testIcon.getInterpolatedU(9)", "testIcon.getInterpolatedV(3)");
			textVertex(tessellator,0.0,-0.1,-0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(3), testIcon.getInterpolatedV(3),"testIcon.getInterpolatedU(3)", "testIcon.getInterpolatedV(3)");
			textVertex(tessellator,0.0,-0.2001,-0.1,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(3), testIcon.getInterpolatedV(7),"testIcon.getInterpolatedU(3)", "testIcon.getInterpolatedV(7)");
			
			textVertex(tessellator,-0.1,-0.2001,-0.0,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(9), testIcon.getInterpolatedV(7),"testIcon.getInterpolatedU(9)", "testIcon.getInterpolatedV(7)");
			textVertex(tessellator,-0.2,-0.1,-0.0,x+0.5,y+0.5,z+0.5,testSide,	testIcon.getInterpolatedU(9), testIcon.getInterpolatedV(3),"testIcon.getInterpolatedU(9)", "testIcon.getInterpolatedV(3)");
			textVertex(tessellator,-0.2,-0.1,-0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(3), testIcon.getInterpolatedV(3),"testIcon.getInterpolatedU(3)", "testIcon.getInterpolatedV(3)");
			textVertex(tessellator,-0.1,-0.2001,-0.1,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(3), testIcon.getInterpolatedV(7),"testIcon.getInterpolatedU(3)", "testIcon.getInterpolatedV(7)");
			
			textVertex(tessellator,-0.2,-0.1,-0.0,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(9), testIcon.getInterpolatedV(7),"testIcon.getInterpolatedU(9)", "testIcon.getInterpolatedV(7)");
			textVertex(tessellator,-0.2,0.1,-0.0,x+0.5,y+0.5,z+0.5,testSide,	testIcon.getInterpolatedU(9), testIcon.getInterpolatedV(3),"testIcon.getInterpolatedU(9)", "testIcon.getInterpolatedV(3)");
			textVertex(tessellator,-0.2,0.1,-0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(3), testIcon.getInterpolatedV(3),"testIcon.getInterpolatedU(3)", "testIcon.getInterpolatedV(3)");
			textVertex(tessellator,-0.2,-0.1,-0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(3), testIcon.getInterpolatedV(7),"testIcon.getInterpolatedU(3)", "testIcon.getInterpolatedV(7)");
			
			textVertex(tessellator,-0.2,0.1,-0.0,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(9), testIcon.getInterpolatedV(7),"testIcon.getInterpolatedU(9)", "testIcon.getInterpolatedV(7)");
			textVertex(tessellator,-0.1,0.2001,-0.0,x+0.5,y+0.5,z+0.5,testSide,	testIcon.getInterpolatedU(9), testIcon.getInterpolatedV(3),"testIcon.getInterpolatedU(9)", "testIcon.getInterpolatedV(3)");
			textVertex(tessellator,-0.1,0.2001,-0.1,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(3), testIcon.getInterpolatedV(3),"testIcon.getInterpolatedU(3)", "testIcon.getInterpolatedV(3)");
			textVertex(tessellator,-0.2,0.1,-0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getInterpolatedU(3), testIcon.getInterpolatedV(7),"testIcon.getInterpolatedU(3)", "testIcon.getInterpolatedV(7)");
			
					
			textVertex(tessellator,-0.5,-0.22,0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getMaxU(), testIcon.getMaxV(),"testIcon.getMaxU()", "testIcon.getMaxV()");
			textVertex(tessellator,-0.5,0.22,0.2,x+0.5,y+0.5,z+0.5,testSide,testIcon.getMaxU(), testIcon.getMinV(),"testIcon.getMaxU()", "testIcon.getMinV()");
			textVertex(tessellator,-0.5,0.22,-0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getMinU(), testIcon.getMinV(),"testIcon.getMinU()", "testIcon.getMinV()");
			textVertex(tessellator,-0.5,-0.22,-0.2,x+0.5,y+0.5,z+0.5,testSide, testIcon.getMinU(), testIcon.getMaxV(),"testIcon.getMinU()", "testIcon.getMaxV()");*/
		return true;
}

	
	private void renderPipeCornerAdd(Tessellator tessellator, RenderSide rotatez, int x, int y, int z, IIcon testIcon,
			IIcon testIcon_add, boolean  Xminus, boolean  Xplus, boolean  Yminus, boolean  Yplus, boolean  Zminus, boolean  Zplus) {
			if(Zplus&&Yplus) {
			addVertex(tessellator,-0.2,0.1,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.2,0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.2,-0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.2,-0.1,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.2,-0.1,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.2,-0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.2,0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.2,0.1,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.1,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,-0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			}
			if(Zplus&&Yminus) {
			addVertex(tessellator,0.2,-0.1,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.2,-0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.2,0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.2,0.1,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.2,0.1,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.2,0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.2,-0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.2,-0.1,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.1,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.1,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			}
			if(Xplus&&Yplus) {

			addVertex(tessellator,0.1,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.1,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.1,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.1,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.1,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.1,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.1,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.1,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.2,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.0,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.0,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.0,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));

			}
			if(Xplus&&Yminus) {

			addVertex(tessellator,0.1,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.1,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.1,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.1,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.1,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.1,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.1,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.1,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.2,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.0,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.0,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.0,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			}
			if(Xplus&&Zminus) {

			addVertex(tessellator,0.1,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.1,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.1,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.1,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.1,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.1,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.1,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.1,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.2,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));

			}
			if(Xminus&&Zminus) {
			addVertex(tessellator,-0.1,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.1,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.1,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.1,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.1,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.1,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.1,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.1,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.2,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			}
			if(Xminus&&Zplus) {
			addVertex(tessellator,-0.1,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.1,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.1,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.1,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,-0.1,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.1,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(8),testIcon.getInterpolatedV(4));
			addVertex(tessellator,0.1,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(4));
			addVertex(tessellator,-0.1,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(4),testIcon.getInterpolatedV(8));
			addVertex(tessellator,0.2,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(9),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getInterpolatedU(3),testIcon.getInterpolatedV(7));

			}
			if(Xplus&&Zplus) {

			addVertex(tessellator,0.1,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,0.1,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.1,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.1,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.2,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.1,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.2,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.2,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.1,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.2,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(2));
			addVertex(tessellator,-0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(2));
			}
		
	}


	private void renderPipeCorner(Tessellator tessellator, RenderSide rotatez, int x, int y, int z, IIcon testIcon,
			IIcon testIcon_add, boolean  Xminus, boolean  Xplus, boolean  Yminus, boolean  Yplus, boolean  Zminus, boolean  Zplus) {
			renderPipeCornerAdd(tessellator,RenderSide.RotateY, x,y,z,testIcon,testIcon_add,Xminus,Xplus,Yminus,Yplus,Zminus,Zplus);
			if(Xplus) {
			addVertex(tessellator,0.0,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.5,-0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.4,-0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.5,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.5,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.4,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.5,0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.4,-0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.5,-0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.5,0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.4,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.5,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.5,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.4,-0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,0.4,0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,0.4,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,0.4,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMaxV());
			addVertex(tessellator,0.5,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,0.5,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,0.5,0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,0.5,-0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMaxV());
			}
			if(Xminus) {
			addVertex(tessellator,0.0,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.0,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.1,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.1,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.0,-0.2,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.5,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.4,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,-0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.5,-0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.5,0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.4,0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.5,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.4,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.5,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.5,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.4,0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.5,0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.5,-0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,-0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.4,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,-0.4,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,-0.4,0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,-0.4,-0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMaxV());
			addVertex(tessellator,-0.5,-0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,-0.5,0.2,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,-0.5,0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,-0.5,-0.2,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMaxV());
			}
			if(Zminus) {
			addVertex(tessellator,-0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.1,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.1,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.1,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.1,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.1,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.1,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,-0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,-0.1,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,-0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.1,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.2,-0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,-0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.2,-0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,0.2,-0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.2,-0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,-0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,-0.2,-0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.2,-0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,0.2,-0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.2,-0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,-0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,0.2,0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,-0.2,0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,-0.2,-0.2,-0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMaxV());
			addVertex(tessellator,-0.2,-0.2,-0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,-0.2,0.2,-0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,0.2,0.2,-0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,0.2,-0.2,-0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMaxV());
			}
			if(Zplus) {
			addVertex(tessellator,0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.1,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.1,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.1,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.1,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,-0.1,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.1,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,-0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.1,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,-0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,-0.1,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.1,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.2,0.0,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,-0.2,0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,-0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.2,0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,0.2,0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.2,0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,-0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,-0.2,0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.2,0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,0.2,0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.2,0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,-0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,-0.2,0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,0.2,0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,0.2,-0.2,0.4,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMaxV());
			addVertex(tessellator,0.2,-0.2,0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,0.2,0.2,0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,-0.2,0.2,0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,-0.2,-0.2,0.5,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMaxV());
			}
			if(Yplus) {
			addVertex(tessellator,0.2,0.0,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.4,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.4,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.0,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.0,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.4,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.4,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.0,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.0,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.4,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.4,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.0,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.4,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.0,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.0,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.4,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.5,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.5,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,0.5,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.5,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,0.5,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.5,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,0.5,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.5,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,0.2,0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,0.2,0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,-0.2,0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMaxV());
			addVertex(tessellator,-0.2,0.5,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,0.2,0.5,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,0.2,0.5,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,-0.2,0.5,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMaxV());
			}
			if(Yminus) {
			addVertex(tessellator,-0.2,0.0,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.4,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.4,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.0,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.0,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.4,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.4,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,0.0,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,0.0,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,-0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,-0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.1,-0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.4,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.4,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.0,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.4,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.0,0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.0,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.4,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.1,-0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.1,0.0,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,0.0,-0.1,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.2,-0.5,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,-0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.5,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,-0.5,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,-0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.5,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,-0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,-0.5,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.5,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.2,-0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,-0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.2,-0.5,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.5,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.2,-0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.2,-0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,-0.2,-0.4,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,-0.2,-0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,0.2,-0.4,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMaxV());
			addVertex(tessellator,0.2,-0.5,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,-0.2,-0.5,0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,-0.2,-0.5,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,0.2,-0.5,-0.2,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(),testIcon.getMaxV());
			}
	}


	private void renderPipeDirection(Tessellator tessellator,RenderSide f, int x, int y, int z, IIcon testIcon, IIcon testIcon_add,
			double d0) {

			addVertex(tessellator,0.4,0.2+d0,0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,0.2+d0,-0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.2+d0,-0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.2+d0,0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,0.1+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,0.2+d0,0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.2+d0,0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.1+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,0.2+d0,-0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,0.1+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.1+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.2+d0,-0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,-0.1+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.4,0.1+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.1+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,-0.1+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.1+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,0.1+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,0.1+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,-0.1+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.2+d0,0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.2+d0,-0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,-0.2+d0,-0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,-0.2+d0,0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.1+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.2+d0,0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,-0.2+d0,0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,-0.1+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.2+d0,-0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,-0.4,-0.1+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,-0.1+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,-0.2+d0,-0.1,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(7));
			addVertex(tessellator,0.5,0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.4,0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.5,0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.5,-0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.4,-0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,-0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.5,-0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.4,-0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.5,-0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.5,0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.4,0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.5,0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.5,-0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,0.4,-0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,0.5,-0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,0.5,0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,0.5,0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,0.5,-0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getMaxV());
			addVertex(tessellator,0.4,-0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,0.4,0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,0.4,0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,0.4,-0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getMaxV());
			addVertex(tessellator,-0.5,-0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.4,-0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,-0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.5,-0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.5,0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.4,0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.5,0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.4,-0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.5,-0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.5,0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.4,0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.5,0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.5,-0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(3));
			addVertex(tessellator,-0.4,-0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getInterpolatedV(5));
			addVertex(tessellator,-0.4,-0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,-0.4,0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,-0.4,0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,-0.4,-0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getMaxV());
			addVertex(tessellator,-0.5,-0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getMaxV());
			addVertex(tessellator,-0.5,0.2+d0,0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMaxU(),testIcon.getMinV());
			addVertex(tessellator,-0.5,0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getMinV());
			addVertex(tessellator,-0.5,-0.2+d0,-0.2,x+0.5,y+0.5,z+0.5,f, testIcon.getMinU(),testIcon.getMaxV());

	}


	private void setRotation(RenderSide RotateY, double angle) {

		if(RotateY == RenderSide.RotateY) {
			if(angle == 0) {
				SideOne = RenderSide.Ypositiv;
				SideTwo = RenderSide.Ynegativ;
				SideThree = RenderSide.Xnegativ;
				SideFour = RenderSide.Xpositiv;
				SideFive = RenderSide.Zpositiv;
				SideSix = RenderSide.Znegativ;
			}
			if(angle == 90) {
				SideOne = RenderSide.Ypositiv;
				SideTwo = RenderSide.Ynegativ;
				SideThree = RenderSide.Znegativ;
				SideFour = RenderSide.Zpositiv;
				SideFive = RenderSide.Xnegativ;
				SideSix = RenderSide.Xpositiv;
			}
			if(angle == 180) {
				SideOne = RenderSide.Ypositiv;
				SideTwo = RenderSide.Ynegativ;
				SideThree = RenderSide.Xpositiv;
				SideFour = RenderSide.Xnegativ;
				SideFive = RenderSide.Znegativ;
				SideSix = RenderSide.Zpositiv;
				}
			if(angle == 270) {
				SideOne = RenderSide.Ypositiv;
				SideTwo = RenderSide.Ynegativ;
				SideThree = RenderSide.Zpositiv;
				SideFour = RenderSide.Znegativ;
				SideFive = RenderSide.Xpositiv;
				SideSix = RenderSide.Xnegativ;
				}
			}
			if(angle == 0) {
				theta = 0;
			}
			if(angle == 90) {
				theta = 84.822/2;
			}
			if(angle == 180) {
				theta = 84.822;
			}
			if(angle == 270) {
				theta = 84.822+84.822/2;
			}
		sinTheta = Math.sin(theta);
		cosTheta = Math.cos(theta);
	}

	/**
	 * Updates the Tesselator with brightness for the side 
	 */
	private void updateTesselatorToSide(Tessellator tessellator,IBlockAccess world, Block block, int x, int y, int z, RenderSide sideOne) {

		if(sideOne == RenderSide.Xpositiv) {
		tessellator.setNormal(1.0F,0.0F, 0.0F);
		int lightvar =block.getMixedBrightnessForBlock(world, x+1, y, z);
		tessellator.setBrightness(lightvar);	
		}
		
		if(sideOne == RenderSide.Xnegativ) {
		tessellator.setNormal(-1.0F,0.0F, 0.0F);
		int lightvar =block.getMixedBrightnessForBlock(world, x-1, y, z);
		tessellator.setBrightness(lightvar);	
		}
		
		if(sideOne == RenderSide.Ypositiv) {
		tessellator.setNormal(0.0F,1.0F, 0.0F);
		int lightvar =block.getMixedBrightnessForBlock(world, x, y+1, z);
		tessellator.setBrightness(lightvar);	
		}
		
		if(sideOne == RenderSide.Ynegativ) {
		tessellator.setNormal(0.0F,-1.0F, 0.0F);
		int lightvar =block.getMixedBrightnessForBlock(world, x, y-1, z);
		tessellator.setBrightness(lightvar);	
		}
		
		if(sideOne == RenderSide.Zpositiv) {
		tessellator.setNormal(0.0F,0.0F, 1.0F);
		int lightvar =block.getMixedBrightnessForBlock(world, x, y, z+1);
		tessellator.setBrightness(lightvar);	
		}
		
		if(sideOne == RenderSide.Znegativ) {
		tessellator.setNormal(0.0F,0.0F, -1.0F);
		int lightvar =block.getMixedBrightnessForBlock(world, x, y, z-1);
		tessellator.setBrightness(lightvar);	
		}
	}

	/**
	 * 
	 * 
			textVertex(tessellator,0.3,-0.5,0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(), testIcon.getMaxV(),"testIcon.getMaxU()", "testIcon.getMaxV()");
			textVertex(tessellator,0.3,0.3,0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,	testIcon.getMaxU(), testIcon.getMinV(),"testIcon.getMaxU()", "testIcon.getMinV()");
			textVertex(tessellator,-0.3,0.3,-0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(), testIcon.getMinV(),"testIcon.getMinU()", "testIcon.getMinV()");
			textVertex(tessellator,-0.3,-0.5,-0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(), testIcon.getMaxV(),"testIcon.getMinU()", "testIcon.getMaxV()");
			
			textVertex(tessellator,-0.3,-0.5,-0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(), testIcon.getMaxV(),"testIcon.getMaxU()", "testIcon.getMaxV()");
			textVertex(tessellator,-0.3,0.3,-0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,	testIcon.getMaxU(), testIcon.getMinV(),"testIcon.getMaxU()", "testIcon.getMinV()");
			textVertex(tessellator,0.3,0.3,0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(), testIcon.getMinV(),"testIcon.getMinU()", "testIcon.getMinV()");
			textVertex(tessellator,0.3,-0.5,0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(), testIcon.getMaxV(),"testIcon.getMinU()", "testIcon.getMaxV()");
		
			textVertex(tessellator,-0.3,-0.5,0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(), testIcon.getMaxV(),"testIcon.getMaxU()", "testIcon.getMaxV()");
			textVertex(tessellator,-0.3,0.3,0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,	testIcon.getMaxU(), testIcon.getMinV(),"testIcon.getMaxU()", "testIcon.getMinV()");
			textVertex(tessellator,0.3,0.3,-0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(), testIcon.getMinV(),"testIcon.getMinU()", "testIcon.getMinV()");
			textVertex(tessellator,0.3,-0.5,-0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(), testIcon.getMaxV(),"testIcon.getMinU()", "testIcon.getMaxV()");
			
			textVertex(tessellator,0.3,-0.5,-0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMaxU(), testIcon.getMaxV(),"testIcon.getMaxU()", "testIcon.getMaxV()");
			textVertex(tessellator,0.3,0.3,-0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,	testIcon.getMaxU(), testIcon.getMinV(),"testIcon.getMaxU()", "testIcon.getMinV()");
			textVertex(tessellator,-0.3,0.3,0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(), testIcon.getMinV(),"testIcon.getMinU()", "testIcon.getMinV()");
			textVertex(tessellator,-0.3,-0.5,0.3,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, testIcon.getMinU(), testIcon.getMaxV(),"testIcon.getMinU()", "testIcon.getMaxV()");
			
			
	 * @param tessellator
	 * @param x
	 * @param y
	 * @param z
	 * @param positionx
	 * @param positiony
	 * @param positionz
	 * @param rotatey
	 * @param d
	 * @param e
	 * @param icons
	 * @param icon
	 */
	private void textVertex(Tessellator tessellator,double x ,double y,double z,double positionx,double positiony,double positionz, RenderSide rotatey, double d, double e,String icons,String icon) {

				double posx = x;
				double posy = y;
				double posz = z;
				if(rotatey == RenderSide.RotateZ) {
					posx = x * cosTheta - y * sinTheta;
					posy = y * cosTheta + x * sinTheta;}
				if(rotatey == RenderSide.RotateX) {
					posy = y * cosTheta - z * sinTheta;
					posz = z * cosTheta + y * sinTheta;}
				if(rotatey == RenderSide.RotateY) {
					posx = x * cosTheta + z * sinTheta;
					posz = z * cosTheta - x * sinTheta;	}	
			
					tessellator.addVertexWithUV(positionx+posx, positiony+posy, positionz+posz, d,e);  
					//System.out.println("addVertex(tessellator,"+(Math.round(posx * 10.0) / 10.0)+","+(Math.round(posy * 10.0) / 10.0)+","+(Math.round(posz * 10.0) / 10.0)+",x+0.5,y+0.5,z+0.5,RenderSide.RotateY, "+icons+","+icon+");");
					System.out.println("textVertex(tessellator,"+(Math.round(posx * 10.0) / 10.0)+","+(Math.round(posy * 10.0) / 10.0)+","+(Math.round(posz * 10.0) / 10.0)+",x+0.5,y+0.5,z+0.5,RenderSide.RotateY, "+icons+","+icon+", "+icons+","+icon+");");
					}


	private void addVertex(Tessellator tessellator,double x ,double y,double z,double positionx,double positiony,double positionz, RenderSide rotatey, double d, double e) {

				double posx = x;
				double posy = y;
				double posz = z;
				if(rotatey == RenderSide.RotateZ) {
					posx = x * cosTheta - y * sinTheta;
					posy = y * cosTheta + x * sinTheta;}
				if(rotatey == RenderSide.RotateX) {
					posy = y * cosTheta - z * sinTheta;
					posz = z * cosTheta + y * sinTheta;}
				if(rotatey == RenderSide.RotateY) {
					posx = x * cosTheta + z * sinTheta;
					posz = z * cosTheta - x * sinTheta;	}	
			
					tessellator.addVertexWithUV(positionx+posx, positiony+posy, positionz+posz, d,e);  
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