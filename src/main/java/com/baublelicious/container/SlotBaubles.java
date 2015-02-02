package com.baublelicious.container;

import baubles.api.IBauble;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBaubles extends Slot {

	public SlotBaubles(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
    {
		return stack != null && stack.getItem() instanceof IBauble;
    }
}