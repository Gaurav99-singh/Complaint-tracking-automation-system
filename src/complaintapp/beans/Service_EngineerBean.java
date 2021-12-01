package complaintapp.beans;

public class Service_EngineerBean 
{
	String engineerId,engineerName;

	public Service_EngineerBean(String engineerId, String engineerName) {
		super();
		this.engineerId = engineerId;
		this.engineerName = engineerName;
	}

	public Service_EngineerBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEngineerId() {
		return engineerId;
	}

	public void setEngineerId(String engineerId) {
		this.engineerId = engineerId;
	}

	public String getEngineerName() {
		return engineerName;
	}

	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}
	
	public String toString()
	{
		return engineerName;
	}
}
