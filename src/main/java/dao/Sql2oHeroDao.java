package dao;

import Models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2oHeroDao implements HeroDao{


    private final Sql2o sql2o;
    public Sql2oHeroDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void addHero(Hero hero){
        String sql = "INSERT INTO  hero( age, name, power, move, weapon, weakness, squadId) " +
                "VALUES(:age,:name,:power,:move,:weapon,:weakness,:squadId)";

        //open connection
        try( Connection conn = sql2o.open() ){

            //Get row key and assign to id
            int id = (int) conn.createQuery(sql,true)
                    .bind(hero)
                    .executeUpdate()
                    .getKey();
            hero.setId(id);
        }catch(Sql2oException ex){
            System.out.println(ex);
        }
    }
}
