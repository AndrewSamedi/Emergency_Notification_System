package project;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.ArrayList;

public class EmNoSystem {
    JFrame theFrame;
    JLabel label1,label2,label3,label4;
    JTextField name_user;
    JTextField lastname_user;
    JPasswordField passwordField;
    JTextField phoneNumber_user;
    JButton save_date;
    JButton log_in;
    JButton third_login;
    Container container;
    Container containerTextAdd;
    Container containerSOS;
    Container container_LogIn;
    Container container_UpDate_message;
    JLabel logIn_label1_phone;
    JLabel update_text_label;
    JTextArea textToSend;
    JTextArea addNumbers;
    JLabel newLabel1,newLabel2,newLabel3;
    JButton add_second_info;
    boolean finder_user = false;
    private String number;
    private String pool_numbers;




    public static void main(String[] args) {

        new EmNoSystem().buildSigIn();
    }



    public void buildSigIn(){

           theFrame = new JFrame("Emergency Notification System");
           theFrame.setSize(600,450);
           theFrame.setLocation(100,100);
           theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

           container = theFrame.getContentPane();
           container.setLayout(null);


           label1 = new JLabel("Name");
           label2 = new JLabel("Last Name");
           label3 = new JLabel("Password");
           label4 = new JLabel("Phone");



           label1.setBounds(50,50,100,20);
           label2.setBounds(50,100,100,20);
           label3.setBounds(50,150,100,20);
           label4.setBounds(50,200,100,20);

           container.add(label1);
           container.add(label2);
           container.add(label3);
           container.add(label4);

           name_user = new JTextField();
           name_user.setBounds(120,50,120,20);
           container.add(name_user);

           lastname_user = new JTextField();
           lastname_user.setBounds(120,100,120,20);
           container.add(lastname_user);

           passwordField = new JPasswordField();
           passwordField.setBounds(120,150,120,20);
           container.add(passwordField);

           phoneNumber_user = new JTextField();
           phoneNumber_user.setBounds(120,200,120,20);
           container.add(phoneNumber_user);

           save_date = new JButton("SIGN IN");
           Font bold = new Font("bold",Font.BOLD,12);
           save_date.setBounds(100,280,100,30);
           save_date.setForeground(Color.WHITE);
           save_date.setBackground(Color.BLACK);
           save_date.setFont(bold);
           container.add(save_date);
           save_date.addActionListener(new Save_Date_Listener());

           log_in = new JButton("LOG IN");
           log_in.setBounds(350,50, 100,20);
           log_in.setForeground(Color.WHITE);
           log_in.setBackground(Color.BLACK);
           log_in.setFont(bold);
           container.add(log_in);
           log_in.addActionListener(new ButtonLogIn_Listener());

           theFrame.setVisible(true);
    }
    public void buildLogIn(){
        theFrame.setContentPane(new Container());
        container_LogIn = theFrame.getContentPane();

        logIn_label1_phone = new JLabel("Enter your phone number:");
        logIn_label1_phone.setBounds(50,50,200,30);
        container_LogIn.add(logIn_label1_phone);

        phoneNumber_user = new JTextField();
        phoneNumber_user.setBounds(210,55,120,20);
        container_LogIn.add(phoneNumber_user);

        Font bold = new Font("bold",Font.BOLD,12);
        third_login = new JButton("LOG IN");
        third_login.setBounds(100,80, 100,20);
        third_login.setForeground(Color.WHITE);
        third_login.setBackground(Color.BLACK);
        third_login.setFont(bold);
        container_LogIn.add(third_login);
        third_login.addActionListener(new LogIn_Listener());

        theFrame.setVisible(true);
    }

    public void buildAddInfoForSend(){
        theFrame.setContentPane(new Container());
        containerTextAdd = theFrame.getContentPane();

        newLabel1 = new JLabel("Enter the text you want to send...");
        newLabel2 = new JLabel("Add the numbers...");
        newLabel3 = new JLabel("(Write down the numbers through '7' and ' ; ')");

        newLabel1.setBounds(50,50,200,20);
        newLabel2.setBounds(50,200,200,20);
        newLabel3.setBounds(50,350,300,20);

        containerTextAdd.add(newLabel1);
        containerTextAdd.add(newLabel2);
        containerTextAdd.add(newLabel3);

        textToSend = new JTextArea();
        textToSend.setBounds(50,80,400,100);
        textToSend.setLineWrap(true);
        containerTextAdd.add(textToSend);

        addNumbers = new JTextArea();
        addNumbers.setBounds(50,230,400,100);
        addNumbers.setLineWrap(true);
        containerTextAdd.add(addNumbers);

        add_second_info = new JButton("Finalize");
        add_second_info.setBounds(350,350,100,20);
        containerTextAdd.add(add_second_info);
        add_second_info.addActionListener(new SaveTextToSendListener());

        theFrame.setVisible(true);
    }

    public void build_button_SOS() {
        theFrame.setContentPane(new Container());
        containerSOS = theFrame.getContentPane();

        JButton button_SOS = new JButton("SOS");
        button_SOS.setBounds(200, 200, 200, 100);
        Font bold = new Font("bold", Font.BOLD, 32);
        button_SOS.setForeground(Color.BLACK);
        button_SOS.setBackground(Color.RED);
        button_SOS.setFont(bold);
        containerSOS.add(button_SOS);
        button_SOS.addActionListener(new SOS_Listener());


        JButton button_update_text = new JButton("Update Text Message");
        button_update_text.setBounds(380, 20, 200, 30);
        Font nextBold = new Font("bold", Font.BOLD, 12);
        button_update_text.setForeground(Color.WHITE);
        button_update_text.setBackground(Color.BLACK);
        button_update_text.setFont(nextBold);
        containerSOS.add(button_update_text);
        button_update_text.addActionListener(new UpDate_Listener());


        theFrame.setVisible(true);
    }

    public void buildUpdate_textMessage(){
        theFrame.setContentPane(new Container());
        container_UpDate_message = theFrame.getContentPane();

        update_text_label = new JLabel("Enter new text:");
        update_text_label.setBounds(50,50,200,30);
        container_UpDate_message.add(update_text_label);

        textToSend = new JTextArea();
        textToSend.setBounds(50,80,400,100);
        textToSend.setLineWrap(true);
        container_UpDate_message.add(textToSend);

        Font bold = new Font("bold", Font.BOLD, 12);
        JButton update_mess = new JButton("Update Text Message");
        update_mess.setBounds(50,200, 200,25);
        update_mess.setForeground(Color.WHITE);
        update_mess.setBackground(Color.BLACK);
        update_mess.setFont(bold);
        container_UpDate_message.add(update_mess);
        update_mess.addActionListener(new Update_text_message_Listener());

        theFrame.setVisible(true);
    }




    public class Save_Date_Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           Database_Interaction db = new Database_Interaction();
           Connection conn =  db.database_connectin("postgres","YoungBoy1306)");
           number = phoneNumber_user.getText();
           finder_user = db.search_by_number(conn,"date_users",number);
           if (finder_user == true){
                build_button_SOS();
           }else{
                db.insert_user(conn,"date_users",name_user.getText(),lastname_user.getText(),phoneNumber_user.getText(), passwordField.getText());
                buildAddInfoForSend();
           }
        }
    }

    public class ButtonLogIn_Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buildLogIn();
        }
    }
    public class LogIn_Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Database_Interaction db = new Database_Interaction();
            Connection conn =  db.database_connectin("postgres","YoungBoy1306)");
            number = phoneNumber_user.getText();
            finder_user = db.search_by_number(conn,"date_users",number);
            if(finder_user == true){
                build_button_SOS();
            }else{
                buildSigIn();
            }
        }
    }
    public class UpDate_Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buildUpdate_textMessage();
        }
    }

    public class Update_text_message_Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Database_Interaction db = new Database_Interaction();
            Connection conn = db.database_connectin("postgres","YoungBoy1306)");
            String user_id = db.return_id(conn,"date_users",number);
            db.update_text_message(conn,"text_into_message", textToSend.getText(),user_id);
            build_button_SOS();
        }
    }

    public class SaveTextToSendListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
           Database_Interaction db = new Database_Interaction();
           Connection conn = db.database_connectin("postgres","YoungBoy1306)");
           String user_id = db.return_id(conn,"date_users",number);
           db.insert_text_to_send(conn,"text_into_message",user_id,textToSend.getText(),addNumbers.getText());
        }
    }

    public class SOS_Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Database_Interaction db = new Database_Interaction();
            Connection connection = db.database_connectin("postgres","YoungBoy1306)");
            String user_id = db.return_id(connection,"date_users",number);

            String text_to_send = db.return_text_to_send(connection,"text_into_message",user_id);
            pool_numbers = db.return_pool_numbers(connection,"text_into_message",user_id);
            System.out.println(pool_numbers);

            SMSCSender sd= new SMSCSender("samedi", "fbe11ecf6ce88b1bd8c6d24ceb50f307ff81fe0a", "utf-8", true);
            sd.sendSms(pool_numbers, text_to_send, 0, "", "", 0, "", "");
            sd.getBalance();
        }
    }

}
