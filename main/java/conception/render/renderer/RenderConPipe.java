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
		int lightValue = (int) (block.getMixedBrightnessForBlock(world, x, y, z)*0.8);
		int part = world.getBlockMetadata(x, y, z);
		IIcon sideIron = Blocks.double_stone_slab.getIcon(0,5);
		IIcon topIcon = Blocks.stonebrick.getIcon(0,1);
		tessellator.setBrightness(lightValue);
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		
    	int type = 0;
		
		/**
		 *  north 0
		 *  south 84.822
		 *  east  84.822/2
		 *  west  84.822+84.822/2
		 **/
		double theta = 0;
		int ax = 0;
		switch(part) {
		case 0:{ theta = 0;  ax = 2;type = 1; break;}
		case 1:{ theta = 84.822;  ax = 2;type = 1;  break;}
		case 2:{ theta = 84.822/2;  ax = 2;type = 1;  break;}
		case 3:{ theta = 84.822+84.822/2;  ax = 2;type = 1;  break;}
		case 4:{ theta = 0;  ax = 2;type = 2; break;}
		case 5:{ theta = 84.822;  ax = 2;type = 2;  break;}
		case 6:{ theta = 84.822/2;  ax = 2;type = 2;  break;}
		case 7:{ theta = 84.822+84.822/2;  ax = 2;type = 2;  break;}
		case 8:{ theta = 0;  ax = 2;type = 3; break;}
		case 9:{ theta = 84.822;  ax = 2;type = 3; break;}
		case 10:{ theta = 84.822/2;  ax = 2;type = 3; break;}
		case 11:{ theta = 84.822+84.822/2;  ax = 2;type = 3; break;}
		}
		sinTheta = Math.sin(theta);
		cosTheta = Math.cos(theta);
		
		
		
		
		
			//------------------base
		/*
	addVertex(tessellator,0.5, -0.5, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),sideIron.getMaxV());
	addVertex(tessellator,0.5, 0.1, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
	addVertex(tessellator,-0.5, 0.1, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
	addVertex(tessellator,-0.5, -0.5, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(),sideIron.getMaxV());
		
	addVertex(tessellator,0.5, 0.1, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(), (float) (sideIron.getMaxV()-0.041));
	addVertex(tessellator,0.5, 0.7, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
	addVertex(tessellator,0.5, 0.7, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
	addVertex(tessellator,-0.5, 0.1, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(), (float) (sideIron.getMaxV()-0.041));

	addVertex(tessellator,-0.5, -0.5, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),sideIron.getMaxV());
	addVertex(tessellator,-0.5, 0.1, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
	addVertex(tessellator,0.5, 0.1, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
	addVertex(tessellator,0.5, -0.5, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(),sideIron.getMaxV());
		
	addVertex(tessellator,-0.5, 0.1, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(), (float) (sideIron.getMaxV()-0.041));
	addVertex(tessellator,0.5, 0.7, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
	addVertex(tessellator,0.5, 0.7, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.00));
	addVertex(tessellator,0.5, 0.1, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(), (float) (sideIron.getMaxV()-0.041));
		
	addVertex(tessellator,-0.5, -0.5, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),sideIron.getMaxV());
	addVertex(tessellator,-0.5, 0.1, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
	addVertex(tessellator,-0.5, 0.1, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
	addVertex(tessellator,-0.5, -0.5, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(),sideIron.getMaxV());

		
	addVertex(tessellator,0.5, -0.5, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),sideIron.getMaxV());
	addVertex(tessellator,0.5, 0.1, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.025));
	addVertex(tessellator,0.5, 0.1, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.025));
	addVertex(tessellator,0.5, -0.5, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(),sideIron.getMaxV());
		
	addVertex(tessellator,0.5, 0.1, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(), (float) (sideIron.getMaxV()-0.041));
	addVertex(tessellator,0.5, 0.7, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),  (float) (sideIron.getMinV()+0.00));
	addVertex(tessellator,0.5, 0.7, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(),  (float) (sideIron.getMinV()+0.00));
	addVertex(tessellator,0.5, 0.1, 0.5,x+0.5,y+0.5,z+0.5,ax,  sideIron.getMinU(), (float) (sideIron.getMaxV()-0.041));
		
	addVertex(tessellator,0.5, -0.5, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(),sideIron.getMaxV());
	addVertex(tessellator,0.5, -0.5, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMaxU(), sideIron.getMinV());
	addVertex(tessellator,-0.5, -0.5, 0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(),sideIron.getMinV());
	addVertex(tessellator,-0.5, -0.5, -0.5,x+0.5,y+0.5,z+0.5,ax, sideIron.getMinU(),sideIron.getMaxV());
		//------------------top
		
	addVertex(tessellator,0.5, 0.7, 0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
	addVertex(tessellator,0.5, 0.9, 0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMaxU(), topIcon.getMinV());
	addVertex(tessellator,-0.6, 0.24, 0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMinU(), topIcon.getMinV());
	addVertex(tessellator,-0.6, 0.04, 0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));

	addVertex(tessellator,0.5, 0.9, 0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
	addVertex(tessellator,0.5, 0.9, -0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMaxU(), topIcon.getMinV());
	addVertex(tessellator,-0.6, 0.24, -0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMinU(), topIcon.getMinV());
	addVertex(tessellator,-0.6, 0.24, 0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
		
	addVertex(tessellator,0.5, 0.7, -0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
	addVertex(tessellator,0.5, 0.9, -0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMaxU(), topIcon.getMinV());
	addVertex(tessellator,0.5, 0.9, 0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMinU(), topIcon.getMinV());
	addVertex(tessellator,0.5, 0.7, 0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
		
	addVertex(tessellator,-0.6, 0.04, 0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
	addVertex(tessellator,-0.6, 0.24, 0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMaxU(), topIcon.getMinV());
	addVertex(tessellator,-0.6, 0.24, -0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMinU(), topIcon.getMinV());
	addVertex(tessellator,-0.6, 0.04, -0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
		
	addVertex(tessellator,-0.6, 0.04, -0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
	addVertex(tessellator,-0.5, 0.1, -0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMaxU(), topIcon.getMinV());
	addVertex(tessellator,-0.5, 0.1, 0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMinU(), topIcon.getMinV());
	addVertex(tessellator,-0.6, 0.04, 0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
		
	addVertex(tessellator,-0.6, 0.04, -0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMaxU(), (float) (topIcon.getMaxV()-0));
	addVertex(tessellator,-0.6, 0.24, -0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMaxU(), topIcon.getMinV());
	addVertex(tessellator,0.5, 0.9, -0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMinU(), topIcon.getMinV());
	addVertex(tessellator,0.5, 0.7, -0.5,x+0.5,y+0.5,z+0.5,ax, topIcon.getMinU(), (float) (topIcon.getMaxV()-0));
		*/
if(type == 1) {
addVertex(tessellator,0.5,0.5002503490146017,0.4997495255733308,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27503908);
addVertex(tessellator,-0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27503908);
addVertex(tessellator,-0.5,0.5002503490146017,0.4997495255733308,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27146092);
addVertex(tessellator,0.5,0.4996493608850766,-0.7002503239321881,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,0.5,0.4996493608850766,-0.7002503239321881,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,-0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27146092);
addVertex(tessellator,-0.5,-0.4997495255733308,0.5002503490146017,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,-0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27503908);
addVertex(tessellator,0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27503908);
addVertex(tessellator,0.5,-0.4997495255733308,0.5002503490146017,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,-0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27146092);
addVertex(tessellator,0.5,-0.5003505137028559,-0.6997495004909172,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,0.5,-0.5003505137028559,-0.6997495004909172,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.25003907);
addVertex(tessellator,0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27146092);
addVertex(tessellator,-0.5,0.5002503490146017,0.4997495255733308,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,-0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27503908);
addVertex(tessellator,-0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27503908);
addVertex(tessellator,-0.5,-0.4997495255733308,0.5002503490146017,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,0.5,-0.4997495255733308,0.5002503490146017,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27503908);
addVertex(tessellator,0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27503908);
addVertex(tessellator,0.5,0.5002503490146017,0.4997495255733308,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27146092);
addVertex(tessellator,0.5,-0.5003505137028559,-0.6997495004909172,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,0.5,0.4996493608850766,-0.7002503239321881,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.25003907);
addVertex(tessellator,0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27146092);
addVertex(tessellator,0.5,-0.4997495255733308,0.5002503490146017,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,0.5,0.5002503490146017,0.4997495255733308,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,-0.5,0.5002503490146017,0.4997495255733308,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.25003907);
addVertex(tessellator,-0.5,-0.4997495255733308,0.5002503490146017,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,0.5,0.4996493608850766,-0.7002503239321881,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,0.5,0.4995491961968224,-0.9002502988497747,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,-0.6,0.4998797396680612,-0.24025038162173923,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,-0.6,0.4999799043563154,-0.04025040670415276,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,0.5,0.4995491961968224,-0.9002502988497747,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,0.5,-0.5004506783911101,-0.8997494754085038,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,-0.6,-0.5001201349198713,-0.23974955818046834,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,-0.6,0.4998797396680612,-0.24025038162173923,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,0.5,-0.5003505137028559,-0.6997495004909172,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,0.5,-0.5004506783911101,-0.8997494754085038,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,0.5,0.4995491961968224,-0.9002502988497747,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,0.5,0.4996493608850766,-0.7002503239321881,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,-0.6,0.4999799043563154,-0.04025040670415276,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,-0.6,0.4998797396680612,-0.24025038162173923,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,-0.6,-0.5001201349198713,-0.23974955818046834,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,-0.6,-0.500019970231617,-0.03974958326288184,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,-0.6,-0.500019970231617,-0.03974958326288184,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,-0.5,-0.5000500196380934,-0.09974957573815779,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,-0.5,0.4999498549498392,-0.10025039917942871,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,-0.6,0.4999799043563154,-0.04025040670415276,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,-0.6,-0.500019970231617,-0.03974958326288184,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,-0.6,-0.5001201349198713,-0.23974955818046834,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,0.5,-0.5004506783911101,-0.8997494754085038,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,0.5,-0.5003505137028559,-0.6997495004909172,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
	}
	if(type == 2) {
addVertex(tessellator,0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27503908);
addVertex(tessellator,-0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27503908);
addVertex(tessellator,-0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27146092);
addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,-0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27146092);
addVertex(tessellator,-0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,-0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27503908);
addVertex(tessellator,0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27503908);
addVertex(tessellator,0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,-0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27146092);
addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.25003907);
addVertex(tessellator,0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27146092);
addVertex(tessellator,-0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,-0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27503908);
addVertex(tessellator,-0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27503908);
addVertex(tessellator,-0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27503908);
addVertex(tessellator,0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27503908);
addVertex(tessellator,0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27146092);
addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.25003907);
addVertex(tessellator,0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27146092);
addVertex(tessellator,0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,-0.5,-0.4989973504493254,0.5010006429582233,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.25003907);
addVertex(tessellator,-0.5,-0.5010006429582233,-0.4989973504493254,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,0.5,0.9009998403212426,0.4981960334457663,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,-0.6,0.24100116467226057,0.49951820650163886,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,-0.6,0.04100156599075085,0.4999188650034184,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,0.5,0.9009998403212426,0.4981960334457663,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,0.5,0.898996547812345,-0.5018019599617825,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,-0.6,0.23899787216336277,-0.5004797869059099,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,-0.6,0.24100116467226057,0.49951820650163886,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,0.5,0.898996547812345,-0.5018019599617825,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,0.5,0.9009998403212426,0.4981960334457663,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,0.5,0.701000241639733,0.49859669194754586,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,-0.6,0.04100156599075085,0.4999188650034184,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,-0.6,0.24100116467226057,0.49951820650163886,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,-0.6,0.23899787216336277,-0.5004797869059099,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,-0.6,0.038998273481853044,-0.5000791284041302,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,-0.6,0.038998273481853044,-0.5000791284041302,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,-0.5,0.09899815308630597,-0.5001993259546642,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,-0.5,0.10100144559520377,0.49979866745288454,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,-0.6,0.04100156599075085,0.4999188650034184,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,-0.6,0.038998273481853044,-0.5000791284041302,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,-0.6,0.23899787216336277,-0.5004797869059099,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,0.5,0.898996547812345,-0.5018019599617825,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,0.5,0.6989969491308351,-0.5014013014600027,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
	}
if(type == 3) {
addVertex(tessellator,0.5,0.4984952744589479,-0.5015002107099241,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,0.5,-0.10150201664237522,-0.4996972489593383,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27503908);
addVertex(tessellator,-0.5,-0.10150201664237522,-0.4996972489593383,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27503908);
addVertex(tessellator,-0.5,0.4984952744589479,-0.5015002107099241,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,0.5,-0.10150201664237522,-0.4996972489593383,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27146092);
addVertex(tessellator,0.5,-0.7014993077436984,-0.49789428720875273,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,0.5,-0.7014993077436984,-0.49789428720875273,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,-0.5,-0.10150201664237522,-0.4996972489593383,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27146092);
addVertex(tessellator,-0.5,0.5015002107099241,0.4984952744589479,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,-0.5,-0.09849708039139918,0.5002982362095336,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27503908);
addVertex(tessellator,0.5,-0.09849708039139918,0.5002982362095336,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27503908);
addVertex(tessellator,0.5,0.5015002107099241,0.4984952744589479,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,-0.5,-0.09849708039139918,0.5002982362095336,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27146092);
addVertex(tessellator,0.5,-0.6984943714927223,0.5021011979601191,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,0.5,-0.6984943714927223,0.5021011979601191,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.25003907);
addVertex(tessellator,0.5,-0.09849708039139918,0.5002982362095336,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27146092);
addVertex(tessellator,-0.5,0.4984952744589479,-0.5015002107099241,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,-0.5,-0.10150201664237522,-0.4996972489593383,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27503908);
addVertex(tessellator,-0.5,-0.09849708039139918,0.5002982362095336,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27503908);
addVertex(tessellator,-0.5,0.5015002107099241,0.4984952744589479,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,0.5,0.5015002107099241,0.4984952744589479,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,0.5,-0.09849708039139918,0.5002982362095336,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27503908);
addVertex(tessellator,0.5,-0.10150201664237522,-0.4996972489593383,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27503908);
addVertex(tessellator,0.5,0.4984952744589479,-0.5015002107099241,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,0.5,-0.09849708039139918,0.5002982362095336,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.27146092);
addVertex(tessellator,0.5,-0.6984943714927223,0.5021011979601191,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,0.5,-0.7014993077436984,-0.49789428720875273,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.25003907);
addVertex(tessellator,0.5,-0.10150201664237522,-0.4996972489593383,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.27146092);
addVertex(tessellator,0.5,0.5015002107099241,0.4984952744589479,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.31246093);
addVertex(tessellator,0.5,0.4984952744589479,-0.5015002107099241,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.25003907);
addVertex(tessellator,-0.5,0.4984952744589479,-0.5015002107099241,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.25003907);
addVertex(tessellator,-0.5,0.5015002107099241,0.4984952744589479,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.31246093);
addVertex(tessellator,0.5,-0.7014993077436984,-0.49789428720875273,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,0.5,-0.9014984047774727,-0.49729329995855753,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,-0.6,-0.24150138456601727,-0.4992765578842017,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,-0.6,-0.04150228753224291,-0.4998775451343969,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,0.5,-0.9014984047774727,-0.49729329995855753,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,0.5,-0.8984934685264967,0.5027021852103145,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,-0.6,-0.23849644831504122,0.5007189272846703,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,-0.6,-0.24150138456601727,-0.4992765578842017,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,0.5,-0.6984943714927223,0.5021011979601191,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,0.5,-0.8984934685264967,0.5027021852103145,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,0.5,-0.9014984047774727,-0.49729329995855753,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,0.5,-0.7014993077436984,-0.49789428720875273,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,-0.6,-0.04150228753224291,-0.4998775451343969,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,-0.6,-0.24150138456601727,-0.4992765578842017,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,-0.6,-0.23849644831504122,0.5007189272846703,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,-0.6,-0.03849735128126685,0.500117940034475,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,-0.6,-0.03849735128126685,0.500117940034475,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,-0.5,-0.09849708039139918,0.5002982362095336,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,-0.5,-0.10150201664237522,-0.4996972489593383,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,-0.6,-0.04150228753224291,-0.4998775451343969,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
addVertex(tessellator,-0.6,-0.03849735128126685,0.500117940034475,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.49996093);
addVertex(tessellator,-0.6,-0.23849644831504122,0.5007189272846703,x+0.5,y+0.5,z+0.5,ax, 0.65623045,0.43753907);
addVertex(tessellator,0.5,-0.8984934685264967,0.5027021852103145,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.43753907);
addVertex(tessellator,0.5,-0.6984943714927223,0.5021011979601191,x+0.5,y+0.5,z+0.5,ax, 0.62501955,0.49996093);
}
		
		return true;
	}

	private boolean check(Block block) {
		// TODO Auto-generated method stub
		return block == mainRegistry.blockConSmoke || block.getMaterial() == Material.gourd;
	}
	
	private void addVertex(Tessellator tessellator,double x ,double y,double z,double positionx,double positiony,double positionz, int ax, double d, double e) {

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
			tessellator.addVertexWithUV(positionx+posx, positiony+posy, positionz+posz, d,e);  
			//System.out.println("addVertex(tessellator,"+posx+","+posy+","+posz+",x+0.5,y+0.5,z+0.5,ax, "+d+","+e+");");
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