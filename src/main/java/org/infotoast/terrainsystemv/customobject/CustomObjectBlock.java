package org.infotoast.terrainsystemv.customobject;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.infotoast.terrainsystemv.math.Vector3;

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

public class CustomObjectBlock extends CustomObjectItem {
    private Material blockType;
    private Vector3 relativePos;
    private String data;

    /*
    * This is the class for both blocks AND tile entities.
    * Arguments:
    * Vector3 (the relative position of the block)
    * Material (The material of the block)
    * String data (The blockData associated with said block, e.g. [waterlogged=true]) Tile entities can use JSON strings following the []
     */

    public CustomObjectBlock(Vector3 relativePos1, Material blockType1) {
        relativePos = relativePos1;
        blockType = blockType1;
    }

    public CustomObjectBlock(Vector3 relativePos1, Material blockType1, String data1) {
        relativePos = relativePos1;
        blockType = blockType1;
        data = data1;
    }

    public CustomObjectBlock(int x, int y, int z, Material blockType1) {
        relativePos = new Vector3(x, y, z);
        blockType = blockType1;
    }

    public CustomObjectBlock(int x, int y, int z, Material blockType1, String data1) {
        relativePos = new Vector3(x, y, z);
        blockType = blockType1;
        data = data1;
    }

    public Vector3 getRelativePos() {
        return relativePos;
    }

    public Material getBlockType() {
        return blockType;
    }

    public String getBlockData() {
        return data;
    }

    public void spawn(byte rotation, Vector3 spawnStart, World world) {
        int y = spawnStart.getY() + relativePos.getY();
        int x;
        int z;
        switch (rotation) {
            case 0:
                x = spawnStart.getX() + relativePos.getX();
                z = spawnStart.getZ() + relativePos.getZ();
                break;
            case 1:
                x = spawnStart.getX() - relativePos.getX();
                z = spawnStart.getZ() + relativePos.getZ();
                break;
            case 2:
                x = spawnStart.getX() - relativePos.getX();
                z = spawnStart.getZ() - relativePos.getZ();
                break;
            default:
                x = spawnStart.getX() + relativePos.getX();
                z = spawnStart.getZ() - relativePos.getZ();
        }

        world.getBlockAt(x, y, z).setType(blockType);
        if (data != null) {
            Block b = world.getBlockAt(x, y, z);
            b.setBlockData(b.getBlockData().merge(blockType.createBlockData(data)));
        }
    }
}
