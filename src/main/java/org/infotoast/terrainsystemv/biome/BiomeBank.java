package org.infotoast.terrainsystemv.biome;

import org.bukkit.block.Biome;
import org.infotoast.terrainsystemv.HeightMap;
import org.infotoast.terrainsystemv.WorldImproved;
import org.infotoast.terrainsystemv.math.Utility;

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

public enum BiomeBank {
    /*
    * The following code is where to put each of your biomes.
    * They must be put in the grid area in BiomeGrid before they will spawn.
    * Mountainous biomes will spawn at high y levels
    *
    * Oceanic and deep oceanic will spawn below sea level
    * Rivers will spawn wherever rivers go
    * Beaches will spawn next to oceans and rivers
    * And Flat biomes spawn on flat land.
     */
    // Mountainous
    EXAMPLE_BIOME_MOUNTAINOUS(new ExampleBiomeHandler(), BiomeType.MOUNTAINOUS),
    // Oceanic
    EXAMPLE_BIOME_OCEANIC(new ExampleBiomeHandler(), BiomeType.OCEANIC),
    // River
    EXAMPLE_BIOME_RIVER(new ExampleBiomeHandler(), BiomeType.RIVER),
    // Deep Ocean
    EXAMPLE_BIOME_DEEP_OCEAN(new ExampleBiomeHandler(), BiomeType.DEEP_OCEANIC),
    // Flat
    EXAMPLE_BIOME_FLAT(new ExampleBiomeHandler(), BiomeType.FLAT),
    // Beaches
    EXAMPLE_BIOME_BEACH(new ExampleBiomeHandler(), BiomeType.BEACH),

    // Standard ocean placeholder.
    OCEAN(new ExampleBiomeHandler(), BiomeType.OCEANIC),
    // Swamp biome placeholder. Put swamp below:
    SWAMP(new ExampleBiomeHandler(), BiomeType.OCEANIC);

    public static final BiomeBank[] VALUES = values();
    private final BiomeHandler handler;
    private final BiomeType type;
    private static final int seaLevel = 61;
    BiomeBank(BiomeHandler handler, BiomeType type) {
        this.handler = handler;
        this.type = type;
    }

    public BiomeType getType() {
        return type;
    }

    public Biome getVanillaBiome() {
        return handler.getBiome();
    }

    public BiomeHandler getHandler() {
        return handler;
    }

    public static BiomeBank calculateBiome(WorldImproved world, int x, int z, int height) {
        double dither = 0.1d;
        double temperature = world.getTemperature(x, z);
        double moisture = world.getMoisture(x, z);
        Random rand = world.getHashedRand((int) (temperature * 10000), (int) (moisture * 10000), height);

        // Oceanic Biomes
        if (height < 61) {
            BiomeBank bank = BiomeGrid.calculateBiome(
                    BiomeType.OCEANIC,
                    temperature + rand.nextDouble() * (dither - -dither) + -dither,
                    moisture + rand.nextDouble() * (dither - -dither) + -dither
            );
            int trueHeight = (int) HeightMap.getRiverlessHeight(world, x, z);

            if (trueHeight >= seaLevel) {
                return BiomeGrid.calculateBiome(BiomeType.RIVER,
                        temperature + rand.nextDouble() * (dither - -dither) + -dither,
                        moisture + rand.nextDouble() * (dither - -dither) + -dither
                        );
            }

            if (bank == SWAMP) {
                if (height >= seaLevel - Utility.randInt(rand, 9, 11)) {
                    return SWAMP;
                } else {
                    bank = OCEAN;
                }
            }

            if (height <= 51) {
                bank = BiomeBank.valueOf("DEEP_" + bank);
            }
            return bank;
        }

        if (height >= 80 - Utility.randInt(rand, 0, 5)) {
            return BiomeGrid.calculateBiome(
                    BiomeType.MOUNTAINOUS,
                    temperature + rand.nextDouble() * (dither - -dither) + -dither,
                    moisture + rand.nextDouble() * (dither - -dither) + -dither
            );
        }

        if (height <= seaLevel + Utility.randInt(rand, 0, 4)) {
            return BiomeGrid.calculateBiome(
                    BiomeType.BEACH,
                    temperature + rand.nextDouble() * (dither - -dither) + -dither,
                    moisture + rand.nextDouble() * (dither - -dither) + -dither
            );
        }

        return BiomeGrid.calculateBiome(
                BiomeType.FLAT,
                temperature + rand.nextDouble() * (dither - -dither) + -dither,
                moisture + rand.nextDouble() * (dither - -dither) + -dither
        );
    }
}
