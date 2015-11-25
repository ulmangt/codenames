function onLoad( ) {

var gameGrid = document.getElementById( "gameGrid" );
if ( gameGrid != null ) {

    for (var i = 0; i < gameGrid.rows.length; i++) {
    
        for (var j = 0; j < gameGrid.rows[i].cells.length; j++) {
        
            gameGrid.rows[i].cells[j].onclick = (function (i, j) {
            
                return function ( ) {
                    alert('R' + (i + 1) + 'C' + (j + 1));
                };
                
            }(i, j));
        }
    }
}

}