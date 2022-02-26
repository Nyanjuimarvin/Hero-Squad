package dao;

import Models.Hero;
import Models.Squad;

import java.util.List;

public interface SquadDao {

    //List of all Squads
    List<Squad> getAllHeroes();
    List <Hero> allHeroesInASquad (int squadId);

    //CRUD
    void addSquad(Squad squad);
    Squad findById(int id);
    void updateSquad(int id,int size,int name,String cause);
    void deleteAllSquads();
}
