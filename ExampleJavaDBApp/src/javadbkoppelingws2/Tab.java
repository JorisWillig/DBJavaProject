/******************************************************************************
 * This abstract class encompasses the panels that make up the different tabs *
 * in this program. This might seem a bit tedious but it is to make the main  *
 * class a bit more neat by taking most of the Swing stuff out of it.         *
 ******************************************************************************/

package javadbkoppelingws2;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JPanel;

public abstract class Tab extends JPanel{
    
    final int WIDTH;
    final int HEIGHT;
    
    int X_MARGIN;
    int Y_MARGIN;
    int TEXT_FIELD_HEIGHT;
    int TEXT_FIELD_WIDTH;
    
    static Connection conn;
    
    public Tab(int width, int height) {
        try {
            conn = new DataSourceV2().getConnection();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        WIDTH = width;
        HEIGHT = height;
        
        X_MARGIN = WIDTH/40;
        Y_MARGIN = HEIGHT/40;
        TEXT_FIELD_HEIGHT = 30;
        TEXT_FIELD_WIDTH = 300;
        
    }
    
}
