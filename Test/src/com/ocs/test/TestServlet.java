package com.ocs.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ocs.util.DBConnection;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String name=request.getParameter("name");
		String age="";
		DBConnection db=new DBConnection();
		String sql="select address,personid from TestDB.Persons where firstname=?";
		System.out.println(sql);
		ArrayList<Object> sqlParam=new ArrayList<Object>();
		sqlParam.add(name);
		
		ArrayList<String[]> results=db.searchTable(sql, sqlParam);
		
		if(results.size()>0) {
		name=results.get(0)[0];
		age=results.get(0)[1];
		} else {
			name="";
			age="0";
		}
		
		
		String json = "{ \"name\": \""
				+ name.toUpperCase()
				+ "\",  \"age\": "
				+ Double.parseDouble(age)*2
				+ " }";
		
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	        
	        
	}

}
