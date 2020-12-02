
package io.github.limepotato.lunarium.creativetab;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import io.github.limepotato.lunarium.item.ItemLimbo;
import io.github.limepotato.lunarium.ElementsLunariumMod;

@ElementsLunariumMod.ModElement.Tag
public class TabLunariumTab extends ElementsLunariumMod.ModElement {
	public TabLunariumTab(ElementsLunariumMod instance) {
		super(instance, 7);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tablunarium_tab") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(ItemLimbo.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static CreativeTabs tab;
}
