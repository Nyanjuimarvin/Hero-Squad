package Models;

import java.util.Objects;

public class Squad {
    private int id;
    private int maxSize;
    private String name;
    private String cause;

    public Squad(int maxSize,String name,String cause){
        this.maxSize = maxSize;
        this.name = name;
        this.cause = cause;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( !(o instanceof Squad) ) return false;
        Squad squad = (Squad) o;
        return id == squad.id && maxSize == squad.maxSize
                && Objects.equals(name, squad.name)
                && Objects.equals(cause, squad.cause);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maxSize, name, cause);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public String getName() {
        return name;
    }

    public String getCause() {
        return cause;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
