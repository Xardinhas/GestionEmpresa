
package nuestrosBeans;
// cadena de conecion: <jsp:useBean id="basico" scope="session" class="nuestrosBeans.baseDeDatos"/>

import java.sql.*;
import java.beans.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class baseDeDatos {
    
    Connection conn = null;
    
    java.sql.Statement stmt = null;
    java.sql.PreparedStatement prepSt = null;
    
    
    ResultSet rset = null;
    ResultSetMetaData meta=null;
    CallableStatement stan=null;
    //final String usuario = "system";
    //final String password = "javaoracle";
    final String cadenaconexion = "jdbc:oracle:thin:@localhost:1521:XE";
    
    public void Conectar(String user, String pass){
        
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(cadenaconexion, user, pass);
            
        } catch (SQLException ex) {
            Logger.getLogger(baseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void finConectar(){
        try {
            
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(baseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearStatement() throws Exception{
        stmt = conn.createStatement(); 
    }
    
    public void crearPreparedStatement(String consultaSQL) throws Exception{
        prepSt = conn.prepareStatement(consultaSQL);
    }
    public void ejUpdatePrepStat() throws Exception{
        prepSt.executeUpdate();
    }
    public void crearCallableStatement(String cadena) throws Exception{
        stan = conn.prepareCall(cadena);
    }
    public void ejCallableStatement() throws Exception{
        stan.executeUpdate();
    }
    public ResultSet crearResultSet(String consultaSQL) throws Exception{
        
        rset = stmt.executeQuery(consultaSQL);
        return rset;  
    }
    
    public ResultSetMetaData crearResultSetMetaDataSt() throws Exception{
        meta= rset.getMetaData();
        return meta;
    }
    
    public ResultSetMetaData crearResultSetMetaDataPrepSt() throws Exception{
        meta = rset.getMetaData();
        return meta;
    }
    
}
