package com.baublelicious.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import com.baublelicious.Baublelicious;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaubleliciousBaublesItem extends Item implements IBauble
{
    protected String name;


    public BaubleliciousBaublesItem()
    {
        super();
        setMaxStackSize(1);
        setCreativeTab(Baublelicious.BaubleliciousTab);

    }



    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if(!world.isRemote) {
            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
            for(int i = 0; i < baubles.getSlots(); i++)
                if((baubles.getStackInSlot(i) == null || baubles.getStackInSlot(i).isEmpty()) && baubles.isItemValidForSlot(i, player.getHeldItem(hand), player)) {
                    baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
                    if(!player.capabilities.isCreativeMode){
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                    }
                    onEquipped(player.getHeldItem(hand), player);
                    break;
                }
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (itemstack.getItemDamage()==0 && player.ticksExisted%39==0) {

        }
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.RARE;
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName();
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 1.9f);
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 2f);
    }

    public void registerItemModel(Item item) {
        Baublelicious.proxy.registerItemRenderer(item, 0, name);
    }
    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return false;
    }

}