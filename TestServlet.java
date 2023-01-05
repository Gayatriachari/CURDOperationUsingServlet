package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = { "/testServlet" }, 
		initParams = { 
				@WebInitParam(name = "url", value = "jdbc:mysql:///enterprice_java"), 
				@WebInitParam(name = "user", value = "root"), 
				@WebInitParam(name = "password", value = "Achari@2001")
		})
public class TestServlet extends HttpServlet {				
	private static final long serialVersionUID = 1L; 
	Connection connection=null; 
	PreparedStatement pstmt=null;
	ResultSet resultSet=null;

	static {
		System.out.println("Test servlet loading....");
	} 
	public TestServlet() {
		System.out.println("Test servlet Instantiation....");
	}
	public void init() {
		System.out.println("Test servlet initialization...."); 
    	ServletConfig config=getServletConfig();
    	
    	String url=config.getInitParameter("url");
    	String user=config.getInitParameter("user");
    	String password=config.getInitParameter("password"); 
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url, user,password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void destroy() {
		System.out.println("Test servlet DeInstantiation....");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post method calling...");
		response.setContentType("text/html"); 
		PrintWriter out=response.getWriter();
		String option1=request.getParameter("insert"); 
		String option2=request.getParameter("update");
		String option3=request.getParameter("delete");
		String option4=request.getParameter("select"); 
		if(option1!=null) {
			if(option1.equals("insert")) {
				out.println("<html><head><title>Enter data</title></head>");
				out.println("<body bgcolor='yellow'>");
				out.println("<marquee>");
				out.println("<h1 style='color:black'>enter the below details to store in the database</h1>");
				out.println("</marquee>");
				out.println("<form method='POST' action='./insert'>");
		    		out.println("<table align='center'>");
		    			out.println("<tr>");
		    				out.println("<td>Name</td>");
		    				out.println("<td><input type='text' name='name'></td></br>");
		    			out.println("</tr>");
		    			out.println("<tr>");
		    				out.println("<td>Email</td>");
		    				out.println("<td><input type='email' name='email' placeholder='xxxxxxx@gmil.com'></td></br>");
		    			out.println("</tr>");
		    			out.println("<tr>");
		    				out.println("<td>city</td>");
		    				out.println("<td><input type='text' name='city'></td></br>");
		    			out.println("</tr>");
		    			out.println("<tr>");		  
		    				out.println("<td>Country</td>");
		    				out.println("<td><input type='text' name='country'></td></br>");
		    			out.println("</tr>");
		    			out.println("<tr>");
		    				out.println("<td></td>");
		    				out.println("<td><input type='submit' value='insert into database'> </td>");
		    			out.println("</tr>");
		    		out.println("</table>");
		        out.println("</form>");  
		        out.println("</body>"); 
		        out.println("</html>");
			}
		} 
		if(option2!=null) {
			if(option2.equals("update")) {
				out.println("<html><head><title>Enter data</title></head>");
				out.println("<body bgcolor='lightgreen'>");
				out.println("<marquee>");
				out.println("<h1 style='color:black'>enter the id number for which u want to change the data</h1>");
				out.println("</marquee>");
				out.println("<form method='POST' action='./update'>");
	    		out.println("<table align='center'>");
	    			out.println("<tr>");
	    				out.println("<td>ID</td>");
	    				out.println("<td><input type='text' name='id'></td></br>");
	    			out.println("</tr>");
	    			out.println("<tr>");
	    				out.println("<td>Change Name to</td>");
	    				out.println("<td><input type='text' name='name'></td></br>");
	    			out.println("</tr>");
	    			out.println("<tr>");
	    				out.println("<td>Change Email to</td>");
	    				out.println("<td><input type='email' name='email' placeholder='xxxxxxx@gmil.com'></td></br>");
	    			out.println("</tr>");
	    			out.println("<tr>");
	    				out.println("<td>Change city to</td>");
	    				out.println("<td><input type='text' name='city'></td></br>");
	    			out.println("</tr>");
	    			out.println("<tr>");		  
	    				out.println("<td>Change Country to</td>");
	    				out.println("<td><input type='text' name='country'></td></br>");
	    			out.println("</tr>");
	    			out.println("<tr>");
	    				out.println("<td></td>");
	    				out.println("<td><input type='submit' value='update&submit'> </td>");
	    			out.println("</tr>");
	    		out.println("</table>");
	            out.println("</form>");  
				out.println("</body>");
				out.println("</html>");
				
			}
				
		}
		if(option3!=null) {
			if(option3.equals("delete")) {
				out.println("<html><head><title>Enter data</title></head>");
				out.println("<body bgcolor='#ddddff'>");
				out.println("<marquee>");
				out.println("<h1 style='color:black'>enter existed id to delete that row</h1>");
				out.println("</marquee>");
				out.println("<form method='POST' action='./delete'>");
	    		out.println("<table align='center'>");
	    			out.println("<tr>");
	    				out.println("<td>ID</td>");
	    				out.println("<td><input type='text' name='id'></td></br>");
	    			out.println("</tr>");
	    			out.println("<tr>");
    					out.println("<td></td>");
    					out.println("<td><input type='submit' value='update&submit'> </td>");
    				out.println("</tr>");
	    		out.println("</table>");
	    		out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}			
		}
		if(option4!=null) {
			if(option4.equals("select")) {
				String readData="select * from students"; 
	            try {
					pstmt=connection.prepareStatement(readData);
				} catch (SQLException e) {
					e.printStackTrace();
				}
	            if(pstmt!=null) {
	         	   try {
					resultSet =pstmt.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
	         	out.println("<html><head><title>Reading page</title></head>");
	         		out.println("<body bgcolor='pink'>");
	         			out.println("<marquee>");
	         				out.println("<h1 style='color:black'>data present in the database is shown below </h1>");
	         			out.println("</marquee>"); 
	         			if(resultSet!=null) {
	         				try {
	         					out.println("<table border='1' align='center'>");
	         						out.println("<tr>"); 
	         						out.println("<th>Id</th>"); 
	         						out.println("<th>Name</th>");
	         						out.println("<th>Email</th>");
	         						out.println("<th>city</th>");
	         						out.println("<th>country</th>");
         					    out.println("</tr>");
	         					while(resultSet.next()) {
	         						int id=resultSet.getInt(1);
	         						String name=resultSet.getString(2); 
	         						String email=resultSet.getString(3);
	         						String city=resultSet.getString(4);
	         						String country=resultSet.getString(5); 
	         					
	         						out.println("<tr>"); 
	         							out.println("<td>"+id+"</td>");
	         							out.println("<td>"+name+"</td>");
	         							out.println("<td>"+email+"</td>");
	         							out.println("<td>"+city+"</td>");
	         							out.println("<td>"+country+"</td>");
	         						out.println("</tr>"); 
						        }
	         					
	         					out.println("</table>");
	         				} catch (SQLException e) {
	         						e.printStackTrace();
	         				}
	         			}
	         			out.println("<a style='text-align:center' href='./choosing.html'>click on the link to go to home page</a>");
	         		out.println("</body>");
	         	   
	         	out.println("</html>");   
	            }
			}		
		}		
	}
}
