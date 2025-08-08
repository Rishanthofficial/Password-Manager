import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

import DataBase.JdbcDB;


public class PassManager {
    private JFrame f;
    private PasswordGenerator passwordGenerator;
    private JdbcDB db;

    public PassManager() {
        this.passwordGenerator = new PasswordGenerator(8);
        this.db = new JdbcDB();
        db.StartConnection();
        initializeUI();
    }

    private void initializeUI() {
        Font font = new Font("Arial", Font.BOLD, 16);
        Font font2 = new Font("Arial",Font.PLAIN,16);

        f = new JFrame("Password Manager");
        ImageIcon icon = new ImageIcon(PassManager.class.getResource("image.png"));

        JLabel pass_icon = new JLabel(icon);
        pass_icon.setBounds(240,30,120,220);

        String country[] = {"none","Google","Microsoft","Amazon","Flipkart","Instagram",
        "Spotify","Facebook","Steam","Epic Games","Other"};
        JComboBox<String> webList = new JComboBox<>(country);
        webList.setBounds(170, 240,350,30);
        JLabel domain = new JLabel("Domain:");
        domain.setFont(font);
        domain.setBounds(100, 240,350,30);
        
        JLabel userLabel = new JLabel("Email/Username:");
        userLabel.setBounds(35, 280,350,30);
        userLabel.setFont(font);
        JTextField user = new JTextField();
        user.setBounds(170, 280,260,30);
        user.setFont(font2);
        JButton show = new JButton("Show");
        show.setBounds(435, 280,90,30);
        
        JLabel passlabel = new JLabel("Password:");
        passlabel.setBounds(82, 315,350,30);
        passlabel.setFont(font);
        JTextField password = new JTextField();
        password.setBounds(170, 315,200,30);
        password.setFont(font2);
        JButton gen_pass = new JButton("Auto Generate");
        gen_pass.setBounds(375, 315,150,30);
        
        JButton submit = new JButton("Add");
        submit.setBounds(170, 355,200,30);

        JButton change = new JButton("Change");
        change.setBounds(375, 355,150,30);
        
        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String domain = (String) (webList.getSelectedItem());
                String username = (String)(user.getText());
                if(domain.equals("none")){
                    JOptionPane.showMessageDialog(f, "Please select the Domain!!");
                }
                else{
                    ArrayList<String> datalist  = db.retrieveData(domain, username);
                    showTableDialog(f,datalist);
                }
            }
        });        
        
        gen_pass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                password.setText(passwordGenerator.generatePassword());
            }
        });
        
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String domain = (String) (webList.getSelectedItem());
                String username = (String)(user.getText());
                String passStr = (String)(password.getText());
                if(domain.equals("none")){
                    JOptionPane.showMessageDialog(f, "Please select the Domain!!");
                }
                else if(username.isEmpty() || passStr.isEmpty()){
                    JOptionPane.showMessageDialog(f, "Please enter Username and Password!!");
                }
                else{
                    db.StoreData(domain, username, passStr);
                    JOptionPane.showMessageDialog(f, "Password Stored");
                }
                user.setText(null);
                password.setText(null);
            }
        });

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                db.CloseConnection();
            }
        });

        change.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String domain = (String) (webList.getSelectedItem());
                String username = (String)(user.getText());
                String passStr = (String)(password.getText());
                if(domain.equals("none")){
                    JOptionPane.showMessageDialog(f, "Please select the Domain!!");
                }
                else if(username.isEmpty() || passStr.isEmpty()){
                    JOptionPane.showMessageDialog(f, "Please enter Username and Password!!");
                }
                else{
                    db.ChangePassword(domain, username, passStr);
                    JOptionPane.showMessageDialog(f, "Password Updated");
                }
                user.setText(null);
                password.setText(null);
            }
        });

        f.add(pass_icon);f.add(webList);f.add(user);f.add(password);
        f.add(gen_pass);f.add(passlabel);f.add(domain);f.add(userLabel);
        f.add(show);f.add(submit);f.add(change);
        f.setSize(600, 600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showTableDialog(JFrame parentFrame,ArrayList<String> dataList) {
        String[][] data = new String[dataList.size()][3];
        for (int i = 0; i < dataList.size(); i++) {
            String[] row = dataList.get(i).split(" ");
            data[i] = row;
        }
        String[] columnNames = {"Domain", "Username", "Password"};
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JDialog dialog = new JDialog(parentFrame, "Table Dialog", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new BorderLayout());
        dialog.add(scrollPane, BorderLayout.CENTER);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new PassManager();
    }
} 