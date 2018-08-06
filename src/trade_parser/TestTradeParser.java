package trade_parser;
import junit.framework.TestCase;
import java.math.BigDecimal;

public class TestTradeParser extends TestCase {
	
	public void testXetra() throws Exception {
		Trade trade = TradeParser.parse("iIT1234567890;t1;p123.34");
		assertEquals("IT1234567890", trade.getIsin());
		assertEquals(new Integer(1), trade.getType());
		assertEquals(new BigDecimal("123.34"), trade.getPrice());
	}
	
	public void testLSE() throws Exception {
		Trade trade = TradeParser.parse("t1;iGB1234567890;p123.34");
		assertEquals("GB1234567890", trade.getIsin());
		assertEquals(new Integer(1), trade.getType());
		assertEquals(new BigDecimal("123.34"), trade.getPrice());
	}
	
	public void testLIFFE() throws Exception {
		Trade trade = TradeParser.parse("t1;iGB1234567890;p123.34;v1000");
		assertEquals("GB1234567890", trade.getIsin());
		assertEquals(new Integer(1), trade.getType());
		assertEquals(new BigDecimal("123.34"), trade.getPrice());
	}
	
	public void testError1() {
		try {
			Trade trade = TradeParser.parse("iGB1234567890;p123.34");
			fail("Expected exception");
		}catch (Exception e) {}
	}
	
	public void testError2(){
		try {
			Trade trade = TradeParser.parse("iGB12345678901;p123.34;t1");
			fail("Expected exception");
		}
		catch (Exception e) {}
	}
	
	public void testError3(){
		try {
			Trade trade = TradeParser.parse("iGB12345678901;t2;p123.34;t1");
			fail("Expected exception");
		}
		catch (Exception e) {}
	}
	
	// No arguments passed
	public void testError4(){
		try {
			Trade trade = TradeParser.parse("");
			fail("Expected exception");
		}
		catch (Exception e) {}
	}
	
	// Invalid delimiters
	public void testError5(){
		try {
			Trade trade = TradeParser.parse("iGB12345678901:t2;p123.34,t1");
			fail("Expected exception");
		}
		catch (Exception e) {}
	}
	
	// Null argument
	public void testError6(){
		try {
			Trade trade = TradeParser.parse(null);
			fail("Expected exception");
		}
		catch (Exception e) {}
	}
}