package common;

public class EastUiTreeResult {
	private long id;
	private String text;
	private String state;
	
	
	public EastUiTreeResult(long id, String text, String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
	}
	
	public EastUiTreeResult() {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
