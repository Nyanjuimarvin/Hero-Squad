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
        String connectionString = "jdbc:h2:~/hero-squad.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);

        //Show Every Hero and Squad
        get("/",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("squads",squadDao.getAllSquads());
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


        //Post Squad Form
        post("/squads",(request, response) -> {
//            Map <String,Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int size = Integer.parseInt(request.queryParams("size"));
            String cause = request.queryParams("cause");
            squadDao.addSquad(new Squad(size,name,cause));
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //Post Hero Form
        post("/heroes",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            String move = request.queryParams("move");
            String weapon = request.queryParams("weapon");
            String weakness = request.queryParams("weakness");
            int squadId = Integer.parseInt(request.queryParams("squadId"));
            heroDao.addHero(new Hero(age,name,power,move,weapon,weakness,squadId));
            System.out.println(heroDao.getAllHeroes().size());
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        get("/squads/:id",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int squadId = Integer.parseInt( request.params("id"));
            model.put("squad",squadDao.findById(squadId));
            model.put("heroes",squadDao.allHeroesInASquad(squadId));
            System.out.println(squadDao.allHeroesInASquad(squadId).size());
            return new ModelAndView(model,"squad.hbs");

        },new HandlebarsTemplateEngine());

    }
}
