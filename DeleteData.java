package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = { "/delete" }, 
		initParams = { 
				@WebInitParam(name = "url", value = "jdbc:mysql:///enterprice_java"), 
				@WebInitParam(name = "user", value = "root"), 
				@WebInitParam(name = "password", value = "Achari@2001")
		})
public class DeleteData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection=null;
    private PreparedStatement pstmt=null;
	
    static {
    	System.out.println("Delete servlet Loading...");
    }
    public DeleteData(){
    	System.out.println("Delete servlet instantiation...");
    } 
    public void init() {
    	System.out.println("Delete servlet initialization..."); 
    	
    	ServletConfig config=getServletConfig();
    	
    	String url=config.getInitParameter("url");
    	String user=config.getInitParameter("user");
    	String password=config.getInitParameter("password"); 
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url, user,password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
    } 
	public void destroy() {
		System.out.println("Delete servlet DeInitialization...");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Get the data from request object
		String Id=request.getParameter("id"); 
		
		//formating the data
		int id1=Integer.valueOf(Id);
		
		//2. Prepare a response and sent the data to database
		String deleteData="delete from students where id=?"; 
 	    try {
			pstmt=connection.prepareStatement(deleteData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	   int rowsModified=0;
 	    if(pstmt!=null) {
 		   try {
			pstmt.setInt(1, id1);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		try {
			rowsModified = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3.prepare a response and send it to the end user
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
						
		out.println("<html><head><title>update page</title></head>");
		out.println("<body>");
		if(rowsModified==1) {
			out.println("<h1 style='color:green; text-align:center'>Successfully deleted the data</h1>");
		    out.println("<a style='text-align:center' href='./choosing.html'>click on the link to go to home page</a>");
		}else {
			out.println("<h1 style='color:red;text-align:center'>No id matched to delete the data</h1>");
			out.println("<a style='text-align:center' href='./choosing.html'>click me to go back to home page nd enter existed id</a>");
		}	
		out.println("</body>");
		out.println("</html>");
		out.close();   

 	   }

	}

}
