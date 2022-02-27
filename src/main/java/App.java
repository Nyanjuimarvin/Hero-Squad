import static spark.Spark.*;

import Models.Hero;
import Models.Squad;
import dao.Sql2oHeroDao;
import dao.Sql2oSquadDao;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);

        //Show Every Hero and Category
        get("/",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            List <Hero> heroes = heroDao.getAllHeroes();
            model.put("heroes",heroes);
            List <Squad> squads = squadDao.getAllSquads();
            model.put("squads",squads);
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //Get squad Form
        get("/squad/new",(request, response) ->{
            Map <String,Object> model = new HashMap<>();
            List <Squad> squads = squadDao.getAllSquads();
            model.put("squads",squads);
            return new ModelAndView(model,"squad-form.hbs");
        },new HandlebarsTemplateEngine() );


        //Get Hero Form
        get("/hero/new",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            List <Hero> heroes = heroDao.getAllHeroes();
            return new ModelAndView(model,"hero-form.hbs");
        },new HandlebarsTemplateEngine());

    }
}
