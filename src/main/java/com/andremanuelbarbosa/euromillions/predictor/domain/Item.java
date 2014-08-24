package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class Item implements Comparable<Item> {

  private static final DecimalFormat DECIMAL_FORMAT_RELATIVE_FREQ = new DecimalFormat(".000000000000");

  private final int id;
  private final ItemType itemType;
  private final int interval;
  private final double relativeFreq;
  private final List<Integer> intervals;

  public Item(int id, ItemType itemType, int interval, double relativeFreq, List<Integer> intervals) {

    this.id = id;
    this.itemType = itemType;
    this.interval = interval;
    this.relativeFreq = relativeFreq;
    this.intervals = intervals;
  }

  @Override
  public int compareTo(Item item) {

    return id - item.getId();
  }

  public abstract double getAverageInterval();

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

  public List<Integer> getIntervals() {

    return intervals;
  }

  @Override
  public int hashCode() {

    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {

    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public String toString() {

    return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
  }
}
