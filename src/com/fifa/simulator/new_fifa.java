package com.fifa.simulator;
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

 
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
 
public class new_fifa {
    private static WebDriver driver;
    String Title = null;
    String URL = null;
    String alertText = "";
    String str;
	String[] cut;
	int i,j;
	
	String page_source= "";
	StringBuilder sb = new StringBuilder();
//	int index = page_source.indexOf("var ep = parseInt(\"");
	int index;
	long ep = 0;
	String[] split = null;
	
	long min_default = 0; 
	String id="";
	String pw="";
	static String url="";
	String log = "";
	
	
	Simulator simul = new Simulator();
	
	
	public void login(String idTest, String pwText, long minimum){
		// 
		id = idTest;
		pw = pwText;
		min_default = minimum;
	}
	
	public void url(String urlText, String min){
		url = urlText;
		min_default = Long.parseLong(min);
	}
 
    @BeforeClass
    public static void setUp() throws Exception {
//    	File subDir=new File("lib");
    	
//    	System.setProperty("webdriver.chrome.driver", "C:/Users/Administrator/workspace/PackageSimulator/bin/chromedriver.exe"); //크롬 드라이버 파일 경로설정
    	File file = new File("c:/chromedriver.exe");
    	File file2 = new File("d:/chromedriver.exe");
   		if(file.isFile()){         
   			System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe"); //크롬 드라이버 파일 경로설정		
		}else if(file2.isFile()){
			System.setProperty("webdriver.chrome.driver", "d:/chromedriver.exe"); //크롬 드라이버 파일 경로설정	
		}else{
			runner.taIntroduce.append("chromedriver.exe 파일이 없습니다. \n 파일을 C: 또는 D: 드라이브에 복사해주세요");
		}

   		 
        
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //응답시간 5초설정
        

//		http://fifaonline3.nexon.com/iteminfo/item/simulator.aspx?type=pkg&n4pid=515072829&icon=P30403// 08플래티넘
//		http://fifaonline3.nexon.com/iteminfo/item/simulator.aspx?type=pkg&n4pid=515082003&icon=P30407 //06월컵
//		http://fifaonline3.nexon.com/iteminfo/item/simulator.aspx?type=pkg&n4pid=515082001&icon=P30401 // tots
//슈퍼문      http://fifaonline3.nexon.com/iteminfo/item/simulator.aspx?type=pkg&n4pid=515090701&icon=P30417
		driver.get(url); 
		
		
	
		 driver.manage().window().maximize();
    }
 
	public void simulator(long max) throws Exception {
		min_default = max;
		while (ep < min_default) {
			page_source = driver.getPageSource();
			sb = new StringBuilder(page_source);
			index = sb.indexOf("Int(\"");
			if (index == -1) {
				String URL = driver.getCurrentUrl();
				if (URL.equals("http://bulletin.nexon.com/nxk/error.html?error=503")) {
					runner.taIntroduce.append("\n");
					runner.taIntroduce.setCaretPosition(runner.taIntroduce.getDocument().getLength());  // 맨아래로 스크롤한다.
					runner.taIntroduce.append("*************************************************\n");
					runner.taIntroduce.setCaretPosition(runner.taIntroduce.getDocument().getLength()); 
					runner.taIntroduce.append("http://bulletin.nexon.com/nxk/error.html?error=503\n 사용자 접속 증가 페이지 다운 \n 다시시작\n");
					runner.taIntroduce.setCaretPosition(runner.taIntroduce.getDocument().getLength()); 
					runner.taIntroduce.append("*************************************************\n");
					runner.taIntroduce.setCaretPosition(runner.taIntroduce.getDocument().getLength()); 
					runner.taIntroduce.append("\n");
					runner.taIntroduce.setCaretPosition(runner.taIntroduce.getDocument().getLength()); 
					System.out.println(URL);
					driver.quit();
					setUp();
					login();
				} else {
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						System.out.println("000");
						return;
					}
					page_source = driver.getPageSource();
					sb = new StringBuilder(page_source);
					index = sb.indexOf("Int(\"");
				}
			} else {
				str = page_source.substring(index, index + 20);
				split = str.split("\\D+");
				ep = Long.parseLong(split[1]);
				// System.out.println(ep);
				i++;
				if (ep < min_default) {
					driver.navigate().refresh();
					try{
						Thread.sleep(2500);
					}catch(InterruptedException e){
						System.out.println("0");
						return;
					}
				}

			}
			
			
			log = ep + " EP \n";
			System.out.println("-----------"+log);
			try{
				if (ep > 700000000) {
					j++;
					runner.taIntroduce.append(log);
					runner.taIntroduce.setCaretPosition(runner.taIntroduce.getDocument().getLength());
				}
					runner.number.setText(Integer.toString(i) + " 번째");
			}catch(NullPointerException e){
				
			}
		   
//					System.out.println(ep + " EP 최소값과 가격차이 " + (min_default - ep) + " EP " + i + " 번째 시도");
		}
	
		
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			System.out.println("1");
			return;
		}
		driver.findElement(By.className("open-all")).click();
		System.out.println("모두 개봉 클릭");

		System.out.println(ep + " 나이스!!!!");
		driver.findElement(By.className("btnRankingReg")).click();
		
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			System.out.println("2");
			return;
		}
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		runner.taIntroduce.append("=================================================\n");
		runner.taIntroduce.setText(Integer.toString(i) + " 번째\n");
		runner.taIntroduce.setCaretPosition(runner.taIntroduce.getDocument().getLength()); 
		runner.taIntroduce.append(alertText+"\n");
		runner.taIntroduce.setCaretPosition(runner.taIntroduce.getDocument().getLength()); 
		runner.taIntroduce.append("=================================================\n");	
		runner.taIntroduce.setCaretPosition(runner.taIntroduce.getDocument().getLength()); 
		alert.accept();
		System.out.println(alertText);

		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			System.out.println("3");
			return;
		}
		alert = driver.switchTo().alert();
		alertText = alert.getText();
		runner.taIntroduce.append(alertText+"\n");
		runner.taIntroduce.setCaretPosition(runner.taIntroduce.getDocument().getLength()); 
		System.out.println(alertText);
		runner.taIntroduce.append("=================================================\n");
		runner.taIntroduce.setCaretPosition(runner.taIntroduce.getDocument().getLength()); 
		alert.accept();
		if(!alertText.contains("랭킹이 등록 되어 있습니다.")){
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a"); 
			System.out.println(sdf.format(dt).toString()); 
			runner.submit.setText(split[1]+" EP   "+sdf.format(dt).toString());
			runner.tf_minimum.setText(split[1]);
			File ep = new File("d:");
	        File ep2 = new File("c:");
			try {
				if (ep.isDirectory()) {
					BufferedWriter out = new BufferedWriter(new FileWriter("d:/ep.txt"));
					out.write(split[1]); //
					out.close();
				} else {
					BufferedWriter out = new BufferedWriter(new FileWriter("c:/ep.txt"));
					out.write(split[1]);
					out.close();
				}
			} catch (IOException e1) {
				System.err.println(e1); // 에러가 있다면 메시지 출력
				runner.taIntroduce.append("C 또는 D드라이브가 없습니다.\n");
				// System.exit(1);
			}
		}


		if (ep > min_default) {
			driver.navigate().refresh();
			// Thread.sleep(2500);
			min_default = ep + 1;
			System.out.println("max_default: " + min_default);
			simulator(min_default);
		} else {
			simulator(min_default);
		}

	}
    public void login() {
    	
        driver.findElement(By.id("GNB_LoginButton")).click();   
//        
        driver.findElement(By.xpath("//*[@id='PS_Login_ID']")).sendKeys(id);  //ID
        driver.findElement(By.xpath("//*[@id='PS_Login_PW']")).sendKeys(pw); //비번
        driver.findElement(By.className("psButton01")).click(); //로그인 버튼 클릭
        
        try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			return;
		}
		try {
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
			runner.taIntroduce.append(alertText+"\n");
			runner.taIntroduce.setCaretPosition(runner.taIntroduce.getDocument().getLength());
			alert.accept();
		} // try
		catch (NoAlertPresentException Ex) {
			
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			return;
		}
		
    }
    
 
    @Test
    public void run() throws Exception {
        
    	login();
		Thread.sleep(500);
		simulator(min_default);
	
	
    }
     
  
 
    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }
 
}