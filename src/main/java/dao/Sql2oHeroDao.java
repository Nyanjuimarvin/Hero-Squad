package dao;

import Models.Hero;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oHeroDao implements HeroDao{


    private final Sql2o sql2o;
    public Sql2oHeroDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void addHero(Hero hero){
        String sql = "INSERT INTO hero( age, name, power, move, weapon, weakness, squadid) " +
                "VALUES( :age,:name,:power,:move,:weapon,:weakness,:squadId)";

        //open connection
        try( Connection conn = Db.sql2o.open() ){
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

    //List of all heroes
    @Override
    public List <Hero> getAllHeroes(){
        try( Connection conn = Db.sql2o.open() ){
          return conn.createQuery("SELECT * FROM hero")
                  .throwOnMappingFailure(false)//Eliminate Mapping to property Error
                    .executeAndFetch(Hero.class);//Fetch Hero List
        }
    }

    //Hero at specific id
    @Override
    public Hero findById(int id){
        try( Connection conn = Db.sql2o.open() ){
            return conn.createQuery("SELECT * FROM hero WHERE id = :id")
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Hero.class);
        }
    }

    @Override
    public void updateHero( int id, int age, String name, String power, String move, String weapon, String weakness,int squadId) {
        String sql = "UPDATE hero SET ( age,name,power,move,weapon,weakness,squadid) = (:age,:name,:power,:move,:weapon,:weakness,:squadid) " +
                "WHERE id = :id";

        try( Connection conn = Db.sql2o.open() ){
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .addParameter("age",age)
                    .addParameter("name",name)
                    .addParameter("power",power)
                    .addParameter("move",move)
                    .addParameter("weapon",weapon)
                    .addParameter("weakness",weakness)
                    .addParameter("squadid",squadId)
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        try( Connection conn = Db.sql2o.open() ){
            conn.createQuery("DELETE FROM hero WHERE id = :id")
                    .addParameter("id",id)
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAll() {

        try( Connection conn = Db.sql2o.open() ){
            conn.createQuery("DELETE FROM hero")
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println(ex);
        }
    }
}
