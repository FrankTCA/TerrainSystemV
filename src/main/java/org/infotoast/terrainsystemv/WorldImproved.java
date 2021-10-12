package org.infotoast.terrainsystemv;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.generator.WorldInfo;
import org.infotoast.terrainsystemv.biome.BiomeBank;
import org.infotoast.terrainsystemv.math.ChunkCache;
import org.infotoast.terrainsystemv.math.FastNoise;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

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

public class WorldImproved {
    public static final HashMap<String, WorldImproved> WORLDS = new HashMap<>();
    private final String worldName;
    private final long seed;
    private transient FastNoise tempOctave;
    private transient FastNoise moistureOctave;

    /*
    * This class is for a number of uses.
    * One of the main uses it can be used for is converting spigots (ugly) new WorldInfo class back to the old World class.
    * It is also used for the biome moisture and temperature maps.
    * It will save each world it is given for later use, to keep track of multiworld situations.
     */

    public WorldImproved(String name, long seed) {
        this.worldName = name;
        this.seed = seed;
    }

    // TODO: Stop using world fully
    public WorldImproved(World world) {
        worldName = world.getName();
        seed = world.getSeed();
    }

    public WorldImproved(WorldInfo world) {
        worldName = world.getName();
        seed = world.getSeed();
    }

    // The following set of methods are what you should use to actually initialize the WorldImproved class
    public static WorldImproved get(World world) {
        return WORLDS.computeIfAbsent(world.getName(), (k) -> new WorldImproved(world));
    }

    public static WorldImproved get(WorldInfo world) {
        return WORLDS.computeIfAbsent(world.getName(), (k) -> new WorldImproved(world));
    }

    public static WorldImproved get(String name, long seed) {
        return WORLDS.computeIfAbsent(name, (k) -> new WorldImproved(name, seed));
    }

    public long getSeed() {
        return seed;
    }

    // Gets the FastNoise object for temperature
    private FastNoise getTemperatureOctave() {
        if (tempOctave == null) {
            tempOctave = new FastNoise((int) (seed * 2));
            tempOctave.SetNoiseType(FastNoise.NoiseType.ValueFractal);
            tempOctave.SetFractalOctaves(6);
            tempOctave.SetFrequency(0.0007f);
        }
        return tempOctave;
    }

    // Gets the FastNoise object for moisture
    private FastNoise getMoistureOctave() {
        if (moistureOctave == null) {
            moistureOctave = new FastNoise((int) (seed * 3));
            moistureOctave.SetNoiseType(FastNoise.NoiseType.ValueFractal);
            moistureOctave.SetFractalOctaves(6);
            moistureOctave.SetFrequency(0.0007f);
        }
        return moistureOctave;
    }

    public Random getRand(long d) {
        return new Random(seed * d);
    }

    public Random getHashedRand(long x, int y, int z) {
        return new Random(Objects.hash(seed, x, y, z));
    }

    public Random getHashedRand(int x, int y, int z, long multiplier) {
        return new Random(Objects.hash(seed, x, y, z) * multiplier);
    }

    public BiomeBank getBiomeBank(int x, int z) {
        ChunkCache cache = CustomChunkGenerator.getCache(this, x, z);
        BiomeBank cachedValue = cache.getBiome(x, z);
        if (cachedValue != null) return cachedValue;
        return cache.cacheBiome(x, z, BiomeBank.calculateBiome(this, x, z, HeightMap.getBlockHeight(this, x, z)));
    }

    // Gets the temperature of a certain location so biome may be determined
    public double getTemperature(int x, int z) {
        return getTemperatureOctave().GetNoise(x, z) * 6;
    }

    // Gets the moisture of a certain location so biome may be determined
    public double getMoisture(int x, int z) {
        return getMoistureOctave().GetNoise(x, z) * 6;
    }

    // Gets the world name
    public String getName() {
        return worldName;
    }

    // Gets the World object, useful for converting the yucky WorldInfo objects
    public World getWorld() {
        return Bukkit.getWorld(worldName);
    }
}
