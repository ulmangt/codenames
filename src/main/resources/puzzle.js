function onLoad( ) {

var gameGrid = document.getElementById( "gameGrid" );
if ( gameGrid != null ) {

    for (var i = 0; i < gameGrid.rows.length; i++) {
    
        for (var j = 0; j < gameGrid.rows[i].cells.length; j++) {
        
            var cell = gameGrid.rows[i].cells[j];
        
            cell.onclick = (function (i, j) {
            
                return function ( ) {
                    !$(this).hasClass( "answer" ) {
                    	$(this).toggleClass( "clicked" );
                    }
                };
                
            }(i, j));
        }
    }
}

}