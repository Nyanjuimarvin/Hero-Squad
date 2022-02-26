package dao;

import Models.Hero;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mozilla.javascript.EcmaError;
import org.sql2o.Connection;
import org.sql2o.*;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oHeroDaoTest {
    private Sql2oHeroDao heroDao;
    private Connection conn;

    public Hero setUpHero(){
        return new Hero(24,"Ciel","Immortality","Ciel Somer","Seventh Holy Scripture","Roa Valdamjong",7);
    }

    @BeforeEach
    //Open Connection before each test
    void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    //Close resource after each test
    void tearDown() throws Exception {
        conn.close();
    }

    @Test
    void addHero_ObjectsAreAddedWithId() {
        Hero hero = setUpHero();
        int initialId = hero.getId();
        heroDao.addHero(hero);
        assertNotEquals(initialId, hero.getId());

    }

    @Test
    void getAllHeroes_AllHeroObjectsAreAdded() throws Exception {
        Hero hero1 = setUpHero();
        heroDao.addHero(hero1);
        Hero hero2 = new Hero(33,"VenomSnake","Tactics","CQC","Any Millitary Issue","Solid Snake",11);
        heroDao.addHero(hero2);
        assertEquals(2,heroDao.getAllHeroes().size());
    }

    @Test
    public void HeroesAreFoundById() throws Exception {
        Hero hero = new Hero(33,"VenomSnake","Tactics","CQC","Any Millitary Issue","Solid Snake",11);
        heroDao.addHero(hero);
        Hero newHero = heroDao.findById(hero.getId());
        assertEquals(hero,heroDao.findById(newHero.getId()) );
    }

    @Test
    void updateHeroesOnAGivenId() throws Exception {
        Hero hero1 = setUpHero();
        String initialName = hero1.getName();
        int initialAge = hero1.getAge();
        String initialWeakness = hero1.getWeakness();
        heroDao.addHero(hero1);
        heroDao.updateHero(hero1.getId(), 33,"VenomSnake","Tactics","CQC","Any Millitary Issue","Solid Snake",11);
        assertNotEquals(initialName,heroDao.findById(1).getName());
        assertNotEquals(initialAge,heroDao.findById(1).getAge());
        assertNotEquals(initialWeakness,heroDao.findById(1).getWeakness());
    }

    @Test
    void deleteById() throws Exception {
        Hero hero1 = setUpHero();
        heroDao.addHero(hero1);
        Hero hero2 = new Hero(33,"VenomSnake","Tactics","CQC","Any Millitary Issue","Solid Snake",11);
        heroDao.addHero(hero2);
        heroDao.deleteById(hero2.getId());
        assertFalse(heroDao.getAllHeroes().contains(hero2));
    }

    @Test
    void deleteAll() throws Exception {
        Hero hero1 = setUpHero();
        heroDao.addHero(hero1);
        Hero hero2 = new Hero(33,"VenomSnake","Tactics","CQC","Any Millitary Issue","Solid Snake",11);
        heroDao.addHero(hero2);
        heroDao.deleteAll();
        assertEquals(0,heroDao.getAllHeroes().size());
    }
}