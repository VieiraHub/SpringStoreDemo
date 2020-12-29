package com.vieiraatelier.demostore.domain.enums;

public enum PaymentStatus {

	PENDING(1, "Pending"), CLOSED(2, "Closed"), CANCELED(3, "Canceled");
	
	private int code;
	private String description;
	
	private PaymentStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {  return code;  }
	
	public String getDescription() { return description;  }
	
	public static PaymentStatus toEnum(Integer code) {
		if (code == null) return null;
		
		for (PaymentStatus x : PaymentStatus.values()) {
			if (code.equals(x.getCode())) return x;
		}
		
		throw new IllegalArgumentException("Invalid Id: " + code);
	}
}
