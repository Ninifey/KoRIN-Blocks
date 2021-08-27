package conception.Blocks;

import java.util.List;

import conception.CONMAIN;
import conception.mainRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockConPipe extends Block
{

    public static final String[] field_150096_a = new String[] {"Xstraight","Ystraight","Zstraight","Corner"};
	private String b;
	public static int  rendtype;
	public static Block instance;

    @SideOnly(Side.CLIENT)
    private IIcon[] field_150095_b;
    private static final String __OBFID = "CL_00000335";
    
    public BlockConPipe(String Texturename) 
    {
        super(Material.iron);
           setStepSound(Block.soundTypeStone);
   		   setCreativeTab(CONMAIN.KoRINTAB);
   		   setBlockName(Texturename);
   		   setLightOpacity(0);
   		   
   		   b = Texturename;
   		  
   }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if (p_149691_2_ < 0 || p_149691_2_ >= this.field_150095_b.length)
        {
            p_149691_2_ = 0;
        }

        return this.field_150095_b[p_149691_2_];
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 2));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 3));
    }
    
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
    	int temp = 0;
		boolean  Xminus = world.getBlock(x-1, y, z)==mainRegistry.blockConPipe;
		if(Xminus) {++temp;}
		boolean  Xplus = world.getBlock(x+1, y, z)==mainRegistry.blockConPipe;
		if(Xplus) {++temp;}
		boolean  Yminus = world.getBlock(x, y-1, z)==mainRegistry.blockConPipe;
		if(Yminus) {++temp;}
		boolean  Yplus = world.getBlock(x, y+1, z)==mainRegistry.blockConPipe;
		if(Yplus) {++temp;}
		boolean  Zminus = world.getBlock(x, y, z-1)==mainRegistry.blockConPipe;
		if(Zminus) {++temp;}
		boolean  Zplus = world.getBlock(x, y, z+1)==mainRegistry.blockConPipe; 
		if(Zplus) {++temp;}

		if(temp==2 && ((Xminus && Xplus) || (Yminus && Yplus) || (Zminus && Zplus))) {
			if(Xminus && Xplus) {world.setBlockMetadataWithNotify(x, y, z, 0, 2);}
			if(Zminus && Zplus) {world.setBlockMetadataWithNotify(x, y, z, 1, 2);}
			if(Yminus && Yplus) {world.setBlockMetadataWithNotify(x, y, z, 2, 2);}
		}else if(temp>1){
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}else {
			if(Xminus || Xplus) {world.setBlockMetadataWithNotify(x, y, z, 0, 2);}
			if(Zminus || Zplus) {world.setBlockMetadataWithNotify(x, y, z, 1, 2);}
			if(Yminus || Yplus) {world.setBlockMetadataWithNotify(x, y, z, 2, 2);}
		}
    }
    
    public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_) {
    	int temp = 0;
		boolean  Xminus = world.getBlock(x-1, y, z)==mainRegistry.blockConPipe;
		if(Xminus) {++temp;}
		boolean  Xplus = world.getBlock(x+1, y, z)==mainRegistry.blockConPipe;
		if(Xplus) {++temp;}
		boolean  Yminus = world.getBlock(x, y-1, z)==mainRegistry.blockConPipe;
		if(Yminus) {++temp;}
		boolean  Yplus = world.getBlock(x, y+1, z)==mainRegistry.blockConPipe;
		if(Yplus) {++temp;}
		boolean  Zminus = world.getBlock(x, y, z-1)==mainRegistry.blockConPipe;
		if(Zminus) {++temp;}
		boolean  Zplus = world.getBlock(x, y, z+1)==mainRegistry.blockConPipe; 
		if(Zplus) {++temp;}

		if(temp==2 && ((Xminus && Xplus) || (Yminus && Yplus) || (Zminus && Zplus))) {
			if(Xminus && Xplus) {world.setBlockMetadataWithNotify(x, y, z, 0, 2);}
			if(Zminus && Zplus) {world.setBlockMetadataWithNotify(x, y, z, 1, 2);}
			if(Yminus && Yplus) {world.setBlockMetadataWithNotify(x, y, z, 2, 2);}
		}else if(temp>1){
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}else {
			if(Xminus || Xplus) {world.setBlockMetadataWithNotify(x, y, z, 0, 2);}
			if(Zminus || Zplus) {world.setBlockMetadataWithNotify(x, y, z, 1, 2);}
			if(Yminus || Yplus) {world.setBlockMetadataWithNotify(x, y, z, 2, 2);}
		}
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_150095_b = new IIcon[field_150096_a.length];

        for (int i = 0; i < this.field_150095_b.length; ++i)
        {
                this.field_150095_b[i] = p_149651_1_.registerIcon(CONMAIN.MODID+":pipe");	
        }
    }


    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean getBlocksMovement(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_)
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return rendtype;
    }
    /**
     * Returns true if the specified block can be connected by a fence
     */
    public boolean canConnectFenceTo(IBlockAccess p_149826_1_, int p_149826_2_, int p_149826_3_, int p_149826_4_)
    {
        Block block = p_149826_1_.getBlock(p_149826_2_, p_149826_3_, p_149826_4_);
        return block != this && block != Blocks.fence_gate ? (block.getMaterial().isOpaque() && block.renderAsNormalBlock() ? block.getMaterial() != Material.gourd : false) : true;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        return true;
    }


    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        return p_149727_1_.isRemote ? true : ItemLead.func_150909_a(p_149727_5_, p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
    }

}