package dao;


import Models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSquadDao implements SquadDao{
    private final Sql2o sql2o;
    public Sql2oSquadDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void addSquad(Squad squad) {
        String sql = "INSERT INTO squad(name,maxsize,cause) VALUES (:name,:maxSize,:cause)";
        try( Connection conn = Db.sql2o.open() ){
            int id = (int)conn.createQuery(sql,true)
                    .bind(squad)
                    .executeUpdate()
                    .getKey();
            squad.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public List<Squad> getAllSquads() {
        try( Connection conn = Db.sql2o.open() ){
            return conn.createQuery("SELECT * FROM squad")
                    .executeAndFetch(Squad.class);
        }
    }


    @Override
    public Squad findById(int id) {
        try( Connection conn = Db.sql2o.open() ){
            return conn.createQuery("SELECT * FROM squad WHERE id = :id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Squad.class);
        }
    }

    @Override
    public List<Hero> allHeroesInASquad(int squadId) {
        try( Connection conn = Db.sql2o.open() ){
           return conn.createQuery("SELECT * FROM hero WHERE squadid = :squadid")
                   .addParameter("squadid",squadId)
                   .executeAndFetch(Hero.class);

        }
    }

    @Override
    public void updateSquad(int id, int size, String name, String cause) {
        String sql = "UPDATE squad SET (name,maxsize,cause) = (:name,:maxsize,:cause) WHERE id = :id";

        try( Connection conn = Db.sql2o.open() ){
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .addParameter("name",name)
                    .addParameter("maxsize",size)
                    .addParameter("cause",cause)
                    .executeUpdate();

        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        try( Connection conn = Db.sql2o.open() ){
            conn.createQuery("DELETE FROM squad WHERE id = :id")
                    .addParameter("id",id)
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void deleteAllSquads() {
        try( Connection conn = Db.sql2o.open() ){
            conn.createQuery("DELETE FROM squad")
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }
}
