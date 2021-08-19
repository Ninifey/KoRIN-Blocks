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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockConWall extends Block
{

    public static final String[] field_150096_a = new String[] {"oak", "spruce", "birch", "jungle", "acacia", "big_oak","default", "mossy", "cracked", "chiseled", "glass"};
	private String b;
	public static int  rendtype;
	public static Block instance;

    @SideOnly(Side.CLIENT)
    private IIcon[] field_150095_b;
    private static final String __OBFID = "CL_00000335";
    
    public BlockConWall(String Texturename) 
    {
        super(Material.iron);
           setStepSound(Block.soundTypeStone);
   		   setCreativeTab(CONMAIN.KoRINTAB);
   		   setBlockName(Texturename);
   		   
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
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 4));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 5));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 6));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 7));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 8));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 9));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 10));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_150095_b = new IIcon[field_150096_a.length];

        for (int i = 0; i < this.field_150095_b.length; ++i)
        {
        	if(i<= 5) {
                this.field_150095_b[i] = p_149651_1_.registerIcon(Blocks.planks.getIcon(2,i).getIconName());
        	}else if (i< 10) {
                this.field_150095_b[i] = p_149651_1_.registerIcon(Blocks.stonebrick.getIcon(2,i-5).getIconName());
        	}else {
                this.field_150095_b[i] = p_149651_1_.registerIcon(Blocks.glass.getIcon(2,i-10).getIconName());	
        	}
        }
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
    {
        boolean flag = this.canConnectFenceTo(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_ - 1);
        boolean flag1 = this.canConnectFenceTo(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_ + 1);
        boolean flag2 = this.canConnectFenceTo(p_149743_1_, p_149743_2_ - 1, p_149743_3_, p_149743_4_);
        boolean flag3 = this.canConnectFenceTo(p_149743_1_, p_149743_2_ + 1, p_149743_3_, p_149743_4_);
        boolean flag4 = this.canConnectFenceTo(p_149743_1_, p_149743_2_, p_149743_3_+1, p_149743_4_);
        boolean flag5 = this.canConnectFenceTo(p_149743_1_, p_149743_2_, p_149743_3_-1, p_149743_4_);
        float f = 0.2F;
        float f1 = 0.8F;
        float f2 = 0.2F;
        float f3 = 0.8F;
        float f4 = 0.2F;
        float f5 = 0.8F;

        if (flag)
        {
            f2 = 0.0F;
        }

        if (flag1)
        {
            f3 = 1.0F;
        }

        if (flag || flag1)
        {
            this.setBlockBounds(f, 0.0F, f2, f1, 0.8F, f3);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        f2 = 0.375F;
        f3 = 0.625F;

        if (flag2)
        {
            f = 0.0F;
        }

        if (flag3)
        {
            f1 = 1.0F;
        }

        if (flag2 || flag3 || !flag && !flag1)
        {
            this.setBlockBounds(f, 0.0F, f2, f1, 0.8F, f3);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        if (flag)
        {
            f2 = 0.0F;
        }

        if (flag1)
        {
            f3 = 1.0F;
        }

        this.setBlockBounds(f, f4, f2, f1, f5, f3);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {

        boolean flag = this.canConnectFenceTo(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_ - 1);
        boolean flag1 = this.canConnectFenceTo(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_ + 1);
        boolean flag2 = this.canConnectFenceTo(p_149719_1_, p_149719_2_ - 1, p_149719_3_, p_149719_4_);
        boolean flag3 = this.canConnectFenceTo(p_149719_1_, p_149719_2_ + 1, p_149719_3_, p_149719_4_);
        
        boolean flag4 = this.canConnectFenceTo(p_149719_1_, p_149719_2_, p_149719_3_+1, p_149719_4_);
        boolean flag5 = this.canConnectFenceTo(p_149719_1_, p_149719_2_, p_149719_3_-1, p_149719_4_);

        float f = 0.2F;
        float f1 = 0.8F;
        float f2 = 0.2F;
        float f3 = 0.8F;
        float f4 = 0.0F;
        float f5 = 1.0F;

        if (flag)
        {
            f2 = 0.0F;
        }

        if (flag1)
        {
            f3 = 1.0F;
        }

        if (flag2)
        {
            f = 0.0F;
        }

        if (flag3)
        {
            f1 = 1.0F;
        }

        this.setBlockBounds(f, f4, f2, f1, f5, f3);
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

    public static boolean func_149825_a(Block p_149825_0_)
    {
        return p_149825_0_ == mainRegistry.blockWall;
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