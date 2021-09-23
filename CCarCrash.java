import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class CCarCrash2 extends JFrame implements ActionListener,ChangeListener {

	private JPanel middle, right, bottom, right1, right2, right3,right4,right5,right6;
	private JButton arrows[] = new JButton[9];
	private JButton optionbutton1,optionbutton2,optionbutton3,exit,act,run,reset,moveright,moveleft,moveup,movedown;
	private boolean north = false,east = true,south = false,west = false,play = true,auto = false,o1 = true,o2 =false,o3=false,r = true;
	private JButton background[][] = new JButton[13][16];
	private Icon iconBackground, careast,carnorth,carsouth,carwest,iconwallhoriz,iconwallvert,iconwalltopright,iconwalltopleft,iconwallbottomright,iconwallbottomleft,iconrun,iconact,iconreset,cartestcrash,compaseast,compasnorth,compassouth,compaswest,iconpause,person,iconfinish;
	private Border raisedbevel, loweredbevel;
	private JLabel compas,option,square,direction,timer,timerblank;
	private JTextField optionfield,squarefield,directionfield,hours,mins,sec;
	private final ArrayList<Integer> route = new ArrayList<Integer>();
    private int pathIndex,sect = 00, mint = 00,hourt = 00,test = 0,first = 0;
    private final int MOVE_UP = 1,MOVE_RIGHT = 2,MOVE_DOWN = 3,MOVE_LEFT = 4;
    private Timer delay,stopwatch;
    private JSlider slider;
    private JMenuBar menuBar;
    private JMenu scenarioMenu,editMenu,controlMenu,helpMenu;
    private JMenuItem newMenuItem,openMenuItem,exitMenuItem,helptopicMenuItem,aboutMenuItem,runitem,pauseitem,actitem,resetitem;
    private Border greyline,greylinethin,blackline,compound;
    private EmptyBorder emptyBorder,gap;
    private CompoundBorder inner,outter,gaps2,gaps3;
    private Container window;
    private int[] check = {0,0};
	private int finish =178;
	/*
	 * 0 = Space
	 * 1 = Horizontal wall
	 * 2 = vertical wall
	 * 3 = top left corner
	 * 4 = top right corner
	 * 5 = bottom left corner
	 * 6 = bottom right corner
	 * 7 = pathfinding
	 * 8 = obsiticals
	 * 9 = end
	 * 10 = car
	 * 20 = offlimit space when random spawing for option 3
	 */ 
	
	//Array for option 1
     int [][] option1 = 
    	 		{{3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4},
    			 {2,10,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
    			 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
    			 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
    			 {2,20,20,20,3,1,1,1,1,1,1,4,0,0,0,2},
    			 {2,0,0,0,2,0,0,0,0,0,0,2,0,0,0,2},
    			 {2,0,0,0,2,0,0,0,0,0,0,2,0,0,0,2},
    			 {2,0,0,0,2,0,0,0,0,0,0,2,0,0,0,2},
    			 {2,0,0,0,5,1,1,1,1,1,1,6,0,0,0,2},
    			 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
    			 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
    			 {2,9,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
    			 {5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,6}
    			 };
     
     //array for option 2
     
     private int[][] array = option1;
     int [][] option2 = 
    	 {{3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4},
    	 {2,10,0,0,0,0,0,2,0,0,0,0,0,0,0,2},
    	 {2,0,0,0,0,0,0,2,0,1,1,4,0,0,0,2},
    	 {2,0,0,0,0,0,0,0,0,0,0,2,0,0,0,2},
    	 {2,20,20,20,3,1,1,1,1,1,1,4,0,0,0,2},
    	 {2,0,9,0,2,0,0,0,0,0,0,2,0,0,0,2},
    	 {2,0,0,0,2,0,0,0,0,0,0,2,1,1,0,2},
    	 {2,0,0,0,2,0,0,0,0,0,0,2,0,0,0,2},
    	 {2,1,1,0,5,1,1,1,1,1,1,6,0,1,1,2},
    	 {2,0,0,0,0,2,0,0,0,0,0,2,0,0,0,2},
    	 {2,0,0,1,1,6,0,0,0,2,0,2,0,0,0,2},
    	 {2,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
    	 {5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,6}
    			 };
    	 
     //array for option 3
     int [][] option3 = 
      	{{3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4},
  		 {2,10,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
  		 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
  		 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
  		 {2,20,20,20,3,1,1,1,1,1,1,4,0,0,0,2},
  		 {2,0,9,0,2,20,20,20,20,20,20,2,0,0,0,2},
  		 {2,0,0,0,2,20,20,20,20,20,20,2,0,0,0,2},
  		 {2,0,0,0,2,20,20,20,20,20,20,2,0,0,0,2},
  		 {2,0,0,0,5,1,1,1,1,1,1,6,0,0,0,2},
  		 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
  		 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
  		 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
  		 {5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,6}
  		 };
     

     
	public static void main(String[] args) {

		CCarCrash2 main = new CCarCrash2(); //Creating a instance of the class
		main.setSize(810, 650); //Setting the size of the window
		main.creategui(); //Calling the creategui method that then calls all the draw methods to draw the all of the panels and objects on the screen.
		main.setResizable(false); //Stops the window being resized either by the full screen button or dragging the sides out. 
		main.setLocationRelativeTo(null); //This sets the window to opeing in the center of the screen.
		main.setVisible(true); //Make the application visable
		main.setTitle("CCarCrash – Car Race Application"); //Sets the title of the application window.
		main.setIconImage(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/greenfoot.png"))); //Sets the icon image for the top left hand corner as well as the one on the task bar.
		
	}

	
	public void creategui() {

		// Create default things
		setDefaultCloseOperation(EXIT_ON_CLOSE); //This sets the default closing action to exit on close which means the application window to close rather then just hide which is the default.
		window = getContentPane(); //Creates a content pane.
		window.setLayout(new BorderLayout()); //Set the layout of the entire application to borderlayout this is used to postion the panes on the panel.
		delay = new Timer(1000, this); //Creating a timer
		stopwatch = new Timer(1000, this);
		utilImages(); //All of these methods draw all the all the panels and buttons ect on the window
		utilBorders();
		drawMenuBar();
		drawMiddlePanel();
		drawRightPanel();
		drawDirectionArrows();
		drawBottomPanel();
		buildmap(option1); //Builds the course defualts to option1
	}
	
	/*#################################################
	 * 
	 * Util Methods
	 * 
	 ##################################################*/
	private void utilImages() {

		//Assinging images to all of the icons that we created at the top of the page.
		iconBackground = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("images/space.jpg")));
		iconwallhoriz = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("images/wall-horiz.png")));
		iconwallvert = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("images/wall-vert.png")));
		iconwalltopleft = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("images/wall-NW.png")));
		iconwalltopright = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("images/wall-NE.png")));
		iconwallbottomleft = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("images/wall-SW.png")));
		iconwallbottomright = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("images/wall-SE.png")));
		careast = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("images/cartest.png")));
		cartestcrash = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("images/cartestcrash.png")));
		iconact = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/step.png")));
		iconrun = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/run.png")));
		iconreset = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/reset.png")));
		compaseast = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/east.jpg")));
		compasnorth = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/north.jpg")));
		compassouth = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/south.jpg")));
		compaswest = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/west.jpg")));
		carnorth = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/carnorth.png")));
		carsouth = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/carsouth.png")));
		carwest = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/carwest.png")));
		iconpause = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/pause.png")));
		person = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/person.png")));
		iconfinish = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash2.class.getResource("images/sandstone.jpg")));
	}
	
	
	private void utilBorders() {
				//Creating all the Borders i will be using 
				//using a mixture of compound borders and normal ones to create different border effects.
				greyline = BorderFactory.createLineBorder(Color.lightGray,3);
				greylinethin = BorderFactory.createLineBorder(Color.lightGray,1);
				blackline = BorderFactory.createLineBorder(Color.BLACK);
				raisedbevel = BorderFactory.createRaisedBevelBorder();
		        loweredbevel = BorderFactory.createLoweredBevelBorder();
		        compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		        emptyBorder = new EmptyBorder(-1, -3, -3, -4);
		        inner = new CompoundBorder(raisedbevel, loweredbevel);
		        outter = new CompoundBorder(inner, emptyBorder);
		        gap = new EmptyBorder(1,1,1,1);
		        gaps2 = new CompoundBorder(greyline,gap);
		        gaps3 = new CompoundBorder(gaps2,blackline);
	}
	
	
	public void timer() {
		//This method is used to make the timer displayed on the right hand of the screen using 
		// using a timer to call this method every second then using if statments to add digits and count up.
		if (sect < 10) {
			sec.setText("0"+Integer.toString(sect));
		}
		else if (sect < 60 && sect > 10) {
			sec.setText(Integer.toString(sect));
		}
		else if (sect > 60) {
			sect = 00;
			mint++;
			if (mint < 10) {
				mins.setText("0"+Integer.toString(mint));
			}
			else if (mint > 10 && mint < 60) {
			mins.setText(Integer.toString(mint));
			}
		}
			else if (mint > 60) {
				mint= 00;
				hourt++;
				if (hourt < 10) {
					hours.setText("0"+Integer.toString(hourt));
				}
				else if (hourt < 60 && hourt > 10) {
					hours.setText(Integer.toString(hourt));
				}
			}
		
			
	sect++;
	}
	
	public void random() { // This method is used to generate the randomise place to spawn the characters in on option3
		Random random = new Random();
		for (int row = 0; row < option3.length; row++) {
			for (int col = 0; col < option3[0].length; col++) {
				if (option3[row][col] == 12) {
					option3[row][col] = 0;
				}
			}
		}
		for (int row = 0; row < option3.length; row++) {
			for (int col = 0; col < option3[0].length; col++) {
				if (option3[row][col] == 0) {
					int rn = random.nextInt(17);
					if (rn == 5) {
						option3[row][col] = 12;
					}
				}

			}
		}
	}
	
	public int[][] whatarray() { //This method tells us which tack option is being used
		
		if (o1 == true && o2 == false && o3 == false) {
			return option1;
		}
		else if (o1 == false && o2 == true && o3 == false) {
			return option2;
		}
		else if (o1 == false && o2 == false && o3 == true) {
			return option3;
		}
		return option1;
	}
	
	
	public void buildmap(int [][] array) { //This method takes in an 2d array then generates a track using it.
	int up = 0;	
	for (int row = 0; row < array.length; row++) {
	            for (int col = 0; col < array[0].length; col++) {
	            	up++;
	              background[row][col] = new JButton(""+up);
	              background[row][col].setText(""+up);
	              background[row][col].setBorderPainted(false);
	              switch (array[row][col]) { //Switch statment to tell it which objects to place according to the 2d array we pass to it so for example 1 is a horizontal wall.
	            	 
	            	case 1 : background[row][col].setIcon(iconwallhoriz);
	            	break;
	            	case 2 : background[row][col].setIcon(iconwallvert);
	            	break;
	            	case 3 : background[row][col].setIcon(iconwalltopleft);
	            	break;
	            	case 4 : background[row][col].setIcon(iconwalltopright);
	            	break;
	            	case 5 : background[row][col].setIcon(iconwallbottomleft);
	            	break;
	            	case 6 : background[row][col].setIcon(iconwallbottomright);
	            	break;
	            	case 9 : background[row][col].setIcon(iconfinish);
	            	break;
	            	case 10 : background[row][col].setIcon(careast);
	            	break;
	            	case 12 : background[row][col].setIcon(person);
	            	break;
	            	default : background[row][col].setIcon(iconBackground);
	            	}
	            	middle.add(background[row][col]); //adds the button to the screen.
	            }
		}
	}
	
public String squareAuto () { //This method returns a string which is used to show what square the car is driving over this method does this when the car is being driven on from the run or act button.
		int pathX = route.get(pathIndex);
        int pathY = route.get(pathIndex + 1);
		String var = background[pathY][pathX].getText();
		int x = Integer.parseInt(var);
		if (test%2 == 0) {
			for (int i = 0; i < check.length -1; i ++ ) {
				check[i] = check[i + 1];
				
				check[i + 1] = x;
			}
		}
		test++;
		if (check[1] - 1 == check[0]) {
			directionfield.setText("E");
			compas.setIcon(compaseast);
			background[pathY][pathX].setIcon(careast);
		}
		else if (check[1] + 1 == check[0]) {
			directionfield.setText("W");
			compas.setIcon(compaswest);
			background[pathY][pathX].setIcon(carwest);
		}
		
		else if (check[1] + 16 == check[0]) {
			directionfield.setText("N");
			compas.setIcon(compasnorth);
			background[pathY][pathX].setIcon(carnorth);
		}
		else if (check[1] - 16 == check[0]) {
			directionfield.setText("S");
			compas.setIcon(compassouth);
			background[pathY][pathX].setIcon(carsouth);
		}
		
		return var;
		
	}
	
	public String squareManual() { //this method does the same as above but only when the manual buttons are pressed.
		String var = "";
		for (int row = 0; row < option1.length; row++) {
            for (int col = 0; col < option1[0].length; col++) {
            	if(option1[row][col] == 10) {
            		var = background[row][col].getText();
            	
            	}
            	
            }
		}
		
		return var;
	}
	
	public void direction () { //This method is used to show the direction the car is facing and also sets the compass icon to show the different directions. 
		if (north == true && east == false && south == false && west == false) { //Set these boolean values when the car changes direction
			directionfield.setText("N");  //sets the the direction text field box to is this case N for north
			compas.setIcon(compasnorth); //Then this one sets the icon for the compass each one has a different image so for north,south etc.
		}
		else if (north == false && east == true && south == false && west == false) {
			directionfield.setText("E");
			compas.setIcon(compaseast);
		}
		else if (north == false && east == false && south == true && west == false) {
			directionfield.setText("S");
			compas.setIcon(compassouth);
		}
		else if (north == false && east == false && south == false && west == true) {
			directionfield.setText("W");
			compas.setIcon(compaswest);
		}
		else {
			dirset(false, false, false, false); //sets all direction to false if something goes wrong
		}
	
	}
	public void dirset(boolean north,boolean east,boolean south,boolean west) { //this method is to used set the direction the car is in to be used in the direction above
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}
	
	public void messageBox(String x) {//This method is used to bring up a message box with a message that is passed to the method
		JOptionPane.showMessageDialog(null, x);
	}
	
	/*#################################################
	 * 
	 * Methods that Draw Items on the screen
	 * 
	 ##################################################*/
	
	public void drawoption1() { //this method is used when the option1 button is hit to redraw the track.
		endgame(false);  //Redraws the buttons if the game is over
		o1 = true; //sets the option1 to true so it knows what track is currently loaded 
		o2 = false;
		o3 = false;
		array = option1;
		middle.removeAll(); //removes all the buttons
		middle.validate();
		middle.repaint();
		buildmap(option1); //then rebuilds the track
		middle.validate();
		middle.repaint(); //then repaints it on 
		optionfield.setText("Option 1"); //set the option text box to option 1
		finish =178; //and reset the finish as it is different from the other two tracks
	}
	
	public void drawoption2() { //same as the method above but for track 2
		endgame(false);
		o1 = false;
		o2 = true;
		o3 = false;
		array = option2;
		middle.removeAll();
		middle.validate();
		middle.repaint();
		buildmap(option2);
		middle.validate();
		middle.repaint();
		optionfield.setText("Option 2");
		finish = 83;
	}
	
	public void drawoption3() { //same as the method above but for track 3
		endgame(false);
		o1 = false;
		o2 = false;
		o3 = true;
		array = option3;
		random();
		middle.removeAll();
		middle.validate();
		middle.repaint();
		buildmap(option3);
		middle.validate();
		middle.repaint();
		optionfield.setText("Option 3");
		background[1][1].setIcon(careast);
		finish = 83;
	}
	
	
	private void drawMenuBar() {
		  //Menu Bar at the top of the page
        menuBar = new JMenuBar(); //creates the base menu bar 
        setJMenuBar(menuBar); 
        
        scenarioMenu = new JMenu("Scenario"); //creating the scenario option bar 
        editMenu = new JMenu("Edit");
        controlMenu = new JMenu("Controls");
        helpMenu = new JMenu("Help");
        menuBar.add(scenarioMenu); //adding the scenario menu onto the menu bar
        menuBar.add(editMenu);
        menuBar.add(controlMenu);
        menuBar.add(helpMenu);

        newMenuItem = new JMenuItem("New"); //Adding options to the menu items
        openMenuItem = new JMenuItem("Open");
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(this);
        helptopicMenuItem = new JMenuItem("Help Topic");
        helptopicMenuItem.addActionListener(this);
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(this);
        
        runitem = new JMenuItem("Run");
        pauseitem = new JMenuItem("Pause");
        actitem = new JMenuItem("Act");
        resetitem = new JMenuItem("Reset");
        
        runitem.addActionListener(this);
        pauseitem.addActionListener(this);
        actitem.addActionListener(this);
        resetitem.addActionListener(this);
        
        controlMenu.add(runitem);
        controlMenu.add(pauseitem);
        controlMenu.add(actitem);
        controlMenu.add(resetitem);
        
        scenarioMenu.add(newMenuItem); //This then adds items to the scenario Menu which is then add to the menu bar
        scenarioMenu.add(openMenuItem);
        scenarioMenu.addSeparator();
        scenarioMenu.add(exitMenuItem);
         
        helpMenu.add(helptopicMenuItem);
        helpMenu.add(aboutMenuItem);
	}
	
	
	private void drawMiddlePanel() {
		// Create big main panel
		middle = new JPanel();
		middle.setBorder(outter);
		window.add(middle, BorderLayout.CENTER);
		middle.setLayout(new GridLayout(13, 16));
	}
	
	private void drawBottomPanel() {
		// Create bottom panel
		bottom = new JPanel();
		bottom.setBorder(compound);
		bottom.setPreferredSize(new Dimension(100, 55));
		window.add(bottom, BorderLayout.SOUTH);
		
		//Create bottom buttons for act run and reset
		act = new JButton("Act");
		bottom.add(act);
		
		act.setIcon(iconact);
		act.addActionListener(this); //adding  action listener to the act button so we can see if the button has been pressed
		
		run = new JButton("Run");
		bottom.add(run);
		
		run.setIcon(iconrun);
		run.addActionListener(this);
		
		reset = new JButton("Reset");
		bottom.add(reset);
		reset.setIcon(iconreset);
		reset.addActionListener(this);
		//SLIDER
		slider = new JSlider(JSlider.HORIZONTAL,0,2000,1000); //adding a horizontal slider this a with min max and default value
		slider.addChangeListener(this); //adding a change listener to the slider to see if it has been moved
		slider.setMajorTickSpacing(1000); //Adding large lines on the slider 
		slider.setMinorTickSpacing(100); // adding small lines on the slider
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		Hashtable<Integer, JLabel> tags = new Hashtable<>(); // to create custom labels on the slider so i can use seconds
		tags.put(0, new JLabel("0s"));
		tags.put(500, new JLabel("0.5s"));
		tags.put(1000, new JLabel("1s"));
		tags.put(1500, new JLabel("1.5s"));
		tags.put(2000, new JLabel("2s"));
		slider.setLabelTable(tags);
		slider.setPaintLabels(true);
		bottom.add(slider); //Adding slider to panel
	}
	
	
	private void drawRightPanel() {
		// Create Right panel
				right = new JPanel();
				right.setBorder(compound);
				right.setPreferredSize(new Dimension(200, 100));
				window.add(right, BorderLayout.EAST);
				// Split the right panel into three sections
				right.setLayout(new GridLayout(6, 1));

				right1 = new JPanel();
				right.add(right1);
				right1.setLayout(new GridLayout(3,2));
				
				right2 = new JPanel();
				right.add(right2);
				right2.setLayout(new GridLayout(3, 3));
				
				right3 = new JPanel();
				right.add(right3);
				right3.setLayout(new GridLayout(2,3,4,4));

				right4 = new JPanel();
				right.add(right4);
				

				right5 = new JPanel();
				right.add(right5);
				right5.setLayout(new GridLayout(2,2,4,4));
				
				right6 = new JPanel();
				right.add(right6);
				
				//Create Right1 buttons
				option = new JLabel("Option:");
				right1.add(option,0,0);
				
				square = new JLabel("Square:");
				right1.add(square,0,1);
				
				direction = new JLabel("Direction");
				right1.add(direction,0,2);
				
				optionfield = new JTextField("Option 1");
				right1.add(optionfield,2,1);
				optionfield.setEditable(false);
				optionfield.setBorder(blackline);
				
				squarefield = new JTextField("18");
				right1.add(squarefield,1,3);
				squarefield.setEditable(false);
				squarefield.setBorder(blackline);
				
				directionfield = new JTextField("E");
				right1.add(directionfield,1,5);
				directionfield.setEditable(false);
				directionfield.setBorder(blackline);
				
				//Create right3 buttons
				timerblank = new JLabel();
				right3.add(timerblank);
				timerblank.setPreferredSize(new Dimension(10, 20));
				timer = new JLabel("    Timer");
				right3.add(timer);
				timer.setPreferredSize(new Dimension(150, 20));
				
				timerblank = new JLabel();
				right3.add(timerblank);
				timerblank.setPreferredSize(new Dimension(10, 20));
				
				hours = new JTextField("00");
				right3.add(hours);
				hours.setHorizontalAlignment(JTextField.CENTER);
				hours.setEditable(false);
				hours.setBorder(blackline);

				mins = new JTextField("00");
				right3.add(mins);
				mins.setHorizontalAlignment(JTextField.CENTER);
				mins.setEditable(false);
				mins.setBorder(blackline);
				
				sec = new JTextField("00");
				right3.add(sec);
				sec.setHorizontalAlignment(JTextField.CENTER);
				sec.setEditable(false);
				sec.setBorder(blackline);
				
				//Create Right5 buttons
				optionbutton1 = new JButton("Option 1");
				optionbutton1.setBackground(Color.white);
				right5.add(optionbutton1);
				optionbutton1.addActionListener(this);
				
				optionbutton2 = new JButton("Option 2");
				optionbutton2.setBackground(Color.white);
				right5.add(optionbutton2);
				optionbutton2.addActionListener(this);
				
				optionbutton3 = new JButton("Option 3");
				optionbutton3.setBackground(Color.white);
				right5.add(optionbutton3);
				optionbutton3.addActionListener(this);
				
				exit = new JButton("Exit");
				exit.setBackground(Color.white);
				right5.add(exit);
				exit.addActionListener(this);
				
				//Create compas
				compas = new JLabel();
				compas.setIcon(compaseast);
				compas.setBorder(greylinethin);
				right6.add(compas);
	}
	
	private void drawDirectionArrows() {
		// to create the right had side control buttons using a switch method
				for (int i = 0; i < 9; i++) {
					arrows[i] = new JButton("" + i);
					switch(i) {
					case 1: arrows[i].setText("Λ");
							arrows[i].setBackground(Color.white);
							arrows[i].setBorder(gaps3);
							moveup = arrows[i];
							moveup.addActionListener(this);
					break;
					
					case 3: arrows[i].setText("<");
							arrows[i].setBackground(Color.white);
							arrows[i].setBorder(gaps3);
							moveleft = arrows[i];
							moveleft.addActionListener(this);
					break;
					
					case 5: arrows[i].setText(">");
						    arrows[i].setBackground(Color.white);
						    arrows[i].setBorder(gaps3);
						    moveright = arrows[i];
						    moveright.addActionListener(this);
				    break;
				    
					case 7: arrows[i].setText("V");
							arrows[i].setBackground(Color.white);
							arrows[i].setBorder(gaps3);
							movedown = arrows[i];
							movedown.addActionListener(this);
					break;
					
					default: arrows[i].setBorder(loweredbevel);
							 arrows[i].setEnabled(false);
							 arrows[i].setText("");
							 
					break;
					
						
					}
					right2.add(arrows[i]); //Adding the arrows to the screen
				}
	}
	
	/*#################################################
	 * 
	 * Methods that Move the car around on the screen
	 * 
	 ##################################################*/
	
	public void move(int nDirection, int [][] array) { //The method used to move the car around the track using the buttons we created above

		if (nDirection == MOVE_RIGHT) { //Check to see if the constant that has been passed to the method is equal to MOVE_RIGHT
			auto = false; //Seting auto to false so the game knows that we are using the arrow keys and not the play button
			dirset(false, true, false, false); //set the direction
			direction();
			squarefield.setText(squareManual());
			for (int row = 0; row < array.length; row++) { //Loop through the 2d array
				for (int col = 0; col < array[0].length; col++) {

					if (array[row][col] == 10) { //Check to see if the array is equal to 10
						if (array[row][col + 1] != 0 && array[row][col + 1] != 9) { //Then is the array plus one is not equal to 0 or 9
							background[row][col].setIcon(cartestcrash); //Set the background to cartestcrash png
							endgame(true); //End the game
							messageBox("You Have Lost Hit Restart to Play Again"); //Bring up a message telling the user they have lost (message box)
							
						} 
						else {
							if (whatarray() == option1) { //This set of if and else if statments check the finish point depending on the option of the track that is being used
								if (row == 11 && col == 0) {
									endgame(true);
									messageBox("You Have Won Hit Restart to Play Again");
								}
							}
							
							else if (whatarray() == option2) {
								if (row == 5 && col == 1) {
									endgame(true);
									messageBox("You Have Won Hit Restart to Play Again");
								}
							}
							else if (whatarray() == option3) {
								if (row == 5 && col == 1) {
									endgame(true);
									messageBox("You Have Won Hit Restart to Play Again");
								}
							}
							background[row][col].setIcon(iconBackground); //If the next spot is clear then set the current button to background
							background[row][col + 1].setIcon(careast); //then set the next button to the car east button
							array[row][col] = 0; //set the current value to 0
							array[row][col + 1] = 11; //Set the next square to 11
						}
					}
					if (array[row][col] == 11) { //Then if the current value is equal to 11
						array[row][col] = 10; //Set it to 10
					}
				}
			}
		}

		else if (nDirection == MOVE_LEFT) {
			auto = false;
			dirset(false, false, false, true);
			direction();
			squarefield.setText(squareManual());
			for (int i = 0; i < 2; i++) {
				for (int row = 0; row < array.length; row++) {
					for (int col = 0; col < array[0].length; col++) {
						if (array[row][col] == 10) {
							if (array[row][col - 1] != 0 && array[row][col - 1] != 9) {
								background[row][col].setIcon(cartestcrash);
								endgame(true);	
								messageBox("You Have Lost Hit Restart to Play Again");
							} 
							
							else {
								
								if (whatarray() == option1) {
									if (row == 11 && col == 2) {
										endgame(true);
										messageBox("You Have Won Hit Restart to Play Again");
									}
								}
								
								else if (whatarray() == option2) {
									if (row == 5 && col == 3) {
										endgame(true);
										messageBox("You Have Won Hit Restart to Play Again");
									}
								}
								else if (whatarray() == option3) {
									if (row == 5 && col == 3) {
										endgame(true);
										messageBox("You Have Won Hit Restart to Play Again");
									}
								}
								background[row][col].setIcon(iconBackground);
								background[row][col - 1].setIcon(carwest);
								array[row][col] = 0;
								array[row][col - 1] = 11;
							}
						}
						if (array[row][col] == 11) {
							array[row][col] = 10;

						}
					}
				}
			}
		}
		if (nDirection == MOVE_UP) {
			auto = false;
			dirset(true, false, false, false);
			direction();
			squarefield.setText(squareManual());
			for (int i = 0; i < 2; i++) {
				for (int row = 0; row < array.length; row++) {
					for (int col = 0; col < array[0].length; col++) {
						if (array[row][col] == 10) {
							if (array[row - 1][col] != 0 && array[row- 1][col] != 9) {
								background[row][col].setIcon(cartestcrash);
								endgame(true);
								messageBox("You Have Lost Hit Restart to Play Again");
							} else {
								if (whatarray() == option1) {
									if (row == 12 && col == 1) {
										endgame(true);
										messageBox("You Have Won Hit Restart to Play Again");
									}
								}
								
								else if (whatarray() == option2) {
									if (row == 6 && col == 2) {
										endgame(true);
										messageBox("You Have Won Hit Restart to Play Again");
									}
								}
								else if (whatarray() == option3) {
									if (row == 6 && col == 2) {
										endgame(true);
										messageBox("You Have Won Hit Restart to Play Again");
									}
									
								}
								background[row][col].setIcon(iconBackground);
								background[row - 1][col].setIcon(carnorth);
								array[row][col] = 0;
								array[row - 1][col] = 11;
							}
						}
						if (array[row][col] == 11) {
							array[row][col] = 10;
						}
					}
				}
			}
		}
		if (nDirection == MOVE_DOWN) {
			dirset(false, false, true, false);
			direction();
			auto = false;
			squarefield.setText(squareManual());
			for (int row = 0; row < array.length; row++) {
				for (int col = 0; col < array[0].length; col++) {
					if (array[row][col] == 10) {
						if (array[row + 1][col] != 0 && array[row+ 1][col] != 9) {
							background[row][col].setIcon(cartestcrash);
							endgame(true);
							messageBox("You Have Lost Hit Restart to Play Again");
						} else {
							if (whatarray() == option1) {
								if (row == 10 && col == 1) {
									endgame(true);
									messageBox("You Have Won Hit Restart to Play Again");
								}
							}
							
							else if (whatarray() == option2) {
								if (row == 4 && col == 2) {
									endgame(true);
									messageBox("You Have Won Hit Restart to Play Again");
								}
							}
							else if (whatarray() == option3) {
								if (row == 4 && col == 2) {
									endgame(true);
									messageBox("You Have Won Hit Restart to Play Again");
								}
							}
							background[row][col].setIcon(iconBackground);
							background[row + 1][col].setIcon(carsouth);
							array[row][col] = 0;
							array[row + 1][col] = 11;
						}
					}
					if (array[row][col] == 11) {
						array[row][col] = 10;
					}
				}
			}
		}

	}
		
	private void driveCar() {
		squarefield.setText(squareAuto());
		paintcar();
		next();
		squarefield.setText(squareAuto());
		paintcar();
		prev();
		paintbackground();
		next();
		squarefield.setText(squareAuto());
		if (squareAuto().equals(""+finish)) {
			run.setIcon(iconrun);
			run.setText("Run");
			delay.stop();
			stopwatch.stop();
			play = true;
			endgame(true);
			messageBox("You Have Won Hit Restart to Play Again");
		}
	}
	
	public void endgame(Boolean x) {
		
		if (x == true) {
		for (int i = 0; i < arrows.length; i ++) {
			arrows[i].setEnabled(false);
		}
		run.setEnabled(false);
		act.setEnabled(false);
		optionbutton1.setEnabled(false);
		optionbutton2.setEnabled(false);
		optionbutton3.setEnabled(false);
		}
		
		else if (x == false) {
			for (int i = 0; i < arrows.length; i ++) {
				right2.removeAll();
			}
			optionbutton1.setEnabled(true);
			optionbutton2.setEnabled(true);
			optionbutton3.setEnabled(true);
			run.setEnabled(true);
			act.setEnabled(true);
			drawDirectionArrows();
			right2.revalidate();
			right2.repaint();
		}
		
	}
	
	public void run() {
		auto = true;
		if (play == true && first == 0) {
		setup(array);
		run.setIcon(iconpause);
		run.setText("Pause");
		delay.start();
		stopwatch.start();
		play = false;
		first ++;
		}
		else if (play == true && first == 1) {
			run.setIcon(iconpause);
			run.setText("Pause");
			delay.start();
			stopwatch.start();
			play = false;
		}
		else if (play == false) {
			run.setIcon(iconrun);
			run.setText("Run");
			delay.stop();
			stopwatch.stop();
			play = true;
		}
	}

	
	private void act() {
		auto = true;
		if (first == 0) {
		setup(array);
		driveCar();
		first ++;
		}
		else if (first == 1) {
			driveCar();
		}
	}
	
	
	private void reset(int[][] array) {
		endgame(false);
		r = false;
		if (auto == true) {
			paintbackground();
			setup(array);
			paintcar();
			}
			for (int row = 0; row < array.length; row++) {
	            for (int col = 0; col < array[0].length; col++) {
	            	if(array[row][col] == 10) {
	            	background[row][col].setIcon(iconBackground);
	            	array[row][col] = 0;
	            	}
	            }
			}
			first = 0;
			r = false;
			array[1][1] = 10;
			for (int row = 0; row < array.length; row++) {
	            for (int col = 0; col < array[0].length; col++) {
	            		if (array[row][col] == 7) {
	            			array[row][col] = 0;
	            		}
	            	}
	            }
			squarefield.setText("18");
			background[1][1].setIcon(careast);
			route.clear();
			hours.setText("00");
			mins.setText("00");
			sec.setText("00");
			slider.setValue(1000);
			compas.setIcon(compaseast);
			r = false;
		
		if(whatarray() == option3 && r == false) {//TODO clean up
			o1 = false;
			o2 = false;
			o3 = true;
			array = option3;
			random();
			middle.removeAll();
			middle.validate();
			middle.repaint();
			buildmap(option3);
			middle.validate();
			middle.repaint();
			optionfield.setText("Option 3");
			background[1][1].setIcon(careast);
			finish = 83;
		}
		if (whatarray() == option1) {
			background[11][1].setIcon(iconfinish);
			middle.revalidate();
			middle.repaint();
		}
		else if (whatarray() == option2) {
			background[5][2].setIcon(iconfinish);
			middle.revalidate();
			middle.repaint();
		}
		else if (whatarray() == option3) {
			background[5][2].setIcon(iconfinish);
			middle.revalidate();
			middle.repaint();
		}
}	
	
	//This is a depth first search algorithm 
	//i used these as sources as help when writing it
	//Some code has been used from all these sources for the actual algorithm which ONLY is used to generate the path
	//everything else like moving the car by traversing the path and dealing with direction square is all my own work.
	//http://www.migapro.com/depth-first-search/
	//https://www.youtube.com/watch?v=dyrvXiMumXc
	//https://en.wikipedia.org/wiki/Depth-first_search
	
	 private boolean PathFinder(int[][] option, int x, int y, ArrayList<Integer> route) {

	        if (option[y][x] == 9) {
	        	route.add(x);
	        	route.add(y);
	            return true;
	        }
	        
	        if (option[y][x] == 0) {
	            option[y][x] = 7;
	            
	           
	          
	            //RIGHT
	            if (PathFinder(option, x, y + 1, route)) {
	            	route.add(x);
	            	route.add(y);
	                return true;
	            }
	            
	            //DOWN
	            if (PathFinder(option, x -1, y, route)) {
	            	route.add(x);
	            	route.add(y);
	                return true;
	            }
	            
	            //UP
	            if (PathFinder(option, x + 1, y, route)) {
	            	route.add(x);
	            	route.add(y);
	                return true;
	            }

	            //LEFT
	            if (PathFinder(option, x , y -1, route)) {
	            	route.add(x);
	            	route.add(y);
	                return true;
	                
	            }

	          
	        }
	        
	        return false;
	        
	 }
	
	public void setup(int[][] option) {
		option[1][1] = 0;
		PathFinder(option, 1, 1, route);
		pathIndex = route.size() - 2;
	}
	
	//TODO CLEAN UP THESE TWO PAINT Methods
	public void paintcar() {
		int pathX = route.get(pathIndex);
        int pathY = route.get(pathIndex + 1);
        background[pathY][pathX].setIcon(careast);
		middle.validate();
		middle.repaint();
	}
	
	public void paintbackground() {
		int pathX = route.get(pathIndex);
        int pathY = route.get(pathIndex + 1);
		background[pathY][pathX].setIcon(iconBackground);
		middle.validate();
		middle.repaint();
	}
	
	public void next() {
		pathIndex -= 2;
        if (pathIndex < 0) {
            pathIndex = 0;
        }
	}
	
	public void prev() {
		pathIndex += 2;
        if (pathIndex > route.size() - 2) {
            pathIndex = route.size() - 2;
        }	
	}
	
	public void actionPerformed(ActionEvent event) {
	
		//TODO DONT think i really need these here can all be delcaerd at the same time
		
		
		if (event.getSource() == stopwatch) {
			timer();
		}
		
		
		if (event.getSource() == optionbutton1) {
			drawoption1();
		}
		
		if (event.getSource() == optionbutton2) {
			drawoption2();
		}
		
		if (event.getSource() == optionbutton3) {
			drawoption3();
		}
		
		if (event.getSource() == exit) {
			System.exit(EXIT_ON_CLOSE);
		}
		if (event.getSource() == moveright) {
			
			move(MOVE_RIGHT,whatarray());
		}
	
		if (event.getSource() == moveleft) {
			move(MOVE_LEFT,whatarray());
			
		}
		if (event.getSource() == moveup) {
			move(MOVE_UP,whatarray());
			
		}
	
		if (event.getSource() == movedown) {
			move(MOVE_DOWN,whatarray());
		}
		if (event.getSource() == delay) {
			driveCar();
		}
		
		if (event.getSource() == act) {
			act();
		}
		
		if (event.getSource() == reset) {
			reset(whatarray());
		}
		
		if (event.getSource() == run) {	
			run();
		}
		
		if (event.getSource() == runitem) {
			run();
		}
		
		if (event.getSource() == pauseitem) {
			run();
		}
		
		if (event.getSource() == actitem) {
			act();
		}
		
		if (event.getSource() == resetitem) {
			reset(whatarray());
		}
		
		
		if (event.getSource() == exitMenuItem) {
			System.exit(EXIT_ON_CLOSE);
		}
		if (event.getSource() == helptopicMenuItem) {
			URI web = URI.create("https://www.greenfoot.org/doc");
			try {
				java.awt.Desktop.getDesktop().browse(web);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if (event.getSource() == aboutMenuItem) {
			URI web = URI.create("https://www.greenfoot.org/overview");
			try {
				java.awt.Desktop.getDesktop().browse(web);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void stateChanged(ChangeEvent event) {
		if (event.getSource() == slider) {
			int num = slider.getValue();
			delay.setDelay(num);
		}
		
	}
}
