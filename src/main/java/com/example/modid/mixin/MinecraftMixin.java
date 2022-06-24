package com.example.modid.mixin;

import com.example.modid.ExampleMod;

import net.minecraft.client.Minecraft;

import net.minecraft.client.main.GameConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Inject(method = "<init>", at = @At("TAIL"))
	private void example$init(GameConfig gameConfig, CallbackInfo ci) {
		ExampleMod.LOGGER.info("Hello from the Create Fabric Addon Template!");
		ExampleMod.LOGGER.info("In case the creator of this mod forgets to remove this example code, here's");
		ExampleMod.LOGGER.info("the path to their main class. Hopefully you can track down the culprit using it: [{}]", ExampleMod.class.getName());
	}
}
