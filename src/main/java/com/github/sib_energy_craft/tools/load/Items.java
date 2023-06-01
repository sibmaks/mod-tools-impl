package com.github.sib_energy_craft.tools.load;

import com.github.sib_energy_craft.energy_api.utils.Identifiers;
import com.github.sib_energy_craft.sec_utils.load.DefaultModInitializer;
import com.github.sib_energy_craft.tools.item.tree_tap.TreeTapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;

import static com.github.sib_energy_craft.sec_utils.utils.ItemUtils.register;


/**
 * @since 0.0.1
 * @author sibmaks
 */
public final class Items implements DefaultModInitializer {
    public static final Item TREE_TAP;

    static {
        var treeTapSettings = new Item.Settings()
                .maxCount(1)
                .rarity(Rarity.COMMON)
                .maxDamage(60);
        var treeTapItem = new TreeTapItem(ToolMaterials.WOOD, 1.0f, -3.2f, treeTapSettings);
        TREE_TAP = register(ItemGroups.TOOLS, Identifiers.of("tree_tap"), treeTapItem);
    }
}
