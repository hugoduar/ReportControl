ReportControl
=============

ReportControl is a software to manage bugs reports of lab machines 


# Login page

`web/index.jsp` is the login page and `web/WEB-INF/classes/Login.class` is a servlet to control the login

# Configure login database

Go to `src/java/conexion/Conexion.java` and edit the parameters 

	public static Connection getConexion() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:PORT_OF_MYSQL/DB_NAME", "USERNAME", "PASSWORD");
        return con;
    }