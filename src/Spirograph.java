import java.awt.Color;
import java.util.ArrayList;

import processing.core.*;

public class Spirograph extends PApplet {

	public static void main(String[] args) {
		PApplet.main(new String[] {"Spirograph"});
	}
	
	float theta = (float) 1.5708;
	float resolution = (float) .01;
	float R = 0;
	float r = 0;
	float a = 0;
	int red = 255;
	int green = 255;
	int blue = 255;
	String s;
	ArrayList<PVector> path = new ArrayList<PVector>();
	Color c;

	public void settings() {
		  size(600, 600);
		 path = new ArrayList<PVector>();

//		  //radius of ring
		  //R = 300;
//
//		  //radius of disk
		  //r =50;
//
//		  //distance from trace point in disk to center of disk
		  //a = 20;
		 // c = new Color((int)(Math.random() * 0x1000000));
	   // 	R =  (float) Math.floor(Math.random()*(50-300+1)+50);
	   // 	r =  (float) Math.floor(Math.random()*(1-100+1)+1);
	   // 	a =  (float) Math.floor(Math.random()*(1-50+1)+1);
		  
		  mouseClicked();
	}

public void mouseClicked() {
	
	
    	path = new ArrayList<PVector>();
    	R =  (float) Math.floor(Math.random()*(200));
    	r =  (float) Math.floor(Math.random()*(200));
    	a =  (float) Math.floor(Math.random()*(200));
    	
    	red = (int) Math.floor(Math.random()*(255-100+1)+100);
    	green = (int) Math.floor(Math.random()*(255-100+1)+100);
    	blue = (int)  Math.floor(Math.random()*(255-100+1)+100);
    	


		s = "R:" + R  + "r:" + r  + "a:" + a;
		
}
	
	
	void update(){
	    theta += resolution;
	    //float x = (R+r) * cos(theta) + a * cos(((R-r)/r)*theta);
	    //float y = (R+r) * sin(theta) - a * sin(((R-r)/r)*theta);
	    
	    //float x = (R+r) * cos(theta) - a * cos(((R-r)*theta)/r);
	    //float y = (R+r) * sin(theta) - a * sin(((R-r)*theta)/r);
	   
	    
	    float x = (R-r)*cos(theta) + a*cos(((R-r)/r)*theta);
	    float y = (R-r)*sin(theta) - a*sin(((R-r)/r)*theta);
	    
	    PVector pv = new PVector(x+300,y+300);
		path.add(pv);
	}	
	
	public void setup() {

	}

	public void draw() {
		  background(55);
		  update();
		  text(s, 10, 590);
		  stroke(200,200,200,100);
		  ellipse(300,300,R+R, R+R);
		  


		  //center of disk
		  float innerCircleCenX = path.get(path.size()-1).x;
		  float innerCircleCenY = path.get(path.size()-1).y;
		  //float penX = innerCircleCenX + a * cos(theta);
		  //float penY = innerCircleCenX + a * sin(theta);
		  
		  
	      float rsum =  R - r;
	      innerCircleCenX = 300 + rsum * cos(theta);
	      innerCircleCenY = 300 + rsum * sin(theta);
				  
	      
		  ellipse(innerCircleCenX,innerCircleCenY,r*2, r*2);
		  ellipse(path.get(path.size()-1).x,path.get(path.size()-1).y,5, 5);
		  
		  //line from origin to center of disk
		  line(300,300, innerCircleCenX, innerCircleCenY);
		  //line from center of disk to pen circle
		  line(path.get(path.size()-1).x,path.get(path.size()-1).y, innerCircleCenX, innerCircleCenY);
		  noFill();
		  beginShape();
		  
		  
		  for (PVector point : path){
		    vertex(point.x, point.y);
		    stroke(red,green,blue,255);
		    //line(path.get(path.size()-1).x,path.get(path.size()-1).y, point.x, point.y);
		  }
		  endShape();
	}
}