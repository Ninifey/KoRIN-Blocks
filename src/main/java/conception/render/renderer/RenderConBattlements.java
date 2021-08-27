package conception.render.renderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderConBattlements implements ISimpleBlockRenderingHandler {
	int renderType = 0;
	double sinTheta= 0,theta = 0,cosTheta = 0;
	private RenderSide SideOne = RenderSide.Ypositiv;
	private RenderSide SideTwo = RenderSide.Ynegativ;
	private RenderSide SideThree = RenderSide.Xnegativ;
	private RenderSide SideFour = RenderSide.Xpositiv;
	private RenderSide SideFive = RenderSide.Zpositiv;
	private RenderSide SideSix = RenderSide.Znegativ;
	
	public RenderConBattlements(int r) {
		renderType = r;
	}

	
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	  //  renderer.renderBlockAsItem(mainRegistry.blockConBattlements, 1, 1.0F);

		GL11.glTranslatef(-0.5F, -0.8F, -0.5F);
		GL11.glScalef(0.7f, 0.7f, 0.7f);

		IIcon icon = Blocks.stonebrick.getIcon(0,0);

		renderBlockForInventory(renderer, block, 5f / 16f, 7f / 16f, 5f / 16f, icon);
		block.setBlockBoundsForItemRender();

		GL11.glScalef(1f / 1.5f, 1f / 1.5f, 1f / 1.5f);
		GL11.glTranslatef(0.5F, 1.5F, 0.5F);
	}

	private void renderBlockForInventory(RenderBlocks renderer, Block block, double x, double y, double z,
			IIcon icon) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.setColorOpaque_F(0.7F, 0.7F, 0.7F);
		tessellator.setBrightness(4535345);	
		setRotation(RenderSide.RotateY,270);
		IIcon topIcon = icon;
		IIcon sideIron = icon;
		/**
		 *  Render +y
		 */

		//------------------top

		tessellator.startDrawingQuads();
		addVertex(tessellator,0.5,0.9009998403212426,0.4981960334457663,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
		addVertex(tessellator,0.5,0.898996547812345,-0.5018019599617825,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
		addVertex(tessellator,-0.6,0.23899787216336277,-0.5004797869059099,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
		addVertex(tessellator,-0.6,0.24100116467226057,0.49951820650163886,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
	
		/**
		 *  Render -y
		 */

		addVertex(tessellator,0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
		addVertex(tessellator,0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(), sideIron.getMinV());
		addVertex(tessellator,-0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),sideIron.getMinV());
		addVertex(tessellator,-0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());

		//------------------top


		/**
		 *  Render -x
		 */

		addVertex(tessellator,-0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
		addVertex(tessellator,-0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
		addVertex(tessellator,-0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
		addVertex(tessellator,-0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
		//------------------top

		addVertex(tessellator,-0.6,0.04100156599075085,0.4999188650034184,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,  topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
		addVertex(tessellator,-0.6,0.24100116467226057,0.49951820650163886,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,  topIcon.getMaxU(), topIcon.getMinV());
		addVertex(tessellator,-0.6,0.23899787216336277,-0.5004797869059099,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
		addVertex(tessellator,-0.6,0.038998273481853044,-0.5000791284041302,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
		addVertex(tessellator,-0.6,0.038998273481853044,-0.5000791284041302,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
		addVertex(tessellator,-0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
		addVertex(tessellator,-0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
		addVertex(tessellator,-0.6,0.04100156599075085,0.4999188650034184,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,  topIcon.getMinU(), (float) (topIcon.getMaxV()-0));


		/**
		 *  Render +x
		 */

		addVertex(tessellator,0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,  sideIron.getMaxU(),sideIron.getMaxV());
		addVertex(tessellator,0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
		addVertex(tessellator,0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
		addVertex(tessellator,0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
		addVertex(tessellator,0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(), (float) (sideIron.getMaxV()-0.041));
		addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
		addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.00));
		addVertex(tessellator,0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(), (float) (sideIron.getMaxV()-0.041));

		//------------------top

		addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
		addVertex(tessellator,0.5,0.898996547812345,-0.5018019599617825,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
		addVertex(tessellator,0.5,0.9009998403212426,0.4981960334457663,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
		addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,  topIcon.getMinU(), (float) (topIcon.getMaxV()-0));


		/**
		 *  Render +z
		 */

		addVertex(tessellator,0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
		addVertex(tessellator,0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
		addVertex(tessellator,-0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
		addVertex(tessellator,-0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
		addVertex(tessellator,0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(), (float) (sideIron.getMaxV()-0.041));
		addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
		addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
		addVertex(tessellator,-0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(), (float) (sideIron.getMaxV()-0.041));
		//------------------top

		addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
		addVertex(tessellator,0.5,0.9009998403212426,0.4981960334457663,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
		addVertex(tessellator,-0.6,0.24100116467226057,0.49951820650163886,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
		addVertex(tessellator,-0.6,0.04100156599075085,0.4999188650034184,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));

	

		/**
		 *  Render -z
		 */

		addVertex(tessellator,-0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(), (float) (sideIron.getMaxV()-0.041));
		addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
		addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.00));
		addVertex(tessellator,0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(), (float) (sideIron.getMaxV()-0.041));
		addVertex(tessellator,-0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
		addVertex(tessellator,-0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
		addVertex(tessellator,0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
		addVertex(tessellator,0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
		//------------------top

		addVertex(tessellator,-0.6,0.038998273481853044,-0.5000791284041302,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,  topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
		addVertex(tessellator,-0.6,0.23899787216336277,-0.5004797869059099,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,topIcon.getMaxU(), topIcon.getMinV());
		addVertex(tessellator,0.5,0.898996547812345,-0.5018019599617825,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
		addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,topIcon.getMinU(), (float) (topIcon.getMaxV()-0));

		tessellator.draw();
	}



	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {


		
		Tessellator tessellator = Tessellator.instance;
		int part = world.getBlockMetadata(x, y, z);
		tessellator.setColorOpaque_F(0.7F, 0.7F, 0.7F);
		IIcon sideIron = Blocks.stonebrick.getIcon(0,0);
		IIcon topIcon = Blocks.stonebrick.getIcon(0,1);
		int type = 0;
		switch(part) {
		case 0:{ setRotation(RenderSide.RotateY,0); type = 0;break;}
		case 1:{ setRotation(RenderSide.RotateY,90); type = 0;break;}
		case 2:{ setRotation(RenderSide.RotateY,180); type = 0;break;}
		case 3:{ setRotation(RenderSide.RotateY,270); type = 0;break;}
		case 4:{ setRotation(RenderSide.RotateY,0); type = 1;break;}
		case 5:{ setRotation(RenderSide.RotateY,90); type = 1;break;}
		case 6:{ setRotation(RenderSide.RotateY,180); type = 1;break;}
		case 7:{ setRotation(RenderSide.RotateY,270); type = 1;break;}
		/*
		case 8:{ setRotation(RenderSide.RotateY,0); type = 2;break;}
		case 9:{ setRotation(RenderSide.RotateY,90); type = 2;break;}
		case 10:{ setRotation(RenderSide.RotateY,180); type = 2;break;}
		case 11:{ setRotation(RenderSide.RotateY,270); type = 2;break;}
		*/
		}
		
if(type == 2348234) {

}

if(type == 0) {
/**
 *  Render +y
 */
updateTesselatorToSide(tessellator,world,block,x, y, z,SideOne);

//------------------top

addVertex(tessellator,0.5,0.9009998403212426,0.4981960334457663,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,0.5,0.898996547812345,-0.5018019599617825,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
addVertex(tessellator,-0.6,0.23899787216336277,-0.5004797869059099,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
addVertex(tessellator,-0.6,0.24100116467226057,0.49951820650163886,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
	
tessellator.draw();
tessellator.startDrawingQuads();

/**
 *  Render -y
 */
updateTesselatorToSide(tessellator,world,block,x, y, z,SideTwo);

addVertex(tessellator,0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
addVertex(tessellator,0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(), sideIron.getMinV());
addVertex(tessellator,-0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),sideIron.getMinV());
addVertex(tessellator,-0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());

//------------------top

tessellator.draw();
tessellator.startDrawingQuads();

/**
 *  Render -x
 */
updateTesselatorToSide(tessellator,world,block,x, y, z,SideThree);

addVertex(tessellator,-0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
addVertex(tessellator,-0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,-0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,-0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
//------------------top

addVertex(tessellator,-0.6,0.04100156599075085,0.4999188650034184,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,  topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,-0.6,0.24100116467226057,0.49951820650163886,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,  topIcon.getMaxU(), topIcon.getMinV());
addVertex(tessellator,-0.6,0.23899787216336277,-0.5004797869059099,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
addVertex(tessellator,-0.6,0.038998273481853044,-0.5000791284041302,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,-0.6,0.038998273481853044,-0.5000791284041302,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,-0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
addVertex(tessellator,-0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
addVertex(tessellator,-0.6,0.04100156599075085,0.4999188650034184,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,  topIcon.getMinU(), (float) (topIcon.getMaxV()-0));

tessellator.draw();
tessellator.startDrawingQuads();

/**
 *  Render +x
 */
updateTesselatorToSide(tessellator,world,block,x, y, z,SideFour);

addVertex(tessellator,0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,  sideIron.getMaxU(),sideIron.getMaxV());
addVertex(tessellator,0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
addVertex(tessellator,0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(), (float) (sideIron.getMaxV()-0.041));
addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.00));
addVertex(tessellator,0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(), (float) (sideIron.getMaxV()-0.041));

//------------------top

addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,0.5,0.898996547812345,-0.5018019599617825,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
addVertex(tessellator,0.5,0.9009998403212426,0.4981960334457663,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,  topIcon.getMinU(), (float) (topIcon.getMaxV()-0));

tessellator.draw();
tessellator.startDrawingQuads();

/**
 *  Render +z
 */
updateTesselatorToSide(tessellator,world,block,x, y, z,SideFive);

addVertex(tessellator,0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
addVertex(tessellator,0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,-0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,-0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
addVertex(tessellator,0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(), (float) (sideIron.getMaxV()-0.041));
addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
addVertex(tessellator,-0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(), (float) (sideIron.getMaxV()-0.041));
//------------------top

addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,0.5,0.9009998403212426,0.4981960334457663,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
addVertex(tessellator,-0.6,0.24100116467226057,0.49951820650163886,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
addVertex(tessellator,-0.6,0.04100156599075085,0.4999188650034184,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));

tessellator.draw();
tessellator.startDrawingQuads();

/**
 *  Render -z
 */
updateTesselatorToSide(tessellator,world,block,x, y, z,SideSix);

addVertex(tessellator,-0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(), (float) (sideIron.getMaxV()-0.041));
addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.00));
addVertex(tessellator,0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(), (float) (sideIron.getMaxV()-0.041));
addVertex(tessellator,-0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
addVertex(tessellator,-0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
//------------------top

addVertex(tessellator,-0.6,0.038998273481853044,-0.5000791284041302,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,  topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,-0.6,0.23899787216336277,-0.5004797869059099,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,topIcon.getMaxU(), topIcon.getMinV());
addVertex(tessellator,0.5,0.898996547812345,-0.5018019599617825,x+0.5,y+0.5,z+0.5,RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,RenderSide.RotateY,topIcon.getMinU(), (float) (topIcon.getMaxV()-0));

tessellator.draw();
tessellator.startDrawingQuads();
}	
/**
 *  New Type laying
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 */
if(type == 2) {
/**
 *  Render +y
 */
	updateTesselatorToSide(tessellator,world,block,x, y, z,SideOne);
addVertex(tessellator,0.5,0.5002503490146017,0.4997495255733308,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
addVertex(tessellator,0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,-0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,-0.5,0.5002503490146017,0.4997495255733308,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
addVertex(tessellator,0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5, RenderSide.RotateY,  sideIron.getMaxU(), (float) (sideIron.getMaxV()-0.041));
addVertex(tessellator,0.5,0.4996493608850766,-0.7002503239321881,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
addVertex(tessellator,0.5,0.4996493608850766,-0.7002503239321881,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
addVertex(tessellator,-0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(), (float) (sideIron.getMaxV()-0.041));
//------------------top
addVertex(tessellator,0.5,0.4996493608850766,-0.7002503239321881,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,0.5,0.4995491961968224,-0.9002502988497747,x+0.5,y+0.5,z+0.5, RenderSide.RotateY,	topIcon.getMaxU(), topIcon.getMinV());
addVertex(tessellator,-0.6,0.4998797396680612,-0.24025038162173923,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
addVertex(tessellator,-0.6,0.4999799043563154,-0.04025040670415276,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
tessellator.draw();
tessellator.startDrawingQuads();

/**
 *  Render -y
 */
updateTesselatorToSide(tessellator,world,block,x, y, z,SideTwo);
addVertex(tessellator,-0.5,-0.4997495255733308,0.5002503490146017,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
addVertex(tessellator,-0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,0.5,-0.4997495255733308,0.5002503490146017,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
addVertex(tessellator,-0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(), (float) (sideIron.getMaxV()-0.041));
addVertex(tessellator,0.5,-0.5003505137028559,-0.6997495004909172,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
addVertex(tessellator,0.5,-0.5003505137028559,-0.6997495004909172,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.00));
addVertex(tessellator,0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(), (float) (sideIron.getMaxV()-0.041));
//------------------top
addVertex(tessellator,-0.6,-0.500019970231617,-0.03974958326288184,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,-0.6,-0.5001201349198713,-0.23974955818046834,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
addVertex(tessellator,0.5,-0.5004506783911101,-0.8997494754085038,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
addVertex(tessellator,0.5,-0.5003505137028559,-0.6997495004909172,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
tessellator.draw();
tessellator.startDrawingQuads();

/**
 *  Render -x
 */
updateTesselatorToSide(tessellator,world,block,x, y, z,SideThree);
addVertex(tessellator,-0.5,0.5002503490146017,0.4997495255733308,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
addVertex(tessellator,-0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,-0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,-0.5,-0.4997495255733308,0.5002503490146017,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
//------------------top
addVertex(tessellator,-0.6,0.4999799043563154,-0.04025040670415276,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,-0.6,0.4998797396680612,-0.24025038162173923,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
addVertex(tessellator,-0.6,-0.5001201349198713,-0.23974955818046834,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
addVertex(tessellator,-0.6,-0.500019970231617,-0.03974958326288184,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,-0.6,-0.500019970231617,-0.03974958326288184,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,-0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
addVertex(tessellator,-0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
addVertex(tessellator,-0.6,0.4999799043563154,-0.04025040670415276,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
tessellator.draw();
tessellator.startDrawingQuads();

/**
 *  Render +x
 */
updateTesselatorToSide(tessellator,world,block,x, y, z,SideFour);
addVertex(tessellator,0.5,-0.4997495255733308,0.5002503490146017,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
addVertex(tessellator,0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
addVertex(tessellator,0.5,0.5002503490146017,0.4997495255733308,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
addVertex(tessellator,0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(), (float) (sideIron.getMaxV()-0.041));
addVertex(tessellator,0.5,-0.5003505137028559,-0.6997495004909172,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
addVertex(tessellator,0.5,0.4996493608850766,-0.7002503239321881,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.00));
addVertex(tessellator,0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5, RenderSide.RotateY,  sideIron.getMinU(), (float) (sideIron.getMaxV()-0.041));
//------------------top
addVertex(tessellator,0.5,-0.5003505137028559,-0.6997495004909172,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,0.5,-0.5004506783911101,-0.8997494754085038,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
addVertex(tessellator,0.5,0.4995491961968224,-0.9002502988497747,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
addVertex(tessellator,0.5,0.4996493608850766,-0.7002503239321881,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
tessellator.draw();
tessellator.startDrawingQuads();

/**
 *  Render +z
 */
updateTesselatorToSide(tessellator,world,block,x, y, z,SideFive);
//render type 1 south
addVertex(tessellator,0.5,-0.4997495255733308,0.5002503490146017,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMaxU(),sideIron.getMaxV());
addVertex(tessellator,0.5,0.5002503490146017,0.4997495255733308,x+0.5,y+0.5,z+0.5, RenderSide.RotateY,sideIron.getMaxU(), sideIron.getMinV());
addVertex(tessellator,-0.5,0.5002503490146017,0.4997495255733308,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(),sideIron.getMinV());
addVertex(tessellator,-0.5,-0.4997495255733308,0.5002503490146017,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, sideIron.getMinU(),sideIron.getMaxV());
//------------------top
tessellator.draw();
tessellator.startDrawingQuads();

/**
 *  Render -z
 */
updateTesselatorToSide(tessellator,world,block,x, y, z,SideSix);
//------------------top
addVertex(tessellator,0.5,0.4995491961968224,-0.9002502988497747,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
addVertex(tessellator,0.5,-0.5004506783911101,-0.8997494754085038,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMaxU(), topIcon.getMinV());
addVertex(tessellator,-0.6,-0.5001201349198713,-0.23974955818046834,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMinU(), topIcon.getMinV());
addVertex(tessellator,-0.6,0.4998797396680612,-0.24025038162173923,x+0.5,y+0.5,z+0.5, RenderSide.RotateY, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
tessellator.draw();
tessellator.startDrawingQuads();
}
		
		return true;
}

	
	
	
	
	
	
	
	private void setRotation(RenderSide RotateY, double angle) {

		if(RotateY == RenderSide.RotateY) {
			if(angle == 0) {
				theta = 0;
				SideOne = RenderSide.Ypositiv;
				SideTwo = RenderSide.Ynegativ;
				SideThree = RenderSide.Xnegativ;
				SideFour = RenderSide.Xpositiv;
				SideFive = RenderSide.Zpositiv;
				SideSix = RenderSide.Znegativ;
				}
			if(angle == 90) {
				theta = 84.822/2;
				SideOne = RenderSide.Ypositiv;
				SideTwo = RenderSide.Ynegativ;
				SideThree = RenderSide.Znegativ;
				SideFour = RenderSide.Zpositiv;
				SideFive = RenderSide.Xnegativ;
				SideSix = RenderSide.Xpositiv;
			}
			if(angle == 180) {
				theta = 84.822;
				SideOne = RenderSide.Ypositiv;
				SideTwo = RenderSide.Ynegativ;
				SideThree = RenderSide.Xpositiv;
				SideFour = RenderSide.Xnegativ;
				SideFive = RenderSide.Znegativ;
				SideSix = RenderSide.Zpositiv;
				}
			if(angle == 270) {
				theta = 84.822+84.822/2;
				SideOne = RenderSide.Ypositiv;
				SideTwo = RenderSide.Ynegativ;
				SideThree = RenderSide.Zpositiv;
				SideFour = RenderSide.Znegativ;
				SideFive = RenderSide.Xpositiv;
				SideSix = RenderSide.Xnegativ;
			}
			
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