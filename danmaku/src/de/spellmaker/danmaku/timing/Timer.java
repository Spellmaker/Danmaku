package de.spellmaker.danmaku.timing;

public class Timer {
	private TimerListener tl;
	private float ct;
	private float time;
	private int id;
	
	public Timer(float time, int id, TimerListener tl){
		this.tl = tl;
		this.time = time;
		this.id = id;
		this.ct = 0;
	}
	
	public Timer(float offset, float time, int id, TimerListener tl){
		this.tl = tl;
		this.time = time;
		this.id = id;
		this.ct = offset;
	}
	
	public void step(float d){
		ct += d;
		if(ct >= time){
			tl.timerEvent(id);
			ct = 0;
		}
	}
}
