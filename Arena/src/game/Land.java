package game;

/**
 * Class for terrain objects
 * 
 * @author Jacob Charles
 *
 */
public class Land extends GameObject {
	//mask values
	public static final int SOLID = 1; //solid all around (includes top)
	public static final int PLATFORM = 2; //solid top
	public static final int DANGER = 4; //kill on contact
	public static final int BOUNCE = 8; //bouncy (var = bounciness mod, 0 = 100%, 5 = 150%, -2 = 80%, etc.)
	public static final int SLIP = 16; //slippery
	public static final int MOVE = 32; //moves you (var = speed*10)
	public static final int HATCH = 64; //appears when control is on
	public static final int NHATCH = 128; //appears when controls is off
	public static final int SWITCH = 256; //toggles control when hit
	public static final int PIPE = 512; //slide in, slide out
	public static final int WARP = 1024; //for in-game level select (var = target level)
	public static final int CHAR = 2048; //change characters (var = target character)
	public static final int OPTION = 4096; //for in game options (var = various things)
	public static final int GUN = 8192; //emits shots (var = bullet to emit)
	public static final int MUTE = 16384; //makes no sound
	public static final int PSPAWN = 32768; //capable of spawning powerups
	public static final int SOUND = 65536; //makes a sound when touched (var = sound made)
	public static final int ANIMATE = 131072; //animated tile
	public static final int R_ANIMATE = 262144; //animated tile backwards
	public static final int IMAGE = 524288; //draw a special image instead of a tilemap
	private static final int MAX = 1048576-1; //sum of all previous

	//current type is a bitmask
	private int t; //type
	private int v; //extra data
	private int id, m; //id and map number
	
	/**
	 * Construct a rectangular land at the set space
	 * 
	 * @param s
	 * 		skin
	 * @param x
	 * 		left edge
	 * @param y
	 * 		top edge
	 * @param w
	 * 		width
	 * @param h
	 * 		height
	 * @param t
	 * 		bitmask of the platform type
	 */
	public Land(int s, int x, int y, int w, int h, int t) {
		super(x, y, w, h);
		this.setSkin(s);
		this.t = t;
	}
	
	/**
	 * Construct a rectangular land at the set space
	 * 
	 * @param s
	 * 		skin
	 * @param x
	 * 		left edge
	 * @param y
	 * 		top edge
	 * @param w
	 * 		width
	 * @param h
	 * 		height
	 * @param t
	 * 		bitmask of the platform type
	 * @param v
	 * 		extra data for certain types
	 */
	public Land(int s, int x, int y, int w, int h, int t, int v) {
		super(x, y, w, h);
		this.setSkin(s);
		this.t = t;
		this.v = v;
	}
	
	/*land detail getter and setters*/
	public boolean isSolid() {
		return (t&SOLID) > 0;
	}
	public void setSolid(boolean b) {
		if (b) t |= SOLID;
		else t &= MAX-SOLID;
	}
	public boolean isPlatform() {
		return (t&PLATFORM) > 0;
	}
	public void setPlatform(boolean b) {
		if (b) t |= PLATFORM;
		else t &= MAX-PLATFORM;
	}
	public boolean isDanger() {
		return (t&DANGER) > 0;
	}
	public void setDanger(boolean b) {
		if (b) t |= DANGER;
		else t &= MAX-DANGER;
	}
	public boolean isBounce() {
		return (t&BOUNCE) > 0;
	}
	public void setBounce(boolean b) {
		if (b) t |= BOUNCE;
		else t &= MAX-BOUNCE;
	}
	public boolean isSlip() {
		return (t&SLIP) > 0;
	}
	public void setSlip(boolean b) {
		if (b) t |= SLIP;
		else t &= MAX-SLIP;
	}
	public boolean isMove() {
		return (t&MOVE) > 0;
	}
	public void setMove(boolean b) {
		if (b) t |= MOVE;
		else t &= MAX-MOVE;
	}
	public boolean isHatch() {
		return (t&HATCH) > 0;
	}
	public void setHatch(boolean b) {
		if (b) t |= HATCH;
		else t &= MAX-HATCH;
	}
	public boolean isNHatch() {
		return (t&NHATCH) > 0;
	}
	public void setNHatch(boolean b) {
		if (b) t |= NHATCH;
		else t &= MAX-NHATCH;
	}
	public boolean isSwitch() {
		return (t&SWITCH) > 0;
	}
	public void setSwitch(boolean b) {
		if (b) t |= SWITCH;
		else t &= MAX-SWITCH;
	}
	public boolean isPipe() {
		return (t&PIPE) > 0;
	}
	public void setPipe(boolean b) {
		if (b) t |= PIPE;
		else t &= MAX-PIPE;
	}
	public boolean isWarp() {
		return (t&WARP) > 0;
	}
	public void setWarp(boolean b) {
		if (b) t |= WARP;
		else t &= MAX-WARP;
	}
	public boolean isChar() {
		return (t&CHAR) > 0;
	}
	public void setChar(boolean b) {
		if (b) t |= CHAR;
		else t &= MAX-CHAR;
	}
	public boolean isOption() {
		return (t&OPTION) > 0;
	}
	public void setOption(boolean b) {
		if (b) t |= OPTION;
		else t &= MAX-OPTION;
	}
	public boolean isGun() {
		return (t&GUN) > 0;
	}
	public void setGun(boolean b) {
		if (b) t |= GUN;
		else t &= MAX-GUN;
	}
	public boolean isMute() {
		return (t&MUTE) > 0;
	}
	public void setMute(boolean b) {
		if (b) t |= MUTE;
		else t &= MAX-MUTE;
	}
	public boolean isPowerSpawn() {
		return (t&PSPAWN) > 0;
	}
	public void setPowerSpawn(boolean b) {
		if (b) t |= PSPAWN;
		else t &= MAX-PSPAWN;
	}
	public boolean isSound() {
		return (t&SOUND) > 0;
	}
	public void setSound(boolean b) {
		if (b) t |= SOUND;
		else t &= MAX-SOUND;
	}
	public boolean isAnimate() {
		return (t&ANIMATE) > 0;
	}
	public void setAnimate(boolean b) {
		if (b) t |= ANIMATE;
		else t &= MAX-ANIMATE;
	}
	public boolean isRAnimate() {
		return (t&R_ANIMATE) > 0;
	}
	public void setRAnimate(boolean b) {
		if (b) t |= R_ANIMATE;
		else t &= MAX-R_ANIMATE;
	}
	public boolean isImage() {
		return (t&IMAGE) > 0;
	}
	public void setImage(boolean b) {
		if (b) t |= IMAGE;
		else t &= MAX-IMAGE;
	}
	public int getVar() {
		return v;
	}
	public void setVar(int var) {
		this.v = var;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMap() {
		return m;
	}
	public void setMap(int map) {
		this.m = map;
	}
}
