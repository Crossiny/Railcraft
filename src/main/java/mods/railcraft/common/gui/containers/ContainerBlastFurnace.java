/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2018
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/
package mods.railcraft.common.gui.containers;

import mods.railcraft.common.blocks.multi.TileBlastFurnace;
import mods.railcraft.common.gui.slots.SlotOutput;
import mods.railcraft.common.gui.slots.SlotStackFilter;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ContainerBlastFurnace extends RailcraftContainer {

    private final TileBlastFurnace furnace;
    private int lastCookTime;
    private int lastCookTimeTotal;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerBlastFurnace(InventoryPlayer player, TileBlastFurnace tile) {
        super(tile);
        this.furnace = tile;
        addSlot(new SlotStackFilter(TileBlastFurnace.INPUT_FILTER, tile, TileBlastFurnace.SLOT_INPUT, 56, 17));
        addSlot(new SlotStackFilter(TileBlastFurnace.FUEL_FILTER, tile, TileBlastFurnace.SLOT_FUEL, 56, 53));
        addSlot(new SlotOutput(tile, TileBlastFurnace.SLOT_OUTPUT, 116, 21));
        addSlot(new SlotOutput(tile, TileBlastFurnace.SLOT_SLAG, 116, 53));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlot(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            addSlot(new Slot(player, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void addListener(IContainerListener player) {
        super.addListener(player);
        player.sendWindowProperty(this, 0, furnace.getCookTime());
        player.sendWindowProperty(this, 1, furnace.getTotalCookTime());
        player.sendWindowProperty(this, 2, furnace.burnTime);
        player.sendWindowProperty(this, 3, furnace.currentItemBurnTime);
    }

    /**
     * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
     */
    @Override
    public void sendUpdateToClient() {
        super.sendUpdateToClient();

        for (IContainerListener listener : listeners) {
            if (lastCookTime != furnace.getCookTime())
                listener.sendWindowProperty(this, 0, furnace.getCookTime());
            if (lastCookTimeTotal != furnace.getTotalCookTime())
                listener.sendWindowProperty(this, 1, furnace.getTotalCookTime());

            if (lastBurnTime != furnace.burnTime)
                listener.sendWindowProperty(this, 2, furnace.burnTime);

            if (lastItemBurnTime != furnace.currentItemBurnTime)
                listener.sendWindowProperty(this, 3, furnace.currentItemBurnTime);
        }

        lastCookTime = furnace.getCookTime();
        lastCookTimeTotal = furnace.getTotalCookTime();
        lastBurnTime = furnace.burnTime;
        lastItemBurnTime = furnace.currentItemBurnTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        if (id == 0)
            furnace.setCookTime(data);

        if (id == 1)
            furnace.cookTimeTotal = data;

        if (id == 2)
            furnace.burnTime = data;

        if (id == 3)
            furnace.currentItemBurnTime = data;
    }

}
