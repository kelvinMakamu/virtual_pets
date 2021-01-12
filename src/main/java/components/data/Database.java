package components.data;

import org.sql2o.Sql2o;

public class Database {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/virtual_pets",null,null);
}
