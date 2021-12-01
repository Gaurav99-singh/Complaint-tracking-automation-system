package complaintapp.beans;

public class SeeComplaintsBean 
{
	String name,address,phone,product,cmptxt;
	String cmpid, cusid, cmpDate,assigndate, resolvestatus,resolvedate,remarks,feedback,assignstatus;

	public String getAssignstatus() {
		return assignstatus;
	}

	public void setAssignstatus(String assignstatus) {
		this.assignstatus = assignstatus;
	}

	public SeeComplaintsBean(String cmpid, String cusid,String product, String cmptxt,String cmpDate,String assignstatus, String resolvestatus
			, String feedback ) {
		super();
		this.product = product;
		this.cmptxt = cmptxt;
		this.cmpid = cmpid;
		this.cusid = cusid;
		this.resolvestatus = resolvestatus;
		this.cmpDate = cmpDate;
		this.feedback = feedback;
		this.assignstatus = assignstatus;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public SeeComplaintsBean(String cmpid, String cusid, String product, String cmptxt,String cmpDate,
			 String assigndate, String resolvestatus, String resolvedate, String remarks) {
		super();
		this.product = product;
		this.cmptxt = cmptxt;
		this.cmpid = cmpid;
		this.cusid = cusid;
		this.cmpDate = cmpDate;
		this.assigndate = assigndate;
		this.resolvestatus = resolvestatus;
		this.resolvedate = resolvedate;
		this.remarks = remarks;
	}

	public String getCmpid() {
		return cmpid;
	}

	public void setCmpid(String cmpid) {
		this.cmpid = cmpid;
	}

	public String getCusid() {
		return cusid;
	}

	public void setCusid(String cusid) {
		this.cusid = cusid;
	}

	public String getCmpDate() {
		return cmpDate;
	}

	public void setCmpDate(String cmpDate) {
		this.cmpDate = cmpDate;
	}


	public String getAssigndate() {
		return assigndate;
	}

	public void setAssigndate(String assigndate) {
		this.assigndate = assigndate;
	}

	public String getResolvestatus() {
		return resolvestatus;
	}

	public void setResolvestatus(String resolvestatus) {
		this.resolvestatus = resolvestatus;
	}

	public String getResolvedate() {
		return resolvedate;
	}

	public void setResolvedate(String resolvedate) {
		this.resolvedate = resolvedate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCmptxt() {
		return cmptxt;
	}

	public void setCmptxt(String cmptxt) {
		this.cmptxt = cmptxt;
	}

	public SeeComplaintsBean(String name, String address, String phone, String product, String cmptxt) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.product = product;
		this.cmptxt = cmptxt;
	}

	public SeeComplaintsBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
