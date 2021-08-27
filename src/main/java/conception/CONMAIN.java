package conception;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = CONMAIN.MODID, version = CONMAIN.VERSION)
public class CONMAIN
{
    public static final String MODID = "korinblocks";
	public static final String name = "Kingdome of Rin | Blocks";
    public static final String VERSION = "2.0b";

	public static CONMAIN instance;
	public static Configuration config;
	public static CreativeTabs KoRINTAB = new CreativeTabs(12,"KoRIN")
	{public Item getTabIconItem() {return Items.apple;}};
	
	public CONMAIN() {
		instance = this;
	}
	
	@SidedProxy
	(
	clientSide = "conception.BasisClientProxy", 
	serverSide = "conception.BasisCommonProxy"
	)
	
	public static BasisCommonProxy proxy;
	public static BasisClientProxy proxyclient;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent){
		proxy.RegisterRender();
		new mainRegistry();
	}

    @EventHandler
    public void init(FMLInitializationEvent event){
		 	
    }

    
    @EventHandler
	public void postInit(FMLPostInitializationEvent postEvent){

    }


}
	