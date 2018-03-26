import java.awt.Color;
import java.util.ArrayList;

import processing.core.*;

public class Spirograph extends PApplet {

	public static void main(String[] args) {
		PApplet.main(new String[] {"Spirograph"});
	}
	
	
	private float theta = (float) 1.5708;
	private float resolution = (float) .01;
	private float R = 0;
	private float r = 0;
	private float a = 0;
	private int red = 255;
	private int green = 255;
	private int blue = 255;
	private String s;
	private ArrayList<PVector> path = new ArrayList<PVector>();
	private ArrayList<PVector> history =  new ArrayList<PVector>();
	private Color c;
	private int itteration = 1;
	
	
	public void settings() {
		  size(600, 600);
		 path = new ArrayList<PVector>();
	    	R =  (float) Math.floor(Math.random()*(200));
	    	r =  (float) Math.floor(Math.random()*(200));
	    	a =  (float) Math.floor(Math.random()*(200));
	    	
	    	red = (int) Math.floor(Math.random()*(255-100+1)+100);
	    	green = (int) Math.floor(Math.random()*(255-100+1)+100);
	    	blue = (int)  Math.floor(Math.random()*(255-100+1)+100);

	  
	    	PVector thisOne = new PVector((int)Math.floor(R), (int)Math.floor(r), (int)Math.floor(a));
	    	history.add(thisOne);
			s = "R:" + R  + "r:" + r  + "a:" + a;	
		  
		  
	}

public void mouseClicked() {
	
	
	
	if (mouseButton == LEFT){
		itteration++;
	}
	else if (mouseButton == RIGHT) {
		itteration--;
		if (itteration<1) {
			itteration=1;
		}
	}
	
	
	//we want an previos spirographic
	if (itteration < history.size()) {
		path = new ArrayList<PVector>();
		PVector thatOne = history.get(itteration-1);
		R = thatOne.x;
		r = thatOne.y;
		a = thatOne.z;
		s = "R:" + R  + "r:" + r  + "a:" + a;
	}
	
	//we want an previos spirographic
	if (itteration >=history.size()) {
		path = new ArrayList<PVector>();
    	R =  (float) Math.floor(Math.random()*(200));
    	r =  (float) Math.floor(Math.random()*(200));
    	a =  (float) Math.floor(Math.random()*(200));
    	
    	red = (int) Math.floor(Math.random()*(255-100+1)+100);
    	green = (int) Math.floor(Math.random()*(255-100+1)+100);
    	blue = (int)  Math.floor(Math.random()*(255-100+1)+100);
    	PVector newOne = new PVector((int)Math.floor(R), (int)Math.floor(r), (int)Math.floor(a));
    	history.add(newOne);
		s = "R:" + R  + "r:" + r  + "a:" + a;
	}
	
	
	
	
//	if (mouseButton == LEFT){
//		
//		if (itteration == history.size()){
//		path = new ArrayList<PVector>();
//    	R =  (float) Math.floor(Math.random()*(200));
//    	r =  (float) Math.floor(Math.random()*(200));
//    	a =  (float) Math.floor(Math.random()*(200));
//    	
//    	red = (int) Math.floor(Math.random()*(255-100+1)+100);
//    	green = (int) Math.floor(Math.random()*(255-100+1)+100);
//    	blue = (int)  Math.floor(Math.random()*(255-100+1)+100);
//
//  
//    	PVector thisOne = new PVector((int)Math.floor(R), (int)Math.floor(r), (int)Math.floor(a));
//    	history.add(thisOne);
//		s = "R:" + R  + "r:" + r  + "a:" + a;
//		itteration++;
//		}
//		else {
//			path = new ArrayList<PVector>();
//			PVector thatOne = history.get(itteration++);
//			R = thatOne.x;
//			r = thatOne.y;
//			a = thatOne.z;
//			s = "R:" + R  + "r:" + r  + "a:" + a;
//		}
//	}
//	else if (mouseButton == RIGHT && itteration > 0) {
//		path = new ArrayList<PVector>();
//		PVector thatOne = history.get(--itteration);
//		R = thatOne.x;
//		r = thatOne.y;
//		a = thatOne.z;
//		s = "R:" + R  + "r:" + r  + "a:" + a;
//		
//	}
		
}
	
	
	void update(){
	    theta += resolution;
	    
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
		    
		    //for looks
		    //line(path.get(path.size()-1).x,path.get(path.size()-1).y, point.x, point.y);
		  }
		  endShape();
	}
}