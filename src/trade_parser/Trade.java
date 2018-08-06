package trade_parser;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity class to store trade details in DB
 */
@Entity
@Table(name = "trades")
public class Trade {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "trade_id", updatable = false)
	private long id;
	
	@Column(name = "isin", nullable = false, length = 12)
	private String isin;
	
	public String getIsin() {
		return isin;
	}
	
	public void setIsin(String isin) {
		this.isin = isin;
	}
	
	@Column(name = "trade_type", nullable = false)
	private Integer type;
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name = "trade_type", nullable = false)
	private BigDecimal price;
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}