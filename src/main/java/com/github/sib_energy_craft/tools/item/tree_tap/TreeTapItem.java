package com.github.sib_energy_craft.tools.item.tree_tap;

import com.github.sib_energy_craft.energy_api.utils.Identifiers;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Hand;


/**
 * @since 0.0.1
 * @author sibmaks
 */
public class TreeTapItem extends MiningToolItem implements TreeTap {
    /**
     * Tree tap item tag
     */
    public static final TagKey<Block> TREE_TAP_TAG;

    static {
        TREE_TAP_TAG = TagKey.of(RegistryKeys.BLOCK, Identifiers.of("tree_tap"));
    }

    public TreeTapItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(attackDamage, attackSpeed, material, TREE_TAP_TAG, settings);
    }

    @Override
    public boolean onUse(PlayerEntity player, Hand hand, ItemStack treeTap) {
        treeTap.damage(1, player, p -> p.sendToolBreakStatus(hand));
        return true;
    }
}
