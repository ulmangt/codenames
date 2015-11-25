package codenames;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Run
{
    public static void main( String[] args ) throws Exception
    {
        // start a jetty server to host the servlet
        Server server = new Server( 18081 );

        ServletContextHandler context = new ServletContextHandler( ServletContextHandler.SESSIONS );
        context.setContextPath( "/" );
        server.setHandler( context );

        context.addServlet( new ServletHolder( new ServletPuzzle( ) ), "/puzzle" );
        context.addServlet( new ServletHolder( new ServletAnswer( ) ), "/answer" );

        server.start( );
        
        System.out.println( WordDatabase.getWordList( ) );
    }
}
