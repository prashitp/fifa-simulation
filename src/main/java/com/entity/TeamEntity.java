package com.entity;

/**
 * @author Jay Patel
 */
public class TeamEntity {

	@Column(name = "club_id")
	private String clubId;
	
	@Column(name = "club_name")
	private String clubName;
	
	@Column(name = "overall")
	private Long overall;
	
	@Column(name = "attack")
	private Long attack;
	
	@Column(name = "midfield")
	private Long midfield;
	
	@Column(name = "defence")
	private Long defence;
	
	@Column(name = "transfer_budget")
	private Long transferBudget;
	
	public TeamEntity() {
		super();
	}

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public Long getOverall() {
		return overall;
	}

	public void setOverall(Long overall) {
		this.overall = overall;
	}

	public Long getAttack() {
		return attack;
	}

	public void setAttack(Long attack) {
		this.attack = attack;
	}

	public Long getMidfield() {
		return midfield;
	}

	public void setMidfield(Long midfield) {
		this.midfield = midfield;
	}

	public Long getDefence() {
		return defence;
	}

	public void setDefence(Long defence) {
		this.defence = defence;
	}

	public Long getTransferBudget() {
		return transferBudget;
	}

	public void setTransferBudget(Long transferBudget) {
		this.transferBudget = transferBudget;
	}
}
