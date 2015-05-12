package cn.looip.project.repository.domain;
/**
 * 顾客
 * @author gaoxiang
 *
 */
public class Customer {
	private int id;
	private String name;
	private String man;
	private short sex;
	private String qq;
	private short status;

	
	
	public Customer() {
		super();
	}

	public Customer(int id, String name, String man, short sex, String qq,
			short status) {
		super();
		this.id = id;
		this.name = name;
		this.man = man;
		this.sex = sex;
		this.qq = qq;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMan() {
		return man;
	}

	public void setMan(String man) {
		this.man = man;
	}

	public short getSex() {
		return sex;
	}

	public void setSex(short sex) {
		this.sex = sex;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

}
