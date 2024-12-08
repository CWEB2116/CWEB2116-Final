package FantasyManager.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import FantasyManager.manager.CreatureManager;
import FantasyManager.model.Dragon;

public class CreatureTest {
    private CreatureManager manager;

    @Before
    public void setup() {
        manager = new CreatureManager();
    }

    @Test
    public void testAddCreature() {
        manager.addCreature(new Dragon("Smoky", 100, 50));
        assertNotNull(manager.findCreatureByName("Smoky"));
    }

    @Test
    public void testRemoveCreature() {
        manager.addCreature(new Dragon("Fang", 200, 60));
        assertTrue(manager.removeCreature("Fang"));
        assertNull(manager.findCreatureByName("Fang"));
    }
}
