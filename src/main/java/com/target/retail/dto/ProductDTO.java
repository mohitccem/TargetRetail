package com.target.retail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ProductDTO {
    public ProductDTO() {
    }

    @JsonProperty("id")
    private int productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("current_price")
    private PriceDTO priceDTO;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public PriceDTO getPriceDTO() {
        return priceDTO;
    }

    public void setPriceDTO(PriceDTO priceDTO) {
        this.priceDTO = priceDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDTO)) return false;
        ProductDTO that = (ProductDTO) o;
        return getProductId() == that.getProductId() &&
                getProductName().equals(that.getProductName()) &&
                getPriceDTO().equals(that.getPriceDTO());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getProductName(), getPriceDTO());
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", priceDTO=" + priceDTO +
                '}';
    }

}
