package com.dyh.rain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class RainInfo {
	public int x;
	public int y;
	public int fontSize;
	public ArrayList<RainDrop> rainDrops=new ArrayList<RainDrop>();
	public RainInfo(int x, int y,int fontSize) {
		super();
		this.x = x;
		this.y = y;
		this.fontSize=fontSize;
		creatRainDrop();
	}
	
	public Color color_01=Color.WHITE;//白色
	public Color color_02=Color.GRAY;//灰色
	RainDrop rainDrop;
	Random random=new Random();
	public int num=0;//白色的字个数
	public void creatRainDrop(){
		for(int y=0;y<=Rain.HEIGHT;y+=fontSize){
			if(num<8){
				rainDrop=new RainDrop(x, y,(char)(random.nextInt(2)+48),random.nextInt(5)==0?color_01:color_02);
				if(rainDrop.color==color_01){
					num++;
				}
			}else {
				rainDrop=new RainDrop(x, y,(char)(random.nextInt(2)+48),color_02);
			}
			rainDrops.add(rainDrop);
		}
	}
	
	public void drawRain(Graphics g){
		g.setFont(new Font("宋体",Font.BOLD,fontSize));
		for(int i=0;i<rainDrops.size();i++){
			rainDrop=rainDrops.get(i);
			g.setColor(rainDrop.color);
			g.drawString(rainDrop.value+"", rainDrop.x, rainDrop.y);
		}
		//if(random.nextInt(5)==1)
			changeColor();
	}
	
	public void changeColor(){
		for(int i=rainDrops.size()-1;i>=0;i--){
			rainDrop=rainDrops.get(i);
			if(rainDrop.color==color_01&&i+1<rainDrops.size()){
				rainDrop.color=color_02;
				rainDrops.get(i+1).color=color_01;
			}
			if(rainDrop.color==color_01&&i+1==rainDrops.size()){
				rainDrop.color=color_02;
				rainDrops.get(0).color=color_01;
			}
		}
	}
}
