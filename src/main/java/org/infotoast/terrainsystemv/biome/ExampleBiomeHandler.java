package org.infotoast.terrainsystemv.biome;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;

import java.util.Random;

public class ExampleBiomeHandler implements BiomeHandler {

    /*
    * This class is an example and should not actually be used for anything in a working code.
    * It should be simple rolling grass hills.
     */

    @Override
    public boolean isOcean() {
        return false;
    }

    @Override
    public Biome getBiome() {
        return Biome.PLAINS;
    }

    @Override
    public Material[] getSurfaceCrust(Random rand) {
        return new Material[]{Material.GRASS_BLOCK, Material.DIRT, Material.DIRT, Material.DIRT};
    }

    @Override
    public Material populateSmallItems(Random rand) {
        return Material.AIR;
    }

    @Override
    public boolean hasErosion() {
        return false;
    }

    @Override
    public BiomeHandler getTransformHandler() {
        return null;
    }

    @Override
    public void populateLargeItems(World world, Random random, Chunk chunk) {
        // Nothing
    }
}
