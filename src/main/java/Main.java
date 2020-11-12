import java.sql.*;
import java.util.Scanner;

public class Main {

    private static Connection connection;
    private static Statement statement;


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:mydb2.db");
        statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS students(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "Name TEXT NOT NULL," +
                "SurName TEXT NOT NULL," +
                "NameTwo TEXT NOT NULL," +
                "DateBirth TEXT NOT NULL," +
                "GroupSt TEXT NOT NULL" +
                ");");


        //Меню для консоли
        boolean work = true;
        while (work) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Выберети действие:\n1 - Добавить студента в базу\n2 - Удалить студента по номеру\n3 - показать список студентов\n4 - Выход");
            int num = sc.nextInt();

            if(num == 1){
                addStudent();
            }else if(num == 2){
                deleteStudent();
            }else if(num == 3) {
                showStudents();
            }else  if(num == 4){
                work = false;
            }else {
                System.out.println("Нет такой команды");
            }
        }
    }

    //Метод для добавления студентов
    private static void addStudent() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя студента");
        String name = sc.nextLine();
        System.out.println("Введите Фамилию студента");
        String surname = sc.nextLine();
        System.out.println("Введите Отчество студента");
        String nameTwo = sc.nextLine();
        System.out.println("Введите дату рождения студента");
        String dateB = sc.nextLine();
        System.out.println("Введите группу");
        String groupS = sc.nextLine();

        statement.execute("INSERT INTO students(Name, SurName, NameTwo, DateBirth, GroupSt) VALUES('" +
                name + "','" + surname + "','" + nameTwo + "','" + dateB + "','" + groupS + "'" +
                ");");
        System.out.println("СТУДЕНТ ДОБАВЛЕН В БАЗУ");
    }

    //Метод для просмотра списка студентов
    private static void showStudents() throws SQLException {
        ResultSet set = statement.executeQuery("SELECT * FROM students;");
        while (set.next()){
            System.out.println(set.getInt(1) + "," + set.getString(2)+
                    "," + set.getString(3)+ "," + set.getString(4)+
                    "," + set.getString(5)+ "," + set.getString(6));
        }
    }

    //Метод для удаления студента по номеру
    private static void deleteStudent() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите уникальный номер студента");
        String id = sc.nextLine();
        statement.execute("DELETE FROM students WHERE id = " + id + ";");

        System.out.println("СТУДЕНТ ПОД НОМЕРОМ " + id + " - УДАЛЕН ИЗ БАЗЫ");
    }
}

