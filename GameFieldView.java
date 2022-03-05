package View;

import Model.gamefield.Cell;
import Model.gamefield.Direction;
import Model.gamefield.GameField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Виджет для отрисовки поля и всего того, что на нем. Так же управляет робот с помощью клавиатуры
public class GameFieldView extends JPanel {

    private final GameField _field;

    public GameFieldView(GameField field) {
        _field = field;

        setLayout(new GridLayout(_field.height(), _field.width()));
//hello
        Dimension fieldDimension = new Dimension(CellWidget.CELL_SIZE*_field.width(), CellWidget.CELL_SIZE*_field.height());

        setPreferredSize(fieldDimension);
        setMinimumSize(fieldDimension);
        setMaximumSize(fieldDimension);

        for (Cell c: _field) {
            add( new CellWidget( c ) );
        }
//done
        addKeyListener( new KeyController() );
        setFocusable(true);
    }

    private class KeyController implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();

            if(code == KeyEvent.VK_UP) {         // перемещаемся вверх
                _field.robot().move(Direction.north());
            }
            else if(code == KeyEvent.VK_DOWN) {  // перемещаемся вниз
                _field.robot().move(Direction.south());
            }
            else if(code == KeyEvent.VK_LEFT) {  // перемещаемся влево
                _field.robot().move(Direction.west());
            }
            else if(code == KeyEvent.VK_RIGHT) { // перемещаемся вправо
                _field.robot().move(Direction.east());
            }

            repaint(); // Просим операционную систему перерисовать себя
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
