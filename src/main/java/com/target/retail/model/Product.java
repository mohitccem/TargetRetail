package com.target.retail.model;

//import org.springframework.data.cassandra.core.mapping.Column;
//import org.springframework.data.cassandra.core.mapping.PrimaryKey;
//import org.springframework.data.cassandra.core.mapping.Table;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.Objects;

//??implements Serializable
@Entity
@Table(name = "Product")
public class Product {


	@Id
	@Column(name = "id")
	private int productId;

	@Column(name = "price")
	private double price;
	
	private String name;

	@Column(name = "currency_code")
	private String currency_code;

	public Product(int productId) {
		super();
		this.productId = productId;
	}

	public int getProductId() { return productId; }
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	 public String getCurrency() {
		return currency_code;
	}

	public void setCurrency(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product)) return false;
		Product product = (Product) o;
		return getProductId() == product.getProductId() &&
				Double.compare(product.getPrice(), getPrice()) == 0 &&
				getName().equals(product.getName()) &&
				currency_code.equals(product.currency_code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getProductId(), getPrice(), getName(), currency_code);
	}

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", price=" + price +
				", name='" + name + '\'' +
				", currency_code='" + currency_code + '\'' +
				'}';
	}

		/*@Override
	    public boolean equals(Object o) {

	        if (o == this) return true;
	        if (!(o instanceof Product)) {
	            return false;
	        }
	        Product price = (Product) o;

	        return new EqualsBuilder()
	        		.append(productId, price.productId)
	                .append(price, price.price)
	                .append(currency_code, price.currency_code)
	                .isEquals();
	    }*/

	    /*@Override
	    public int hashCode() {
	        return new HashCodeBuilder()
	        		.append(productId)
	                .append(price)
	                .append(currency_code)
	                .toHashCode();

	    }*/


		/*@Override
	    public String toString() {
	        StringBuilder builder = new StringBuilder();
	        builder.append("productId=");
	        builder.append(productId);
	        builder.append("name=");
	        builder.append(", price=");
	        builder.append(price);
	        builder.append(", currency=");
	        builder.append(currency_code);
	       
	        return builder.toString();
	    }*/
	    
}
