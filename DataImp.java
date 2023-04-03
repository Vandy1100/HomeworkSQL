package TestingPac;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DataImp {
    public DataSource dataSource(){
        PGSimpleDataSource dataSource= new PGSimpleDataSource();
        dataSource.setPortNumbers(new int[] {9090});
        dataSource.setUser("postgres");
        dataSource.setPassword("123");
        dataSource.setDatabaseName("data_analytic");
        return dataSource;
    }
}
