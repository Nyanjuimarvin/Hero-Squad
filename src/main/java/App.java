import static spark.Spark.*;

import Models.Hero;
import Models.Squad;
import dao.Sql2oHeroDao;
import dao.Sql2oSquadDao;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/herosquad";
        Sql2o sql2o = new Sql2o(connectionString,"marvin","nrvnqsr13");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);

        //Show Every Hero and Squad
        get("/",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("squads",squadDao.getAllSquads());
            for(int i = 0 ; i <= squadDao.getAllSquads().size(); i++ ){
                model.put("size",squadDao.allHeroesInASquad(i));
            }
            model.put("heroes",heroDao.getAllHeroes());
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //Get squad Form
        get("/squad/new",(request, response) ->{
            Map <String,Object> model = new HashMap<>();
            model.put("squads",squadDao.getAllSquads());
            return new ModelAndView(model,"squad-form.hbs");
        },new HandlebarsTemplateEngine() );


        //Get Hero Form
        get("/hero/new",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            List <Hero> heroes = heroDao.getAllHeroes();
            model.put("heroes",heroes);
            model.put("squads",squadDao.getAllSquads());//Squads for the select field
            return new ModelAndView(model,"hero-form.hbs");
        },new HandlebarsTemplateEngine());


        //Post Squad Form ::CREATE
        post("/squads",(request, response) -> {
//            Map <String,Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int size = Integer.parseInt(request.queryParams("size"));
            String cause = request.queryParams("cause");
            squadDao.addSquad(new Squad(size,name,cause));
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //Post Hero Form ::CREATE
        post("/heroes",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            String move = request.queryParams("move");
            String weapon = request.queryParams("weapon");
            String weakness = request.queryParams("weakness");
            int squadId = Integer.parseInt(request.queryParams("squadId"));
            Hero newHero = new Hero(age,name,power,move,weapon,weakness,squadId);
            while(!(squadDao.allHeroesInASquad(squadId).contains(newHero) ) && !(heroDao.getAllHeroes().contains(newHero))){
                heroDao.addHero(newHero);
            }

            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //Heroes In a specific Squad ::READ
        get("/squads/:id",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int squadId = Integer.parseInt( request.params("id"));
            model.put("squad",squadDao.findById(squadId));
            model.put("heroes",squadDao.allHeroesInASquad(squadId));
            model.put("squads",squadDao.getAllSquads());//Refresh for Dropdown
            return new ModelAndView(model,"squad.hbs");

        },new HandlebarsTemplateEngine());

        //Hero details In A given Squad ::READ
        get("/squads/:squadId/heroes/:heroId",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int squadId = Integer.parseInt(request.params("squadId"));
            int heroId = Integer.parseInt(request.params("heroId"));
            model.put("heroes",heroDao.findById(heroId));
            model.put("squads",squadDao.getAllSquads());
            return new ModelAndView(model,"hero.hbs");
        },new HandlebarsTemplateEngine());

        //Get Form For Squad Updates
        get("/squads/:squadId/update",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("edit",true);//Boolean for update:: Always true
            int squadToEdit = Integer.parseInt(request.params("squadId"));
            model.put("squad",squadDao.findById(squadToEdit));//Show previous in input fields
            model.put("squads",squadDao.getAllSquads());
            return new ModelAndView(model,"squad-form.hbs");
        },new HandlebarsTemplateEngine());

        //Update Squad ::UPDATE
        post("/squad/:squadId",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int idOfSquad = Integer.parseInt(request.params("squadId"));
            String newName = request.queryParams("newname");
            int newSize = Integer.parseInt(request.queryParams("newsize"));
            String newCause = request.queryParams("newcause");
            squadDao.updateSquad(idOfSquad,newSize,newName,newCause);

            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //Get form for hero updates

        get("/hero/:heroId/update",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("update",true);
            int heroToUpdate = Integer.parseInt(request.params("heroId"));
            System.out.println(heroToUpdate);
            model.put("hero",heroDao.findById(heroToUpdate));
            model.put("squad",squadDao.getAllSquads());
            return new ModelAndView(model,"hero-form.hbs");
        },new HandlebarsTemplateEngine());

        //Update Hero ::UPDATE

        post("/heroes/:heroId",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int heroId = Integer.parseInt(request.params("heroId"));
            String newName = request.queryParams("newname");
            int newAge = Integer.parseInt(request.queryParams("newage"));
            String newPower = request.queryParams("newpower");
            String newMove = request.queryParams("newmove");
            String newWeapon = request.queryParams("newweapon");
            String newWeakness = request.queryParams("newweakness");
            int squadId = Integer.parseInt(request.queryParams("squadid"));
            heroDao.updateHero(heroId,newAge,newName,newPower,newMove,newWeapon,newWeakness,squadId);
            response.redirect("/");
            return null;
        });
        // Delete All Squads and Heroes :: DELETE
        get("/squad/delete",(request, response) -> {
            Map <String, Object> model = new HashMap<>();
            heroDao.deleteAll();
            squadDao.deleteAllSquads();
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //Delete squad at given id
        get("/squads/:squadId/delete",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int squadId = Integer.parseInt(request.params("squadId"));
            squadDao.deleteById(squadId);
            model.put("squads",squadDao.getAllSquads());
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //Delete Heroes At A given Id
        get("/heroes/:heroId/delete",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int heroId = Integer.parseInt(request.params("heroId"));
            heroDao.deleteById(heroId);
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());
    }


}
