 
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class UDP_Client extends JFrame implements ActionListener 
{
	JTextField txtfield;
	JTextArea txtarea;
	byte[] serverbffr,clientbffr;
	String senddata;	
	DatagramSocket client,server;
	JButton button1;
 
	 public static void main(String[] args)
	{
 		UDP_Client obj=new UDP_Client();
	}

 
	public UDP_Client()
        {
		this.setSize(300,300);
		this.setTitle("Client");
		this.setBackground(Color.black);
		txtfield=new JTextField();
		txtfield.setBackground(Color.white);
		txtfield.setForeground(Color.blue);
		this.add(txtfield,BorderLayout.NORTH);
		txtarea=new JTextArea();
		txtarea.setBackground(Color.black);
		txtarea.setForeground(Color.green);
 
 
		this.add(txtarea,BorderLayout.CENTER);
		button1=new JButton("SEND");
		this.add(button1,BorderLayout.SOUTH);
		button1.addActionListener(this);
		this.setVisible(true);
		serverbffr=new byte[1024];
		clientbffr=new byte[1024];
		//setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
	//****************************************************************
 
		try
		{
			 client=new DatagramSocket();	
			 server=new DatagramSocket(9999);
			while(true)
			{
            			DatagramPacket datapack=new DatagramPacket(serverbffr,serverbffr.length);
                                InetAddress IP =InetAddress.getByName("localhost");
				client.receive(datapack);
				String msg=new String(datapack.getData());
				//String value=display.getText();
				txtarea.append("\nServer:"+msg);
                        }
                                
		}
 
			catch(Exception e){}	
 
	
 
	 
 
        }
         
		 public void actionPerformed(ActionEvent e)
		{
			try
			{
			if(e.getActionCommand()=="SEND")
			{
                            
                                InetAddress IP =InetAddress.getByName("localhost");
                                 String message=txtfield.getText();
				clientbffr=message.getBytes();
				DatagramPacket sendpack=new DatagramPacket(clientbffr,clientbffr.length,IP,9999);
				client.send(sendpack);
				txtarea.append("\nMyself:"+message);
				txtfield.setText("");
			}
			}
			catch(Exception a){}
		}
 
}


    

