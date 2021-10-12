package org.infotoast.terrainsystemv.customobject;

import de.tr7zw.changeme.nbtapi.NBTContainer;
import de.tr7zw.changeme.nbtapi.NBTEntity;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.infotoast.terrainsystemv.math.Vector3;
import org.infotoast.terrainsystemv.math.Vector3f;

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

public class CustomObjectEntity extends CustomObjectItem {
    private EntityType entType;
    private Vector3f relativePos;
    private NBTContainer data;

    /*
    * CustomObjectItem for spawning entities
    * Parameters:
    * Vector3f (the relative coordinates of the entity)
    * EntityType (what type of entity it is)
    * String (JSON data of the entity)
     */

    public CustomObjectEntity(Vector3f relativePos1, EntityType entType1) {
        entType = entType1;
        relativePos = relativePos1;
    }

    public CustomObjectEntity(Vector3f relativePos1, EntityType entType1, String dataJson) {
        entType = entType1;
        relativePos = relativePos1;
        data = new NBTContainer(dataJson);
    }

    public CustomObjectEntity(float x, float y, float z, EntityType entType1) {
        relativePos = new Vector3f(x, y, z);
        entType = entType1;
    }

    public CustomObjectEntity(float x, float y, float z, EntityType entType1, String dataJson) {
        relativePos = new Vector3f(x, y, z);
        entType = entType1;
        data = new NBTContainer(dataJson);
    }

    public Vector3f getRelativePos() {
        return relativePos;
    }

    public EntityType getEntType() {
        return entType;
    }

    public NBTContainer getNBT() {
        return data;
    }

    public void spawn(byte rotation, Vector3 spawnStart, World world) {
        float y = spawnStart.getY() + relativePos.getY();
        float x;
        float z;
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

        Entity ent = world.spawnEntity(new Location(world, x, y, z), entType);
        if (data != null) {
            NBTEntity nbtent = new NBTEntity(ent);
            nbtent.mergeCompound(data);
        }
    }
}
