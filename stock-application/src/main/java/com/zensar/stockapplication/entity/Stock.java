package com.zensar.stockapplication.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel("This is the model")


         //Jpql 
@Entity(name="MyStock")
@Table(name="Stock")
@NamedQueries(value = {@NamedQuery(name = "Stock.findName", query = "From MyStock s where s.name=?1"),
		@NamedQuery(name = "Stock.findNameAndPrice", query = "From MyStock s where s.name=?1 And s.stockPrice=?2")})


                //OR              

//@NamedQuery(name = "Stock.findName", query = "From Stock s where s.name=?1")
//@NamedQuery(name = "Stock.findNameAndPrice", query = "From Stock s where s.name=?1 And s.stockPrice=?2")

            //Sql

//@NamedNativeQuery(name = "Stock.findName", query = "select * from Stock where name=?1",resultClass = Stock.class)

public class Stock {
	
	//@ApiModelProperty("stock Id of Integer type")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long stockId;
	//@ApiModelProperty("name of String type")
	private String name;
	//@ApiModelProperty("market name of String type")
	private String marketName;
	//@ApiModelProperty("stock price of Integer type")
	private double stockPrice;

}
