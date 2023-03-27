package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * SummaryStatisticDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-27T02:02:07.747436500+03:00[Europe/Moscow]")
public class SummaryStatisticDto {

  @JsonProperty("frequency")
  private Double frequency = null;

  @JsonProperty("avgDuration")
  private Double avgDuration = null;

  public SummaryStatisticDto frequency(Double frequency) {
    this.frequency = frequency;
    return this;
  }

  /**
   * Get frequency
   * @return frequency
  */
  
  @Schema(name = "frequency", required = false)
  public Double getFrequency() {
    return frequency;
  }

  public void setFrequency(Double frequency) {
    this.frequency = frequency;
  }

  public SummaryStatisticDto avgDuration(Double avgDuration) {
    this.avgDuration = avgDuration;
    return this;
  }

  /**
   * Get avgDuration
   * @return avgDuration
  */
  
  @Schema(name = "avgDuration", required = false)
  public Double getAvgDuration() {
    return avgDuration;
  }

  public void setAvgDuration(Double avgDuration) {
    this.avgDuration = avgDuration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SummaryStatisticDto summaryStatisticDto = (SummaryStatisticDto) o;
    return Objects.equals(this.frequency, summaryStatisticDto.frequency) &&
        Objects.equals(this.avgDuration, summaryStatisticDto.avgDuration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(frequency, avgDuration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SummaryStatisticDto {\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
    sb.append("    avgDuration: ").append(toIndentedString(avgDuration)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

