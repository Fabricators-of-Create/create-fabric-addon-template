# Create Fabric Addon Template

This template mod comes pre-configured for developing an addon mod for Create on Fabric.


## Setup

This template is an extension of the base [Fabric Example Mod](https://github.com/FabricMC/fabric-example-mod).
Setup is mostly the same.

Additionally, set `recipe_viewer` in [gradle.properties](gradle.properties). Remember to remove unused
example code. Make sure versions are up-to-date.

When you publish your mod, you should use jars provided by GitHub Actions. These jars will have
build number metadata and will be compressed by the Machete plugin.

## Features
- Access to Create and all of its dependencies
- Mojang Mappings base, with Quilt Mappings and Parchment providing Javadoc and parameters
- VineFlower decompiler for high quality Minecraft sources: `gradlew genSourcesWithVineflower`
- GitHub Actions automatic build workflow
- Machete Gradle plugin to shrink jar file sizes
- Developer QOL: Mod Menu, LazyDFU, recipe viewers

## Other Templates
- [Multi-loader template](https://github.com/Fabricators-of-Create/create-multiloader-addon-template)
- [Forge-only template](https://github.com/kotakotik22/CreateAddonTemplate)

## Help
Questions? Join us in the #devchat channel of the [Create Discord](https://discord.com/invite/hmaD7Se).

## License

This template is available under the CC0 license. Feel free to do as you wish with it.
