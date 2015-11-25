package codenames;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Sets;

public class ServletAnswer extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static final int defaultRows = 5;
    private static final int defaultCols = 5;
    
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {
        String codeString = req.getParameter( "code" );
        int code = codeString == null ? 1 : Integer.parseInt( codeString );

        String rowsString = req.getParameter( "rows" );
        int rows = rowsString == null ? defaultRows : Integer.parseInt( rowsString );
        
        String colsString = req.getParameter( "cols" );
        int cols = colsString == null ? defaultCols : Integer.parseInt( colsString );
        
        int size = rows * cols;
        
        Random r = new Random( code );
        List<String> allWords = WordDatabase.getWordList( );
        LinkedHashSet<String> words = Sets.newLinkedHashSet( );
        
        while ( words.size( ) < size )
        {
            int index = r.nextInt( allWords.size( ) );
            words.add( allWords.get( index ) );
        }
        
        try (BufferedWriter out = new BufferedWriter( new OutputStreamWriter( resp.getOutputStream( ) ) ))
        {
            out.write( "<!DOCTYPE html>" );
            out.write( "<html>" );
            out.write( "<head>" );
            out.write( "  <title>Codenames</title>" );
            out.write( "  <style>" );
            out.write( "    table, th, td {" );
            out.write( "      font-size: 150%;" );
            out.write( "      border: 1px solid black;" );
            out.write( "      border-collapse: collapse;" );
            out.write( "    }" );
            out.write( "    tr { text-align: center; }" );
            out.write( "  </style>" );
            out.write( "</head>" );
            out.write( "<body>" );
            
            out.write( "<table style=\"height: 100%; width: 100%; position: absolute; top: 0; bottom: 0; left: 0; right: 0;\">" );
            
            Iterator<String> iter = words.iterator( );
            
            for ( int row = 0 ; row < rows ; row++ )
            {
                out.write( "<tr>" );
                
                for ( int col = 0 ; col < cols ; col++ )
                {
                    out.write( String.format( "<td>%s</td>", iter.next( ) ) );
                }
                
                out.write( "</tr>" );
            }
            
            out.write( "</table>" );

            out.write( "</body>" );
            out.write( "</html>" );
            
            out.write( code );
        }
    }
}