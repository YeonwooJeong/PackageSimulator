package com.fifa.simulator;

import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

/*
 * Version 1.0
*/
class runner extends Thread{
	Simulator simul = new Simulator();
	public    JLabel labelIntroduce;
	public static new_fifa fifa = new new_fifa();
	public  static  JTextField tf_ip, tf_id , tf_url;
	public static JTextField tf_minimum;
	public  static  JPasswordField tf_pw;
	public static JLabel submit,number;
	public static JTextArea taIntroduce;
	
	public void run(){
		String pw = "";
        // 텍스트 필드값 가져오기
        String id = tf_id.getText();
        String url = tf_url.getText();
        String minimum = tf_minimum.getText();
        char[] secret_pw = tf_pw.getPassword();
        for(char cha : secret_pw){
        	Character.toString(cha);
        	pw += (pw.equals("")) ? ""+cha+"" : ""+cha+"";
        }
        
        
        fifa.url(url,minimum);

        try {
			fifa.setUp();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        fifa.login(id, pw, Long.parseLong(minimum));
        
        try {
			fifa.run();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
}



class JPanel033 extends JPanel{        // 3번째 패널
	runner thread = new runner();
    
	// 클래스 멤버 필드 설정
	private    JLabel name;
    private    JLabel ip;
    private    JLabel id;
    private    JLabel pw;
    private    JLabel url;
    private    JLabel minimum;
            
    private    JCheckBox checkId;
    private    JCheckBox checkPw;
    private    JCheckBox checkUrl;
    private    JCheckBox checkMinimum;
    private    JCheckBox  [] checkbox= new JCheckBox[4];
    
    private    JButton buttonSave;
    private    JButton buttonStop;
    private    JButton buttonRestart;
    public boolean check = true; 
    
    public JPanel033() {         // 3번째 패널 생성자
        
        setLayout(null);
        
        // 라벨
        name = new JLabel("by. YWJ");
        name.setSize(100, 20);   
        name.setLocation(2, 0);
        
//        ip = new JLabel("IP 입력: ");
//        ip.setSize(100, 20);   // setBounds가 아니면 setSize와 setLocation을 동시에 사용해야함
//        ip.setLocation(20, 50);
        
        id = new JLabel("아이디: ");
        id.setBounds(20,30,100,20);
        pw = new JLabel("암호: ");
        pw.setBounds(20,80,100,20);
        url = new JLabel("패키지 주소: ");
        url.setBounds(20,130,100,20);
        
        minimum = new JLabel("최소값: ");
        minimum.setBounds(20,180,100,20);
        
        
        
        checkbox[0] = new JCheckBox("ID 저장");
        checkbox[0] .setBounds(300,100,100,20);
        checkbox[1]= new JCheckBox("PW 저장"); 
        checkbox[1].setBounds(300,150,100,20);
        checkbox[2] = new JCheckBox("저장");
        checkbox[2].setSelected(true);
        checkbox[2].setBounds(300,130,100,20);
        checkbox[3] = new JCheckBox("저장");
        checkbox[3].setSelected(true);
        checkbox[3].setBounds(300,180,100,20);
        
        thread.labelIntroduce = new JLabel("현재 등록된 금액: ");
        thread.labelIntroduce.setBounds(10,280,140,20);
        
        runner.submit = new JLabel();
        runner.submit.setBounds(115,280,400,20);
        runner.submit.setText("0 EP");
        
        runner.number = new JLabel();
        runner.number.setBounds(10,450,100,20);
        runner.number.setText("시도횟수");
            
//        // 텍스트 필드
//        thread.tf_ip = new JTextField();              // 이름 입력 부분
//        thread.tf_ip.setBounds(100,50,200,20);    //   위지 와 사이즈
        // 텍스트 필드
        runner.tf_id = new JTextField();             
        runner.tf_id.setBounds(100,30,200,20);
//        thread.tf_id.setText("todriver08@gmail.com");
        // 텍스트 필드
        runner.tf_pw = new JPasswordField();       
        runner.tf_pw.setEchoChar('*');
        runner.tf_pw.setBounds(100,80,200,20);
        // 텍스트 필드
        runner.tf_url = new JTextField();              
        runner.tf_url.setBounds(100,130,200,20);    
//        thread.tf_url.setText("http://fifaonline3.nexon.com/iteminfo/item/simulator.aspx?type=pkg&n4pid=515091601&icon=P30501");
        // 텍스트 필드
        runner.tf_minimum = new JTextField();          
//        thread.tf_minimum.setText("850000000");
        runner.tf_minimum.setBounds(100,180,200,20);    
        
            
        // 텍스트 에어리어 , 결과 출력부분
        thread.taIntroduce = new JTextArea();
        thread.taIntroduce.setEditable(false);
        // JTextArea는 JScrollPane를 써야 스크롤바가 생긴다.
        JScrollPane scrollPane = new JScrollPane(thread.taIntroduce);
        scrollPane.setBounds(10,300,365,150);
        
        // 버튼        
        buttonSave = new JButton("시작");
        buttonSave.setBounds(80,230,100,20);
        buttonSave.addActionListener(new EventHandlerSave());   // 버튼 리스너 등록
        
        buttonStop = new JButton("정지");
        buttonStop.setBounds(220,230,100,20);
        buttonStop.addActionListener(new EventHandlerStop());   // 버튼 리스너 등록
        
        buttonRestart = new JButton("재시작");
        buttonRestart.setBounds(10,230,100,20);
        buttonRestart.addActionListener(new EventHandlerRestart());   // 버튼 리스너 등록
        // 프레임의 컨테이너의 각종 컴포넌트들을 등록
        add(name);
//        add(ip);
//        add(tf_ip);
        add(thread.labelIntroduce);
        
        add(id);
        add(thread.tf_id);
        
        add(pw);
        add(thread.tf_pw);
        
        add(url);
        add(thread.tf_url);
        
        add(minimum);
        add(thread.tf_minimum);
        
        add(thread.submit);
        add(thread.number);
        
//        add(checkbox[0]);
//        add(checkbox[1]);
        add(checkbox[2]);
        add(checkbox[3]);
        
    
        add(scrollPane);
        add(buttonSave);
        add(buttonStop);
//        add(buttonRestart);
        
        
        File file = new File("d:/save.txt");
        File file2 = new File("c:/save.txt");
        try {
            if(file.isFile()){                      //save.txt 존재 유무 체크
                BufferedReader outReader = new BufferedReader(new FileReader("d:/save.txt")); 
                String read = outReader.readLine();
                System.out.println(read);
                thread.tf_url.setText(read);
                outReader.close();
            }else if(file2.isFile()){
            	BufferedReader outReader = new BufferedReader(new FileReader("c:/save.txt")); 
                String read = outReader.readLine();
                System.out.println(read);
                thread.tf_url.setText(read);
                outReader.close();
            }
        } catch (IOException e1) {
            System.err.println(e1); // 에러가 있다면 메시지 출력
            thread.taIntroduce.append("C 또는 D드라이브가 없습니다.\n");
//            System.exit(1);
        }
        
        File ep = new File("d:/ep.txt");
        File ep2 = new File("c:/ep.txt");
        try {
            if(ep.isFile()){                      //save.txt 존재 유무 체크
                BufferedReader outReader = new BufferedReader(new FileReader("d:/ep.txt")); 
                String read = outReader.readLine();
                System.out.println(read);
                thread.tf_minimum.setText(read);
                outReader.close();
            }else if(ep2.isFile()){
            	BufferedReader outReader = new BufferedReader(new FileReader("c:/ep.txt")); 
                String read = outReader.readLine();
                System.out.println(read);
                thread.tf_minimum.setText(read);
                outReader.close();
            }
        } catch (IOException e1) {
            System.err.println(e1); // 에러가 있다면 메시지 출력
            thread.taIntroduce.append("C 또는 D드라이브가 없습니다.\n");
//            System.exit(1);
        }
    }
    
    class EventHandlerSave implements ActionListener{     // 
        public void actionPerformed(ActionEvent e){
			File file = new File("d:/");
			File file2 = new File("c:/");
			
			if (checkbox[2].isSelected()) {
				try {
					if (file.isDirectory()) {
						BufferedWriter out = new BufferedWriter(new FileWriter("d:/save.txt"));
						out.write(thread.tf_url.getText()); //
						out.close();
					} else {
						BufferedWriter out = new BufferedWriter(new FileWriter("c:/save.txt"));
						out.write(thread.tf_url.getText());
						out.close();
					}
				} catch (IOException e1) {
					System.err.println(e1); // 에러가 있다면 메시지 출력
					thread.taIntroduce.append("C 또는 D드라이브가 없습니다.\n");
					// System.exit(1);
				}
			}
			
			File ep = new File("d:");
	        File ep2 = new File("c:");
			if (checkbox[3].isSelected()) {
				try {
					if (ep.isDirectory()) {
						BufferedWriter out = new BufferedWriter(new FileWriter("d:/ep.txt"));
						out.write(thread.tf_minimum.getText()); //
						out.close();
					} else {
						BufferedWriter out = new BufferedWriter(new FileWriter("c:/ep.txt"));
						out.write(thread.tf_minimum.getText());
						out.close();
					}
				} catch (IOException e1) {
					System.err.println(e1); // 에러가 있다면 메시지 출력
					thread.taIntroduce.append("C 또는 D드라이브가 없습니다.\n");
					// System.exit(1);
				}
			}
			if(check){
				thread.start();	
			}else{
				runner thread = new runner();
				thread.start();	
				check = true;
			}
			

		}
    }   
    class EventHandlerStop implements ActionListener{     // 
        public void actionPerformed(ActionEvent e){
        	System.out.println("정지!");
        	check = false;
        	System.out.println(check);
        	thread.interrupt();
        	thread.taIntroduce.append("시뮬레이터 종료! 다시 시작하려면 시작을 눌러주세요\n");
        	
        }
    } 
    
    class EventHandlerRestart implements ActionListener{     // 
        public void actionPerformed(ActionEvent e){
            
//        	restart.jpanel03.thread.start();
        	
        }
    } 
//    public boolean flag(boolean boo){
//    	
//		return check;
//	}
}


public class Simulator extends JFrame{
    
    public JPanel033 jpanel03 = null;
   
    public static void main(String[] args) {
        Simulator win = new Simulator();
        
        win.setTitle("FIFA Package Simulator");
        win.jpanel03 = new JPanel033();
        
        URL imageURL = Simulator.class.getClassLoader().getResource("p.PNG");
        System.out.println(imageURL);
        ImageIcon img = new ImageIcon(imageURL);
//        ImageIcon img = new ImageIcon("resource/p.PNG");
        win.setIconImage(img.getImage());
        
//        jtab.addTab("화면3", win.jpanel03 );
        win.add(win.jpanel03);
        win.setSize(400,520);
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
    }
    
}
