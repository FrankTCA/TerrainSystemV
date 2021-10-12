package org.infotoast.terrainsystemv.customobject;

import org.bukkit.World;
import org.infotoast.terrainsystemv.math.Vector3;

import java.util.ArrayList;
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

public class CustomObject {
    private String fileName;
    private ArrayList<CustomObjectItem> contents = new ArrayList();
    private World world;
    private Random random;
    private Vector3 spawnPos;
    public static final Object LOCK = new Object();

    /*
    * This is the class that all CustomObjects must extend.
    * CustomObjects should spawn on initialization. You will need to specifically run the spawn() method from your custom constructor.
    * CustomObjects are rotatable, but that does not necessarily work well with blockdata, so you will need to code it to work.
    * The constructor is called, which should super() to this class, which will call the addContents() method, which will add all of your blocks.
    * Finally, your constructor will call the spawn() method, which should work on its own from this class, and not need to be messed with.
    * Once spawn() is called, the custom object will be placed.
    *
    * NOTE: CustomObjects have no boundaries. Any new chunks they bleed into WILL be generated. It is on you if that causes the server to crash, so be weary about making them too big.
     */

    public CustomObject(World world1, Random rand, Vector3 spawnPos1) {
        world = world1;
        random = rand;
        spawnPos = spawnPos1;
        addContents();
    }

    // Call addItem(new <CustomObject*()>) from within your addContents() method.
    protected void addItem(CustomObjectItem item) {
        contents.add(item);
    }

    // Designed to be inherited
    protected void addContents() {
        System.out.println("WARNING: CustomObject was not properly implemented with contents.");
    }

    // Spawns with random rotation
    protected void spawn() {
        spawn((byte)random.nextInt(4));
    }

    // Spawns with rotation specified by a byte between 1-4.
    protected void spawn(byte rotation) {
        for (CustomObjectItem i : contents) {
            synchronized (LOCK) {
                i.spawn(rotation, spawnPos, world);
            }
        }
    }
}
