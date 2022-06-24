# Create Fabric Addon Template

This template mod comes pre-configured for developing an addon mod for Create on Fabric.


## Setup

For environment setup instructions please see the [fabric wiki page](https://fabricmc.net/wiki/tutorial:setup) that relates to the
IDE that you are using.

Once done, this template needs a bit of setup before it's ready:
- change entries in the `fabric.mod.json` file
- rename all instances of `modid` in files and file names with your mod ID,
a unique identifier for your mod.
- replace the default icon
- change your mod package

# Features
Features of this template mod.
- Create dependency pre-configured
- GitHub Actions build workflow
- Quiltflower decompiler through [LoomQuiltflower](https://github.com/Juuxel/LoomQuiltflower) (use `genSourcesWithQuiltflower` instead of `genSources`)
- LazyDFU, ModMenu, and Suggestion Tweaker to ease development
- Mojang mappings (Mojmap)
- Access to all of Create and its dependencies
  - Porting Lib, Registrate, Flywheel, Forge Config API Port, and more

# Help
Questions? Join us in the #devchat channel of the [Create Discord](https://discord.com/invite/hmaD7Se).

## License

This template is available under the CC0 license. Feel free to do as you wish with it.
