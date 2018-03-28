import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import processing.core.*;

public class Spirograph extends PApplet {

	public static void main(String[] args) {
		PApplet.main(new String[] {"Spirograph"});
	}
	
	
	private float theta = (float) 1.5708;
	private float resolution = (float) .1;
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
	JTextField bigRingSize = new JTextField();
	JTextField smallRingSize = new JTextField();
	JTextField distance = new JTextField();
	JTextField speedField = new JTextField();
	
	
	Object[] message0 = {
	    "Ring:", bigRingSize,
	    "Disk", smallRingSize,
	    "Distance", distance,
	    "Speed", speedField
	};

	Object[] message1 = {
		    "Speed", speedField
		};
	
	public void settings() {
		  size(displayWidth, displayHeight);
    	 path = new ArrayList<PVector>();
	    	R =  (float) Math.floor(Math.random()*((displayHeight/4)));
	    	r =  (float) Math.floor(Math.random()*((displayHeight/4)));
	    	a =  (float) Math.floor(Math.random()*((displayHeight/4)));
	    	
	    	red = (int) Math.floor(Math.random()*(255-100+1)+100);
	    	green = (int) Math.floor(Math.random()*(255-100+1)+100);
	    	blue = (int)  Math.floor(Math.random()*(255-100+1)+100);

	  
	    	PVector thisOne = new PVector((int)Math.floor(R), (int)Math.floor(r), (int)Math.floor(a));
	    	history.add(thisOne);
			s = "R:" + R  + "r:" + r  + "a:" + a;	
		  
		  
	}

	public void keyPressed() {
		if (keyCode == 32) {
				save("C:/users/ssustrich/desktop/" +R+"-"+r+"-"+a+".png");
	}
				
		if(keyCode == ENTER)
	      {
			speedField.setText(String.valueOf(resolution));
			int option = JOptionPane.showConfirmDialog(null, message0, "Sizes", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				path = new ArrayList<PVector>();
				int big = Integer.valueOf((bigRingSize.getText()));
	    	    int small = Integer.valueOf((smallRingSize.getText()));
	    	    int dist = Integer.valueOf((distance.getText()));
	    	    resolution = Float.valueOf(speedField.getText());
		      	PVector newOne = new PVector(big, small, dist);
		    	history.add(newOne);
		    	setUpForNextShape(newOne);
				itteration = history.size();
			} 
	      }
		
		if(keyCode == 82 ||keyCode== 114)
	      {
			speedField.setText(String.valueOf(resolution));
			int option = JOptionPane.showConfirmDialog(null, message1, "Resolution", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				path = new ArrayList<PVector>();
	    	    resolution = Float.valueOf(speedField.getText());
			} 
	      }
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
	setUpForNextShape();
}
	
	void update(){
	    theta += resolution;
	    float x = (R-r)*cos(theta) + a*cos(((R-r)/r)*theta);
	    float y = (R-r)*sin(theta) - a*sin(((R-r)/r)*theta);
	    PVector pv = new PVector(x+(displayWidth/2),y+(displayHeight/2));
		path.add(pv);
	}	
	
	public void setup() {

	}

	
	
	public void setUpForNextShape(PVector points) {
		path = new ArrayList<PVector>();
		R =  points.x;
		r = points.y;
		a = points.z;
    	red = (int) Math.floor(Math.random()*(255-100+1)+100);
    	green = (int) Math.floor(Math.random()*(255-100+1)+100);
    	blue = (int)  Math.floor(Math.random()*(255-100+1)+100);
		s = "R:" + R  + "r:" + r  + "a:" + a;
	}
	
	
	
	public void setUpForNextShape() {
		//we want an previos spirographic
		if (itteration <= history.size()) {
			PVector thatOne = history.get(itteration-1);
			setUpForNextShape(thatOne);
		}
		//we want a new spirographic
		else if (itteration > history.size()) {
	    	R =  (float) Math.floor(Math.random()*((displayHeight/4)));
	    	r =  (float) Math.floor(Math.random()*((displayHeight/4)));
	    	a =  (float) Math.floor(Math.random()*((displayHeight/4)));
			PVector newOne = new PVector((int)Math.floor(R), (int)Math.floor(r), (int)Math.floor(a));
	    	history.add(newOne);
	    	setUpForNextShape(newOne);
		}
	}
	
	
	
	public void draw() {
        if (millis() < 1000) {
            ((java.awt.Canvas) surface.getNative()).requestFocus();
        }
		   
		  background(55);
		  update();
		  text(s, 10, 590);
		  text(resolution, 10, 10);
		  stroke(200,200,200,100);
		  
		  
		  //center of ring
		  ellipse((displayWidth/2),(displayHeight/2),R+R, R+R);
		  
		  //center of disk
		  float innerCircleCenX = path.get(path.size()-1).x;
		  float innerCircleCenY = path.get(path.size()-1).y;
		  
		  float rsum =  R - r;
	      innerCircleCenX = (displayWidth/2) + rsum * cos(theta);
	      innerCircleCenY = (displayHeight/2) + rsum * sin(theta);
				  
	      
		  ellipse(innerCircleCenX,innerCircleCenY,r*2, r*2);
		  ellipse(path.get(path.size()-1).x,path.get(path.size()-1).y,5, 5);
		  
		  //line from origin to center of disk
		  line((displayWidth/2),(displayHeight/2), innerCircleCenX, innerCircleCenY);
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