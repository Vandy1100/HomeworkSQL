package TestingPac;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    DataImp dataSource= new DataImp();
    public static void keyPress(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Press Key To Continue....");
        sc.nextLine();
    }
    private static void deleteData(Model model){
        DataImp dataSource= new DataImp();
        System.out.println(model.getId());
        try {
            Connection conn = dataSource.dataSource().getConnection();
            String sql="DELETE FROM topic_tbl WHERE id=?";
            PreparedStatement statement= conn.prepareStatement(sql);
            statement.setInt(1,model.getId());
            statement.executeUpdate();
            System.out.println("DELETE SUCCESSFUL.");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
   private static void updateData(Model model){
       DataImp dataSource= new DataImp();
       try {
           Connection conn = dataSource.dataSource().getConnection();
           String sql="UPDATE topic_tbl SET name=?,gender=?,status=? WHERE id=?";
           PreparedStatement statement = conn.prepareStatement(sql);
           statement.setString(1, model.getName());
           statement.setString(2, model.getGender());
           statement.setBoolean(3, model.isStatus());
           statement.setInt(4,model.getId());
           statement.executeUpdate();
           System.out.println("Update Successful");
       }catch (SQLException e){
           e.printStackTrace();
       }

   }
    private static void insertData(Model model){
        DataImp dataSource= new DataImp();
        try {
            Connection conn= dataSource.dataSource().getConnection();
            String sql = "INSERT INTO topic_tbl (id,name,gender,status) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,model.getId());
            statement.setString(2,model.getName());
            statement.setString(3,model.getGender());
            statement.setBoolean(4,model.isStatus());
            statement.executeUpdate();
        }catch (SQLException el){
            el.printStackTrace();
        }
    }
    private static void getData(){
        DataImp dataSource= new DataImp();
        try {
//            Connection conn= DriverManager.getConnection(url,name,pwd);
            Connection conn= dataSource.dataSource().getConnection();
//            System.out.println(conn.getSchema());
            //1/. sql statemant
            String selectSql="SELECT * FROM topic_tbl";
            PreparedStatement statement=  conn.prepareStatement(selectSql);
            //2.Excute Query sql
            ResultSet resultSet= statement.executeQuery();
            //3/. Pro
            ArrayList<Model> topic= new ArrayList<>();
            while (resultSet.next()){
                int id= resultSet.getInt("id");
                String names=resultSet.getString("name");
                String gender = resultSet.getString("gender");
                boolean status = resultSet.getBoolean("status");
                topic.add(new Model(id,names,gender,status));
            }
//            System.out.println(topic);
            topic.forEach(e->e.getData());
//            topic.forEach(System.out::println);
        }catch (SQLException e){
            e.printStackTrace();

        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataImp dataSource= new DataImp();
        //Update data
        int op;

        do{
            System.out.println("========CRUD==========");
            System.out.println("1.Insert Data.");
            System.out.println("2.Display Data.");
            System.out.println("3.Update Data.");
            System.out.println("4.Delete Data.");
            System.out.println("5.Search Information.");
            System.out.println("----------------");
            System.out.println("Please Choose Option (1-6):");
            op= sc.nextInt();
            switch (op){
                case 1:{
                   Model model=new Model();
                    System.out.println("1.Insert Data.");
                    //Insert
                    System.out.println("Input Id:");
                    model.setId(sc.nextInt());
                    System.out.println("Input Name:");
                    model.setName(sc.next());
                    sc.nextLine();
                    System.out.println("Input Gender:");
                    model.setGender(sc.next());
                    model.setStatus(false);
                    insertData(model);
                    System.out.println("One Row Inserted!!!");
                }break;
                case 2:{
                    //get Data
                    System.out.println("2.Display Data.");
                    System.out.println("-----------------");
                    System.out.println("ID\tName\tGender\tStatus");
                    getData();
                    keyPress();
                }break;
                case 3:{
                    boolean uValue=false;
                    System.out.println("3.Update Data.");
                    int id;
                    System.out.println("==> Enter ID to Update :");
                    id=sc.nextInt();
                    try {
                       Model model=new Model();
                        Connection conn = dataSource.dataSource().getConnection();
                        //1/. sql statemant
                        String selectSql = "SELECT * FROM topic_tbl";
                        PreparedStatement statement = conn.prepareStatement(selectSql);
                        //2.Excute Query sql
                        ResultSet resultSet = statement.executeQuery();
                        while(resultSet.next()){
                            if(id==resultSet.getInt("id")){
                                model.setId(id);
                                System.out.println("==>Update Name:");
                                model.setName(sc.next());
                                sc.nextLine();
                                System.out.println("==>Update Gender:");
                                model.setGender(sc.next());
                                model.setStatus(false);
                                updateData(model);
                                uValue=true;
                            }
                        }
                        if(uValue==false){
                            System.out.println("This ID Don't have In Data!!");
                        }

                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                    keyPress();
                }break;
                case 4:{
                    boolean dValue=false;
                    System.out.println("4.Update Data.");
                    int d_id;
                    System.out.println("==> Enter ID to Delete :");
                    d_id=sc.nextInt();
                    try {
                        Model model=new Model();
                        Connection conn = dataSource.dataSource().getConnection();
                        //1/. sql statemant
                        String selectSql = "SELECT * FROM topic_tbl";
                        PreparedStatement statement = conn.prepareStatement(selectSql);
                        //2.Excute Query sql
                        ResultSet resultSet = statement.executeQuery();
                        while(resultSet.next()){
                            if(d_id==resultSet.getInt("id")){
                                model.setId(d_id);
                                deleteData(model);
                                dValue=true;
                            }
                        }
                        if(dValue==false){
                            System.out.println("This ID Don't have For Delete!!");
                        }

                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                    keyPress();
                }break;
                case 5:{
                    int search;
                    do {
                        System.out.println("Select Information");
                        System.out.println("1.Select By ID.");
                        System.out.println("2.Select By Name.");
                        System.out.println("3.Exit.");
                        System.out.println("-----------------");
                        System.out.println("Please Choose Option (1-3) :");
                        search=sc.nextInt();
                        switch (search){
                            case 1:{
                                boolean isSearch=false;
                                System.out.println("========Select By ID============");
                                int s_id;
                                System.out.println("Enter Id For Select : ");
                                s_id=sc.nextInt();
                                try {
                                    Model model=new Model();
                                    Connection conn = dataSource.dataSource().getConnection();
                                    //1/. sql statemant
                                    String selectSql = "SELECT * FROM topic_tbl";
                                    PreparedStatement statement = conn.prepareStatement(selectSql);
                                    //2.Excute Query sql
                                    ResultSet resultSet = statement.executeQuery();
                                    while(resultSet.next()){
                                        if(s_id==resultSet.getInt("id")){
                                            System.out.println("ID :"+resultSet.getInt("id"));
                                            System.out.println("Name:"+resultSet.getString("name"));
                                            System.out.println("Gender:"+resultSet.getString("gender"));
                                            System.out.println("Status:"+resultSet.getString("status"));
                                            isSearch=true;
                                        }
                                    }
                                    if(!isSearch){
                                        System.out.println("This ID Don't Have...!");
                                    }

                                }catch (SQLException e){
                                    e.printStackTrace();
                                }
                                keyPress();
                            }break;
                            case 2:{
                                boolean isNameSearch=false;
                                System.out.println("========Select By Name============");
                                String s_name;
                                System.out.println("Enter Name For Select : ");
                                s_name=sc.next();
                                try {
                                    Model model=new Model();
                                    Connection conn = dataSource.dataSource().getConnection();
                                    //1/. sql statemant
                                    String selectSql = "SELECT * FROM topic_tbl";
                                    PreparedStatement statement = conn.prepareStatement(selectSql);
                                    //2.Excute Query sql
                                    ResultSet resultSet = statement.executeQuery();
                                    int value=1;
                                    while(resultSet.next()){
                                        if(s_name.equalsIgnoreCase(resultSet.getString("name"))){
                                            System.out.println("=====Information :"+(value)+" =========");
                                            System.out.println("ID :"+resultSet.getInt("id"));
                                            System.out.println("Name:"+resultSet.getString("name"));
                                            System.out.println("Gender:"+resultSet.getString("gender"));
                                            System.out.println("Status:"+resultSet.getString("status"));
                                            value++;
                                            isNameSearch=true;
                                        }
                                    }
                                    if(!isNameSearch){
                                        System.out.println("This Name Don't Have...!");
                                    }

                                }catch (SQLException e){
                                    e.printStackTrace();
                                }
                                keyPress();
                            }break;
                            case 3:{

                            }break;
                            default:{
                                System.out.println("Wrong Option Please Try Again!!");
                                keyPress();
                            }
                        }
                    }while(search!=3);
                }break;
                case 6:{

                }break;
                default:{
                    System.out.println("Wrong Option Please Try Again!!");
                    keyPress();
                }
            }
        }while (op!=6);

    }
}
