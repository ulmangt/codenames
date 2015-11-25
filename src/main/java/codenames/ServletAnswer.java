package codenames;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.io.ByteStreams;

public class ServletAnswer extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {
        String requestString = new String( ByteStreams.toByteArray( req.getInputStream( ) ) );

        try ( BufferedWriter out = new BufferedWriter( new OutputStreamWriter( resp.getOutputStream( ) ) ) )
        {
            out.write( requestString );
        }
    }
}