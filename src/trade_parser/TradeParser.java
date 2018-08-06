package trade_parser;

import java.math.BigDecimal;

/**
 * @author tom
 * TradeParser class to parse trade data 
 * field starting with "i" which is ISIN code, 12 characters
 * field starting with "p" which is Trade price, decimal
 * field starting with "t" which is Trade number, integer
 */
public class TradeParser{
	public static Trade parse(String line) throws Exception {
		Trade trade = new Trade();
		// split line input using ";" as delimeter
		String[] lineParts = line.split(";");
		
		// throw exception when array length is less than 3
		if (lineParts.length < 3) throw new Exception("Insufficient number of arguments");
		
		Integer tradeNumber = null;
		String isinCode = null;
		BigDecimal tradePrice = null; 	
		
		// go through each array element
		for (int i = 0; i < lineParts.length; i++) {
			String part = lineParts[i];
			
			// minimum 2 characters needed
			if (part.length() < 2) continue;
			// get field code
			char fieldCode = part.charAt(0);
			// and description
			String description = part.substring(1, part.length());
			// check if it's "i,p or t"
			if(fieldCode == 'i') {  //ISIN code
				// duplicate ISIN code
				if (isinCode != null) throw new Exception("Duplicate ISIN code");
	
				isinCode = description;
				// invalid number of characters
				if (isinCode.length() != 12) throw new Exception("ISIN code must be 12 char lenght, "
						+ "not" + isinCode.length());
			}else if(fieldCode  == 'p') { // trade price
				// duplicate trade price
				if (tradePrice != null) throw new Exception("Duplicate trade price");
				try {
					tradePrice = new BigDecimal(description);
				}catch(Exception e) {
					throw new Exception("Could not parse trade price "
							+ "from value " + description, e);
				}
			}else if(fieldCode  == 't') { // trade number
				// duplicate trade number
				if (tradeNumber != null) throw new Exception("Duplicate trade number");
				try {
					tradeNumber = Integer.parseInt(description);
				}catch(Exception e) {
					throw new Exception("Could not parse trade trace number "
							+ "from value " + description, e);
				}
			}
		}
		
		// check for null values
		if (tradePrice == null) throw new Exception("Missing trade price");
		if (tradeNumber == null) throw new Exception("Missing trade number");
		if (isinCode == null) throw new Exception("Missing ISIN code");
		
		// Everything is fine if we are at this point
		trade.setIsin(isinCode);
		trade.setPrice(tradePrice);
		trade.setType(tradeNumber);
		
		return trade;
	}
}