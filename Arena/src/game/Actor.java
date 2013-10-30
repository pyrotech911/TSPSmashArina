package game;

/**
 * Class for the player's characters
 * 
 * @author Jacob Charles
 *
 */
public class Actor extends GameObject {

	//current data
	private int id; //used in place of references
	private int at = 1; //air time
	private int dt; //dead time
	private int r; //reload
	private int dir = 1; //direction
	private int cr = 0; //crouch
	private int p = 0; //power up
	private int pv = 0; //power up variable (extra data)
	private int lid = -1, lm = -1; //id and map id of the current land
	private int s = 0; //score
	private int l; //lives

	private int m; //selected RoleModel from the Warehouse

	/**
	 * Spawn a player with a given archetype and location
	 * 
	 * @param x
	 *		start x
	 * @param y
	 * 		start y
	 * @param character
	 * 		which player the character is using
	 */
	public Actor (int x, int y, int character) {
		super(x, y);

		//bind to their RoleModel
		setModel(character);

		//initialize some basic values
		dt = getSpawnTime();
	}

	//check if they're invincible
	public boolean isArmored() {
		return (!isDead() && dt < getSpawnTime()+getSpawnInv());
	}

	/*getters and setters for attributes*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAirTime() {
		return at;
	}
	public void setAirTime(int airTime) {
		this.at = airTime;
	}
	public int getDeadTime() {
		return dt;
	}
	public void setDeadTime(int deadTime) {
		this.dt = deadTime;
	}
	public int getReload() {
		return r;
	}
	public void setReload(int reload) {
		this.r = reload;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public boolean isCrouch() {
		return (cr != 0);
	}
	public void setCrouch(boolean b) {
		if (b) cr = 1;
		else cr = 0;
	}
	public int getPowerup() {
		return p;
	}
	public void setPowerup(int powerup) {
		//enter big mode (get huge)
		if (this.p != Item.BIG && powerup == Item.BIG) {
			setY(getY()-getH());
			setX(getX()-getW()/2);
			setW(getW()*2);
			setH(getH()*2);
		}
		//exit big mode (reset size)
		if (this.p == Item.BIG && powerup != Item.BIG) {
			float cx = getHCenter(), cy = getVCenter();
			setW(Warehouse.getCharacters()[m].getW());
			setH(Warehouse.getCharacters()[m].getH());
			setCenter(cx, cy);
		}
		this.p = powerup;
	}
	public int getPowerupVar() {
		return pv;
	}
	public void setPowerupVar(int powerupVar) {
		this.pv = powerupVar;
	}
	public Land getOnLand() {
		if (lm == -1 || lid == -1) {
			return null;
		}
		return Warehouse.getMaps()[lm].getPieces().get(lid);
	}
	public void setOnLand(Land onLand) {
		if (onLand == null) {
			lm = -1;
			lid = -1;
		}
		else {
			lm = onLand.getMap();
			lid = onLand.getId();
		}
	}
	public int getScore() {
		return s;
	}
	public void setScore(int score) {
		this.s = score;
	}
	public void gainPoint() {
		this.s++;
	}
	public void losePoint() {
		this.s--;
	}
	public int getLives() {
		return l;
	}
	public void setLives(int lives) {
		this.l = lives;
	}
	public void loseLife() {
		this.l--;
	}
	public void gainLife() {
		this.l++;
	}

	/*getters to the RoleModel's properties*/
	public float getRunSpeed() {
		if (getOnLand() != null && getOnLand().isSlip()) { //slippery floor
			return getRoleModel().getRunSpeed()/10;
		}
		return getRoleModel().getRunSpeed();
	}
	public float getAirSpeed() {
		return getRoleModel().getAirSpeed();
	}
	public float getRunSlip() {
		if (getOnLand() != null && getOnLand().isSlip()) { //slippery floor
			return 1-getRoleModel().getRunFrict()/10;
		}
		return getRoleModel().getRunSlip();
	}
	public float getRunFrict() {
		if (getOnLand() != null && getOnLand().isSlip()) { //slippery floor
			return getRoleModel().getRunFrict()/10;
		}
		return getRoleModel().getRunFrict();
	}
	public float getAirSlip() {
		return getRoleModel().getAirSlip();
	}
	public float getAirFrict() {
		return getRoleModel().getAirFrict();
	}
	public float getMaxSpeed() {
		return getRoleModel().getMaxSpeed();
	}
	public float getJumpPower() {
		return getRoleModel().getJumpPower();
	}
	public int getJumpHold() {
		return getRoleModel().getJumpHold();
	}
	public float getTermVel() {
		return getRoleModel().getTermVel();
	}
	public float getGrav() {
		return getRoleModel().getGrav();
	}
	public int getSpawnTime() {
		return getRoleModel().getSpawnTime();
	}
	public int getSpawnInv() {
		return getRoleModel().getSpawnInv();
	}
	public int getShotDelay() {
		return getRoleModel().getShotDelay();
	}
	public ShotModel getShot() {
		return getRoleModel().getShotType();
	}

	//getter and setter for basic player type
	public int getModel() {
		return m;
	}
	//also sets derived fields
	public void setModel(int model) {
		this.m = model;
		RoleModel rm = Warehouse.getCharacters()[model];
		setSkin(rm.getSkin());
		setW(rm.getW());
		setH(rm.getH());
	}
	/**
	 * Get the RoleModel object referenced by m (model). 
	 * @return the actual RoleModel from the warehouse
	 */
	private RoleModel getRoleModel() {
		//Change powerup intercepts your model
		if (p == Item.CHANGE) { //TODO: How to have both a countdown AND a selector
			return Warehouse.getCharacters()[pv];			
		}
		return Warehouse.getCharacters()[m];
	}
}
