package dao;

import Models.Hero;

import java.util.List;

public interface HeroDao {
    //List all Heroes
    List <Hero> getAllHeroes();


    //CRUD

    void addHero(Hero hero);
    Hero findById(int id);

    void UpdateHero(int id, int age,String name,String power,String move,String weapon,String weakness,int SquadId);
    //Delete one
    void deleteById(int id);

    //delete all
    void deleteAll();
}
