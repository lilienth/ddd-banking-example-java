package security;

import java.time.LocalDate;

public class Security {
	//International Securities Identification Number
	private String ISIN;
	private float purchasePrice;
	private LocalDate purchaseDate;
	
	public Security(String iSIN, float purchasePrice, LocalDate purchaseDate) {
		super();
		ISIN = iSIN;
		this.purchasePrice = purchasePrice;
		this.purchaseDate = purchaseDate;
	}

	public String getISIN() {
		return ISIN;
	}

	public float getPurchasePrice() {
		return purchasePrice;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	
	

}
