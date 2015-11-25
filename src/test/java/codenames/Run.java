package codenames;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
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

        context.addServlet( new ServletHolder( new ServletPuzzle( ) ), "/puzzle" );
        context.addServlet( new ServletHolder( new ServletAnswer( ) ), "/answer" );

        ResourceHandler resource_handler = new ResourceHandler( );
        resource_handler.setWelcomeFiles( new String[] { "nounlist.txt" } );
        resource_handler.setResourceBase("src/main/resources");
        
        HandlerList handlers = new HandlerList( );
        handlers.setHandlers( new Handler[] { context, resource_handler, new DefaultHandler( ) } );
        server.setHandler( handlers );

        server.start( );

        System.out.println( WordDatabase.getWordList( ) );
    }
}
