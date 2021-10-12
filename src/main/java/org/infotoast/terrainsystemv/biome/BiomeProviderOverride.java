package org.infotoast.terrainsystemv.biome;

import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;
import org.infotoast.terrainsystemv.HeightMap;
import org.infotoast.terrainsystemv.WorldImproved;

import java.util.ArrayList;
import java.util.List;

public class BiomeProviderOverride extends BiomeProvider {

    /*
    * This class interfaces with the Spigot API and provides the biome for each block.
    * May need to be changed for 1.18, but I'm not sure yet.
    * The getBiome() method returns the biome of each block.
    * The getBiomes() method returns all possible values.
    * As this is an API that is supposed to be extensive, we fed the Spigot API all possible vanilla biomes.
    * It will not take BiomeType.CUSTOM.
     */

    @Override
    public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
        WorldImproved world = WorldImproved.get(worldInfo);
        int height = HeightMap.getBlockHeight(world, x, z);
        BiomeBank bank = BiomeBank.calculateBiome(world, x, z, height);
        return bank.getVanillaBiome();
    }

    @Override
    public List<Biome> getBiomes(WorldInfo worldInfo) {
        ArrayList<Biome> values = new ArrayList<>();
        for (Biome b : Biome.values()) {
            if (b.equals(Biome.CUSTOM)) {
                continue;
            }
            values.add(b);
        }
        return values;
    }
}
