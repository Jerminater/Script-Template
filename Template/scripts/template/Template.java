package scripts.template;

import java.awt.Color;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Login;
import org.tribot.api2007.Login.STATE;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Ending;
import org.tribot.script.interfaces.MessageListening07;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

import scripts.framework.Task;
import scripts.framework.TaskSet;
import scripts.template.data.Vars;
import scripts.template.graphics.FXMLString;
import scripts.template.graphics.GUI;
import scripts.template.graphics.PaintInfoThread;
import scripts.paint.FluffeesPaint;
import scripts.paint.PaintInfo;

@ScriptManifest(authors = { "Jerminater" }, category = "Tools", 
	name = "Tutorial", version = 1.00, 
	description = "Template for the Templates", gameMode = 1)

public class Template extends Script implements MessageListening07, Starting, Ending, Painting, PaintInfo {
		
	private final FluffeesPaint Paint = new FluffeesPaint(this, FluffeesPaint.PaintLocations.BOTTOM_RIGHT_PLAY_SCREEN,
			new Color[]{new Color(255, 251, 255)}, "Tahoma", new Color[]{new Color(255, 218, 185, 127)},
            new Color[]{new Color(139, 119, 101)}, 1, false, 5, 3, 0);
	private URL stylesheet;
	private GUI gui;
	private boolean paintThreadStarted;
	
	public void onStart() {
		// Initializing GUI
		println("Opening GUI");
		try {
			stylesheet = new URL("");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		gui = new GUI(FXMLString.get, stylesheet);
		gui.show();
		while (gui.isOpen())
			sleep(500);
		
		// init vars

	}
	
	@Override
	public void run() {
		//making sure we are logged in
		while (Login.getLoginState() != STATE.INGAME)
			General.sleep(300, 500);
		// Start paint data tracking thread
		new PaintInfoThread(this).start();
		paintThreadStarted = true;
		// initialize tasks and loop through them
		TaskSet tasks = new TaskSet();
	    while (!Vars.get().endCond) {
	    	sleep(100);
	       Task task = tasks.getValidTask();
	        if (task != null) {
	        	Vars.get().status = task.toString();
	            task.execute();
	        }
	    }	
	}
	
	@Override
	public void onEnd() {}
	
	@Override
    public void onPaint(Graphics g) {
		if (paintThreadStarted)
			Paint.paint(g);
	}
	
	@Override
    public String[] getPaintInfo() {
        return new String[] {"name and version", "Runtime: " + Timing.msToString(Vars.get().runTime),
        		"Status: " + Vars.get().status};
    }
	
	@Override
	public void serverMessageReceived(String message) {}

	@Override
	public void clanMessageReceived(String arg0, String arg1) {}

	@Override
	public void duelRequestReceived(String arg0, String arg1) {}

	@Override
	public void personalMessageReceived(String arg0, String arg1) {}

	@Override
	public void playerMessageReceived(String arg0, String arg1) {}

	@Override
	public void tradeRequestReceived(String arg0) {}
}
