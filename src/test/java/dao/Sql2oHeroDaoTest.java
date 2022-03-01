package dao;

import Models.Hero;
import org.junit.jupiter.api.*;
import org.sql2o.Connection;
import org.sql2o.*;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oHeroDaoTest {
    private static Sql2oHeroDao heroDao;
    private static Connection conn;

    public Hero setUpHero(){
        return new Hero(24,"Ciel","Immortality","Ciel Somer","Seventh Holy Scripture","Roa Valdamjong",7);
    }

    @BeforeAll
    //Open Connection before each test
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/herosquad_test";
        Sql2o sql2o = new Sql2o(connectionString,"marvin","nrvnqsr13");
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    //Close resource after each test
    public void tearDown() throws Exception {
        heroDao.deleteAll();
    }

    @AfterAll
    public static void shutdown() throws Exception{
        conn.close();
        System.out.println("connection closed");
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
        assertNotEquals(initialName,heroDao.findById(hero1.getId()).getName());
        assertNotEquals(initialAge,heroDao.findById(hero1.getId()).getAge());
        assertNotEquals(initialWeakness,heroDao.findById(hero1.getId()).getWeakness());
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