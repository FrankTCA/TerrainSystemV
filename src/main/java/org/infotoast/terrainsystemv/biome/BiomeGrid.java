package org.infotoast.terrainsystemv.biome;

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

public class BiomeGrid {

    /*
    * This is one of the more confusing classes here, so here's how it works
    * Each of these arrays are a 2d grid of humidity and temperature.
    * The first dimension is defined by humidity.
    * The more humidity, the further down the array it will be.
    * The second dimension is defined by temperature.
    * The higher the temperature, the further across the array inside the array will be.
    * Biomes will not spawn unless placed in this class so make sure you place them.
    * Biomes may be placed multiple times in this array.
    * It is recommended that more common biomes be placed multiple times.
     */

    private static final BiomeBank[][] terrestrialGrid = {
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT,
                    BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT,
                    BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT,
                    BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT,
                    BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT,
                    BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT,
                    BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT,
                    BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT,
                    BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT,
                    BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT,
                    BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT,
                    BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT, BiomeBank.EXAMPLE_BIOME_FLAT}
    };

    private static final BiomeBank[][] mountainousGrid = {
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS, BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS,
                    BiomeBank.EXAMPLE_BIOME_MOUNTAINOUS}
    };

    private static final BiomeBank[][] oceanicGrid = {
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC,
                    BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC,
                    BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC,
                    BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC,
                    BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC,
                    BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC,
                    BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC,
                    BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC,
                    BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC,
                    BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC,
                    BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC,
                    BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC, BiomeBank.EXAMPLE_BIOME_OCEANIC}
    };

    private static final BiomeBank[][] riverGrid = {
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER,
                    BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER,
                    BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER,
                    BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER,
                    BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER,
                    BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER,
                    BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER,
                    BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER,
                    BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER,
                    BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER,
                    BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER,
                    BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER, BiomeBank.EXAMPLE_BIOME_RIVER}
    };

    private static final BiomeBank[][] beachGrid = {
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH,
                    BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH,
                    BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH,
                    BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH,
                    BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH,
                    BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH,
                    BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH,
                    BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH,
                    BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH
                    , BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH
                    , BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH},
            new BiomeBank[]{BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH
                    , BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH, BiomeBank.EXAMPLE_BIOME_BEACH}
    };

    public static BiomeBank calculateBiome(BiomeType type, double temperature, double moisture) {
        return getBiome(type, (int) Math.round(normalize(temperature)), (int) Math.round(normalize(moisture)));
    }

    static BiomeBank getBiome(BiomeType type, int temperatureIndex, int moistureIndex) {
        if (type == BiomeType.FLAT) {
            return terrestrialGrid[moistureIndex][temperatureIndex];
        } else if (type == BiomeType.OCEANIC) {
            return oceanicGrid[moistureIndex][temperatureIndex];
        } else if (type == BiomeType.MOUNTAINOUS) {
            return mountainousGrid[moistureIndex][temperatureIndex];
        } else if (type == BiomeType.BEACH) {
            return beachGrid[moistureIndex][temperatureIndex];
        } else if (type == BiomeType.RIVER) {
            return riverGrid[moistureIndex][temperatureIndex];
        }

        return null;
    }

    public static double normalize(double i) {
        if (i > 2.5) i = 2.5;
        else if (i < -2.5) i = -2.5;

        i += 2.5;//Range 0 to 5
        i *= 2; //Range 0 to 10

        return i;
    }
}
