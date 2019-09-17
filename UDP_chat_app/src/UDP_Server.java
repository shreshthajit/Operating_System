 

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
import java.io.*;
 
import java.net.*;
 








public class UDP_Server extends JFrame implements ActionListener
{
	JTextField txtfield;
	JTextArea txtarea;
	byte[] serverbffr,clientbffr;
	DatagramSocket client,server;
	JButton button1;
 
 
	public static void main(String [] args)
	{
		UDP_Server server=new UDP_Server();
	}
 
 
	public UDP_Server()
	{	
		this.setSize(300,300);
		this.setTitle("Server");
		txtfield=new JTextField(100);
		txtfield.setBackground(Color.white);
		txtfield.setForeground(Color.blue);
 
		this.add(txtfield,BorderLayout.NORTH);
		txtarea=new JTextArea();
		this.add(txtarea,BorderLayout.CENTER);
		txtarea.setBackground(Color.black);
		txtarea.setForeground(Color.green);
 
		button1=new JButton("SEND");
		this.add(button1,BorderLayout.SOUTH);
		button1.addActionListener(this);
		this.setVisible(true);
		serverbffr=new byte[1024];
		clientbffr=new byte[1024];
		//setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
 
	//*******************************************************************
		try
		{
 			client=new DatagramSocket();
 			server=new DatagramSocket(9999);
			while(true)
			{
                               
            			DatagramPacket datapack=new DatagramPacket(clientbffr,clientbffr.length);
                                ///InetAddress IP=datapack.getAddress();
				server.receive(datapack);
				String msg=new String(datapack.getData());
				txtarea.append("\nClient:"+msg);
                    
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
                                DatagramPacket datapack=new DatagramPacket(serverbffr,serverbffr.length);
                                InetAddress IP=datapack.getAddress();
                                //getByName("localhost");
				String message=txtfield.getText();
				serverbffr=message.getBytes();
				DatagramPacket sendpack=new DatagramPacket(serverbffr,serverbffr.length,IP,9999);
				server.send(sendpack);
				txtarea.append("\nMyself:"+message);
				txtfield.setText("");
			}
			}
			catch(Exception a){}
		}
 

    
}
