package com.example.modid;

import com.simibubi.create.Create;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	public static final String ID = "modid";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Create addon mod [{}] loading alongside Create [{}]!", ID, Create.VERSION);
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}
