import javax.swing.*; 
import javax.swing.table.DefaultTableModel; 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
 
public class MidtermLab { 
    private JFrame frame; 
    private JTable table; 
    private DefaultTableModel tableModel; 
    private JButton animateButton; 
    private Timer timer; 
    private int buttonX = 20; 
    private boolean movingRight = true; 
 
    public MidtermLab() { 
        frame = new JFrame("JTable with Animation"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(500, 300); 
        frame.setLayout(new BorderLayout()); 
 
         
        String[] columns = {"ID", "Name"}; 
        tableModel = new DefaultTableModel(columns, 0); 
        table = new JTable(tableModel); 
        frame.add(new JScrollPane(table), BorderLayout.CENTER); 
 
       
        JPanel panel = new JPanel(); 
        JButton addButton = new JButton("Add"); 
        JButton deleteButton = new JButton("Delete"); 
        animateButton = new JButton("Start"); 
        panel.add(addButton); 
        panel.add(deleteButton); 
        panel.add(animateButton); 
        frame.add(panel, BorderLayout.SOUTH); 
 
         
        addButton.addActionListener(e -> tableModel.addRow(new 
      Object[]{tableModel.getRowCount() + 1, "New"})); 
        deleteButton.addActionListener(e -> { 
            int selectedRow = table.getSelectedRow(); 
            if (selectedRow != -1) 
tableModel.removeRow(selectedRow); 
        }); 
 
         
        timer = new Timer(20, e -> { 
            buttonX += movingRight ? 2 : -2; 
            if (buttonX >= frame.getWidth() - 100) movingRight = false; 
            if (buttonX <= 20) movingRight = true; 
            animateButton.setLocation(buttonX, 10); 
        }); 
 
        animateButton.addActionListener(e -> { 
            if (timer.isRunning()) { 
                timer.stop(); 
                animateButton.setText("Start"); 
            } else { 
                timer.start(); 
                animateButton.setText("Stop"); 
            } 
        }); 
 
        frame.setVisible(true); 
    } 
 
    public static void main(String[] args) { 
        new MidtermLab(); 
    } 
} 