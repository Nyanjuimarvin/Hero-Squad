package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hero {
    private int age;
    private int id;
    private int squadId;
    private String name;
    private String power;
    private String move;
    private String weapon;
    private String weakness;

    private static List <Hero> instances = new ArrayList<>();

    public  Hero(int age,String name,String power,String move,String weapon,String weakness,int squadId){
        this.age = age;
        this.name = name;
        this.power = power;
        this.move = move;
        this.weapon = weapon;
        this.weakness = weakness;
        this.squadId = squadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( !(o instanceof Hero) ) return false;
        Hero hero = (Hero) o;
        return age == hero.age && id == hero.id
                && Objects.equals(name, hero.name)
                && Objects.equals(power, hero.power)
                && Objects.equals(move, hero.move)
                && Objects.equals(weapon, hero.weapon)
                && Objects.equals(weakness, hero.weakness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, id, name, power, move, weapon, weakness);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getMove() {
        return move;
    }

    public String getPower() {
        return power;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getWeakness() {
        return weakness;
    }

    public int getSquadId() {
        return squadId;
    }
}

