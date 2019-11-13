package kr.ac.jj.s201351029;

public class ProductDTO {
	private int p_no;
	private String barcord;
	private String p_name;
	private int p_pri;
	private int p_bpri;
	private int p_qua;
	
	//»ý¼ºÀÚ
	public ProductDTO() {	
	}
	
	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getBarcord() {
		return barcord;
	}

	public void setBarcord(String barcord) {
		this.barcord = barcord;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_pri() {
		return p_pri;
	}

	public void setP_pri(int p_pri) {
		this.p_pri = p_pri;
	}

	public int getP_bpri() {
		return p_bpri;
	}

	public void setP_bpri(int p_bpri) {
		this.p_bpri = p_bpri;
	}

	public int getP_qua() {
		return p_qua;
	}

	public void setP_qua(int p_qua) {
		this.p_qua = p_qua;
	}
	
	public ProductDTO( String barcord,String p_name, int p_pri, int p_bpri, int p_qua) {
		this.barcord = barcord;
		this.p_name = p_name;
		this.p_pri = p_pri;
		this.p_bpri = p_bpri;
		this.p_qua = p_qua;
	}
	
	
}
