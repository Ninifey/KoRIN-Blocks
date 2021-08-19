package conception;

import conception.Blocks.BlockConSmoke;
import conception.Blocks.BlockConWall;
import conception.render.renderer.RenderConSmoke;
import conception.render.renderer.RenderConWall;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BasisClientProxy extends BasisCommonProxy{
	
	public void RegisterRender(){
		// deko blocks
		
		int r = RenderingRegistry.getNextAvailableRenderId();
		ISimpleBlockRenderingHandler renderwall = new RenderConWall(r);
		RenderingRegistry.registerBlockHandler(r, renderwall);
		((BlockConWall)BlockConWall.instance).rendtype = r;
		
		r = RenderingRegistry.getNextAvailableRenderId();
		ISimpleBlockRenderingHandler renderConSmoke = new RenderConSmoke(r);
		RenderingRegistry.registerBlockHandler(r, renderConSmoke);
		((BlockConSmoke)BlockConSmoke.instance).rendtype = r;
	}
}

