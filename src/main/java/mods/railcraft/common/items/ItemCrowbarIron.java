/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2019
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/
package mods.railcraft.common.items;

import mods.railcraft.common.plugins.forge.CraftingPlugin;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class ItemCrowbarIron extends ItemCrowbar {

    public ItemCrowbarIron() {
        super(ItemMaterials.Material.IRON, ToolMaterial.IRON);
    }

    @Override
    public void defineRecipes() {
        CraftingPlugin.addShapedRecipe(new ItemStack(this),
                " RI",
                "RIR",
                "IR ",
                'I', "ingotIron",
                'R', "dyeRed");
        CraftingPlugin.addFurnaceRecipe(new ItemStack(this), new ItemStack(Items.IRON_NUGGET, 1), 0.1f);
    }
}
