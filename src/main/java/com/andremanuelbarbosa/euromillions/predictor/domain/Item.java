package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.text.DecimalFormat;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class Item implements Comparable<Item> {

  private static final DecimalFormat DECIMAL_FORMAT_RELATIVE_FREQ = new DecimalFormat(".000000000000");

  private final int id;
  private final ItemType itemType;
  private final int interval;
  private final double relativeFreq;

  public Item(int id, ItemType itemType, int interval, double relativeFreq) {

    this.id = id;
    this.itemType = itemType;
    this.interval = interval;
    this.relativeFreq = relativeFreq;
  }

  @Override
  public int compareTo(Item item) {

    return id - item.getId();
  }

  public int getId() {

    return id;
  }

  public ItemType getItemType() {

    return itemType;
  }

  public int getInterval() {

    return interval;
  }

  public double getRelativeFreq() {

    return relativeFreq;
  }

  public String getStatisticsLine() {

    return String.format("%6s | %8s | %s", id, interval, DECIMAL_FORMAT_RELATIVE_FREQ.format(relativeFreq));
  }

  @Override
  public String toString() {

    return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
  }
}
