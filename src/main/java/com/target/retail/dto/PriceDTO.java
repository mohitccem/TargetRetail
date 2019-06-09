package com.target.retail.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDTO {

    public PriceDTO() {
    }

    @JsonProperty("value")
    protected String price;

    @JsonProperty("currency_code")
    protected String currencyCode;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceDTO)) return false;
        PriceDTO priceDTO = (PriceDTO) o;
        return getPrice().equals(priceDTO.getPrice()) &&
                currencyCode.equals(priceDTO.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), currencyCode);
    }

    @Override
    public String toString() {
        return "PriceDTO{" +
                "price='" + price + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
