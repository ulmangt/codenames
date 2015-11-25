package codenames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class WordDatabase
{
    static
    {
        readWordList( );
    }

    protected static List<String> wordList;

    protected static final void readWordList( )
    {
        List<String> tempList = Lists.newArrayList( );
        
        try (InputStream in = WordDatabase.class.getClassLoader( ).getResourceAsStream( "nounlist.txt" ))
        {
            BufferedReader reader = new BufferedReader( new InputStreamReader( in ) );
            
            String line = null;
            
            while ( ( line = reader.readLine( ) ) != null )
            {
                tempList.add( line.trim( ) );
            }
            
            wordList = Collections.unmodifiableList( tempList );
        }
        catch ( IOException e )
        {
            e.printStackTrace( );
        }
    }
    
    public static final List<String> getWordList( )
    {
        return wordList;
    }
}
