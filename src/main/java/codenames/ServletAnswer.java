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

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ServletAnswer extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static final int defaultRows = 5;
    private static final int defaultCols = 5;
    
    private static final int defaultFirst = 9;
    private static final int defaultSecond = 8;
    
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {
        String codeString = req.getParameter( "code" );
        int code = codeString == null ? 1 : Integer.parseInt( codeString );

        String rowsString = req.getParameter( "rows" );
        int rows = rowsString == null ? defaultRows : Integer.parseInt( rowsString );
        
        String colsString = req.getParameter( "cols" );
        int cols = colsString == null ? defaultCols : Integer.parseInt( colsString );
        
        String firstString = req.getParameter( "first" );
        int first = firstString == null ? defaultFirst : Integer.parseInt( firstString );
        
        String secondString = req.getParameter( "second" );
        int second = secondString == null ? defaultSecond : Integer.parseInt( secondString );
        
        int size = rows * cols;
        
        Random r = new Random( code );
        List<String> allWords = WordDatabase.getWordList( );
        LinkedHashSet<String> words = Sets.newLinkedHashSet( );
        
        while ( words.size( ) < size )
        {
            int index = r.nextInt( allWords.size( ) );
            words.add( allWords.get( index ) );
        }
        
        boolean redFirst = r.nextBoolean( );
        int numRed = redFirst ? first : second;
        int numBlue = !redFirst ? first : second;
        int numBombs = 1;
        
        LinkedHashSet<Integer> targetSet = Sets.newLinkedHashSet( );
        
        // add 1 for the bomb
        int numTargets = numRed + numBlue + numBombs;
        
        while ( targetSet.size( ) < numTargets )
        {
            int index = r.nextInt( size );
            targetSet.add( index );
        }
        
        List<Integer> targetList = Lists.newArrayList( targetSet );
        
        List<Integer> redTargets = targetList.subList( 0, numRed );
        List<Integer> blueTargets = targetList.subList( numRed, numRed + numBlue );
        List<Integer> bombTargets = targetList.subList( numRed + numBlue, numRed + numBlue + numBombs );
        
        try (BufferedWriter out = new BufferedWriter( new OutputStreamWriter( resp.getOutputStream( ) ) ))
        {
            out.write( "<!DOCTYPE html>" );
            out.write( "<html>" );
            out.write( "<head>" );
            out.write( "  <script type=\"text/javascript\" src=\"puzzle.js\"></script>" );
            out.write( "  <link rel=\"stylesheet\" type=\"text/css\" href=\"puzzle.css\">" );
            out.write( "  <title>Codenames</title>" );
            out.write( "</head>" );
            out.write( "<body onLoad=onLoad();>" );
            
            out.write( "<table id=\"gameGrid\">" );
            
            Iterator<String> iter = words.iterator( );
            int counter = 0;
            
            for ( int row = 0 ; row < rows ; row++ )
            {
                out.write( "<tr>" );
                
                for ( int col = 0 ; col < cols ; col++ )
                {
                    String team = "none";
                    
                    if ( blueTargets.contains( counter ) )
                    {
                        team = "blue";
                    }
                    else if ( redTargets.contains( counter ) )
                    {
                        team = "red";
                    }
                    else if ( bombTargets.contains( counter ) )
                    {
                        team = "bomb";
                    }
                    
                    out.write( String.format( "<td class=\"%s\">%s</td>", team, iter.next( ) ) );
                
                    counter++;
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