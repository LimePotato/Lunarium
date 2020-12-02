
package io.github.limepotato.lunarium.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;

import java.util.Random;

import io.github.limepotato.lunarium.world.WorldLimbo;
import io.github.limepotato.lunarium.ElementsLunariumMod;

@ElementsLunariumMod.ModElement.Tag
public class BlockFluidUncreatedRealityFabric extends ElementsLunariumMod.ModElement {
	@GameRegistry.ObjectHolder("lunarium:fluid_uncreated_reality_fabric")
	public static final Block block = null;
	@GameRegistry.ObjectHolder("lunarium:fluid_uncreated_reality_fabric")
	public static final Item item = null;
	private Fluid fluid;
	public BlockFluidUncreatedRealityFabric(ElementsLunariumMod instance) {
		super(instance, 5);
		fluid = new Fluid("fluid_uncreated_reality_fabric", new ResourceLocation("lunarium:blocks/limboportal"),
				new ResourceLocation("lunarium:blocks/limboportal2")).setLuminosity(9).setDensity(1000).setViscosity(1000).setGaseous(true);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockFluidClassic(fluid, Material.WATER) {
		}.setUnlocalizedName("fluid_uncreated_reality_fabric").setRegistryName("fluid_uncreated_reality_fabric"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName("fluid_uncreated_reality_fabric"));
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		FluidRegistry.registerFluid(fluid);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		ModelBakery.registerItemVariants(item);
		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation("lunarium:fluid_uncreated_reality_fabric", "fluid_uncreated_reality_fabric");
			}
		});
		ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("lunarium:fluid_uncreated_reality_fabric", "fluid_uncreated_reality_fabric");
			}
		});
	}

	@Override
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		boolean dimensionCriteria = false;
		if (dimID == WorldLimbo.DIMID)
			dimensionCriteria = true;
		if (!dimensionCriteria)
			return;
		int i = chunkX + random.nextInt(16) + 8;
		int j = random.nextInt(256);
		int k = chunkZ + random.nextInt(16) + 8;
		new WorldGenLakes(block).generate(world, random, new BlockPos(i, j, k));
	}
}
