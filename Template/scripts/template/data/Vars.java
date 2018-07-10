package scripts.template.data;

public class Vars {
		
	// instance stuff
	private static Vars vars;
	
			
	public static Vars get() {
		return vars == null? vars = new Vars() : vars;
	}
			
	public static void reset() {
		vars = new Vars();
	}
	
	public boolean setting1;
	
	public boolean endCond = false;
	public String status = "Initializing";
	public long runTime;
}
