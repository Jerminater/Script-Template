package scripts.template.graphics;

import org.tribot.api.Timing;

import org.tribot.script.Script;

import scripts.template.data.Vars;

public class PaintInfoThread extends Thread {
	
	private long startTime;
	private Script script;
	
	private boolean running;
	
	public PaintInfoThread(Script script) {
		this.script = script;
		this.startTime = Timing.currentTimeMillis();
		running = true;
	}
	
	@Override
	public void run() {
		while (running) {
			Vars.get().runTime = Timing.currentTimeMillis() - this.startTime;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			running = this.script.isActive();
		}
	}
}