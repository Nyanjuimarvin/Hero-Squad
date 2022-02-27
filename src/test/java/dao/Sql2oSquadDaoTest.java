package dao;

import Models.Hero;
import Models.Squad;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oSquadDaoTest {

    private Connection conn;
    private Sql2oSquadDao squadDao;
    private Sql2oHeroDao heroDao;

    //Helper Squad
    public Squad setUpSquad(){
        return new Squad(1000,"Diamond Dogs","Soldiers Without Borders");
    }

    @BeforeEach
    void setUp() {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        squadDao = new Sql2oSquadDao(sql2o);
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        conn.close();
    }

    @Test
    void squadIsAddedWithId() throws Exception{
        Squad squad = setUpSquad();
        int initialId = squad.getId();
        squadDao.addSquad(squad);
        assertNotEquals(initialId,squad.getId());
    }

    @Test
    void AllSquadsAreAddedInList() throws Exception {
        Squad squad1 = setUpSquad();
        squadDao.addSquad(squad1);
        Squad squad2 = new Squad(3500,"Sentinels","Run Hell Over");
        squadDao.addSquad(squad2);

        assertEquals(2,squadDao.getAllSquads().size());
    }

    @Test
    void squadsAreFoundById() throws Exception {
        Squad squad = setUpSquad();
        squadDao.addSquad(squad);
        Squad newSquad = squadDao.findById(squad.getId());
        assertEquals(squad,newSquad);
    }

    @Test
    void HeroesInAGivenSquadAreFound() throws Exception {
        Squad squad = setUpSquad();
        squadDao.addSquad(squad);
        int squadId = squad.getId();
        Hero hero = new Hero(29,"The Courier","Super Strength","Khan Trick","Couriers Stash","Amnesia",squadId);
        Hero hero1 = new Hero(27,"Gordon Freeman","Intellect","Tau Jump","Zero Point Energy Field Manipulator","Alyx Vance",squadId);
        Hero hero2 = new Hero(22,"Benimaru Shinmon","Compound Pyrokinetic","Lai Hand Sword","Matoi","None",squadId);
        heroDao.addHero(hero);
        heroDao.addHero(hero1);
        assertEquals(2,squadDao.allHeroesInASquad(squadId).size());
        assertTrue(squadDao.allHeroesInASquad(squadId).contains(hero));
        assertTrue(squadDao.allHeroesInASquad(squadId).contains(hero1));
        assertFalse(squadDao.allHeroesInASquad(squadId).contains(hero2));
    }

    @Test
    void squadInGivenIdIsUpdated() throws Exception {
        Squad squad = setUpSquad();
        String initialName = squad.getName();
        int initialSize = squad.getMaxSize();
        String initialCause = squad.getCause();
        squadDao.addSquad(squad);
        squadDao.updateSquad(squad.getId(),6,"Squadra Guardie del corpo","Find the Boss");
        assertNotEquals(initialName,squadDao.findById(squad.getId()).getName());
        assertNotEquals(initialSize,squadDao.findById(squad.getId()).getMaxSize());
        assertNotEquals(initialCause, squadDao.findById(squad.getId()).getCause());
    }

    @Test
    public void SquadsAreDeletedById(){
       Squad squad = setUpSquad();
       Squad squad1 = new Squad(19,"Dark Brotherhood","Contractors");
       squadDao.addSquad(squad);
       squadDao.addSquad(squad1);

       squadDao.deleteById(squad1.getId());
       assertFalse(squadDao.getAllSquads().contains(squad1));

    }

    @Test
    void allSquadsAreDeleted() throws Exception {
        Squad squad = setUpSquad();
        squadDao.addSquad(squad);
        Squad squad1 = new Squad(9,"The Patriots","Rule the Country");
        squadDao.addSquad(squad);
        squadDao.deleteAllSquads();
        assertEquals(0,squadDao.getAllSquads().size());
    }
}