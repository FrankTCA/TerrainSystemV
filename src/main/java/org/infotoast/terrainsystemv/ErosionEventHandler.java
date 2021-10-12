package org.infotoast.terrainsystemv;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.infotoast.terrainsystemv.math.Vector2;

import java.util.ArrayList;

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

public class ErosionEventHandler implements Listener {

    /*
    * This is the class that handles the Erosion operations.
    * The class will wait EROSION_TICKS, and then will attempt to remove blocks around water sources.
    * Right now, erosion is somewhat glitchy, as well as slightly laggy
    * However, it's better than everything else that can be done with the world generation by leagues.
     */

    // Number of times erosion task runs
    private int EROSION_ITERATIONS = 1;
    // Amount of ticks between async erosion cycles
    private long EROSION_TICKS = 500;

    @EventHandler(priority=EventPriority.LOW)
    public void onChunkGen(ChunkPopulateEvent evt) {
        Chunk chunk = evt.getChunk();
        if (!(chunk.getWorld().getGenerator() instanceof CustomChunkGenerator))
            return;
        if (CustomChunkGenerator.chunksToErode.contains(new Vector2(chunk.getX(), chunk.getZ()))) {
            Bukkit.getScheduler().runTaskLater(TerrainSystemV.plugin, new ErosionActor(chunk, EROSION_ITERATIONS), EROSION_TICKS);
            CustomChunkGenerator.chunksToErode.remove(new Vector2(chunk.getX(), chunk.getZ()));
        }
    }

    private class ErosionActor implements Runnable {
        private Chunk chunk;
        private int iterationsLeft;
        public ErosionActor(Chunk cnk, int timesLeft) {
            chunk = cnk;
            iterationsLeft = timesLeft;
        }

        @Override
        public void run() {
            ArrayList<Material> blocksToErode = new ArrayList<>();
            // START Place all blocks you want to be eroded here, for our example we'll use dirt, grass, and stone
            blocksToErode.add(Material.GRASS_BLOCK);
            blocksToErode.add(Material.DIRT);
            blocksToErode.add(Material.STONE);
            blocksToErode.add(Material.GRAVEL);
            blocksToErode.add(Material.DIORITE);
            blocksToErode.add(Material.GRANITE);
            blocksToErode.add(Material.ANDESITE);
            blocksToErode.add(Material.TERRACOTTA);
            blocksToErode.add(Material.SAND);
            blocksToErode.add(Material.SANDSTONE);
            blocksToErode.add(Material.WHITE_TERRACOTTA);
            blocksToErode.add(Material.RED_SAND);
            blocksToErode.add(Material.RED_SANDSTONE);
            // END Blocks to erode
            World world = chunk.getWorld();
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = chunk.getWorld().getMinHeight()+1; y < chunk.getWorld().getMaxHeight(); y++) {
                        if (chunk.getBlock(x, y, z).getType().equals(Material.WATER)) {
                            int rawX = (chunk.getX() << 4) + x;
                            int rawZ = (chunk.getZ() << 4) + z;
                            if (blocksToErode.contains(world.getBlockAt(rawX, y-1, rawZ).getType())) {
                                if (world.getBlockAt(rawX, y, rawZ).getBlockData().getAsString().contains("level=0")) {
                                    if (blocksToErode.contains(world.getBlockAt(rawX, y-2, rawZ).getType())) {
                                        world.getBlockAt(rawX, y-1, rawZ).setType(Material.WATER);
                                    }
                                } else {
                                    world.getBlockAt(rawX, y-1, rawZ).setType(Material.AIR);
                                }
                                world.getBlockAt(rawX, y, rawZ).setType(Material.AIR);
                                if (blocksToErode.contains(world.getBlockAt(rawX+1, y, rawZ).getType()))
                                    world.getBlockAt(rawX+1, y, rawZ).setType(Material.AIR);
                                if (blocksToErode.contains(world.getBlockAt(rawX-1, y, rawZ).getType()))
                                    world.getBlockAt(rawX-1, y, rawZ).setType(Material.AIR);
                                if (blocksToErode.contains(world.getBlockAt(rawX, y, rawZ+1).getType()))
                                    world.getBlockAt(rawX, y, rawZ+1).setType(Material.AIR);
                                if (blocksToErode.contains(world.getBlockAt(rawX, y, rawZ-1).getType()))
                                    world.getBlockAt(rawX, y, rawZ-1).setType(Material.AIR);
                            } else {
                                if (blocksToErode.contains(world.getBlockAt(rawX+1, y, rawZ).getType()))
                                    world.getBlockAt(rawX+1, y, rawZ).setType(Material.WATER);
                                if (blocksToErode.contains(world.getBlockAt(rawX-1, y, rawZ).getType()))
                                    world.getBlockAt(rawX-1, y, rawZ).setType(Material.WATER);
                                if (blocksToErode.contains(world.getBlockAt(rawX, y, rawZ+1).getType()))
                                    world.getBlockAt(rawX, y, rawZ+1).setType(Material.WATER);
                                if (blocksToErode.contains(world.getBlockAt(rawX, y, rawZ-1).getType()))
                                    world.getBlockAt(rawX, y, rawZ-1).setType(Material.WATER);
                            }
                        }
                    }
                }
            }
            if (iterationsLeft-1 > 0)
                Bukkit.getScheduler().runTaskLater(TerrainSystemV.plugin, new ErosionActor(chunk, iterationsLeft-1), EROSION_TICKS);
        }
    }
}
