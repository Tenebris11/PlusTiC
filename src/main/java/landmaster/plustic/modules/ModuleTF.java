package landmaster.plustic.modules;

import static slimeknights.tconstruct.library.materials.MaterialTypes.*;
import static slimeknights.tconstruct.library.utils.HarvestLevels.*;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

import landmaster.plustic.*;
import landmaster.plustic.config.*;
import landmaster.plustic.fluids.*;
import landmaster.plustic.traits.*;
import landmaster.plustic.util.*;
import net.minecraft.util.text.*;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.*;
import slimeknights.mantle.util.*;
import slimeknights.tconstruct.library.*;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.smeltery.*;
import slimeknights.tconstruct.library.traits.*;
import slimeknights.tconstruct.shared.*;

public class ModuleTF implements IModule {

	public void init() {
		if ((Config.thermalFoundation && Loader.isModLoaded("thermalfoundation"))
				|| (Config.substratum && Loader.isModLoaded("substratum"))) {
			// START MATERIALS
			
			Material lumium = new Material("lumium_plustic", TextFormatting.YELLOW);
			lumium.addTrait(Illuminati.illuminati);
			new ModifierRegisterPromise("glowing").thenAccept(modifier -> lumium.addTrait((ITrait)modifier)); // since Glowing is also a Trait
			lumium.addItem("ingotLumium", 1, Material.VALUE_Ingot);
			lumium.setCraftable(false).setCastable(true);
			Utils.setDispItem(lumium, "ingotLumium");
			PlusTiC.proxy.setRenderInfo(lumium, 0xFFFF7F);
			
			FluidMolten lumiumFluid = Utils.fluidMetal("lumium", 0xFFFF7F);
			lumiumFluid.setTemperature(1000);
			Utils.initFluidMetal(lumiumFluid);
			lumium.setFluid(lumiumFluid);
			
			TinkerRegistry.addMaterialStats(lumium,
					new HeadMaterialStats(830, 7f, 6.5f, COBALT),
					new HandleMaterialStats(1.1f, 40),
					new ExtraMaterialStats(60),
					new BowMaterialStats(1.5f, 1.8f, 4));
			PlusTiC.materials.put("lumium", lumium);
			
			Material signalum = new Material("signalum_plustic", TextFormatting.RED);
			signalum.addTrait(BloodyMary.bloodymary);
			signalum.addItem("ingotSignalum", 1, Material.VALUE_Ingot);
			signalum.setCraftable(false).setCastable(true);
			Utils.setDispItem(signalum, "ingotSignalum");
			PlusTiC.proxy.setRenderInfo(signalum, 0xD84100);
			
			FluidMolten signalumFluid = Utils.fluidMetal("signalum", 0xD84100);
			signalumFluid.setTemperature(930);
			Utils.initFluidMetal(signalumFluid);
			signalum.setFluid(signalumFluid);
			
			TinkerRegistry.addMaterialStats(signalum, new HeadMaterialStats(690, 7.5f, 5.2f, OBSIDIAN),
					new HandleMaterialStats(1.2f, 0), new ExtraMaterialStats(55),
					new BowMaterialStats(1.2f, 1.6f, 4.4f));
			PlusTiC.materials.put("signalum", signalum);
			
			Material platinum = new Material("platinum_plustic", TextFormatting.BLUE);
			platinum.addTrait(Global.global, HEAD);
			platinum.addTrait(Heavy.heavy);
			platinum.addTrait(Anticorrosion.anticorrosion);
			platinum.addItem("ingotPlatinum", 1, Material.VALUE_Ingot);
			platinum.setCraftable(false).setCastable(true);
			Utils.setDispItem(platinum, "ingotPlatinum");
			PlusTiC.proxy.setRenderInfo(platinum, 0xB7E7FF);
			
			FluidMolten platinumFluid = Utils.fluidMetal("platinum", 0xB7E7FF);
			platinumFluid.setTemperature(680);
			Utils.initFluidMetal(platinumFluid);
			platinum.setFluid(platinumFluid);
			
			TinkerRegistry.addMaterialStats(platinum, new HeadMaterialStats(720, 8, 6, COBALT),
					new HandleMaterialStats(1.05f, -5),
					new ExtraMaterialStats(60),
					new BowMaterialStats(0.85f, 1.8f, 8));
			
			PlusTiC.materials.put("platinum", platinum);
			
			Material enderium = new Material("enderium_plustic", TextFormatting.DARK_GREEN);
			enderium.addTrait(Portly.portly, HEAD);
			enderium.addTrait(Global.global);
			enderium.addTrait(enderference);
			enderium.addTrait(endspeed, PROJECTILE);
			enderium.addTrait(endspeed, SHAFT);
			enderium.addItem("ingotEnderium", 1, Material.VALUE_Ingot);
			enderium.setCraftable(false).setCastable(true);
			Utils.setDispItem(enderium, "ingotEnderium");
			PlusTiC.proxy.setRenderInfo(enderium, 0x007068);
			
			FluidMolten enderiumFluid = Utils.fluidMetal("enderium", 0x007068);
			enderiumFluid.setTemperature(970);
			Utils.initFluidMetal(enderiumFluid);
			enderium.setFluid(enderiumFluid);
			
			TinkerRegistry.addMaterialStats(enderium,
					new HeadMaterialStats(800, 7.5f, 7, COBALT),
					new HandleMaterialStats(1.05f, -5),
					new ExtraMaterialStats(65),
					new BowMaterialStats(0.9f, 1.9f, 8),
					new ArrowShaftMaterialStats(1, 12));
			
			PlusTiC.materials.put("enderium", enderium);
		}
	}
	
	@Override
	public void init2() {
		if (Config.thermalFoundation && Loader.isModLoaded("thermalfoundation")
				&& Config.pyrotheumSmelt) {
			// SMELTERY FUEL
			Fluid pyrotheum = FluidRegistry.getFluid("pyrotheum");
			TinkerRegistry.registerSmelteryFuel(new FluidStack(pyrotheum, 50), 400);
		}
		if ((Config.thermalFoundation && Loader.isModLoaded("thermalfoundation"))
				|| (Config.substratum && Loader.isModLoaded("substratum"))) {
			// FETCH FLUIDS
			Fluid redstoneFluid = FluidRegistry.getFluid(Loader.isModLoaded("thermalfoundation") ? "redstone" : "liquidredstone");
			Fluid enderFluid = FluidRegistry.getFluid(Loader.isModLoaded("thermalfoundation") ? "ender" : "liquidenderpearl");
			Fluid glowstoneFluid = FluidRegistry.getFluid(Loader.isModLoaded("thermalfoundation") ? "glowstone" : "liquidglowstone");

			TinkerRegistry.registerAlloy(new FluidStack(FluidRegistry.getFluid("lumium"), 72),
					new FluidStack(TinkerFluids.tin, 54),
					new FluidStack(TinkerFluids.silver, 18),
					new FluidStack(glowstoneFluid, 125));
			

			TinkerRegistry.registerAlloy(new FluidStack(FluidRegistry.getFluid("signalum"), 72),
					new FluidStack(TinkerFluids.copper, 54),
					new FluidStack(TinkerFluids.silver, 18),
					new FluidStack(redstoneFluid, 125));
			

			TinkerRegistry.registerAlloy(new FluidStack(FluidRegistry.getFluid("enderium"), 72),
					new FluidStack(TinkerFluids.lead, 54),
					new FluidStack(FluidRegistry.getFluid("platinum"), 18),
					new FluidStack(enderFluid, 125));
			
			if (Config.thermalFoundation && Loader.isModLoaded("thermalfoundation") && Config.tfMelt) {
				TinkerRegistry.registerMelting(new MeltingRecipe(new RecipeMatch.Oredict("dustRedstone", 1, 100), redstoneFluid, 1020));
				TinkerRegistry.registerMelting(new MeltingRecipe(new RecipeMatch.Oredict("blockRedstone", 1, 900), redstoneFluid, 1020));
				TinkerRegistry.registerMelting(new MeltingRecipe(new RecipeMatch.Oredict("dustGlowstone", 1, 250), glowstoneFluid, 1020));
				TinkerRegistry.registerMelting(new MeltingRecipe(new RecipeMatch.Oredict("glowstone", 1, 1000), glowstoneFluid, 1020));
				TinkerRegistry.registerMelting(new MeltingRecipe(new RecipeMatch.Oredict("enderpearl", 1, 250), enderFluid, 1020));
				
				TinkerRegistry.registerMelting("oreFluidRedstone", redstoneFluid, 1000);
				TinkerRegistry.registerMelting("oreFluidGlowstone", glowstoneFluid, 1000);
				TinkerRegistry.registerMelting("oreFluidEnder", enderFluid, 1000);
				
				TinkerRegistry.registerMelting("crystalRedstone", redstoneFluid, 250);
				TinkerRegistry.registerMelting("crystalGlowstone", glowstoneFluid, 250);
				TinkerRegistry.registerMelting("crystalEnder", enderFluid, 250);
			}
		}
	}
	
}
