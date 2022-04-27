package org.infotoast.terrainsystemv;

import org.bukkit.Material;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;

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

public class OrePopulator extends BlockPopulator {

    private static final Object LOCK = new Object();

    /*
    * This is the code that populates ores.
    * The placeOreType() method actually places the ores
    * The populate() method calls the placeOreType() method for each of the items that spawn.
    * You can add or remove ores from the populate() method as you please.
    * They do not have to be naturally spawning blocks, they can be anything.
     */

    private void placeOreType(Random random, LimitedRegion region, int chunkX, int chunkZ, Material type, int chance, int maxSize, int maxVeinNum, int minRange, int maxRange, int rareMaxRange) {
        int x, y, z;
        for (int i = 0; i < maxVeinNum; i++) {
            if (random.nextInt(100) < chance) {
                x = random.nextInt(15);
                z = random.nextInt(15);
                int rawX = (chunkX << 4) + x;
                int rawZ = (chunkZ << 4) + z;

                int range = maxRange;

                if (random.nextInt(50) == 0) range = rareMaxRange;

                y = random.nextInt(range - minRange) + minRange;

                for (int s = 0; s < maxSize; s++){

                    Material t = region.getType(rawX, y, rawZ);
                    if (t != Material.STONE) break;
                    region.setType(rawX, y, rawZ, type);

                    switch (random.nextInt(5)) {
                        case 0:
                            x++;
                            rawX++;
                            break;
                        case 1:
                            y++;
                            break;
                        case 2:
                            z++;
                            rawZ++;
                            break;
                        case 3:
                            x--;
                            rawX--;
                            break;
                        case 4:
                            y--;
                            break;
                        case 5:
                            z--;
                            rawZ--;
                            break;
                    }
                }
            }
        }
    }


    public void populate(WorldInfo worldInfo, Random random, int x, int z, LimitedRegion region) {
        placeOreType(random, region, x, z, Material.DIRT, 50, 20, 3, 2, 70, 255);
        placeOreType(random, region, x, z, Material.GRAVEL, 50, 20, 3, 2, 70, 255);
        placeOreType(random, region, x, z, Material.GRANITE, 50, 20, 3, 2, 70, 255);
        placeOreType(random, region, x, z, Material.DIORITE, 50, 20, 3, 2, 70, 255);
        placeOreType(random, region, x, z, Material.ANDESITE, 50, 20, 3, 2, 70, 255);
        placeOreType(random, region, x, z, Material.COAL_ORE, 50, 20, 7, 16, 70, 255);
        placeOreType(random, region, x, z, Material.IRON_ORE, 40, 15, 5, 2, 51, 60);
        placeOreType(random, region, x, z, Material.GOLD_ORE, 30, 10, 5, 2, 30, 50);
        placeOreType(random, region, x, z, Material.REDSTONE_ORE, 30, 8, 5, 2, 15, 30);
        placeOreType(random, region, x, z, Material.LAPIS_ORE, 30, 5, 3, 2, 30, 50);
        placeOreType(random, region, x, z, Material.DIAMOND_ORE, 100, 8, 1, 2, 15, 30);
    }
}


