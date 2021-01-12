import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() throws Throwable {
        Database.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/virtual_pets_test",null,null);
    }

    @Override
    protected void after() {
        String personDeleteQuery = "DELETE FROM persons";
        try(Connection connection = Database.sql2o.open()){
            connection.createQuery(personDeleteQuery).executeUpdate();
        }
    }
}
