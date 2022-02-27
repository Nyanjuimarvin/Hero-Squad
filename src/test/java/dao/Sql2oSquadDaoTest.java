package dao;

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

    //Helper Squad
    public Squad setUpSquad(){
        return new Squad(1000,"Diamond Dogs","Soldiers Without Borders");
    }

    @BeforeEach
    void setUp() {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        squadDao = new Sql2oSquadDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        conn.close();
    }

    @Test
    void squadIsAddedWithId() {
        Squad squad = setUpSquad();
        int initialId = squad.getId();
        squadDao.addSquad(squad);
        assertNotEquals(initialId,squad.getId());
    }

    @Test
    void AllSquadsAreAddedInList() {
        Squad squad1 = setUpSquad();
        squadDao.addSquad(squad1);
        Squad squad2 = new Squad(3500,"Sentinels","Run Hell Over");
        squadDao.addSquad(squad2);

        assertEquals(2,squadDao.getAllSquads().size());
    }

    @Test
    void squadsAreFoundById() {
        Squad squad = setUpSquad();
        squadDao.addSquad(squad);
        Squad newSquad = squadDao.findById(squad.getId());
        assertEquals(squad,newSquad);
    }

    @Test
    void allHeroesInASquad() {
    }

    @Test
    void updateSquad() {
        Squad squad = setUpSquad();
        String initialName = squad.getName();
        int initialSize = squad.getMaxSize();
        String initialCause = squad.getCause();
        squadDao.addSquad(squad);
        squadDao.updateSquad(squad.getId(),6,"Squadra Guardie del corpo","Find the Boss");
        assertNotEquals(initialName,squad.getName());
        assertNotEquals(initialSize,squad.getMaxSize());
        assertNotEquals(initialCause, squad.getCause());
    }

    @Test
    void deleteAllSquads() {
        Squad squad = setUpSquad();
        squadDao.addSquad(squad);
        Squad squad1 = new Squad(9,"The Patriots","Rule the Country");
        squadDao.addSquad(squad);
        squadDao.deleteAllSquads();
        assertEquals(0,squadDao.getAllSquads().size());
    }
}