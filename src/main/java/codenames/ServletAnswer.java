package codenames;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletAnswer extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {

        System.out.println( req.getParameter( "code" ) );
        
        int code = Integer.parseInt( req.getParameter( "code" ) );

        try (BufferedWriter out = new BufferedWriter( new OutputStreamWriter( resp.getOutputStream( ) ) ))
        {
            out.write( "<!DOCTYPE html>" );
            out.write( "<html>" );
            out.write( "<head>" );
            out.write(   "<title>Codenames</title>" );
            out.write( "</head>" );
            out.write( "<body>" );
            out.write( String.valueOf( code ) );
            out.write( "</body>" );
            out.write( "</html>" );
            
            out.write( code );
        }
    }
}