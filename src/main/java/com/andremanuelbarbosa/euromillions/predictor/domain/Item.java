package com.andremanuelbarbosa.euromillions.predictor.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Item implements Comparable<Item> {

  private final int id;
  private final ItemType itemType;
  private final double relativeFreq;

  public Item(int id, ItemType itemType, double relativeFreq) {

    this.id = id;
    this.itemType = itemType;
    this.relativeFreq = relativeFreq;
  }

  public int getId() {

    return id;
  }

  public ItemType getItemType() {

    return itemType;
  }

  public double getRelativeFreq() {

    return relativeFreq;
  }

  @Override
  public int compareTo(Item item) {

    return id - item.getId();
  }

  @Override
  public String toString() {

    return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
  }
}
