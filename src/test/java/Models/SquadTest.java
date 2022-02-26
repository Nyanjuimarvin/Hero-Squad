package Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquadTest {

    Squad squad;

    public Squad setUpSquad(){
        return new Squad(6,"Foxhound","Counter Terrorism");
    }

    @Test
    public void SquadObjectsAreInstantiated() throws Exception{
        squad = setUpSquad();
        assertTrue(squad instanceof Squad);
    }

    @Test
    public void SquadObjectsAreInstantiatedCorrectly() throws Exception{
        squad = setUpSquad();
        assertEquals(6,squad.getMaxSize());
        assertEquals("Foxhound",squad.getName());
        assertEquals("Counter Terrorism",squad.getCause());
    }


}