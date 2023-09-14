/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;


import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.PaintContext;
import java.awt.Point;
import java.awt.Stroke;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;

/**
 *
 * @author saisanwariya
 */
public class DrawingApplicationFrame extends JFrame
{
       
    JPanel firstPanel = new JPanel();
    JPanel secondPanel = new JPanel();
    DrawPanel canvas = new DrawPanel();
    JPanel topPanel = new JPanel();
    JButton undoButton;
    JButton clearButton;
    JComboBox shapeComboBox;
    String[] listShapes = {"Line", "Oval", "Rectangle"};
    JCheckBox isFilled;
    JCheckBox hasGradient;
    
    
    JButton gradient1;
    JButton gradient2;
    JTextField strokeWidth;
    JTextField strokeDashLenght;
    JCheckBox isDashed;
    
    
    int fromClickX = 0;
    int toClickX = 0;
    int fromClickY = 20;
    int toClickY = 20;
    boolean isDragging = false;
    public ArrayList<MyShapes> myShapes = new ArrayList();
    
    
    JLabel coordinatesLabel = new JLabel("");
   
    
    public void chooseColor(JButton obj){
        JColorChooser Selectorcolor=new JColorChooser();
        Color color=Selectorcolor.showDialog(null, "Choose a color", Color.BLUE);
        obj.setBackground(color);
    }
    public void emptyCanvas(){
        myShapes.removeAll(myShapes);
        canvas.myRepaint();
    }
    public void deleteLastShape(){
        if(myShapes.size()>0){
            myShapes.remove(myShapes.size()-1);
            canvas.myRepaint();
        }
    }
    
    public DrawingApplicationFrame()
    {
       
        GridLayout gridBothLines = new GridLayout(2,1);
        FlowLayout firstLineFlow = new FlowLayout();
        FlowLayout secondLineFlow = new FlowLayout();
        BorderLayout grid = new BorderLayout();
        
       
        undoButton = new JButton("Undo");
        clearButton = new JButton("Clear");
        shapeComboBox = new JComboBox(listShapes);
        isFilled = new JCheckBox();
        hasGradient = new JCheckBox();
        gradient1 = new JButton("gradient1");  
        gradient2 = new JButton("gradient2");
        strokeWidth = new JTextField(2);
        strokeDashLenght = new JTextField(2);
        isDashed = new JCheckBox();
        
        
        canvas.setBackground(Color.white);
        this.setLayout(grid);
        topPanel.setLayout(gridBothLines);
        firstPanel.setLayout(firstLineFlow);
        secondPanel.setLayout(secondLineFlow);
        
        
        
        firstPanel.add(undoButton);
        firstPanel.add(clearButton);
        firstPanel.add(shapeComboBox);
        firstPanel.add(new JLabel("fill shape:"));
        firstPanel.add(isFilled);
        firstPanel.add(new JLabel("fill with gradient:"));
        firstPanel.add(hasGradient);
        
        
        secondPanel.add(gradient1);
        secondPanel.add(gradient2);
        secondPanel.add(new JLabel("Line Width:"));
        secondPanel.add(strokeWidth);
        secondPanel.add(new JLabel("Dash Length:"));
        secondPanel.add(strokeDashLenght);
        secondPanel.add(new JLabel("Dashed:"));
        secondPanel.add(isDashed);
        
        
        topPanel.add(firstPanel);
        topPanel.add(secondPanel);
        
        
        this.add(topPanel, BorderLayout.NORTH);
        this.add(canvas, BorderLayout.CENTER);
        this.add(coordinatesLabel, BorderLayout.SOUTH); 
        
        gradient1.addActionListener(e -> chooseColor(gradient1) );
        gradient2.addActionListener(e -> chooseColor(gradient2) );
        canvas.addMouseListener(canvas.ml2);
        canvas.addMouseMotionListener(canvas.ml);
        undoButton.addActionListener(e->deleteLastShape());
        clearButton.addActionListener(e->emptyCanvas());
    }

    
    private class DrawPanel extends JPanel
    {
        
        MouseMotionListener ml =new MouseHandler();
        MouseListener ml2 =new MouseHandler();
        DrawPanel self;
        public DrawPanel()
        {
            self=this;
        }
        public void myRepaint(){
            this.validate();
            this.repaint();
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            for (MyShapes shape : myShapes) {
                shape.draw(g2d);
            }
        }


        private class MouseHandler extends MouseAdapter implements MouseMotionListener
        {

            public void mousePressed(MouseEvent event)
            {
                fromClickX = event.getX();
                fromClickY = event.getY();
                isDragging = true;
            }

            public void mouseReleased(MouseEvent event)
            {
                
                isDragging = false;
                Point p1 = new Point(fromClickX, fromClickY);
                Point p2 = new Point(toClickX, toClickY);
                Paint paint;
                if(hasGradient.isSelected()){
                    paint = new GradientPaint(0, 0, gradient1.getBackground(), 50, 50, gradient2.getBackground(), true);
                }else{
                    paint = new GradientPaint(0, 0, gradient1.getBackground(), 50, 50, gradient1.getBackground(), true);
                }
                int lineWidth = 1;
                if(isNumeric(strokeWidth.getText())){
                    int strokeInt = Integer.parseInt(strokeWidth.getText());
                    if(strokeInt<1){
                        strokeInt = 1;
                    }
                    lineWidth = strokeInt;
                }
                Stroke strk;
                if (isDashed.isSelected()){
                    float dash[] = { 10.0f };
                    if(isNumeric(strokeDashLenght.getText())){
                        float strokeLength = Integer.parseInt(strokeDashLenght.getText());
                        if(strokeLength>0){
                            dash[0]=strokeLength;
                        }
                        
                    }
                    strk = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dash, 0);
                } else{
                    strk = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                }

                boolean filled = (isFilled.isSelected()?true:false);
                MyShapes shape1;
                switch(shapeComboBox.getSelectedItem().toString()){
                    case "Oval":
                        shape1=new MyOval(p1, p2, paint,  strk,  filled);
                        break;
                    case "Rectangle":
                        shape1=new MyRectangle(p1, p2, paint,  strk,  filled);
                        break;
                    default:
                        shape1=new MyLine(p1, p2, paint,  strk);
                        break;
                }
                myShapes.add(shape1);
                
                self.myRepaint();
            }

            @Override
            public void mouseDragged(MouseEvent event)
            {
                if(isDragging){
                    toClickX = event.getX();
                    toClickY = event.getY();
                    
                }
            }

            @Override
            public void mouseMoved(MouseEvent event)
            {
                coordinatesLabel.setText("X:"+event.getX()+",Y:"+event.getY());
            }
        }

    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
