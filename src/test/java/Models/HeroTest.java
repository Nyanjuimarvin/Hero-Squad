package Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    Hero hero;
    public Hero setUpHero(){
        return new Hero(28,"Doom Slayer","Infinite Stamina","Glory Kill","Super Shotgun","None",1);
    }

    @Test
    public void HeroObjectsAreInstantiated() throws Exception{
        hero = setUpHero();
        assertTrue( hero instanceof Hero);
    }

    @Test
    public void HeroObjectInstantiatesCorrectly() throws Exception{
        hero = setUpHero();
        assertEquals(28,hero.getAge());
        assertEquals("Doom Slayer",hero.getName());
        assertEquals("Infinite Stamina",hero.getPower());
        assertEquals("Glory Kill",hero.getMove());
        assertEquals("Super Shotgun",hero.getWeapon());
        assertEquals("None",hero.getWeakness());
        assertEquals(1,hero.getSquadId());
    }
}