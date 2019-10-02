package com.dyh.rain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Rain extends Frame{
	private static final long serialVersionUID = 1L;
	public static Dimension windowDimension=Toolkit.getDefaultToolkit().getScreenSize();
	public static int WIDTH=(int)windowDimension.getWidth();
	public static int HEIGHT=(int)windowDimension.getHeight();
	public static ArrayList<RainInfo> rainInfos =new ArrayList<RainInfo>();
	class keyDown extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}
	
	class MyThread extends Thread{
		@Override
		public void run() {
			while (true) {
				try {
					repaint();
					sleep(41);
				} catch (Exception e) {
					
				}
			}
		}
	}
	public Rain(){
		this.setUndecorated(true);
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.addKeyListener(new keyDown());
		this.setBackground(Color.BLACK);
		
		creatRainInfo();
		new MyThread().start();
	}
	Image bgImage;
	@Override
	public void update(Graphics g) {
		bgImage=this.createImage(this.getWidth(),this.getHeight());
		Graphics bg=bgImage.getGraphics();
		paint(bg);
		bg.dispose();
		g.drawImage(bgImage, 0, 0,null);
	}
	@Override
	public void paint(Graphics g) {
		drawRain(g);
	}
	
	RainInfo rainInfo;
	public static int fontSize=30;
	public void creatRainInfo(){
		for(int x=0;x<WIDTH;x+=fontSize){
			rainInfo=new RainInfo(x, 0, fontSize);
			rainInfos.add(rainInfo);
		}
	}
	
	public void drawRain(Graphics g){
		for(int i=0;i<rainInfos.size();i++){
			rainInfo=rainInfos.get(i);
			rainInfo.drawRain(g);
		}
	}
}
