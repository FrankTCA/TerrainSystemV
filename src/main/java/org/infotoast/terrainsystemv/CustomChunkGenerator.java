package org.infotoast.terrainsystemv;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import org.bukkit.*;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.infotoast.terrainsystemv.biome.BiomeBank;
import org.infotoast.terrainsystemv.biome.BiomeHandler;
import org.infotoast.terrainsystemv.biome.BiomePopulator;
import org.infotoast.terrainsystemv.biome.BiomeProviderOverride;
import org.infotoast.terrainsystemv.math.ChunkCache;
import org.infotoast.terrainsystemv.math.ChunkCacheLoader;
import org.infotoast.terrainsystemv.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/*
Copyright (c) 2021 by Frank
Developer of Terrain System V * https://infotoast.org
"Founder of Doofinschlatt, Lead Author of Info Toast, OpenTerrainGenerator Developer, and Terrain System V Developer"

All Rights Reserved
ATTRIBUTION ASSURANCE LICENSE (adapted from the original BSD license)
Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the conditions below are met.
These conditions require a modest attribution to Frank (the
"Author"), who hopes that its promotional value may help justify the
thousands of dollars in otherwise billable time invested in writing
this and other freely available, open-source software.

1. Redistributions of source code, in whole or part and with or without
modification (the "Code"), must prominently display this GPG-signed
text in verifiable form.
2. Redistributions of the Code in binary form must be accompanied by
this GPG-signed text in any documentation and, each time the resulting
executable program or a program dependent thereon is launched, a
prominent display (e.g., splash screen or banner text) of the Author's
attribution information, which includes:
(a) Name ("Frank"),
(b) Professional identification ("Developer of Terrain System V"), and
(c) URL ("https://infotoast.org").
3. Neither the name nor any trademark of the Author may be used to
endorse or promote products derived from this software without specific
prior written permission.
4. Users are entirely responsible, to the exclusion of the Author and
any other persons, for compliance with (1) regulations set by owners or
administrators of employed equipment, (2) licensing terms of any other
software, and (3) local regulations regarding use, including those
regarding import, export, and use of encryption software.

THIS FREE SOFTWARE IS PROVIDED BY THE AUTHOR "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
EVENT SHALL THE AUTHOR OR ANY CONTRIBUTOR BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
EFFECTS OF UNAUTHORIZED OR MALICIOUS NETWORK ACCESS;
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

public class CustomChunkGenerator extends ChunkGenerator {

    public static final int seaLevel = 61;

    public static final Object LOCK = new Object();

    public static ArrayList<Vector2> chunksToErode = new ArrayList<>();

    private static final LoadingCache<ChunkCache, ChunkCache> CHUNK_CACHE = CacheBuilder.newBuilder().maximumSize(1000).build(new ChunkCacheLoader());

    /*
    * This method is called by Spigot to generate the rough structure of the world
    * It calls HeightMap to determine the height of each block, then generates stone all the way down
    * Will not generate stone where crust will be. That way it can add the crust later
     */
    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunk) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int rawX = chunkX * 16 + x;
                int rawZ = chunkZ * 16 + z;
                WorldImproved worldi = WorldImproved.get(worldInfo);
                int currentHeight = HeightMap.getBlockHeight(worldi, rawX, rawZ);
                BiomeBank bank = BiomeBank.calculateBiome(worldi, rawX, rawZ, currentHeight);
                Material[] crust = bank.getHandler().getSurfaceCrust(random);
                int undergroundHeight = currentHeight - crust.length;

                for (int y = undergroundHeight; y > worldInfo.getMinHeight(); y--) {
                    setBlockSync(chunk, x, y, z, Material.STONE);
                }
            }
        }
    }

    /*
    * This method generates the surface crust of the world
    * It also marks if chunks are set to erode the water
    * It also generates water if it is beneath sea level.
    * This is also where transformTerrain is called for.
     */
    @Override
    public void generateSurface(WorldInfo world, Random random, int chunkX, int chunkZ, ChunkData chunk) {
        List<BiomeHandler> biomesToTransform = new ArrayList<>();
        boolean willChunkErode = false;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int rawX = chunkX * 16 + x;
                int rawZ = chunkZ * 16 + z;
                WorldImproved worldi = WorldImproved.get(world);
                int currentHeight = HeightMap.getBlockHeight(worldi, rawX, rawZ);
                BiomeBank bank = BiomeBank.calculateBiome(worldi, rawX, rawZ, currentHeight);
                Material[] crust = bank.getHandler().getSurfaceCrust(random);
                int undergroundHeight = currentHeight;
                int index = 0;
                while (index < crust.length) {
                    setBlockSync(chunk, x, undergroundHeight, z, crust[index]);
                    index++;
                    undergroundHeight--;
                }
                // Sea level
                for (int y = currentHeight + 1; y <= seaLevel; y++) {
                    setBlockSync(chunk, x, y, z, Material.WATER);
                }

                Material top = bank.getHandler().populateSmallItems(random);
                setBlockSync(chunk, x, currentHeight+1, z, top);
                BiomeHandler transformHandler = bank.getHandler().getTransformHandler();
                if (transformHandler != null && !biomesToTransform.contains(transformHandler))
                    biomesToTransform.add(transformHandler);
                if (bank.getHandler().hasErosion())
                    willChunkErode = true;
            }
        }
        for (BiomeHandler handler : biomesToTransform) {
            handler.transformTerrain(world, random, chunk, chunkX, chunkZ);
        }
        if (willChunkErode)
            chunksToErode.add(new Vector2(chunkX, chunkZ));
    }

    /*
    * The following code is commented out because it was our implementation prior to worldgen api changes in 1.17.
    * It does not do anything, and is preserved purely for history.
    * Please disregard it.
     */
    /*public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        generator.setScale(0.010D);
        List<BiomeHandler> biomesToTransform = new ArrayList<>();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int rawX = chunkX * 16 + x;
                int rawZ = chunkZ * 16 + z;
                WorldImproved worldi = WorldImproved.get(world);
                int currentHeight = HeightMap.getBlockHeight(worldi, rawX, rawZ);
                BiomeBank bank = BiomeBank.EXAMPLE_BIOME_FLAT(worldi, rawX, rawZ, currentHeight);
                Material[] crust = bank.getHandler().getSurfaceCrust(random);
                biome.setBiome(x, z, bank.getHandler().getBiome());
                int undergroundHeight = currentHeight;
                int index = 0;
                while (index < crust.length) {
                    setBlockSync(chunk, x, undergroundHeight, z, crust[index]);
                    index++;
                    undergroundHeight--;
                }

                for (int y = undergroundHeight; y > 0; y--) {
                    setBlockSync(chunk, x, y, z, Material.STONE);
                }

                // Sea level
                for (int y = currentHeight + 1; y <= seaLevel; y++) {
                    setBlockSync(chunk, x, y, z, Material.WATER);
                }

                setBlockSync(chunk, x, 0, z, Material.BEDROCK);

                Material top = bank.getHandler().populateSmallItems(random);
                setBlockSync(chunk, x, currentHeight+1, z, top);

                BiomeHandler transformHandler = bank.getHandler().getTransformHandler();
                if (transformHandler != null && !biomesToTransform.contains(transformHandler))
                    biomesToTransform.add(transformHandler);
            }
        }
        for (BiomeHandler handler : biomesToTransform) {
            handler.transformTerrain(world, random, chunk, chunkX, chunkZ);
        }
        return chunk;
    }*/

    /*
    * The following set of methods are amazing and beautiful.
    * Thanks to some changes in 1.17, all of the following can be handled by Spigot itself, meaning several aspects
    * of worldgen are now futureproof.
    *
    * Marvin Rieple, you're amazing and I love you.
     */
    @Override
    public boolean shouldGenerateBedrock() {
        return true;
    }

    @Override
    public boolean shouldGenerateDecorations() {
        return true;
    }

    @Override
    public boolean shouldGenerateCaves() {
        return true;
    }

    @Override
    public boolean shouldGenerateStructures() {
        return true;
    }

    @Override
    public boolean shouldGenerateMobs() {
        return true;
    }

    // This method defines the BiomeProvider
    @Override
    public BiomeProvider getDefaultBiomeProvider(WorldInfo world) {
        return new BiomeProviderOverride();
    }

    private void setBlockSync(ChunkData data, int x, int y, int z, Material material) {
        synchronized (LOCK) {
            data.setBlock(x, y, z, material);
        }
    }

    public static ChunkCache getCache(WorldImproved world, int x, int z) {
        ChunkCache cache = new ChunkCache(world, x, 0, z);
        try {
            return CHUNK_CACHE.get(cache);
        } catch (ExecutionException e) {
            e.printStackTrace();
            e.getCause().printStackTrace();
            cache.initInternalCache();
            return cache;
        }
    }

    /*
    * This method interfaces with the Spigot API and tells it what populators.
    * Currently, we only populate biomes and ores and let Spigot handle the rest.
    * You can add onto this method as you please.
     */
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList(new BiomePopulator(), new OrePopulator());
    }
}
