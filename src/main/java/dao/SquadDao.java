package dao;

import Models.Hero;
import Models.Squad;

import java.util.List;

public interface SquadDao {

    //List of all Squads
    List<Squad> getAllSquads();


    //CRUD
    void addSquad(Squad squad);

    Squad findById(int id);
    List <Hero> allHeroesInASquad (int squadId);

    void updateSquad(int id,int size,int name,String cause);

    void deleteAllSquads();
}
