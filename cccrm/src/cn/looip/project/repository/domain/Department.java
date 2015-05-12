package cn.looip.project.repository.domain;
/**
 * 部门
 * @author gaoxiang
 *
 */
public class Department {
	private int id;
	private String depName;// 部门名
	private String depHeader;// 负责人
	private String depQQ;// QQ

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDepHeader() {
		return depHeader;
	}

	public void setDepHeader(String depHeader) {
		this.depHeader = depHeader;
	}

	public String getDepQQ() {
		return depQQ;
	}

	public void setDepQQ(String depQQ) {
		this.depQQ = depQQ;
	}

}
