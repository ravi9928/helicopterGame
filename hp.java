import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;
public class hp extends Applet implements MouseListener,ActionListener{
	int wx,wy,flag,hx,hy,t,s,flagh,start,p;
	AudioClip au,over;
	Button b;
	public void init(){
		b=new Button("Start Game");
		add(b);
		b.addActionListener(this);
		wx=900;
		wy=40;
		hx=50;
		hy=200;
		s=0;		//score
		t=0;		//temp variable
		p=0;   		//temp variable
		flagh=2; 	//game over flag
		flag=0;
		addMouseListener(this);
	}
	public void mouseClicked(MouseEvent e){
		//hy=e.getY();
		if(flagh==0)
		hy=hy-10;
	}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==b){
			start=1;
			au.loop();
			flagh=0;
		}
	}
	
	public void paint(Graphics m){
		Image sky=getImage(getCodeBase(),"project/sky.jpg");
		m.drawImage(sky,0,0,900,600,this);
		Image wall=getImage(getCodeBase(),"project/wall.jpg");
		m.drawImage(wall,wx,wy,40,300,this);
		Image hcp=getImage(getCodeBase(),"project/hcp.gif");
		m.drawImage(hcp,hx,hy,150,100,this);
		m.setColor(Color.red);
		m.drawString("Score"+s,300,20);
		
		au=getAudioClip(getCodeBase(),"project/test1.wav");
		over=getAudioClip(getCodeBase(),"project/gameover.wav");
		
		
		if(start==1){
			if(wx==900){
				flag=0;
			}
			if(wx==0){
				Random r=new Random();
				wy=(Math.abs(r.nextInt())%600);
				System.out.println(wy);
				flag=1;
			}
			if(flag==0){
				wx=wx-2;
			}
			if(flag==1){
				wx=wx+900;
			}
			t=wy+200;
			if(wy<=hy && t>=hy && wx==200){
				flagh=1;
			}
			else{
				flagh=0;
			}
			if(hy==550 || hy==p){
				flagh=1;
			}
			if(flagh==1){
				au.stop();
				over.play();
				start=0;
				wx=200;
			}
			try{
				Thread.sleep(20);
			}
			catch(InterruptedException e){}
			s++;
			hy=hy+1;
			repaint();
		}
		else if(start==0 && flagh==1){
			Image blast=getImage(getCodeBase(),"project/blast.jpg");
			m.drawImage(blast,100,hy,100,100,this);
		    Image over=getImage(getCodeBase(),"project/over.jpg");
			m.drawImage(over,200,200,200,200,this);	
		}
	}
}
//<applet code="hp" width="900" height="600"></applet>