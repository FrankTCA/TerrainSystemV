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
