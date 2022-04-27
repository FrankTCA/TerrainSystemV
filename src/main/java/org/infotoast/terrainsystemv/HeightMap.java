package org.infotoast.terrainsystemv;

import org.infotoast.terrainsystemv.math.ChunkCache;
import org.infotoast.terrainsystemv.math.FastNoise;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;

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

public enum HeightMap {

    /*
    * This is the fun class that determines the height of blocks.
    * I will attempt to explain this process as best I can, but some stuff may be left out, and you're best off reading the code.
    *
    * - Core height is calculated initially from CubicFractal noise.
    * - Attrition noise is then used to smooth out the edges slightly
    * - Mountain noise is then used, but is insignificant except in cases where there is an actual mountain
    * - The top edges are smoothed off, as the worldgen can and will extend mountains much beyond 1024 blocks high.
    * - Ocean noise is then applied where applicable
    * - Finally, river noise is applied where rivers are applicable
    * - It is then multiplied by heightMultiplier (go on, play with it) and then that is the final height that is used.
    *
    * NOTE: Attrition noise appears to break down just barely at distances that are EXTREMELY far away
    * (Earliest signs of breakage are at 12,550,821, but it becomes progressively more extreme closer to 16,777,216)
    * Although this is a bug, I decided that it is better off left in the code, as it is unlikely
    * anyone would genuinely be playing at these distances.
    * The game is still playable at these distances and is far from the overflows that occured prior
    * to Beta 1.8 at 12,550,821
    * (The land looks a little blocky, that's all)
     */

    RIVER {
        @Override
        public double getHeight(WorldImproved world, int x, int z) {
            FastNoise noi = computeNoise(world, w -> {
                FastNoise n = new FastNoise();
                n.SetSeed((int) world.getSeed());
                n.SetNoiseType(FastNoise.NoiseType.PerlinFractal);
                n.SetFrequency(0.005f);
                n.SetFractalOctaves(5);
                return n;
            });
            return 15 - 100 * Math.abs(noi.GetNoise(x, z));
        }
    }, OCEANIC {
        @Override
        public double getHeight(WorldImproved world, int x, int z) {
            FastNoise cubic = computeNoise(world, w -> {
                FastNoise n = new FastNoise((int) world.getSeed() * 12);
                n.SetNoiseType(FastNoise.NoiseType.CubicFractal);
                n.SetFractalOctaves(6);
                n.SetFrequency(0.0007f);
                return n;
            });
            double height = cubic.GetNoise(x, z) * 2.5;

            if (height > 0) height = 0;
            return height * 50;
        }
    }, CORE {
        @Override
        public double getHeight(WorldImproved world, int x, int z) {
            FastNoise cubic = computeNoise(world, w -> {
                FastNoise n = new FastNoise((int) world.getSeed());
                n.SetNoiseType(FastNoise.NoiseType.CubicFractal);
                n.SetFractalOctaves(6);
                n.SetFrequency(0.003f);
                return n;
            });

            double height = cubic.GetNoise(x, z) * 2 * 15 + 13 + seaLevel;

            if (height > seaLevel + 10) height = (height - seaLevel - 10) * 0.1 + seaLevel + 10;
            if (height < seaLevel - 30) height = -(seaLevel - 30 - height) * 0.1 + seaLevel - 30;

            return height;
        }
    }, MOUNTAIN {
        @Override
        public double getHeight(WorldImproved world, int x, int z) {
            FastNoise cubic = computeNoise(world, w -> {
                FastNoise n = new FastNoise((int) world.getSeed() * 7);
                n.SetNoiseType(FastNoise.NoiseType.CubicFractal);
                n.SetFractalOctaves(6);
                n.SetFrequency(0.002f);
                return n;
            });

            double height = cubic.GetNoise(x, z) * 5;
            if (height < 0) height = 0;
            return Math.pow(height, 5) * 5;
        }
    }, ATTRITION {
        @Override
        public double getHeight(WorldImproved world, int x, int z) {
            FastNoise perlin = computeNoise(world, w -> {
                FastNoise n = new FastNoise((int) world.getSeed());
                n.SetNoiseType(FastNoise.NoiseType.PerlinFractal);
                n.SetFractalOctaves(4);
                n.SetFrequency(0.02f);
                return n;
            });
            double height = perlin.GetNoise(x, z) * 14;
            return height < 0 ? 0 : height;
        }
    };
    private static final int seaLevel = 61;
    private static final float heightAmplifier = 1.0f;
    private final Map<WorldImproved, FastNoise> noiseCache = Collections.synchronizedMap(new IdentityHashMap<>(30000));
    protected FastNoise computeNoise(WorldImproved world, Function<WorldImproved, FastNoise> func) {
        synchronized (noiseCache) {
            return noiseCache.computeIfAbsent(world, func);
        }
    }

    public static double getNoiseGradient(WorldImproved world, int x, int z, int radius) {
        double totalChangeInGradient = 0;
        int count = 0;
        double centerNoise = getBlockHeight(world, x, z);
        for (int nx = -radius; nx <= radius; nx++) {
            for (int nz = -radius; nz <= radius; nz++) {
                if (nx == 0 && nz == 0) continue;
                totalChangeInGradient += Math.abs(getBlockHeight(world, x + nx, z + nz) - centerNoise);
                count++;
            }
        }
        return totalChangeInGradient / count;
    }

    // Gets the height without rivers
    public static double getRiverlessHeight(WorldImproved tw, int x, int z) {
        double height = HeightMap.CORE.getHeight(tw, x, z);

        if (height > seaLevel + 4) {
            height += HeightMap.ATTRITION.getHeight(tw, x, z);
        } else {
            height += HeightMap.ATTRITION.getHeight(tw, x, z) * 0.8;
        }

        //double oldHeight = height;
        if (height > seaLevel + 4) {
            height += HeightMap.MOUNTAIN.getHeight(tw, x, z);
        } else {
            float frac = (float) height / (float) (seaLevel + 4);
            height += HeightMap.MOUNTAIN.getHeight(tw, x, z) * (frac);
        }

        /*
        * Height multipliers
        * For 1.18.2, the height has been increased,
        * meaning we can attempt to make mountains etc more drastic
         */
        if (80 < height && height <= 100)
            height = 80 + (height - 80) * 1.2;
        if (100 < height && height <= 150)
            height = 100 + (height - 100) * 1.3;
        if (150 < height && height <= 200)
            height = 150 + (height - 150) * 1.4;
        if (200 < height && height <= 275)
            height = 200 + (height - 200) * 1.5;
        if (275 < height)
            height = 275 + (height - 275) * 0.5;
        if (295 < height)
            height = 295 + (height - 295) * 0.3;
        if (305 < height)
            height = 305 + (height - 305) * 0.1;
        if (315 < height)
            height = 315 + (height - 315) * 0.05;

        return (height + HeightMap.OCEANIC.getHeight(tw, x, z)) * heightAmplifier;
    }

    // Gets the height with rivers
    public static double getPreciseHeight(WorldImproved world, int x, int z) {
        ChunkCache cache = CustomChunkGenerator.getCache(world, x, z);
        double cachedValue = cache.getHeight(x, z);
        if (cachedValue != 0) return cachedValue;
        double height = HeightMap.CORE.getHeight(world, x, z);

        if (height > seaLevel + 4) {
            height += HeightMap.ATTRITION.getHeight(world, x, z);
        } else {
            height += HeightMap.ATTRITION.getHeight(world, x, z) * 0.8;
        }

        if (height > seaLevel + 4) {
            height += HeightMap.MOUNTAIN.getHeight(world, x, z);
        } else {
            float frac = (float) height / (float) (seaLevel + 4);
            height += HeightMap.MOUNTAIN.getHeight(world, x, z) * (frac);
        }

        if (80 < height)
            height = 80 + (height - 80) * 1.2;
        if (100 < height)
            height = 100 + (height - 100) * 1.1;
        if (150 < height)
            height = 150 + (height - 150) * 1.2;
        if (200 < height)
            height = 200 + (height - 200) * 1.2;
        if (275 < height)
            height = 275 + (height - 275) * 0.5;
        if (295 < height)
            height = 295 + (height - 295) * 0.3;
        if (305 < height)
            height = 305 + (height - 305) * 0.1;
        if (315 < height)
            height = 315 + (height - 315) * 0.05;

        // Oceans
        height += HeightMap.OCEANIC.getHeight(world, x, z);

        // River Depth
        double depth = HeightMap.RIVER.getHeight(world, x, z);
        depth = depth < 0 ? 0 : depth;

        // Shallow
        if (height - depth >= seaLevel - 15) {
            height -= depth;
        } else if (height > seaLevel - 15 && height - depth < seaLevel - 15) {
            height = seaLevel - 15;
        }

        cache.cacheHeight(x, z, height*heightAmplifier);
        return height * heightAmplifier;
    }

    // Gets the highest block (e.g. not a float)
    public static int getBlockHeight(WorldImproved world, int x, int z) {
        return (int) getPreciseHeight(world, x, z);
    }

    public abstract double getHeight(WorldImproved world, int x, int z);
}
