/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.items;

import mods.railcraft.api.core.items.IFilterItem;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;
import mods.railcraft.common.util.inventory.InvTools;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by CovertJaguar on 5/29/2017 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class ItemFilterSimple extends ItemRailcraft implements IFilterItem {
    public ItemFilterSimple() {
    }

    public static ItemStack setPrototype(ItemStack filter, ItemStack prototype) {
        filter = filter.copy();
        NBTTagCompound nbt = new NBTTagCompound();
        prototype.writeToNBT(nbt);
        InvTools.setItemDataRailcraft(filter, "prototype", nbt);
        return filter;
    }

    @Nullable
    public static ItemStack getPrototype(ItemStack stack) {
        NBTTagCompound nbt = InvTools.getItemDataRailcraft(stack, "prototype");
        if (nbt != null) {
            return ItemStack.loadItemStackFromNBT(nbt);
        }
        return InvTools.emptyStack();
    }

    public boolean isValidPrototype(ItemStack stack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> info, boolean adv) {
        super.addInformation(stack, player, info, adv);
        ItemStack prototype = getPrototype(stack);
        if (!InvTools.isEmpty(prototype)) {
            info.add(LocalizationPlugin.translate("item.railcraft.filter.tips.prototype"));
            info.add("-" + prototype.getDisplayName());
            info.add("--" + prototype.getItem().getRegistryName() + "#" + prototype.getMetadata());
            addAdditionalInfo(stack, prototype, info, adv);
        }
    }

    protected void addAdditionalInfo(ItemStack stack, ItemStack prototype, List<String> info, boolean adv) {
    }
}
